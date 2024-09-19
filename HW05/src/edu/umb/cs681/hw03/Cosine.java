package edu.umb.cs681.hw03;

import java.util.List;
import java.util.stream.IntStream;

public class Cosine implements DistanceMetric{
	
	public double distance(List<Double> p1, List<Double> p2) {
        double dotProduct = IntStream.range(0, p1.size())
	            .mapToDouble(i ->  p1.get(i) * p2.get(i))
	            .sum();
        double P1magnitude = calcMagnitude(p1);
        double P2magnitude = calcMagnitude(p2);
        double cosineSimilarity = dotProduct / (P1magnitude * P2magnitude);
        return 1 - cosineSimilarity;
    }
	
	private double calcMagnitude(List<Double> point) {

        double sumOfSquares = IntStream.range(0, point.size())
	            .mapToDouble(i ->  point.get(i)*point.get(i))
	            .sum();
        return Math.sqrt(sumOfSquares);
    }
	
	
}
