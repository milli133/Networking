package Uebungsklausur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT {
    public static void main(String[] args) {
        System.out.println("starting server on port 5678");

        try {
            PageCache cache = new PageCache();
            cache.warmUp("src/Uebungsklausur/web/demoURLs.txt");
            WebProxy wp = new WebProxy(cache);

            ServerSocket serverSocket = new ServerSocket(5678);

            while (true) {
                System.out.println("Waiting for client connection...");
                Socket client = serverSocket.accept();
                if (client.isConnected()) {
                    System.out.println("Connection established with " + client.getPort());
                    Thread clientThread = new Thread(new ClientHandler(client, wp));
                    clientThread.start();
                }

                if(client.isClosed())
                    System.out.println("Connection closed with " + client.getPort());
            }
        } catch (IOException e) {
            System.out.println("Error while connecting...");
        }
    }
}
