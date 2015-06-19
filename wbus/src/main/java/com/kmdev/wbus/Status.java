package com.kmdev.wbus;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public class Status {

    public String code;
    
    public String text;
    
    public Status() { }
    
    public Status(String cod, String txt) {
        this.code = cod;
        this.text = txt;
    }
    
    public String getCode() { return code; }
    
    public String getText() { return text; }
}
