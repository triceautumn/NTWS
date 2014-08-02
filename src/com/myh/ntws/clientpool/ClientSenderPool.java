package com.myh.ntws.clientpool;
import com.myh.ntws.rpc.service.ClientSender;

public interface ClientSenderPool{

    /**
     * 向当前的pool添加client
     * @param client
     */
    public void addClient(ClientSender client);


    /**
     * 移除一个client
     * @param client
     */
    public void removeClient(ClientSender client);
    
    /**
     * 当有client退出的时候的处理，相当于提供给client的回调
     * @param client
     */
    public void clientExit(ClientSender client);
}
