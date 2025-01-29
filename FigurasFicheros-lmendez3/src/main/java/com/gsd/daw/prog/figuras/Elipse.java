package com.gsd.daw.prog.figuras;

public class Elipse extends Figura {
    private int cx, cy, r1, r2;

    public Elipse(int cx, int cy, int r1, int r2, Stroke stroke) {
        super(stroke);  // Pasamos el objeto Stroke al constructor de la clase base
        this.cx = cx;
        this.cy = cy;
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public String generarSVG() {
        String color = stroke.getColor();  // Accedemos a los métodos de Stroke
        int ancho = stroke.getWidth();     // Accedemos al ancho del trazo

        // Generamos el SVG con los valores correspondientes
        return String.format("<ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
                             cx, cy, r1, r2, color, ancho);
    }
}
