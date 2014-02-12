package com.alibaba.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {
	/**
	 * 远程接口方法必须抛出 java.rmi.RemoteException
	 */
	public String say() throws RemoteException;
}
