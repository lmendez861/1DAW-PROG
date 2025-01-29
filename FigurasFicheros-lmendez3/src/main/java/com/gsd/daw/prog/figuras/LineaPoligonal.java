package com.gsd.daw.prog.figuras;

import java.util.List;

public class LineaPoligonal extends Figura {
    private List<int[]> puntos;
    // private Stroke stroke;  Ya no necesitas declarar stroke aquí, está en Figura

    public LineaPoligonal(List<int[]> puntos, Stroke stroke) {
        super(stroke); // Llama al constructor de Figura con el objeto Stroke
        this.puntos = puntos;
        // this.stroke = stroke; Ya no es necesario, se inicializa en el super()
    }

    @Override
    public String generarSVG() {
        StringBuilder puntosStr = new StringBuilder();
        for (int[] punto : puntos) {
            puntosStr.append(punto[0]).append(",").append(punto[1]).append(" ");
        }
        // Accede al stroke a través de la clase padre:
        return String.format("<polyline points=\"%s\" stroke=\"%s\" fill=\"none\" />", puntosStr.toString(), this.stroke.toString());
    }
}
