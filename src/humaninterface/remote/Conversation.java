package humaninterface.remote;

import humaninterface.TextualInterface;

import java.io.IOException;

public class Conversation implements Runnable {

    private final TextualInterface ti;

    public Conversation(TextualInterface ti) {
        this.ti = ti;
    }


    @Override
    public void run() {
        String name = ti.askForString("What is your name? : ");
        int age = ti.askForInteger("What is your age? : ", 0, 200);
        String[] food = {"Banana", "Ice cream", "Tomato", "Grapes", "Apple"};
        int favorite = ti.makeSingleChoice("What do like best? : ", food);

        ti.sendMessage("Hi " + name);
        ti.sendMessage("It seems that you are " + age + " years old and like " + food[favorite]);
        try {
            ti.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
