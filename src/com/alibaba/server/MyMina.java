package com.alibaba.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyMina extends Remote {

	//���巢����Ϣ�ķ���
	public void sendToAllClient(Object message) throws RemoteException;
}
