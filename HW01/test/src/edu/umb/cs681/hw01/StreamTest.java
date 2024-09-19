package edu.umb.cs681.hw01;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingInt;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.IntSummaryStatistics;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw01.Directory;
import edu.umb.cs681.hw01.File;
import edu.umb.cs681.hw01.FileSystem;
import edu.umb.cs681.hw01.fs.util.*;

class StreamTest {
	
	static FileSystem fs;
	static Directory root;

	@BeforeAll
	public static void setUpFS() {
		fs=TestFixtureInitializer.createFS();
		root=fs.getRootDirs().get(0);
	}
	
	@Test
	public void FSCrawlingVisitorStreamTest() {
		FileCrawlingVisitor fcv=new FileCrawlingVisitor();
		root.accept(fcv);
		assertEquals(3,fcv.files().filter((File f)->f.getCreationTime().isAfter(LocalDateTime.of(2023, 11, 2, 14, 30, 0))) .filter((File f) -> f.getName().toLowerCase().endsWith(".java")).count());
		
	}
	
	@Test
	public void FileGroupByExtensionAndSummarizingTest() {
		FileCrawlingVisitor fcv=new FileCrawlingVisitor();
		root.accept(fcv);
		Map<String, IntSummaryStatistics> FileSummary =fcv.files().collect(
			    groupingBy(
			        (File f) -> {
			            if (f.getName().endsWith(".java")) {
			                return "java";
			            } else if (f.getName().endsWith(".class")) {
			                return "class";
			            } else {
			                return "other";
			            }
			        },
			        summarizingInt((File f) -> f.getSize())
			    )
			);
		
		assertEquals(5.25,FileSummary.get("java").getAverage());
		assertEquals(4,FileSummary.get("java").getCount());
		assertEquals(1,FileSummary.get("java").getMin());
		assertEquals(5.25,FileSummary.get("class").getAverage());
		assertEquals(4,FileSummary.get("class").getCount());
		assertEquals(0,FileSummary.get("class").getMin());
		assertEquals(3.0,FileSummary.get("other").getAverage());
		assertEquals(1,FileSummary.get("other").getCount());
		assertEquals(3,FileSummary.get("other").getMin());
	}
	

}
