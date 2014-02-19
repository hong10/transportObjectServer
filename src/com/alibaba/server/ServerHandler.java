package com.alibaba.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.domain.MyRequestObject;
import com.alibaba.domain.MyResponseObject;

public class ServerHandler extends IoHandlerAdapter {
	/*服务端接受到的文件名*/
	private String fileName ;
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
	private static final Logger logger = LoggerFactory
			.getLogger(MyServer.class);
	@Override
	public void sessionCreated(IoSession session) throws Exception {

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		//sessionHashMap.put("first", session);
		/*SessionMap map = SessionMap.getInstance();
		
		map.sessions.put(session.getId(), session);
		System.out.println(session.getId());*/
//		System.out.println(SessionMap.sessions.size());
		//System.out.println(acceptor.getManagedSessionCount());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error(cause.getMessage(), cause);
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		logger.info("Received " + message);
		
			MyRequestObject myReqOjb = (MyRequestObject) message;
			MyResponseObject myResObj = new MyResponseObject(myReqOjb
					.getName(), myReqOjb.getValue());
			session.write(myResObj);
		
		
	}
	
	

	@Override
	public void messageSent(IoSession session, Object message)
			throws Exception {
		logger.info("Sent " + message);
	}

}
