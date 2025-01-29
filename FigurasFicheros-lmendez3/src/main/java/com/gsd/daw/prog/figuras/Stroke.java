package com.gsd.daw.prog.figuras;

public class Stroke {
    private String nombre;
    private int rojo;
    private int verde;
    private int azul;
    private int ancho;

    public Stroke(String nombre, int rojo, int verde, int azul, int ancho) {
        this.nombre = nombre;
        this.rojo = rojo;
        this.verde = verde;
        this.azul = azul;
        this.ancho = ancho;
    }

    public String getNombre() {
        return nombre;
    }

    public String generarSVG() {
        return String.format("stroke=\"rgb(%d,%d,%d)\" stroke-width=\"%d\"",
                rojo, verde, azul, ancho);
    }

    // Devuelve el color en formato RGB
    public String getColor() {
        return String.format("rgb(%d,%d,%d)", rojo, verde, azul);
    }

    // Devuelve el ancho del trazo
    public int getWidth() {
        return ancho;
    }
}

