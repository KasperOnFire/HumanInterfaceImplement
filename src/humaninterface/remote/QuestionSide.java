/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import humaninterface.TextualInterface;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joachim
 */
public class QuestionSide implements TextualInterface {

    TextualConnectionInterface conn;

    public QuestionSide(TextualConnectionInterface conn) {
        this.conn = conn;
    }

    @Override
    public void sendMessage(String msg) {
        try {
            conn.writeString("sendMessage");
            conn.writeString(msg);
        } catch (IOException ex) {
            Logger.getLogger(QuestionSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String askForString(String question) {
        try {
            conn.writeString("askForString");
            conn.writeString(question);
            return conn.readString();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String askForPassword(String question) {
        try{
            conn.writeString("askForPassword");
            return askForString(question);
        }catch (IOException e){
            return  null;
        }
    }

    @Override
    public String askForEmail(String question) {
        try {
            conn.writeString("askForEmail");
            return askForString(question);
        }catch (IOException e){
            return null;
        }
    }

    @Override
    public int askForInteger(String question) {
        try {
            conn.writeString("askForInteger");
            conn.writeString(question);
            return conn.readInt();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public int askForInteger(String question, int min, int max) {

        try {
            while (true) {
                conn.writeString("askForIntegerRange");
                conn.writeString(question);
                conn.writeInt(min);
                conn.writeInt(max);
                int answer = conn.readInt();
                if (answer >= min && answer <= max) {
                    return answer;
                }
            }
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public int makeSingleChoice(String question, String[] choices) {
        try {
            while(true){
                conn.writeString("makeSingleChoice");
                conn.writeString(question);
                conn.writeStringArray(choices);
                int answer = conn.readInt();
                if(answer <= choices.length && answer >= 1){
                    return answer;
                }
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void close() throws IOException {
        try{
            conn.writeString("close");
            conn.close();
        }catch (IOException e){

        }
    }
}
