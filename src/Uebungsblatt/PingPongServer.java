package Uebungsblatt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PingPongServer {

    public static void main(String[] args) {

        System.out.println("starting server on port 3333");

        try {
            ServerSocket serverSocket = new ServerSocket(3333);

            while (true) {
                processClient(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void processClient(Socket client) throws IOException {

        System.out.println("waiting for clients...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String received, answer;

        while (!(received = reader.readLine()).equalsIgnoreCase("exit")) {

            switch (received) {
                case "Ping":
                    answer = "Pong";
                    break;
                case "Pong":
                    answer = "Ping";
                    break;
                default:
                    answer = "ERROR";
                    break;
            }
            System.out.println("Sending " + answer + " to client");
            writer.write(answer);
            writer.newLine();
            writer.flush();
        }
    }
}
