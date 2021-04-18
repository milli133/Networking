package Uebungsklausur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLLoader {

    public static WebPage loadWebPage(String url) throws IOException {

        BufferedReader reader = null;

        try {
            URL site = new URL(url);

            try {
                reader = new BufferedReader(new InputStreamReader(site.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String input, output = "";
        while ((input = reader.readLine()) != null) {
            // System.out.println(input);
            output = output.concat(input);
        }

        return new WebPage(url, output);
    }
}
