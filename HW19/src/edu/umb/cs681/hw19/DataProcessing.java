package edu.umb.cs681.hw19;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import edu.umb.cs681.hw03.Distance;
import edu.umb.cs681.hw03.DistanceMetric;
import edu.umb.cs681.hw03.Euclidean;

import static java.util.stream.Collectors.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

public class DataProcessing {
	
	
	private static List<DataRecord> readDataFromFile(String fileName) {
        List<DataRecord> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Skip header
            reader.readLine();
            String line;
            int count_of_lines=0;
            while ((line = reader.readLine()) != null) {
            	
            	if(count_of_lines>13) {
                String[] parts = line.split(",");
                DataRecord record = new DataRecord(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        Double.parseDouble(parts[2]),
                        Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5]),
                        Double.parseDouble(parts[6])
                );
                data.add(record);
            	}else {
            		count_of_lines++;
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
	
	private static List<Double> GetLatitudesAndLongitudesOfThePoint(String fileName) {
		List<Double> Points=new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            String line;
            
            int count_of_lines=0;
            while ((line = reader.readLine()) != null) {
            	String[] parts = line.split(" Latitude  |   Longitude ");
            	if(count_of_lines==2) {
            		Points.add(Double.parseDouble(parts[1]));
            		Points.add(Double.parseDouble(parts[2]));
            		break;
            	}else {
            		count_of_lines++;
            	}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return Points;
		
	}
	

	public static void main(String[] args) {
		
		System.out.println("DATA PROCESSING 1");
        List<Double> pointsOf1stLocation =GetLatitudesAndLongitudesOfThePoint("./location_1.csv");
        List<Double> pointsOf2ndLocation =GetLatitudesAndLongitudesOfThePoint("./location_2.csv");
        DistanceMetric e=new Euclidean();
		Distance d=new Distance();
		System.out.println("DISTANCE BETWEEN 2 POINTS");
		System.out.println("Distance between point-1 and point-2 : "+e.distance(pointsOf1stLocation, pointsOf2ndLocation));
		List<Double> pointsOf6thLocation =GetLatitudesAndLongitudesOfThePoint("./location_6.csv");
	    List<Double> pointsOf3rdLocation =GetLatitudesAndLongitudesOfThePoint("./location_3.csv");
	    e=new Euclidean();
	    d=new Distance();
	    System.out.println("Distance between point-6 and point-3 : "+e.distance(pointsOf6thLocation, pointsOf3rdLocation));
	    System.out.println();
	    
	    System.out.println("DATA PROCESSING 2");
	    System.out.println("SIMILARITY MATRIX OF VARIOUS POINTS");
	    List<List<Double>> PointsOf1stAnd2ndLocation=new ArrayList<>();
	    PointsOf1stAnd2ndLocation.add(pointsOf1stLocation);
	    PointsOf1stAnd2ndLocation.add(pointsOf2ndLocation);
		System.out.println( "distance matrix among 1st and 2nd point");
		List<List<Double>> DistanceMatrixOf1stAnd2ndPoint=d.matrix(PointsOf1stAnd2ndLocation,e);
		DistanceMatrixOf1stAnd2ndPoint.forEach(innerList ->
        System.out.println(innerList.stream().parallel()
                .map(Object::toString)
                .collect(joining(" "))));

	    List<List<Double>> PointsOf3rdAnd6thLocation=new ArrayList<>();
	    PointsOf3rdAnd6thLocation.add(pointsOf3rdLocation);
	    PointsOf3rdAnd6thLocation.add(pointsOf6thLocation);
		System.out.println( "distance matrix among 3rd and 6th point");
		List<List<Double>> DistanceMatrixOf3rdAnd6thPoint=d.matrix(PointsOf3rdAnd6thLocation,e);
		DistanceMatrixOf3rdAnd6thPoint.forEach(innerList ->
	    System.out.println(innerList.stream().parallel()
	        .map(value -> value + " ")
	        .collect(joining()))
				);

		System.out.println();
		
		System.out.println("DATA PROCESSING 3");
		System.out.println("YEARLY AND DAILY STATISTICS OF 11 POINTS");
        List<List<DataRecord>> data = new ArrayList<>() ;
        for(int i=1;i<=11;i++) {
        	System.out.println("LOCATION-"+i);
        	data.add(readDataFromFile("./location_"+i+".csv"));
        	 Map<Integer, List<DataRecord>> dataByYear = data.get(i-1).stream().parallel()
                     .collect(groupingBy(DataRecord::getYear));
             System.out.println("YEARLY STATISTICS");
             dataByYear.forEach((year, records) -> {
                 DoubleSummaryStatistics statsPrectotcorr = records.stream().parallel()
                         .mapToDouble(DataRecord::getPrectotcorr)
                         .summaryStatistics();
                 System.out.println("Year-" + year+" Prectotcorr Stats: " + statsPrectotcorr);
             });   
             Stream<DataRecord> dataByday = data.get(i-1).stream().parallel();
             System.out.println("DAILY STATISTICS");
             DoubleSummaryStatistics statsPrectotcorr = dataByday
                     .mapToDouble(DataRecord::getPrectotcorr)
                     .summaryStatistics();
             System.out.println("Prectotcorr statistics: "+statsPrectotcorr); 
        }   
        
        
        

        
    }
	
}

