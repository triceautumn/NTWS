package com.myh.ntws.rpc.bootstrap;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import com.myh.ntws.rpc.service.ClientHandler;


public class NTWClient
{
	public ClientBootstrap start(InetSocketAddress socketAddress,final ClientHandler clientHandler)
	{
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool()
						,Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory(){
			public ChannelPipeline getPipeline() throws Exception
			{
				return Channels.pipeline(new ObjectEncoder(),new ObjectDecoder(
						ClassResolvers.cacheDisabled(this.getClass().getClassLoader())),clientHandler);
			}
			
		});
		bootstrap.connect(socketAddress);
		return bootstrap;
	}
}
