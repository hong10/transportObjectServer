package com.alibaba.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Hello extends UnicastRemoteObject implements HelloInterface {
	private String message;

	/**
	 * ���붨�幹�췽������ʹ��Ĭ�Ϲ��췽����Ҳ���������ȷ��д��������Ϊ�������׳���RemoteException�쳣
	 */
	public Hello(String msg) throws RemoteException {
		message = msg;
	}

	/**
	 * Զ�̽ӿڷ�����ʵ��
	 */
	public String say() throws RemoteException {
		System.out.println("Called by HelloClient");
		return message;
	}
}
