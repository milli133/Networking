package Uebungsklausur;

import Uebungsklausur.web.CacheMissException;

import java.io.*;
import java.util.HashMap;

public class PageCache {
    private HashMap<String, WebPage> cache = new HashMap<String, WebPage>();

    public HashMap<String, WebPage> getCache() {
        return cache;
    }

    public WebPage readFromCache(String url) throws CacheMissException {

        if (cache.containsKey(url))
            return cache.get(url);
        else
            throw new CacheMissException("URL Missing in Cache!");
    }

    public void writeToCache(WebPage webPage) {
        cache.put(webPage.getUrl(), webPage);
    }

    public void warmUp(String pathToURLs) throws FileNotFoundException {
        File file = new File(pathToURLs);
        FileReader fr = new FileReader(file);

        try {
            BufferedReader br = new BufferedReader(fr);

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                cache.put(line, URLLoader.loadWebPage(line));
                System.out.println("Loaded: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

