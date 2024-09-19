package edu.umb.cs681.hw02;

import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;


public class Transcript {
	
	public static Map<String, Double> CalculateUndergradAndGraduateGPA(HashMap<String, String> transcript){
		
		Map<String, Double> groupedCourses =   transcript.entrySet().stream().collect(
				groupingBy(
						entry -> {
							if(isUndergrad(entry.getKey())) {return "undergrad";}
							else {return "grad";}
								}, 
						averagingDouble(entry -> convertGradeToGPA(entry.getValue()))
						));
		return groupedCourses;
		
	}
	
	
	
	 public static String getCourseType(String course) {
	        if (isUndergrad(course) && course.contains("major")) {
	            return "majorUndergrad";
	        } else if(!isUndergrad(course)){
	            return "grad";
	        }else {
	        	return "";
	        }
	    }
	
	 
	public static double CalculateMajorUndergradGPA(HashMap<String, String> transcript){
		double averageGPA = transcript.entrySet().stream()
	            .filter(entry -> isUndergrad(entry.getKey()) && entry.getKey().contains("major"))
	            .collect(averagingDouble(entry -> convertGradeToGPA(entry.getValue())));				 			
			return averageGPA;
		}


	public static double CalculateMajorgradGPA(HashMap<String, String> transcript){
		double averageGPA = transcript.entrySet().stream()
	            .filter(entry -> !isUndergrad(entry.getKey()) && entry.getKey().contains("major"))
	            .collect(averagingDouble(entry -> convertGradeToGPA(entry.getValue())));				 			
			return averageGPA;
		}
	
	public static Map<String, Double> calculateUndergradGPAGradGPA(HashMap<String, String> transcript){
		Map<String, Double> GPAs = transcript.entrySet().stream()
                .collect(groupingBy(
                        entry -> getCourseType(entry.getKey()),
                                averagingDouble(entry -> convertGradeToGPA(entry.getValue())
                        )
                ));
        return GPAs;
		
	}

	public static boolean isUndergrad(String course) {
		
		char thirdChar = course.charAt(2);
		return Character.getNumericValue(thirdChar) <= 4;
	   
	}
	
	public static  double convertGradeToGPA(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            default:
                return 0.0; 
        }
    }

	public static void main(String[] args) {
		HashMap<String, String> transcript=new HashMap<>();
		transcript.put("CS680-major","A");
		transcript.put("CS681-major","B");
		transcript.put("CS682-major","A");
		transcript.put("CS666","C");
		transcript.put("CS480-major","A");
		transcript.put("CS481-major","B");
		transcript.put("CS482-major","A");
		transcript.put("CS466","C");
		
		
		

			
		       
		Map<String, Double> GPAs=CalculateUndergradAndGraduateGPA(transcript);
		double undergradGPA=GPAs.get("undergrad");
		double gradGPA=GPAs.get("grad");
		System.out.println("Undergraduate GPA: " + undergradGPA);
		System.out.println("Graduate GPA: " + gradGPA);
		
		
		double undergradMajorGPA=CalculateMajorUndergradGPA(transcript);
		System.out.println("Undergraduate Major GPA: " + undergradMajorGPA);
		
		
		double gradMajorGPA=CalculateMajorgradGPA(transcript);
		System.out.println("graduate Major GPA: " + gradMajorGPA);
		
		
		Map<String, Double> AllTogetherGPAs=calculateUndergradGPAGradGPA(transcript);
		System.out.println("Major Undergrad GPA: " + AllTogetherGPAs.get("majorUndergrad"));
        System.out.println("Grad GPA: " + AllTogetherGPAs.get("grad"));
		
		
		
		

	}



}
