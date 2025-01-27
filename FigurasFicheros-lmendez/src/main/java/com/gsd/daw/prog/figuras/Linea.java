package com.gsd.daw.prog.figuras;

public class Linea {
    // Atributos
    private final Punto inicio;
    private final Punto fin;
    private Stroke stroke;

    // Constructor que toma dos puntos para la línea
    public Linea(Punto inicio, Punto fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Los puntos de inicio y fin no pueden ser nulos.");
        }
        this.inicio = inicio;
        this.fin = fin;
        this.stroke = new Stroke("black", 1); // Inicialización por defecto
    }

    // Establece el color y el grosor del trazo
    public void setStroke(Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("El Stroke no puede ser nulo.");
        }
        this.stroke = stroke;
    }

    // Genera el código SVG de la línea
    public String toSvg() {
        return String.format(
            "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"%s\" stroke-width=\"%d\" />",
            inicio.getX(), inicio.getY(), fin.getX(), fin.getY(), stroke.getColor(), stroke.getWidth()
        );
    }

    @Override
    public String toString() {
        return String.format("Linea [inicio=%s, fin=%s, stroke=%s]", inicio, fin, stroke);
    }
}

