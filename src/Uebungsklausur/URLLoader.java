package Uebungsklausur;

import Uebungsklausur.web.URLLoaderException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class URLLoader {

    public static WebPage loadWebPage(String url) throws URLLoaderException, IOException {

        BufferedReader reader;

        try {
            URL site = new URL(url);
            reader = new BufferedReader(new InputStreamReader(site.openStream()));

            String input, output = "";
            while ((input = reader.readLine()) != null) {
                output = output.concat(input);
            }

            return new WebPage(url, output);

        } catch (Exception e) {
            throw new URLLoaderException("IO Error", e.getCause());
        }
    }
}
