package Uebungsklausur;

import Uebungsklausur.web.URLLoaderException;

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

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
              BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))){
            //System.out.println("Connection established...");

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
                            }
                            catch (URLLoaderException e) {
                                answer = "Something went wrong while loading the webpage...";
                            }
                            break;
                        case "stats", "Stats":
                            if (param.equalsIgnoreCase("hits"))
                                answer = wp.getNumCacheHits();
                            if (param.equalsIgnoreCase("misses"))
                                answer = wp.getNumCacheMisses();
                            break;
                        default:
                            answer = "Error: Command invalid!";
                    }
                }
                System.out.println("Sending: '" + answer + "' to client");
                writer.write(answer);
                writer.newLine();
                writer.flush();
            }
            System.out.println("Connection terminated...");

        } catch (Exception e) {
            System.out.println("Something went wrong while reading client request...");
        }
    }
}
