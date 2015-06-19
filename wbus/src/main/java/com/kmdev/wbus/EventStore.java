package com.kmdev.wbus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public final class EventStore extends Properties {

    private static final long serialVersionUID = 1087648305L;
    
    public EventStore(Class<?> mainClass) {
        try {
        	InputStream input = mainClass.getClassLoader().getResourceAsStream("events.properties");
        	if (null == input)
        		throw new IOException("events.properties file could not be found.");
            load(input);
            input.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failure: events.properties file could not be found on classpath.");
        }
    }
    
}
