package com.test.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myh.ntws.server.bootstrap.IServerStart;
public class Server
{
	public static void main(String[] args)
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IServerStart server = (IServerStart) ac.getBean("userService");
		server.start();
	}
}
