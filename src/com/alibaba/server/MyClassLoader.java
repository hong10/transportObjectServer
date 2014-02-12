package com.alibaba.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {
	private String name;

	private String path = MyClassLoader.getSystemClassLoader().getResource("")
			.getPath();;

	public MyClassLoader(String name) {
		this.name = name;
	}

	MyClassLoader(ClassLoader parent, String name) {
		super(parent);
		this.name = name;
	}

	/**
	 * 重写findClass
	 */
	@Override
	public Class<?> findClass(String name) {
		byte[] data = loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

	public byte[] loadClassData(String name) {
		try {
			name = name.replace(".", "//");
			FileInputStream is = new FileInputStream(new File(path + name
					+ ".myclass"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int b = 0;
			while ((b = is.read()) != -1) {
				baos.write(b);
			}
			System.out.println("我是自定义类加载器哦！");
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
