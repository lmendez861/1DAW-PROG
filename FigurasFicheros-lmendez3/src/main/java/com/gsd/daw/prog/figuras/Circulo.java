package com.gsd.daw.prog.figuras;

public class Circulo extends Figura {
    private int cx, cy, r;

    public Circulo(int cx, int cy, int r, Stroke stroke) {
        super(stroke);  // Ahora pasamos el objeto Stroke al constructor de la clase padre
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    @Override
    public String generarSVG() {
        return String.format("<circle cx=\"%d\" cy=\"%d\" r=\"%d\" %s />",
                             cx, cy, r, stroke.generarSVG());
    }
}
