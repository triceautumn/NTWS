package com.myh.ntws.rpc.service;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

import com.myh.ntws.helper.Transport;

public abstract class ChannelClientHandler extends SimpleChannelUpstreamHandler
{
	private final InternalLogger logger = InternalLoggerFactory
			.getInstance(ChannelClientHandler.class);
	private Transport transport;

	public Transport getTransport()
	{
		return transport;
	}

	public void setTransport(Transport transport)
	{
		this.transport = transport;
	}

	private Channel channel = null;

	public Channel getChannel()
	{
		return channel;
	}

	public void setChannel(Channel channel)
	{
		this.channel = channel;
	}

	protected abstract void processor(Channel channel, Object message);

	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception
	{
		processor(ctx.getChannel(), e.getMessage());

	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception
	{
		super.channelConnected(ctx, e);
		channel = ctx.getChannel();
	}

}