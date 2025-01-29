package com.gsd.daw.prog.figuras;

public class Rectangulo extends Figura {
    private int x, y, width, height;

    public Rectangulo(int x, int y, int width, int height, Stroke stroke) {
        super(stroke);  // Pasamos el objeto Stroke al constructor de la clase base
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String generarSVG() {
        String color = stroke.getColor();  // Accedemos al color del Stroke
        int ancho = stroke.getWidth();     // Accedemos al ancho del Stroke

        // Generamos el SVG del rectángulo
        return String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
                             x, y, width, height, color, ancho);
    }

    @Override
    public String toSvg() {
        return generarSVG();  // Llamamos al método generarSVG() que ya tiene la lógica del SVG.
    }
}
