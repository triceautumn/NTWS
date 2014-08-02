package com.myh.ntws.server.service;

import org.jboss.netty.channel.Channel;
import com.myh.ntws.helper.Transport;

public class ServerSender
{
	private Channel channel = null;
	
	public ServerSender(Channel channel, Transport transport)
	{
		this.channel = channel;
	}
	public void send(Object object)
	{
		channel.write(object);
	}
}
