package humaninterface.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import dk.tobiasgrundtvig.util.socket.impl.SocketConnectionImpl;
import humaninterface.impl.ConsoleTextualInterface;

import java.io.IOException;

public class TestClient {
    public static void main(String[] args) {
        try {
            SocketConnection socketConn = new SocketConnectionImpl("localhost", 5665);
            TextualConnectionInterface tic = new TextualConnectionImp(socketConn);
            AnswerSide as = new AnswerSide(tic, new ConsoleTextualInterface());

            as.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
