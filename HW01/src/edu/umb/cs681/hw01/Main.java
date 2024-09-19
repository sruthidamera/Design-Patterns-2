package edu.umb.cs681.hw01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.IntSummaryStatistics;
import java.util.Map;

import static java.util.stream.Collectors.*;

import edu.umb.cs681.hw01.TestFixtureInitializer;
import edu.umb.cs681.hw01.fs.util.FileCrawlingVisitor;

public class Main {
	static FileSystem fs;
	static Directory root;

	public static void main(String[] args) {
		
		fs=TestFixtureInitializer.createFS();
		root=fs.getRootDirs().get(0);
		FileCrawlingVisitor fcv=new FileCrawlingVisitor();
		root.accept(fcv);
		System.out.println("Number of files that was created after a November 2, 2023 and has .java extension : "+fcv.files().filter((File f)->f.getCreationTime().isAfter(LocalDateTime.of(2023, 11, 2, 14, 30, 0))) .filter((File f) -> f.getName().toLowerCase().endsWith(".java")).count());
		System.out.println();
		
		
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
		
		FileSummary.forEach((fileType, summ) -> {
		    long totalCount = summ.getCount();
		    double mean = summ.getAverage();
		    int min = summ.getMin();
		    int max = summ.getMax();

		    System.out.println("Type of file: " + fileType);
		    System.out.println("file Count: " + summ.getCount());
		    System.out.println("Mean of " + summ.getAverage());
		    System.out.println("Min : " + summ.getMin());
		    System.out.println("Max: " + summ.getMax());
		    System.out.println();
		});

	}
	
	
	

}

