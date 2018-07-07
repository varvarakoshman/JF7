package task2;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class PropertiesManagerTS extends Thread {

    private ResourceBundle bundle;
    private ConcurrentHashMap<String, String> keyMap;

    PropertiesManagerTS(String prop, Locale locale) throws NoPropFileException {
        try {
            this.bundle = ResourceBundle.getBundle(prop, locale);
            this.keyMap = new ConcurrentHashMap<>();
            bundle.keySet().forEach(key -> keyMap.put(key, (String) bundle.getObject(key)));
        } catch (MissingResourceException e) {
            throw new NoPropFileException(e);
        }
    }

    public String getValue(String key) throws NoKeyException {
            if (!keyMap.containsKey(key)) throw new NoKeyException("no such key");
            return this.keyMap.get(key);
    }

    //SERIOUS PERFORMANCE ISSUES FOLLOW LOCKING THE ENTIRE HASH MAP
/*
    public String getValue(String key) throws NoKeyException {
        synchronized (this) { //SERIOUS PERFORMANCE ISSUES!
            if (!keyMap.containsKey(key)) throw new NoKeyException("no such key");
            return this.keyMap.get(key);
        }
    }
*/
    public void run() {
        try {
            String threadName;
            for (String key : bundle.keySet()) {
                threadName = Thread.currentThread().getName();
                System.out.printf("Thread %s receives an answer: %s to question %s\n", threadName, getValue(key), key);
            }
        } catch (NoKeyException e) {
            e.printStackTrace();
        }
    }

}
