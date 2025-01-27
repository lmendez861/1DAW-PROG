package com.gsd.daw.prog.figuras;


public class Rectangulo {
    // Atributos
    private final Punto origen;
    private final Integer ancho; 
    private final Integer alto; 
    private Stroke stroke; 

    // Constructor con un objeto Punto para el origen
    public Rectangulo(Punto origen, Integer ancho, Integer alto) {
        if (origen == null || ancho == null || alto == null) {
            throw new IllegalArgumentException("El origen, ancho y alto no pueden ser null.");
        }
        this.origen = origen;
        this.ancho = ancho;
        this.alto = alto;
        this.stroke = new Stroke("black", 1); // Inicialización por defecto
    }

    // Constructor alternativo con coordenadas y dimensiones directamente
    public Rectangulo(int xRect, int yRect, int anchoRect, int altoRect, String colorRect) {
        if (colorRect == null) {
            throw new IllegalArgumentException("El color no puede ser null.");
        }
        this.origen = new Punto(xRect, yRect); // Inicializa el origen con un nuevo Punto
        this.ancho = anchoRect;
        this.alto = altoRect;
        this.stroke = new Stroke(colorRect, 1); // Inicialización con color personalizado
    }

    // Método para establecer el trazo (Stroke)
    public void setStroke(Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("El Stroke no puede ser null.");
        }
        this.stroke = stroke;
    }

    // Método para generar el código SVG
    public String toSvg() {
        return String.format(
            "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
            origen.getX(), origen.getY(), ancho, alto, stroke.getColor(), stroke.getWidth()
        );
    }

    // Método toString para representar el rectángulo como cadena
    @Override
    public String toString() {
        return String.format("Rectangulo [origen=%s, ancho=%d, alto=%d, stroke=%s]", 
                origen, ancho, alto, stroke);
    }


}
