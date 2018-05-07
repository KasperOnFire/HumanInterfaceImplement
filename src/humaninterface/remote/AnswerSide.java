package humaninterface.remote;

import humaninterface.TextualInterface;

import java.io.IOException;

public class AnswerSide implements Runnable {

    private final TextualConnectionInterface conn;
    private final TextualInterface ti;

    public AnswerSide(TextualConnectionInterface conn, TextualInterface ti) {
        this.conn = conn;
        this.ti = ti;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String method = conn.readString();
                switch (method) {
                    case "sendMessage": {
                        String message = conn.readString();
                        ti.sendMessage(message);
                        break;
                    }

                    case "askForString": {
                        String question = conn.readString();
                        String result = ti.askForString(question);
                        conn.writeString(result);
                        break;
                    }

                    case "askForPassword": {
                        String question = conn.readString();
                        String result = ti.askForString(question);
                        conn.writeString(result);
                        break;
                    }

                    case "askForEmail": {
                        String question = conn.readString();
                        String result = ti.askForString(question);
                        conn.writeString(result);
                        break;
                    }

                    case "askForInteger": {
                        String question = conn.readString();
                        int res = ti.askForInteger(question);
                        conn.writeInt(res);
                        break;
                    }

                    case "askForIntegerRange": {
                        String question = conn.readString();
                        int min = conn.readInt();
                        int max = conn.readInt();
                        int res = ti.askForInteger(question, min, max);
                        conn.writeInt(res);
                        break;
                    }

                    case "makeSingleChoice": {
                        String question = conn.readString();
                        String[] choices = conn.readStringArray();
                        int res = ti.makeSingleChoice(question, choices);
                        conn.writeInt(res);
                        break;
                    }

                    case "close": {
                        ti.close();
                        conn.close();
                        return;
                    }

                    default: {
                        throw new RuntimeException("Unknown method " + method);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
