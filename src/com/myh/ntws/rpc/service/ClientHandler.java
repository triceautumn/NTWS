package com.myh.ntws.rpc.service;

import org.jboss.netty.channel.Channel;


public class ClientHandler extends ChannelClientHandler
{
	private Object returnObject = null;
	
	public Object getReturnObject()
	{
		return returnObject;
	}

	public void setReturnObject(Object returnObject)
	{
		this.returnObject = returnObject;
	}
	@Override
    protected void processor(Channel channel, Object message) {  
        this.returnObject = (Object)message; 
    }  
}
