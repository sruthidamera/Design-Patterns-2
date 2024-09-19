package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.*;

public class Image {
    private int width;
    private int height;
    private List<List<Color>> pixels;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<Color> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new Color(0, 0, 0)); // Initialize with black color
            }
            this.pixels.add(row);
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public List<List<Color>> pixels() {
        return pixels;
    }

    public void setColor(int x, int y, Color color) {
        pixels.get(y).set(x, color);
    }
    
    
    
	public static void main(String[] args) {
		Random rand = new Random();
		Image origImg = new Image(5,5);
		for (int i=0;i<5;i++) {
			for (int j=0;j<5;j++) {
				int r=rand.nextInt(10);
				int g=rand.nextInt(10);
				int b=rand.nextInt(10);
				origImg.setColor(i, j, new Color(r,g,b));
			}
		}

		
		Image newImg = origImg.pixels().stream().flatMap(row-> row.stream()) .map((Color pixColor)->{
			int avgColor=(pixColor.red()+pixColor.blue()+pixColor.green())/3;		
			return new Color(avgColor,avgColor,avgColor);
				}) .collect(collectingAndThen(
                        toList(),
                        colors -> {
                            Image img = new Image(origImg.width(), origImg.height());
                            int x=0;
                            int y=0;
                            for (Color color : colors) {
                                
                                img.setColor(x, y, color);
                                x++;
                                if(x==origImg.width()) {
                                	x=0;
                                	y++;
                                }
                            }
                            return img;
                        }));
		
		System.out.println("Pixels of orig Img:");
	    for (int i = 0; i < origImg.height(); i++) {
	        for (int j = 0; j < origImg.width(); j++) {
	            Color pixelColor = origImg.pixels().get(i).get(j);
	            System.out.print("(" + pixelColor.red() + "," + pixelColor.green() + "," + pixelColor.blue() + ") ");
	        }
	        System.out.println();
	    }
		
		System.out.println("Pixels of newImg:");
	    for (int i = 0; i < newImg.height(); i++) {
	        for (int j = 0; j < newImg.width(); j++) {
	            Color pixelColor = newImg.pixels().get(i).get(j);
	            System.out.print("(" + pixelColor.red() + "," + pixelColor.green() + "," + pixelColor.blue() + ") ");
	        }
	        System.out.println();
		
	
	}		
		
		
	
	}
    }

