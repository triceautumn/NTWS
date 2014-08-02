package com.myh.ntws.server.service;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.jboss.netty.channel.Channel;

import com.myh.ntws.helper.Transport;

public class ServerHandler extends ChannelServerHandler
{
	public ServerHandler(String initPath)
	{
		super(initPath);
	}

	@Override
	protected void processor(Channel channel, Object message)
	{
		Transport transport = (Transport) message;
		Object[] params = (Object[]) transport.getMessage();
		Object ret = null;
		try {
			Method method = initMethods.get(transport.getClazz()
					+ transport.getMethod());
			if (method == null) {

			} else {
				Class<?> clazz = Class.forName(transport.getClazz());
				ret = method.invoke(clazz.newInstance(), params);
			}
			ServerSender sender = new ServerSender(channel, transport);
			sender.send(ret);
		} catch (Exception e) 
		{
			throw new IllegalAccessError(e.getMessage());
		}

	}

	public static void main(String[] args)
	{
		ServerHandler handler = new ServerHandler(
				"src/com/myh/ntws/server/init/init.xml");
		handler.processor(null, null);
	}
}
