package edu.umb.cs681.hw01;

import java.time.LocalDateTime;

import edu.umb.cs681.hw01.Directory;
import edu.umb.cs681.hw01.File;
import edu.umb.cs681.hw01.FileSystem;
import edu.umb.cs681.hw01.Link;



public class TestFixtureInitializer {
	static Directory repo;
	static Directory src ;
	static File A;
	static File B;
	static Directory bin;
	static File Ac;
	static File Bc;
	static Directory test;
	static Directory srcTest;
	static File ATest;
	static File BTest;
	static Directory binTest;
	static File ATestc;
	static File BTestc;
	static File readme;

	
	static Link rm;
	

	public static FileSystem createFS() {
		
	FileSystem fs= FileSystem.getFileSystem();
	
	LocalDateTime currentTime = LocalDateTime.of(2023, 11, 2, 14, 30, 0);
	
	
	repo =new Directory(null,"repo",0,currentTime);
	
	fs.appendRootDir(repo);
	
	
	//root 1st child
	src=new Directory(repo,"src",0,currentTime);
	A=new File(src,"A.java",5, LocalDateTime.of(2023, 11, 3, 14, 30, 0));
	B=new File(src,"B.java",1,LocalDateTime.of(2023, 11, 3, 14, 30, 0));
	
	//root 2nd child
	bin=new Directory(repo,"bin",0,currentTime);
	Ac=new File(bin,"A.class",2,currentTime);
	Bc=new File(bin,"B.class",0,currentTime);
	
	//root 3rd child
	test=new Directory(repo,"test",1,currentTime);
	srcTest=new Directory(test,"src",4,currentTime);
	ATest= new File(srcTest,"ATest.java",7,LocalDateTime.of(2023, 11, 3, 14, 30, 0));
	BTest= new File(srcTest,"BTest.java",8,currentTime);
	binTest=new Directory(test,"bin",2,currentTime);
	ATestc= new File(binTest,"ATest.class",9,LocalDateTime.of(2023, 11, 3, 14, 30, 0));
	BTestc= new File(binTest,"BTest.class",10,currentTime);
	
	
	//root 4th child
	readme=new File(repo,"readme.md",3,currentTime);
	
	
	
	
	
	

	return fs;
	
	}
}
