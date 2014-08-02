package com.myh.ntws.server.bootstrap.imp;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import com.myh.ntws.helper.ParseXml;
import com.myh.ntws.server.bootstrap.IServerStart;
import com.myh.ntws.server.service.ServerHandler;

public class ServerStartImp implements IServerStart
{
	private String initPath;
	
	public String getInitPath()
	{
		return initPath;
	}

	public void setInitPath(String initPath)
	{
		this.initPath = initPath;
	}

	public void start()
	{
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool()
						,Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new ChannelPipelineFactory(){
			//ObjectDecoder ¼Ì³Ð ChannelHander£¬
			public ChannelPipeline getPipeline() throws Exception
			{
				return Channels.pipeline(new ObjectEncoder(),new ObjectDecoder(
						ClassResolvers.cacheDisabled(this.getClass().getClassLoader()))
				,new ServerHandler(initPath));
			}
			
		});
		int port = ParseXml.getPort(initPath);
		bootstrap.bind(new InetSocketAddress(port));
		System.out.println(port);
	}
}
