package Uebungsklausur;

import Uebungsklausur.web.URLLoaderException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLLoader {

    public static WebPage loadWebPage(String url) throws URLLoaderException {

        try {
            URL site = new URL(url);
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(site.openStream()))) {

                String input, output = "";
                while ((input = reader.readLine()) != null) {
                    output = output.concat(input);
                }
                return new WebPage(url, output);

            } catch (IOException e) {
                throw new URLLoaderException("IO Error", e.getCause());
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
