package com.myh.ntws.rpc.service;


import org.jboss.netty.bootstrap.ClientBootstrap;

import com.myh.ntws.clientpool.NettyClientPool;
import com.myh.ntws.helper.Transport;
import com.myh.ntws.lifecycle.LifeCycle;

public class ClientSender implements LifeCycle 
{
	private ClientBootstrap bootstrap;
	
	private ClientHandler clientHandler;
	private NettyClientPool clientPool;
	
	public void setBootstrap(ClientBootstrap bootstrap)
	{
		this.bootstrap = bootstrap;
	}

	public ClientBootstrap getBootstrap()
	{
		return bootstrap;
	}

	public ClientHandler getClientHandler()
	{
		return clientHandler;
	}

	public void setClientHandler(ClientHandler clientHandler)
	{
		this.clientHandler = clientHandler;
	}
	public void setClientPool(NettyClientPool clientPool)
	{
		this.clientPool = clientPool;
	}

	public NettyClientPool getClientPool()
	{
		return clientPool;
	}

	public Object get(String clazz, String method, Object... params)
	{
		class Result
		{
			public Object o;
		}

		final Result ret = new Result();

		synchronized (ret) {
			try {
				invoke(clazz, method, params, new ResultHandler<Object>()
				{
					@Override
					public void processor(Object message)
					{
							ret.o = message;
					}
				});
			} catch (Exception e) {
		}
			
			return ret.o;
		}
	}
	private void invoke(String clazz, String method, Object[] params,
			ResultHandler<Object> resultHandler)
	{
		Transport transport = new Transport();
		transport.setClazz(clazz);
		transport.setMethod(method);
		transport.setMessage(params);
		while(clientHandler.getChannel()==null);
		clientHandler.getChannel().write(transport);
		while(clientHandler.getReturnObject()==null);
		resultHandler.processor(clientHandler.getReturnObject()); 
	}

	@Override
	public void start()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop()
	{
		bootstrap.shutdown();
		bootstrap=null;
		clientPool.removeClient(this);	
	}
}
