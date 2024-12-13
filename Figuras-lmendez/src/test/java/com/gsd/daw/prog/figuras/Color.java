package com.gsd.daw.prog.figuras;

public class Color {
    // Atributos inmutables
    private final Byte red;   
    private final Byte green; 
    private final Byte blue; 


    public Color(Byte red, Byte green, Byte blue) {
        if (red == null || green == null || blue == null) {
            throw new IllegalArgumentException("Los valores RGB no pueden ser null.");
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    // Getters
    public Byte getRed() {
        return red;
    }

    public Byte getGreen() {
        return green;
    }

    public Byte getBlue() {
        return blue;
    }

  
    public String toSvg() {
        return String.format("rgb(%d, %d, %d)", red & 0xFF, green & 0xFF, blue & 0xFF);
    }

    @Override
    public String toString() {
        return String.format("Color [red=%d, green=%d, blue=%d]", red & 0xFF, green & 0xFF, blue & 0xFF);
    }
}

