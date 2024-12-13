package com.gsd.daw.prog.figuras;

public class Rectangulo {
    // Atributos
    private final Punto origen;
    private final Integer ancho; 
    private final Integer alto; 
    private Stroke stroke; 


    public Rectangulo(Punto origen, Integer ancho, Integer alto) {
        if (origen == null || ancho == null || alto == null) {
            throw new IllegalArgumentException("El origen, ancho y alto no pueden ser null.");
        }
        this.origen = origen;
        this.ancho = ancho;
        this.alto = alto;
        this.stroke = new Stroke("black", 1); // Inicialización por defecto
    }


    public void setStroke(Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("El Stroke no puede ser null.");
        }
        this.stroke = stroke;
    }

    public String toSvg() {
        return String.format(
            "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
            origen.getX(), origen.getY(), ancho, alto, stroke.getColor(), stroke.getWidth()
        );
    }

    @Override
    public String toString() {
        return String.format("Rectangulo [origen=%s, ancho=%d, alto=%d, stroke=%s]", 
                origen, ancho, alto, stroke);
    }
}
