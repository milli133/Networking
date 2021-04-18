package Uebungsklausur.web;

public class CacheMissException  extends  Exception{
    public CacheMissException(String message, Throwable cause) {
        super("CACHE MISSING!", cause);
    }

    public CacheMissException(String message) {
        super(message);
    }
}
