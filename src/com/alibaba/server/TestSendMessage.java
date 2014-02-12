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

			// 如果要从另一台启动了RMI注册服务的机器上查找hello实例
			// HelloInterface hello =
			// (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");

			// 调用远程方法
//			System.out.println(hello.say());
			server.sendToAllClient(new MyResponseObject("myp", "i love you"));
		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*// TODO Auto-generated method stub
		// MyServer.sendToClient("推送的消息",Long.valueOf(1));

		// 新建一个类加载器  
        MyClassLoader cl = new MyClassLoader("myClassLoader");  
          
        // 加载类，得到Class对象  
        Class<?> clazz = cl.loadClass("com.alibaba.server.MyServer");  
          
        // 得到类的实例  
        MyServer server = (MyServer) clazz.newInstance();
        server.sendToAllClient("推送消息");
        
//        Animal animal = (Animal) clazz.newInstance();  
//        animal.say();  
*/		
	}
}
