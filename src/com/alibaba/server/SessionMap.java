package com.alibaba.server;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

public class SessionMap  {

	private static SessionMap instance = new SessionMap();
	
	public static SessionMap getInstance(){
		return instance;
	}
	
	public volatile ConcurrentHashMap<Long, IoSession> sessions = new ConcurrentHashMap<Long, IoSession> ();

//	public  ConcurrentHashMap<Long, IoSession> getSessions() {
//		return sessions;
//	}
	
	
	
}
