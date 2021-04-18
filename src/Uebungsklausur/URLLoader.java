package Uebungsklausur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLLoader {

    public static WebPage loadWebPage(String url) throws IOException {

        BufferedReader reader;

        try {
            URL site = new URL(url);

            try {
                reader = new BufferedReader(new InputStreamReader(site.openStream()));


                String input, output = "";
                while ((input = reader.readLine()) != null) {
                    output = output.concat(input);
                }

                return new WebPage(url, output);
            } catch (IOException e) {
                System.out.println("Something went wrong while loading the webpage...");;
            }
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }
}
