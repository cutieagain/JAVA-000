package com.cutie.geek.java0.week1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class GeekClassLoader extends ClassLoader{
    private static final String filePath = "/Users/cutie/2、learn/极客时间/java训练营一期/作业/JAVA-000/Week_01/Hello.xlass";
    private static final String className = "Hello";
    private static final String classMethod = "hello";

    public static void main(String[] args) {
        try {
            Class clazz = new GeekClassLoader().loadClass(className);
            Method helloMethod = clazz.getMethod(classMethod);
            helloMethod.invoke(clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes = getEnBytesByFilePath(filePath);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    public static byte[] getEnBytesByFilePath(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length && (numRead = fileInputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fileInputStream.close();
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) (255 - buffer[i]);
        }
        return buffer;
    }
}
