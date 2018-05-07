/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author joachim
 */
public interface TextualConnectionInterface extends Closeable {
    
    public void writeString(String message) throws IOException;
    public void writeInt(int message) throws IOException;
    public void writeStringArray(String[] message) throws IOException;
    
    public int readInt() throws IOException;
    public String readString() throws IOException;
    public String[] readStringArray() throws IOException;
}
