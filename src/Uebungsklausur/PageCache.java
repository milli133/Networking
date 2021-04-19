package Uebungsklausur;

import Uebungsklausur.web.CacheMissException;
import Uebungsklausur.web.URLLoaderException;

import java.io.*;
import java.util.HashMap;

public class PageCache {
    private HashMap<String, WebPage> cache = new HashMap<>();

    public HashMap<String, WebPage> getCache() {
        return cache;
    }

    public WebPage readFromCache(String url) throws CacheMissException {
        if (cache.containsKey(url))
            return cache.get(url);
        else {
            throw new CacheMissException("URL Missing in Cache!");
        }
    }

    public void writeToCache(WebPage webPage) {
        cache.put(webPage.getUrl(), webPage);
    }

    public void warmUp(String pathToURLs) {
        try {
            File file = new File(pathToURLs);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                writeToCache(URLLoader.loadWebPage(line));
                System.out.println("Loaded: " + line);
            }
        } catch (IOException | URLLoaderException e) {
            System.out.println("Something went wrong while warming up the cache...");
        }

    }
}

