package com.kmdev.wbus;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public abstract class TypeConverter {

    public static Double toDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
    
    public static Integer toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException ex) {
            return null;
        }
    }

    public static Boolean toBool(String value) {
        return Boolean.parseBoolean(value);
    }
    
}
