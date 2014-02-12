package com.alibaba.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.alibaba.rmi.Hello;
import com.alibaba.rmi.HelloInterface;

public class TestMyServer {
	
	public static void main(String[] args){
		try {
			// ����RMIע�����ָ���˿�Ϊ1099����1099ΪĬ�϶˿ڣ�
			// Ҳ����ͨ������ ��java_home/bin/rmiregistry 1099����
			// ���������ַ�ʽ�������ٴ�һ��DOS����
			// ����������rmiregistry����ע����񻹱���������RMIC����һ��stub��Ϊ������
			LocateRegistry.createRegistry(1099);

			// ����Զ�̶����һ������ʵ����������hello����
			// �����ò�ͬ����ע�᲻ͬ��ʵ��
//			HelloInterface hello = new Hello("Hello, world!");
			
			MyServer server = new MyServer();
			
			// ��helloע�ᵽRMIע��������ϣ�����ΪHello
			Naming.rebind("server", server);
			
			server.start();
			// ���Ҫ��helloʵ��ע�ᵽ��һ̨������RMIע�����Ļ�����
			// Naming.rebind("//192.168.1.105:1099/Hello",hello);

			System.out.println("Mina Server is ready.");
		} catch (Exception e) {
			System.out.println("Mina Server failed: " + e);
		}
	}

}
