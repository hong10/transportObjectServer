package com.alibaba.rmi;

import java.rmi.Naming;

public class HelloClient {
	/**
	 * ����Զ�̶��󲢵���Զ�̷���
	 */
	public static void main(String[] argv) {
		try {
			HelloInterface hello = (HelloInterface) Naming.lookup("Hello");

			// ���Ҫ����һ̨������RMIע�����Ļ����ϲ���helloʵ��
			// HelloInterface hello =
			// (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");

			// ����Զ�̷���
			System.out.println(hello.say());
		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}