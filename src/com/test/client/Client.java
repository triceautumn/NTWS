package com.test.client;

import com.myh.ntws.rpc.service.ClientProxy;
import com.myh.ntws.rpc.service.ClientSender;

public class Client
{
	public static void main(String[] args)
	{
		ClientProxy clientProxy = new ClientProxy();
		ClientSender sender = clientProxy.getClientSender("localhost", 8888);
		Object rel = sender.get("com.test.WebService", "test1", new Object[]{});
		System.out.println("**********************"); 
		System.out.println(rel);
	} 
}
