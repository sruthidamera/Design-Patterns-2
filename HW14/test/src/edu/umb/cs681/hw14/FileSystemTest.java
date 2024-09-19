package edu.umb.cs681.hw14;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw14.Directory;
import edu.umb.cs681.hw14.FileSystem;
import edu.umb.cs681.hw14.TestFixtureInitializer;

class FileSystemTest {
	static FileSystem fs;
	static Directory root;

	@BeforeAll
	public static void setUpFS() {
		fs=TestFixtureInitializer.createFS();
		root=fs.getRootDirs().get(0);
	}

    @Test
    public void SingletonTestWhetherReturnsSameObject() {
    	FileSystem fs1=FileSystem.getFileSystem();
    	FileSystem fs2=FileSystem.getFileSystem();
    	assertSame(fs1,fs2);
    }
    
    
    @Test
    public void GetRootDirsTest() {
    	assertEquals("repo",fs.getRootDirs().get(0).getName());
    }
    
    
    @Test
    public void SingletonTestWhetherReturnsSameObjectWhenCalledFromMultipleThreads() {
    	final FileSystem[] fs1 = {null};
        final FileSystem[] fs2 = {null};
    	
    	Thread t1 = new Thread(() -> {
    		fs1[0] = FileSystem.getFileSystem();
        });
    	Thread t2 = new Thread(() -> {
    		 fs2[0] = FileSystem.getFileSystem();
        });
    	t1.start();
    	t2.start();
    	
    	try {
    		t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	assertSame(fs1[0], fs2[0]);
    }

}