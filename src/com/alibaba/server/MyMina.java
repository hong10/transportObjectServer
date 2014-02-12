package com.alibaba.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyMina extends Remote {

	//定义发送消息的方法
	public void sendToAllClient(Object message) throws RemoteException;
}
