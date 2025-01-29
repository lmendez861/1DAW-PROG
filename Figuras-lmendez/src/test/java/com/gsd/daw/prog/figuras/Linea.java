package com.gsd.daw.prog.figuras;

public class Linea {
    // Atributos
    private final Punto punto1; // Primer punto de la línea
    private final Punto punto2; // Segundo punto de la línea
    private Stroke stroke; // Atributos del trazo (color y grosor)


    public Linea(Punto punto1, Punto punto2) {
        if (punto1 == null || punto2 == null) {
            throw new IllegalArgumentException("Los puntos de la línea no pueden ser null.");
        }
        this.punto1 = punto1;
        this.punto2 = punto2;
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
            "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"%s\" stroke-width=\"%d\"/>",
            punto1.getX(), punto1.getY(), punto2.getX(), punto2.getY(), stroke.getColor(), stroke.getWidth()
        );
    }

    @Override
    public String toString() {
        return String.format("Linea [punto1=%s, punto2=%s, stroke=%s]", punto1, punto2, stroke);
    }
}
