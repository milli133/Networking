package Uebungsklausur;

import Uebungsklausur.web.CacheMissException;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket client;
    private WebProxy wp;

    public ClientHandler(Socket client, WebProxy proxy) {
        this.client = client;
        this.wp = proxy;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connection established...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String received, answer = "";

            while (!(received = reader.readLine()).equalsIgnoreCase("bye")) {
                if (!received.contains(" "))
                    answer = "Error: Command invalid!";
                else {
                    String command, param;
                    String[] splitReceived = received.split(" ");
                    command = splitReceived[0];
                    param = splitReceived[1];

                    switch (command) {
                        case "fetch", "Fetch":
                            try {
                                answer = wp.fetch(param).getContent();
                            } catch (Exception e) {
                                answer = "Error: Cannot load webpage from URL";
                                System.out.println(e.getMessage()); }
                            break;
                        case "stats", "Stats":
                            answer = "Error: Command invalid!";

                            if (param.equalsIgnoreCase("hits"))
                                answer = wp.getNumCacheHits();
                            if (param.equalsIgnoreCase("misses"))
                                answer = wp.getNumCacheMisses();
                            break;
                        default: answer = "ERROR";
                    }
                }
                System.out.println("Sending " + answer + " to client");
                writer.write(answer);
                writer.newLine();
                writer.flush();
            }
            System.out.println("Connection terminated...");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
