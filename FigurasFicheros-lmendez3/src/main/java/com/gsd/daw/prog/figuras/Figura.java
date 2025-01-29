package com.gsd.daw.prog.figuras;

public abstract class Figura {
    protected Stroke stroke; // Ahora es un objeto Stroke

    // Constructor de la clase Figura
    public Figura(Stroke stroke) {
        this.stroke = stroke;
    }

    // Método abstracto que deben implementar las clases hijas
    public abstract String generarSVG();

    // Método toSvg que devuelve el SVG generado por la figura
    public String toSvg() {
        // Llamamos al método generarSVG() para obtener el SVG de la figura
        return generarSVG();
    }
}
