package UE;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

public class ReadFromURLViaSocket {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("news.orf.at", 80);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write("GET / HTTP/1.1\r\nHost: news.orf.at\r\n\r\n");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

