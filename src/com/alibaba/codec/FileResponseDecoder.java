package com.alibaba.codec;

import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.alibaba.domain.FileResponse;

/**
 * �ļ��������˽�����
 * 
 */
public class FileResponseDecoder extends CumulativeProtocolDecoder {

	protected boolean doDecode(IoSession session, IoBuffer in,
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
				return false;
	}

}
