package Uebungsklausur;

import Uebungsklausur.web.CacheMissException;

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

    public WebPage fetch(String url) throws CacheMissException, IOException {
        try {
            WebPage site = cache.readFromCache(url);
            if (site != null) {
                numCacheHits++;
                return site;
            }
        } catch (CacheMissException e) {
            numCacheMisses++;
            return URLLoader.loadWebPage(url);
        }
        return null;
    }

    public String getNumCacheHits() {
        return "Stat hits: " + numCacheHits;
    }

    public String getNumCacheMisses() {
        return "Stat misses: " + numCacheMisses;
    }

    public boolean writePageToCache(String pathToFile) {
        try {
            FileWriter writer = new FileWriter(pathToFile);

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
