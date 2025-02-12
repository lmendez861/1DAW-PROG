package com.gsd.daw.prog.figuras;

import java.util.List;

public class Contenedor extends Figura {
    private List<Figura> figuras;  // Lista de figuras dentro del contenedor

    public Contenedor(List<Figura> figuras, Stroke stroke) {
        super(stroke);  // Pasamos el objeto Stroke al constructor de la clase base
        this.figuras = figuras;
    }

    @Override
    public String generarSVG() {
        StringBuilder svgBuilder = new StringBuilder();

        // Iteramos sobre todas las figuras contenidas y las convertimos a SVG
        for (Figura figura : figuras) {
            svgBuilder.append(figura.generarSVG()).append("\n");
        }

        return svgBuilder.toString();
    }

    @Override
    public String toSvg() {
        return generarSVG();  // Como generamos SVG en generarSVG(), solo lo llamamos aquí.
    }
}

