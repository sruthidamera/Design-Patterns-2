package edu.umb.cs681.hw14;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.umb.cs681.hw14.fs.util.FileCrawlingVisitor;

public class FileCrawlingRunnable implements Runnable{
	
	Directory root;
	FileCrawlingVisitor fcv;
	AtomicBoolean done=new AtomicBoolean(false);
	
	FileCrawlingRunnable(Directory root){
		this.root=root;
		this.fcv=new FileCrawlingVisitor();
	}
	
	public void PrintFoundFiles() {
		LinkedList<File> files=this.fcv.getFiles();
		System.out.print("Found files : ");
		for(File f: files) {
			System.out.print(f.getName()+" ");
		}
		System.out.println();
	}
	
	public LinkedList<File> getCrawledFiles(){
		return fcv.getFiles();
	}
	
	public void setDone() {
			done.set(true);
	}
	
	@Override
	public void run() {
		root.accept(fcv);
		try {
            while(!done.get()) {
                Thread.sleep(300);
            }
        } catch(InterruptedException ex) {
            
        }
		
	}

	public static void main(String args[]) {
		LinkedList<File> sharedList=new LinkedList();
		LocalDateTime currentTime = LocalDateTime.of(2023, 11, 2, 14, 30, 0);
		
		Directory repo1 =new Directory(null,"repo-1",0,currentTime);
		Directory src=new Directory(repo1,"src-1",0,currentTime);
		File A=new File(src,"A.java-1",1,currentTime);
		File B=new File(src,"B.java-1",1,currentTime);
		Directory test=new Directory(repo1,"test-1",0,currentTime);
		Directory SrcTest=new Directory(test,"test-src-1",0,currentTime);
		File Atest= new File(SrcTest,"Atest.java-1",1,currentTime);
		File Btest= new File(SrcTest,"Btest.java-1",1,currentTime);
		File readme=new File(repo1,"readme.md-1",1,currentTime);
		
		
		Directory repo2 =new Directory(null,"repo-2",0,currentTime);
		Directory src2=new Directory(repo2,"src-2",0,currentTime);
		File A2=new File(src2,"A.java-2",1,currentTime);
		File B2=new File(src2,"B.java-2",1,currentTime);
		Directory test2=new Directory(repo2,"test-2",0,currentTime);
		Directory SrcTest2=new Directory(test2,"test-src-2",0,currentTime);
		File Atest2= new File(SrcTest2,"Atest.java-2",1,currentTime);
		File Btest2= new File(SrcTest2,"Btest.java-2",1,currentTime);
		File readme2=new File(repo2,"readme.md-2",1,currentTime);
		
		Directory repo3 =new Directory(null,"repo-3",0,currentTime);
		Directory src3=new Directory(repo3,"src-3",0,currentTime);
		File A3=new File(src3,"A.java-3",1,currentTime);
		File B3=new File(src3,"B.java-3",1,currentTime);
		Directory test3=new Directory(repo3,"test-3",0,currentTime);
		Directory SrcTest3=new Directory(test3,"test-src-3",0,currentTime);
		File Atest3= new File(SrcTest3,"Atest.java-3",1,currentTime);
		File Btes2t3= new File(SrcTest3,"Btest.java-3",1,currentTime);
		File readme3=new File(repo3,"readme.md-3",1,currentTime);
		
		FileCrawlingRunnable fcr1=new FileCrawlingRunnable(repo1);
		FileCrawlingRunnable fcr2=new FileCrawlingRunnable(repo2);
		FileCrawlingRunnable fcr3=new FileCrawlingRunnable(repo3);
		Thread t1=new Thread(fcr1);
		Thread t2=new Thread(fcr2);
		Thread t3=new Thread(fcr3);
		t1.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fcr1.setDone();
		fcr2.setDone();
		fcr3.setDone();
		
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		
		
		sharedList.addAll(fcr1.getCrawledFiles());
		sharedList.addAll(fcr2.getCrawledFiles());
		sharedList.addAll(fcr3.getCrawledFiles());
		
		System.out.print("Crawled files by all Threads: ");
		for(File f: sharedList) {
			System.out.print(f.getName()+" ");
		}
		System.out.println();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
