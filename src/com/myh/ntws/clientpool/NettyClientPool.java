package com.myh.ntws.clientpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import com.myh.ntws.rpc.service.ClientSender;

public class NettyClientPool implements ClientSenderPool {
	  private int size=10;
	    private String remoteHost;
	    private LinkedBlockingQueue<ClientSender> clients;

	    public NettyClientPool() {
	        this.clients = new LinkedBlockingQueue<ClientSender>();
	    }
	    /**
	     * 获取当前连接的远端的地址
	     *
	     * @return
	     */
	    public String getRemoteHost() {
	        return this.remoteHost;
	    }

	    /**
	     * 获取当前pool的规模，其实也就是有多少个client
	     *
	     * @return
	     */
	    public int getSize() {
	        return this.size;
	    }

	    /**
	     * 当有client退出的时候的处理，相当于提供给client的回调
	     *
	     * @param client
	     */
	    public void clientExit(ClientSender clientsender) {
	        Logger.getLogger("main").warning("有client退出了");
	        this.clients.remove(clientsender);
	    }
	    /**
	     * 向当前的pool添加client
	     *
	     * @param client
	     */
	    public void addClient(ClientSender client) {
	        try {
	            this.clients.put(client);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * 移除一个client
	     *
	     * @param client
	     */
	    public void removeClient(ClientSender clientsender) {
	        this.clients.remove(clientsender);
	    }
}
