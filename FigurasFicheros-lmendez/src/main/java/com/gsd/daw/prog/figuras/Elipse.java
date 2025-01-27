package com.gsd.daw.prog.figuras;


public class Elipse {
    // Atributos
    private final Punto centro;
    private final int radio1; 
    private final int radio2; 
    private Stroke stroke;

    // Constructor
    public Elipse(Punto centro, int radio1, int radio2) {
        if (centro == null || radio1 <= 0 || radio2 <= 0) {
            throw new IllegalArgumentException("El centro no puede ser null y los radios deben ser mayores a cero.");
        }
        this.centro = centro;
        this.radio1 = radio1;
        this.radio2 = radio2;
        this.stroke = new Stroke("black", 1); // Inicialización por defecto
    }

    // Método para modificar el Stroke
    public void setStroke(Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("El Stroke no puede ser null.");
        }
        this.stroke = stroke;
    }

    // Genera el SVG para la elipse
    public String toSvg() {
        return String.format(
            "<ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
            centro.getX(), centro.getY(), radio1, radio2, stroke.getColor(), stroke.getWidth()
        );
    }

    @Override
    public String toString() {
        return String.format("Elipse [centro=%s, radio1=%d, radio2=%d, stroke=%s]", 
                centro, radio1, radio2, stroke);
    }


}
