package com.kmdev.wbus.message;

import com.kmdev.wbus.Status;

/**
 * <p>Representa a mensagem de saida que sera retornada ao cliente.</p>
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public class MessageOut extends Message {

    public Status status = new Status();

    public Object data;
    
    public MessageOut() { super(); }
    
    public MessageOut(String act, String errCode, Object bean) {
        super(act);
        this.data = bean;
        this.status = new Status(errCode, "");
    }

    public Object getData() { return data; }
    public void setData(Object bean) { this.data = bean; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status value) { this.status = value; }
    
    public String toString() {
        return String.format("ID: %s, Error Code: %s, Error Text: %s", id, status.code, status.text);
    }
}
