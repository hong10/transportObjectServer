package com.alibaba.server;

import java.rmi.Naming;

import com.alibaba.domain.MyResponseObject;

public class TestSendMessage {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {

		try {
			MyMina server = (MyMina) Naming.lookup("server");

			// 构建一个对象

			server.sendToAllClient(new MyResponseObject("test", "success"));

		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}

	}
}
