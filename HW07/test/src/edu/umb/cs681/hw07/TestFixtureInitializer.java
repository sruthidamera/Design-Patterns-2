package edu.umb.cs681.hw07;


import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;

import edu.umb.cs681.hw07.Directory;
import edu.umb.cs681.hw07.File;
import edu.umb.cs681.hw07.FileSystem;

public class TestFixtureInitializer {
	static Directory repo;
	static Directory src ;
	static Directory test;
	static File readme;
	static File A;
	static File B;
	static Directory SrcTest;
	static File Atest;
	static File Btest;

	
	public static FileSystem createFS() {
		
	FileSystem fs= FileSystem.getFileSystem();
	
	LocalDateTime currentTime = LocalDateTime.of(2023, 11, 2, 14, 30, 0);
	
	
	repo =new Directory(null,"repo",0,currentTime);
	
	fs.appendRootDir(repo);
	
	//root 1st child
	src=new Directory(repo,"src",0,currentTime);
	A=new File(src,"A.java",1,currentTime);
	B=new File(src,"B.java",1,currentTime);
	
	//2nd child
	test=new Directory(repo,"test",0,currentTime);
	SrcTest=new Directory(test,"test-src",0,currentTime);
	Atest= new File(SrcTest,"Atest.java",1,currentTime);
	Btest= new File(SrcTest,"Btest.java",1,currentTime);
	
	//3rd child
	readme=new File(repo,"readme.md",1,currentTime);
	
	return fs;
	
	
	}
}