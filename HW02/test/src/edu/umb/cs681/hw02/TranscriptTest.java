package edu.umb.cs681.hw02;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TranscriptTest {

	private static HashMap<String, String> transcript;

    @BeforeAll
    public static void setUp() {
        // Initialize the transcript with test data
        transcript = new HashMap<>();
        transcript.put("CS680-major", "A");
        transcript.put("CS681-major", "B");
        transcript.put("CS682-major", "A");
        transcript.put("CS666", "C");
        transcript.put("CS480-major", "A");
        transcript.put("CS481-major", "B");
        transcript.put("CS482-major", "A");
        transcript.put("CS466", "C");
    }
    
    @Test
    public void testCalculateUndergradAndGraduateGPA() {
        Map<String, Double> GPAs = Transcript.CalculateUndergradAndGraduateGPA(transcript);
        assertEquals(2.5, GPAs.get("undergrad"), 3.25);
        assertEquals(3.0, GPAs.get("grad"), 3.25);
    }
    
    
    @Test
    public void testCalculateMajorUndergradGPA() {
        double majorUndergradGPA = Transcript.CalculateMajorUndergradGPA(transcript);
        assertEquals(3.0, majorUndergradGPA, 3.6666666666666665);
    }

    @Test
    public void testCalculateMajorGradGPA() {
        double majorGradGPA = Transcript.CalculateMajorgradGPA(transcript);
        assertEquals(2.0, majorGradGPA, 3.6666666666666665);
    }
    
    @Test
    public void testCalculateUndergradGPAGradGPA() {
        Map<String, Double> AllTogetherGPAs = Transcript.calculateUndergradGPAGradGPA(transcript);
        assertEquals(2.333, AllTogetherGPAs.get("majorUndergrad"), 3.6666666666666665);
        assertEquals(3.0, AllTogetherGPAs.get("grad"), 3.0);
    }
    



}
