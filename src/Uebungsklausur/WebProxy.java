package Uebungsklausur;

import Uebungsklausur.web.CacheMissException;
import Uebungsklausur.web.URLLoaderException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WebProxy {

    private PageCache cache;
    private int numCacheHits = 0, numCacheMisses = 0;

    public WebProxy() {
        this.cache = new PageCache();
    }

    public WebProxy(PageCache cache) {
        this.cache = cache;
    }

    public WebPage fetch(String url) throws URLLoaderException {

        WebPage site;

        try {
            site = cache.readFromCache(url);
            numCacheHits++;
        } catch (CacheMissException e) {
            System.out.println(e.getMessage());
            System.out.println("Caching: " + url + "...");
            site =  URLLoader.loadWebPage(url);
            cache.writeToCache(site);
            numCacheMisses++;
        }
        return site;
    }

    public String getNumCacheHits() {
        return "Stat hits: " + numCacheHits;
    }

    public String getNumCacheMisses() {
        return "Stat misses: " + numCacheMisses;
    }

    public boolean writePageCacheToFile(String pathToFile) {

        try (FileWriter writer = new FileWriter(pathToFile)){

            for (Map.Entry<String, WebPage> mapEntry : cache.getCache().entrySet()) {
                writer.write(mapEntry.getKey() + ";" + mapEntry.getValue().getContent());
                System.out.println("Successfully cached " + mapEntry.getKey() + " to file");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to file...");
            return false;
        }
    }
}
