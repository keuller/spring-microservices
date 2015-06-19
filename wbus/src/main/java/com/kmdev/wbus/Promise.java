package com.kmdev.wbus;

import org.springframework.web.context.request.async.DeferredResult;
import com.kmdev.wbus.message.MessageOut;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 * @param <T>
 */
public class Promise extends DeferredResult<MessageOut> {
    
    public Promise(long milisec) {
        super(milisec);
    }
    
}
