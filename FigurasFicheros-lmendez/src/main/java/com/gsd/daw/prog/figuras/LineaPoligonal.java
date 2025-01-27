package com.gsd.daw.prog.figuras;

public class LineaPoligonal {
    // Atributos
    private final Punto[] puntos;  // Array de puntos que definen la línea poligonal
    private Stroke stroke;         // Atributos del trazo (color y grosor)

    // Constructor que toma un array de puntos
    public LineaPoligonal(Punto[] puntos) {
        if (puntos == null || puntos.length == 0) {
            throw new IllegalArgumentException("El array de puntos no puede ser null ni estar vacío.");
        }
        for (Punto punto : puntos) {
            if (punto == null) {
                throw new IllegalArgumentException("Los puntos no pueden ser null.");
            }
        }
        this.puntos = puntos;
        this.stroke = new Stroke("black", 1); // Inicialización por defecto
    }

    // Establece el color y el grosor del trazo
    public void setStroke(Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("El Stroke no puede ser null.");
        }
        this.stroke = stroke;
    }

    // Genera el código SVG de la línea poligonal
    public String toSvg() {
        StringBuilder puntosSvg = new StringBuilder();
        for (Punto punto : puntos) {
            puntosSvg.append(punto.getX()).append(",").append(punto.getY()).append(" ");
        }

        return String.format(
            "<polyline points=\"%s\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
            puntosSvg.toString().trim(), stroke.getColor(), stroke.getWidth()
        );
    }

    @Override
    public String toString() {
        StringBuilder puntosString = new StringBuilder();
        for (Punto punto : puntos) {
            puntosString.append(punto).append(" ");
        }

        return String.format("LineaPoligonal [puntos=[%s], stroke=%s]", puntosString.toString().trim(), stroke);
    }
}
