package edu.umb.cs681.hw07;


import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
	LinkedList<Directory> rootDirectories;
	private static FileSystem fsObject;
	private static ReentrantLock lock=new ReentrantLock();
	private FileSystem() {
		rootDirectories=new LinkedList<>();
	}
	

    public static FileSystem getFileSystem() {
    	lock.lock();
    	try {
	        if(fsObject == null) {
	        	fsObject = new FileSystem();
	        }
	        return fsObject;
    	}finally {
    		lock.unlock();
    	}
    }

	
	public LinkedList<Directory> getRootDirs() {
		return rootDirectories;
	}
	
	public void appendRootDir(Directory root) {
		rootDirectories.add(root);
	}
	
	
	
	
	
	
}