package com.alibaba.domain;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class MyFileInputStream extends FileInputStream implements Serializable {

	
	public MyFileInputStream(File file) throws FileNotFoundException {
		super(file);
		// TODO Auto-generated constructor stub
	}

	public MyFileInputStream(FileDescriptor fdObj) {
		super(fdObj);
		// TODO Auto-generated constructor stub
	}

	public MyFileInputStream(String name) throws FileNotFoundException {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
}
