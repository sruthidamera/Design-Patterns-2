package edu.umb.cs681.hw14;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw14.Directory;
import edu.umb.cs681.hw14.File;
import edu.umb.cs681.hw14.FileSystem;
import edu.umb.cs681.hw14.TestFixtureInitializer;

class DirectoryTest{
	static FileSystem fs;
	static Directory root;

	@BeforeAll
	public static void setUpFS() {
		fs=TestFixtureInitializer.createFS();
		root=fs.getRootDirs().get(0);
	}

	private String[] dirToStringArray(Directory d) {
		
		if(d.getParent()==null) {
			String[] dirInfo= {d.getName(),d.getSize()+"","null",d.getCreationTime()+""};
			return dirInfo;
		}
		else {
			String[] dirInfo= {d.getName(),d.getSize()+"",d.getParent().getName(),d.getCreationTime()+""};
			return dirInfo;
		}
		
	}
	
	@Test
	public void verifyDirectoryEqualityRoot() {
		String[] expected= {"repo","0","null",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,dirToStringArray(root));
	}
	
	@Test
	public void verifyDirectoryEqualitySrc() {
		Directory src=(Directory) root.getChildren().get(0);
		String[] expected= {"src","0","repo",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,dirToStringArray(src));
	}
	@Test
	public void verifyDirectoryEqualityTest() {
		Directory test=(Directory) root.getChildren().get(1);
		String[] expected= {"test","0","repo",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,dirToStringArray(test));
	}
	
	@Test
	public void verifyDirectoryEqualitySrcTest() {
		Directory SrcTest=(Directory) ((Directory) root.getChildren().get(1)).getChildren().get(0);
		String[] expected= {"test-src","0","test",LocalDateTime.of(2023, 11, 2, 14, 30, 0)+""};
		assertArrayEquals(expected,dirToStringArray(SrcTest));
	}
	
	
	@Test
    void GetTotalSizeTest() {
        assertEquals(5,root.getTotalSize());
    }
	
	@Test 
	void GetChildrenTest() {
		assertEquals(3,root.getChildren().size());
	}
	@Test 
	void CountChildrenTest() {
		assertEquals(3,root.countChildren());
	}
	
	
	@Test 
	void GetSubDirectoriesTest() {
		assertEquals(2,root.getSubDirectories().size());
	}
	
	@Test 
	void GetFilesTest() {
		assertEquals(1,root.getFiles().size());
	}
	
	@Test 
	void IsDirectoryTest() {
		assertTrue(root.isDirectory());
	}
	@Test 
	void IsFileTest() {
		assertFalse(root.isFile());
	}
	
	

   

}
