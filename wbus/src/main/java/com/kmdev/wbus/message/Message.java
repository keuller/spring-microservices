package com.kmdev.wbus.message;

/**
 * <p>Mensagem generica.</p>
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public abstract class Message {
    
    public String id;

    public String action;
    
    public Message() {
        this.action = "";
    }
    
    public Message(String act) {
        this.action = act;
    }
    
    public String getId() { return id; }
    public void setId(String value) { this.id = value; }
    
    public String getAction() { return action; }
    public void setAction(String value) { this.action = value; }
    
}
