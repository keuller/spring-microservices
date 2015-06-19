package com.kmdev.wbus.message;

import java.util.Map;

/**
 * <p>Representa mensagem de entrada.</p>
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public class MessageIn<T> extends Message {
    
    public T model;
    
    public Map<String,Object> params;
    
    public MessageIn() { }
    
    public MessageIn(String action, T bean) {
        super(action);
        this.model = bean;
    }
    
    public T getModel() { return model; }
    public void setModel(T bean) { this.model = bean; }
    
    public Map<String,Object> getParams() { return params; }
    public void setParams(Map<String,Object> values) { this.params = values; }
    
}
