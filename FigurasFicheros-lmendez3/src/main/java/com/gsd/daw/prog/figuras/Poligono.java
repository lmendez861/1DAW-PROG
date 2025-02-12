package com.gsd.daw.prog.figuras;

import java.util.List;

public class Poligono extends Figura {
    private List<int[]> puntos;
    // private Stroke stroke;  No es necesario declararlo aquí

    public Poligono(List<int[]> puntos, Stroke stroke) {
        super(stroke); // Llama al constructor de Figura con el objeto Stroke
        this.puntos = puntos;
        // this.stroke = stroke;  No es necesario inicializarlo aquí
    }

    @Override
    public String generarSVG() {
        StringBuilder puntosStr = new StringBuilder();
        for (int[] punto : puntos) {
            puntosStr.append(punto[0]).append(",").append(punto[1]).append(" ");
        }
        return String.format("<polygon points=\"%s\" stroke=\"%s\" fill=\"none\" />", puntosStr.toString(), this.stroke.toString()); // Usa this.stroke
    }
}