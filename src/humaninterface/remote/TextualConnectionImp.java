/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import java.io.IOException;

/**
 *
 * @author joachim
 */
public class TextualConnectionImp implements TextualConnectionInterface {
    
    private SocketConnection conn;
    
    public TextualConnectionImp(SocketConnection socket){
        conn = socket;
    }

    @Override
    public void writeString(String message) throws IOException {
        conn.writeUTF(message);
    }

    @Override
    public void writeInt(int message) throws IOException {
        conn.writeInt(message);
    }

    @Override
    public void writeStringArray(String[] message) throws IOException {
        conn.writeInt(message.length);
        for (int i = 0; i < message.length; i++) {
            conn.writeUTF(message[i]);
        }
    }

    @Override
    public int readInt() throws IOException {
        return conn.readInt();
    }

    @Override
    public String readString() throws IOException {
        return conn.readUTF();
    }

    @Override
    public String[] readStringArray() throws IOException {
        int length = conn.readInt();
        
        String[] message = new String[length];
        for (int i = 0; i < length; i++) {
            message[i] = conn.readUTF();            
        }
        return message;
    }

    @Override
    public void close() throws IOException {
        conn.close();
    }
    
}
