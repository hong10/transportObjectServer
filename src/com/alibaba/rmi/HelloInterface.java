package com.alibaba.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {
	/**
	 * Զ�̽ӿڷ��������׳� java.rmi.RemoteException
	 */
	public String say() throws RemoteException;
}
