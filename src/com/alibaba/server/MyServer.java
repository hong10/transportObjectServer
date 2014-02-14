package com.alibaba.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.codec.FileCodecFactory;
import com.alibaba.dexcodec.FileTransportCodecFactory;
import com.alibaba.dexcodec.FileTransportDecoder;
import com.alibaba.dexcodec.FileTransportEncoder;
import com.alibaba.domain.DeviceStatus;
import com.alibaba.domain.DevicesPool;
import com.alibaba.domain.MyRequestObject;
import com.alibaba.domain.MyResponseObject;
import com.alibaba.rmi.HelloInterface;

public class MyServer extends UnicastRemoteObject implements MyMina {
	private static final Logger logger = LoggerFactory
			.getLogger(MyServer.class);
	private static IoAcceptor acceptor;

	private static ConcurrentHashMap<String, IoSession> sessionHashMap = new ConcurrentHashMap<String, IoSession>();

	public MyServer() throws RemoteException {

	}

	public void start() {
		acceptor = new NioSocketAcceptor();

		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

		acceptor.getFilterChain()
				.addLast(
						"protocol",
						new ProtocolCodecFilter(new FileTransportCodecFactory(
								new FileTransportEncoder(),
								new FileTransportDecoder())));

		acceptor.setHandler(new ServerHandler());

		try {
			acceptor.bind(new InetSocketAddress(9999));
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	// �����пͻ���������������
	public void sendToAllClient(Object message) {

		IoSession session;

		Map conMap = acceptor.getManagedSessions();

		Iterator iter = conMap.keySet().iterator();
		System.out.println(acceptor.getManagedSessionCount());
		while (iter.hasNext()) {
			Object key = iter.next();
			System.out.println("**********");
			session = (IoSession) conMap.get(key);
			// System.out.println(session);
			session.write(message);

			// session.write("" + key.toString());
		}
	}

	// ��ͻ��˷�������
	public static void sendToClient(Object message, Long sessionId) {

		// sessionCurHashMapΪȫ�ֱ�������һ��ConcurrentHashMap
		// System.out.println(SessionMap.sessions.size());
		// System.out.println(SessionMap.sessions.keys());
		// System.out.println(SessionMap.sessions.values());

		SessionMap map = SessionMap.getInstance();
		IoSession sendSession = map.sessions.get(sessionId);

		WriteFuture future = sendSession.write(message); // ��������
		future.awaitUninterruptibly(); // �ȴ��������ݲ������
		if (future.isWritten()) {
			// �����Ѿ����ɹ�����
			System.out.println("�����Ѿ����ɹ�����");
		} else {
			// ���ݷ���ʧ��
			System.out.println("���ݷ���ʧ��");
		}
	}

	// ���Է���
	public static void main(String[] args) {
		// start();

		// sendToAllClient(new MyResponseObject("hong", "good"));
	}

}
