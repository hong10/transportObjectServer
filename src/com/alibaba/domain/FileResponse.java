package com.alibaba.domain;

import java.nio.ByteBuffer;

/**
 * �������Ӧ��
 */
public class FileResponse {
	//������ļ�����
    private ByteBuffer fileContents;
    //ָ��
    
    private int order;
    public FileResponse(ByteBuffer fileContents, int order) {
        this.fileContents = fileContents;
        this.order = order;
    }

    public ByteBuffer getFileContents() {
		return fileContents;
	}

	public void setFileContents(ByteBuffer fileContents) {
		this.fileContents = fileContents;
	}
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	
	
	
}
