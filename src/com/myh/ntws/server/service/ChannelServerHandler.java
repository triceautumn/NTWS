package com.myh.ntws.server.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

import com.myh.ntws.helper.Transport;
import com.myh.ntws.server.init.NTWSInit;

public abstract class ChannelServerHandler extends SimpleChannelUpstreamHandler {  
    private  InternalLogger logger = InternalLoggerFactory.getInstance(ChannelServerHandler.class);  
    protected  Map<String, Method> initMethods = null;  
  
    public ChannelServerHandler() {  
    }  
    public ChannelServerHandler(String initPath) {  
    	initMethods = NTWSInit.init(initPath);
    }  
      
    protected abstract void processor(Channel channel, Object message);  
  
  
    @Override  
    public final void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {  
    	Transport t = (Transport) e.getMessage();  
        String className = t.getClazz(); 
        String methodName = t.getMethod();  
        logger.info("Invoke Handler:" + className + ", Invoke Method:" + methodName);  
        System.out.println(className+" : " + methodName); 
        processor(ctx.getChannel(), t);  
    } 
}
