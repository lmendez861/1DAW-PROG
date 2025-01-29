package com.gsd.daw.prog.figuras;

public class Triangulo extends Figura {
    private int x1, y1, x2, y2, x3, y3;

    public Triangulo(int x1, int y1, int x2, int y2, int x3, int y3, Stroke stroke) {
        super(stroke);  // Pasamos el objeto Stroke al constructor de la clase base
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public String generarSVG() {
        String color = stroke.getColor();  // Accedemos a los métodos de Stroke
        int ancho = stroke.getWidth();     // Accedemos al ancho del trazo

        // Generamos el SVG con los puntos del triángulo
        return String.format("<polygon points=\"%d,%d %d,%d %d,%d\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
                             x1, y1, x2, y2, x3, y3, color, ancho);
    }
}
