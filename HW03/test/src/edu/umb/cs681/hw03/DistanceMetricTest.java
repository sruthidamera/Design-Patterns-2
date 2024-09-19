package edu.umb.cs681.hw03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw03.Cosine;
import edu.umb.cs681.hw03.Distance;
import edu.umb.cs681.hw03.DistanceMetric;
import edu.umb.cs681.hw03.Euclidean;
import edu.umb.cs681.hw03.Manhattan;

class DistanceMetricTest {
	

	static List<List<Double>> generatedPoints= new ArrayList<>();
	
	public static List<List<Double>> generateTestData(int numOfPoints) {
        List<List<Double>> points = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numOfPoints; i++) {
            List<Double> point=new ArrayList<>();
            for(int j=0;j<150;j++) {
            	point.add(random.nextDouble(100000));
            }
            points.add(point);
        }

        return points;
    }
	
	

	@BeforeAll
	public static void setUp() {
		generatedPoints=generateTestData(1500);
	}
	
	
	@Test
	public void EuclideanDistanceMatrixTest() {
		DistanceMetric e=new Euclidean();
		Distance d=new Distance();
		List<List<Double>> distanceMatrix= d.matrix(generatedPoints,e);
		int x=100;
		int y=120;
		assertEquals(e.distance(generatedPoints.get(x), generatedPoints.get(y)),distanceMatrix.get(x).get(y));
	}
	
	@Test
	public void ManhattanDistanceMatrixTest() {
		DistanceMetric e=new Manhattan();
		Distance d=new Distance();
		List<List<Double>> distanceMatrix= d.matrix(generatedPoints,e);
		int x=100;
		int y=120;
		assertEquals(e.distance(generatedPoints.get(x), generatedPoints.get(y)),distanceMatrix.get(x).get(y));
	}
	
	@Test
	public void CosineDistanceMatrixTest() {
		DistanceMetric e=new Cosine();
		Distance d=new Distance();
		List<List<Double>> distanceMatrix= d.matrix(generatedPoints,e);
		int x=100;
		int y=120;
		assertEquals(e.distance(generatedPoints.get(x), generatedPoints.get(y)),distanceMatrix.get(x).get(y));
	}
	
	
	
	
	
	
	

}
