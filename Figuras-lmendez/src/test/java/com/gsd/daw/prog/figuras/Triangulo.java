package com.gsd.daw.prog.figuras;

public class Triangulo {
    // Atributos
    private final Punto[] puntos; // Array de puntos que definen el triángulo
    private Stroke stroke; // Atributos del trazo (color y grosor)


    public Triangulo(Punto[] puntos) {
        if (puntos == null || puntos.length != 3) {
            throw new IllegalArgumentException("El array de puntos debe contener exactamente 3 puntos.");
        }
        for (Punto punto : puntos) {
            if (punto == null) {
                throw new IllegalArgumentException("Los puntos no pueden ser null.");
            }
        }
        this.puntos = puntos;
        this.stroke = new Stroke("black", 1); // Inicialización por defecto
    }


    public void setStroke(Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("El Stroke no puede ser null.");
        }
        this.stroke = stroke;
    }


    public String toSvg() {
        StringBuilder puntosSvg = new StringBuilder();
        for (Punto punto : puntos) {
            puntosSvg.append(punto.getX()).append(",").append(punto.getY()).append(" ");
        }

        return String.format(
            "<polygon points=\"%s\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
            puntosSvg.toString().trim(), stroke.getColor(), stroke.getWidth()
        );
    }

    @Override
    public String toString() {
        StringBuilder puntosString = new StringBuilder();
        for (Punto punto : puntos) {
            puntosString.append(punto).append(" ");
        }

        return String.format("Triangulo [puntos=[%s], stroke=%s]", puntosString.toString().trim(), stroke);
    }
}
