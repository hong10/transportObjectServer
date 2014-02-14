package com.alibaba.dexcodec;

import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.alibaba.domain.FileResponse;

public class FileTransportDecoder implements MessageDecoder {

	@Override
	public MessageDecoderResult decodable(IoSession arg0, IoBuffer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		//����һ����������СΪin.limit()
		ByteBuffer bf = ByteBuffer.allocate(in.limit());

		//��ȡin��ͷ���Զ����ָ��
		int order = in.getInt();

		//���õ�ǰ��ȡiobuffer��λ�ã��Զ���ָ���Ѿ����꣬��д����bf�У�������Ҫ���α��ƶ����Զ���ָ��ĺ��棬����Ҫ�ü�����
		int len = in.limit() - bf.limit() ;

		//ͨ������Ĵ����Ѿ���iobuffer�е��α��Ƶ��˷��Ͷ���ĵڶ����֣�Ҳ����FileResponse�е�fileContents����ֶΣ�
		bf = in.position(len).buf();

		FileResponse fr = new FileResponse(bf, order);
		out.write(fr);
		
		return MessageDecoderResult.OK;
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
