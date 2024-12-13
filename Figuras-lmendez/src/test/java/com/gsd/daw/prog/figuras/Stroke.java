package com.gsd.daw.prog.figuras;

public class Stroke {
    private final String color; // Color del trazo
    private final int width;    // Grosor del trazo

    public Stroke(String color, int width) {
        if (color == null || color.isEmpty() || width <= 0) {
            throw new IllegalArgumentException("El color no puede ser nulo o vacío, y el grosor debe ser mayor a 0.");
        }
        this.color = color;
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }
}
