package Uebungsblatt;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PingPongClient {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 3333)
        ) {
            System.out.println("Starting client...");
            Scanner scanner = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String input;

            while (!(input = scanner.next()).equals("")) {
                if (input.equalsIgnoreCase("Exit"))
                    break;

                System.out.println("Sending " + input);
                writer.write(input);
                writer.newLine();
                writer.flush();

                String answerFromServer = reader.readLine();
                System.out.println("Answer from Server: " + answerFromServer);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
