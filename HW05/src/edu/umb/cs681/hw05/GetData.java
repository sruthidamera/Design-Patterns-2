package edu.umb.cs681.hw05;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetData {
    public static void main(String[] args) {

        String apiUrl = "https://power.larc.nasa.gov/api/temporal/daily/point";
        String parameters = "PRECTOTCORR,RH2M,GWETPROF,GWETROOT,GWETTOP";
        String community = "AG";
        String[] longitude = {"-71.0954","78.4156","78.3719","78.358","78.4801","78.3601","-71.0534","78.3849","76.355","77.6204","78.469"};
        String[] latitude = {"42.3851","17.5222","17.4986","17.4955","17.3616","17.4925","42.3186","17.539","9.5018","13.0499","17.4062"};
        String start = "20200101";
        String end = "20240101";
        String format = "CSV";
        for(int i=0;i<11;i++) {
        String fullUrl = String.format("%s?parameters=%s&community=%s&longitude=%s&latitude=%s&start=%s&end=%s&format=%s",
                apiUrl, parameters, community, longitude[i], latitude[i], start, end, format);
        
        try {
            URL url = new URL(fullUrl);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("GET");
            
            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                    response.append("\n");
                }
                reader.close();
                
              
                saveToFile(response.toString(), "./location_"+(i+1)+".csv");
                
                System.out.println("API response data saved to location_"+(i+1)+".csv");
            } else {
                System.out.println("Failed to fetch data from the API. Response code: " + responseCode);
            }

            
            connection.disconnect();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        }
    }
    
    private static void saveToFile(String data, String fileName) {
    	String directoryPath = "./"; 
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + fileName));
            
            String[] lines = data.split("\n");
            
            for (String line : lines) {
                writer.write(line);
                writer.newLine(); 
            }
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
