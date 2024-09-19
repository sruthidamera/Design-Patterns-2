package edu.umb.cs681.hw07;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw07.Directory;
import edu.umb.cs681.hw07.File;
import edu.umb.cs681.hw07.FileSystem;

class FileTest {
	static FileSystem fs;
	static Directory root;

	@BeforeAll
	public static void setUpFS() {
		fs=TestFixtureInitializer.createFS();
		root=fs.getRootDirs().get(0);
	}

	private String[] FileToStringArray(File f) {
		
		if(f.getParent()==null) {
			String[] dirInfo= {f.getName(),f.getSize()+"","null",f.getCreationTime()+""};
			return dirInfo;
		}
		else {
			String[] dirInfo= {f.getName(),f.getSize()+"",f.getParent().getName(),f.getCreationTime()+""};
			return dirInfo;
		}
		
	}
	
	
	@Test
	public void verifyFileEqualityA() {
		File A=(File) ((Directory) root.getChildren().get(0)).getChildren().get(0);
		
		String[] expected= {"A.java","1","src",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,FileToStringArray(A));
	}
	
	@Test
	public void verifyFileEqualityB() {
		File B=(File) ((Directory) root.getChildren().get(0)).getChildren().get(1);
		
		String[] expected= {"B.java","1","src",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,FileToStringArray(B));
	}
	
	@Test
	public void verifyFileEqualityATest() {
		File Atest=(File) ((Directory) ((Directory) root.getChildren().get(1)).getChildren().get(0)).getChildren().get(0);
		
		String[] expected= {"Atest.java","1","test-src",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,FileToStringArray(Atest));
	}
	
	@Test
	public void verifyFileEqualityBTest() {
		
		File Btest=(File) ((Directory) ((Directory) root.getChildren().get(1)).getChildren().get(0)).getChildren().get(1);
		String[] expected= {"Btest.java","1","test-src",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,FileToStringArray(Btest));
	}
	
	@Test
	public void verifyFileEqualityReadMe() {
		File readme=(File) root.getChildren().get(2);
		
		String[] expected= {"readme.md","1","repo",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,FileToStringArray(readme));
	}
	
	@Test 
	void IsDirectoryTest() {
		File readme=(File) root.getChildren().get(2);
		assertFalse(readme.isDirectory());
	}
	
	@Test 
	void IsFileTest() {
		File readme=(File) root.getChildren().get(2);
		assertTrue(readme.isFile());
	}
	
	
}