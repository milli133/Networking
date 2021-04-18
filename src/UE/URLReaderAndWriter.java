package UE;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class URLReaderAndWriter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;

        try {
            URL site = new URL("https://de.wikipedia.org/wiki/Primzahl");

            try {
                reader = new BufferedReader(new InputStreamReader(site.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        File file = new File("src/UE/data/primzahlen.html");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        String input = "";
        while ((input = reader.readLine()) != null) {
            System.out.println(input);
            writer.write(input);
        }

        reader.close();
        writer.close();
    }
}
