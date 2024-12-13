package com.gsd.daw.prog.figuras;

public class TrianguloEquilatero {
    // Atributos
    private final Punto origen;  
    private final Integer tamanoLado;  
    private Stroke stroke;  


    public TrianguloEquilatero(Punto origen, Integer tamanoLado) {
        if (origen == null || tamanoLado == null || tamanoLado <= 0) {
            throw new IllegalArgumentException("El origen no puede ser null y el tamaño del lado debe ser mayor que cero.");
        }
        this.origen = origen;
        this.tamanoLado = tamanoLado;
        this.stroke = new Stroke("black", 1); // Inicialización por defecto
    }


    public void setStroke(Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("El Stroke no puede ser null.");
        }
        this.stroke = stroke;
    }


    public String toSvg() {
        int x1 = origen.getX();
        int y1 = origen.getY();

        int x2 = x1 + tamanoLado;
        int y2 = y1;

        int x3 = x1 + tamanoLado / 2;
        int y3 = y1 - (int) (Math.sqrt(3) / 2 * tamanoLado);  // Altura del triángulo equilátero

        String puntosSvg = String.format("%d,%d %d,%d %d,%d", x1, y1, x2, y2, x3, y3);

        return String.format(
            "<polygon points=\"%s\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
            puntosSvg, stroke.getColor(), stroke.getWidth()
        );
    }

    @Override
    public String toString() {
        return String.format("TrianguloEquilatero [origen=%s, tamanoLado=%d, stroke=%s]", 
                origen, tamanoLado, stroke);
    }
}
