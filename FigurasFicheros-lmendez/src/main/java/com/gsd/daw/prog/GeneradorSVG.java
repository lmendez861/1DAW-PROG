package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorSVG {
    public void generarSVG(Object[] figuras, String nombreArchivo) throws IOException {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\">\n");

            // Generar el SVG para cada figura
            for (Object figura : figuras) {
                if (figura instanceof Linea) {
                    writer.write(((Linea) figura).toSvg() + "\n");
                } else if (figura instanceof LineaPoligonal) {
                    writer.write(((LineaPoligonal) figura).toSvg() + "\n");
                } else if (figura instanceof Rectangulo) {
                    writer.write(((Rectangulo) figura).toSvg() + "\n");
                } else if (figura instanceof Triangulo) {
                    writer.write(((Triangulo) figura).toSvg() + "\n");
                } else {
                    throw new IllegalArgumentException("Tipo de figura no soportado: " + figura.getClass().getSimpleName());
                }
            }

            writer.write("</svg>");
        }
    }
}
