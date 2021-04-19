package Uebungsklausur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        System.out.println("starting server on port 5678");

            try {
                PageCache cache = new PageCache();
                cache.warmUp("src/Uebungsklausur/web/demoURLs.txt");
                WebProxy wp = new WebProxy(cache);

                ServerSocket serverSocket = new ServerSocket(5678);

                while (true) {
                    Socket client = serverSocket.accept();

                    if (client.isConnected()) {
                        ClientHandler handler = new ClientHandler(client, wp);
                        handler.run();
                    }
                }
            } catch (IOException e) {
                System.out.println("Error while connecting...");
            }
        }
    }
