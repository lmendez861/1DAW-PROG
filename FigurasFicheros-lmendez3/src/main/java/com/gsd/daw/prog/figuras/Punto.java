package com.gsd.daw.prog.figuras;

public class Punto extends Figura {
    private int x, y;
    // private Stroke stroke;  No es necesario declararlo aquí

    public Punto(int x, int y, Stroke stroke) {
        super(stroke); // Llama al constructor de Figura con el objeto Stroke
        this.x = x;
        this.y = y;
        // this.stroke = stroke; No es necesario inicializarlo aquí
    }

    @Override
    public String generarSVG() {
        return String.format("<circle cx=\"%d\" cy=\"%d\" r=\"2\" stroke=\"%s\" fill=\"none\" />", x, y, this.stroke.toString()); // Usa this.stroke
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}