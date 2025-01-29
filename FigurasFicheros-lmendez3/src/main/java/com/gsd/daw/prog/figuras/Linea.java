package com.gsd.daw.prog.figuras;

public class Linea extends Figura {
    private int x1, y1, x2, y2;

    public Linea(int x1, int y1, int x2, int y2, Stroke stroke) {
        super(stroke);  // Pasamos el objeto Stroke al constructor de la clase base
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String generarSVG() {
        String color = stroke.getColor();  // Accedemos al color del Stroke
        int ancho = stroke.getWidth();     // Accedemos al ancho del Stroke

        // Generamos el SVG de la línea
        return String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"%s\" stroke-width=\"%d\"/>",
                             x1, y1, x2, y2, color, ancho);
    }

    @Override
    public String toSvg() {
        return generarSVG();  // Llamamos al método generarSVG() que ya tiene la lógica del SVG.
    }
}
