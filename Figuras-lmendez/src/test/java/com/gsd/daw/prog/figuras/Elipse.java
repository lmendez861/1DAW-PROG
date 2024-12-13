package com.gsd.daw.prog.figuras;

public class Elipse {
    // Atributos
    private final Punto centro;
    private final Integer radio1; 
    private final Integer radio2; 
    private Stroke stroke; 


    public Elipse(Punto centro, Integer radio1, Integer radio2) {
        if (centro == null || radio1 == null || radio2 == null) {
            throw new IllegalArgumentException("El centro y los radios no pueden ser null.");
        }
        this.centro = centro;
        this.radio1 = radio1;
        this.radio2 = radio2;
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
