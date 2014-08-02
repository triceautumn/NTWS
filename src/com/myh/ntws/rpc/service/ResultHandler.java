package com.myh.ntws.rpc.service;
public interface ResultHandler<T> {  
      
    public void processor(T message);  

}  