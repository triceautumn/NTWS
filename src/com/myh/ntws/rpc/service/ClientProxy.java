package com.myh.ntws.rpc.service;

import java.net.InetSocketAddress;

import org.jboss.netty.bootstrap.ClientBootstrap;

import com.myh.ntws.clientpool.NettyClientPool;
import com.myh.ntws.rpc.bootstrap.NTWClient;

public class ClientProxy
{
	private int count =0;
	
	public synchronized ClientSender  getClientSender(String hostname,int port)
	{	NettyClientPool clientPool = new NettyClientPool();
		InetSocketAddress socketAddress = new InetSocketAddress(hostname,port);
		NTWClient client=  new NTWClient();
		ClientHandler clientHandler = new ClientHandler();
		ClientBootstrap bootstrap = client.start(socketAddress,clientHandler);	
		ClientSender clientSender = new ClientSender();
		clientSender.setClientHandler(clientHandler);
		clientSender.setBootstrap(bootstrap);
		clientSender.setClientPool(clientPool);
		clientPool.addClient(clientSender);
		return clientSender;
	}
}
