package UE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class DemoServerSocket {

    public static void main(String[] args) {

        int counter = 0;
        System.out.println("starting server on port 1111");

        try {
            ServerSocket serverSocket = new ServerSocket(1111);

            while(true) {
                System.out.println("waiting for clients...");
                try (
                    Socket client = serverSocket.accept();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    )
                {
                    LocalDateTime ldt = LocalDateTime.now();
                    System.out.println("Sending date and time to client" + ldt.toString());
                    writer.write(ldt.toString());
                    writer.flush();
                } catch (IOException e) {e.printStackTrace();}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}