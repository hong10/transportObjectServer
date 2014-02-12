package com.alibaba.server;

import java.rmi.Naming;

import com.alibaba.domain.MyResponseObject;
import com.alibaba.rmi.HelloInterface;


public class TestSendMessage {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		
		try {
			MyMina server =  (MyMina) Naming.lookup("server");

			// ���Ҫ����һ̨������RMIע�����Ļ����ϲ���helloʵ��
			// HelloInterface hello =
			// (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");

			// ����Զ�̷���
//			System.out.println(hello.say());
			server.sendToAllClient(new MyResponseObject("myp", "i love you"));
		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*// TODO Auto-generated method stub
		// MyServer.sendToClient("���͵���Ϣ",Long.valueOf(1));

		// �½�һ���������  
        MyClassLoader cl = new MyClassLoader("myClassLoader");  
          
        // �����࣬�õ�Class����  
        Class<?> clazz = cl.loadClass("com.alibaba.server.MyServer");  
          
        // �õ����ʵ��  
        MyServer server = (MyServer) clazz.newInstance();
        server.sendToAllClient("������Ϣ");
        
//        Animal animal = (Animal) clazz.newInstance();  
//        animal.say();  
*/		
	}
}
