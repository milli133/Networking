package Uebungsklausur.web;

public class URLLoaderException extends Exception {
    public URLLoaderException(String message, Throwable cause) {
        super("URL LOAD ERROR", cause);
    }
}
