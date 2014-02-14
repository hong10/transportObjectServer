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

import com.alibaba.domain.FileResponse;
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
		
		if(message instanceof FileResponse){
			FileResponse fr = (FileResponse) message;
	        System.out.println("服务端接受到的数据"+fr.getOrder());
//	        setFileName(fr.getFileName());
//	        fileName = "D:\\"+fileName;
	        if(fr.getOrder() == 0x11){
	        	fileName = "D:\\tasklist";
	        	FileInputStream fis = null;
	        	try{
	        		fis = new FileInputStream(fileName);
	        	}catch(FileNotFoundException e){
	        		System.out.println("请求文件不存在");
	        		/*FileResponse frs = new FileResponse(null, "请求文件不存在");
	        		session.write(frs);*/
	        		//session.write("请求文件不存在");
	        	}
	            ByteBuffer bf = getFileContents(fis);
	            FileResponse frs = new FileResponse(bf, 0x11);
	            session.write(frs);
	        }else{
	        	session.write("指令错误");
	        }
		}else{
			MyRequestObject myReqOjb = (MyRequestObject) message;
			MyResponseObject myResObj = new MyResponseObject(myReqOjb
					.getName(), myReqOjb.getValue());
			session.write(myResObj);
		}
		
		

		/*
		 * //接受客户端发送的对象 DevicesPool dp = (DevicesPool) message;
		 * ArrayList<DeviceStatus> al = dp.getStatusPool(); for (int i =
		 * 0; i < al.size(); i++) { DeviceStatus ds = al.get(i);
		 * System.out.println(ds.toString()); }
		 * 
		 * 
		 * //反馈给客户端消息 session.write("我收到了"+al.size()+"个对象");
		 */

	}
	
	
	/**
     * 根据文件流得到文件的ByteBuffer
     * @param fis
     * @return
     * @throws IOException
     */
    public ByteBuffer getFileContents(FileInputStream fis) throws IOException{
		// 得到文件通道
		FileChannel fc = fis.getChannel();
		// 指定大小为20MB的缓存区
		ByteBuffer bf = ByteBuffer.allocate(1024*1000*20);
		while (fc.read(bf) != -1) 
		{
 		   //把缓存中当前的位置回复为零前，把缓冲区的limit 设置为之前position值
			bf.flip();
			// 输出缓冲区中的内容
			while (bf.hasRemaining()) 
			{
				bf.get();
		      //System.out.print((char) bf.get());
			}
			break;
			// 清理缓冲区，准备再次读取数据
			//bf.clear();
		}
		return bf;
    }

	@Override
	public void messageSent(IoSession session, Object message)
			throws Exception {
		logger.info("Sent " + message);
	}

}
