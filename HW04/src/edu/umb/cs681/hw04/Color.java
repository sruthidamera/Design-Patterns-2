package edu.umb.cs681.hw04;

import static java.util.stream.Collectors.*;

public class Color {
    private int redScale;
    private int greenScale;
    private int blueScale;

    public Color(int r, int g, int b) {
        this.redScale = r;
        this.greenScale = g;
        this.blueScale = b;
    }

    public int red() {
        return redScale;
    }

    public int green() {
        return greenScale;
    }

    public int blue() {
        return blueScale;
    }

//    public void setRed(int red) {
//        this.redScale = red;
//    }
//
//    public void setGreen(int green) {
//        this.greenScale = green;
//    }
//
//    public void setBlue(int blue) {
//        this.blueScale = blue;
//    }
}
