package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        // Control de excepciones en la ejecución
        try {
            // Leer las figuras desde el archivo
            LectorFiguras lector = new LectorFiguras();
            Object[] figuras = lector.leerFiguras("figuras.txt");

            // Generar el archivo SVG con las figuras leídas
            GeneradorSVG generador = new GeneradorSVG();
            generador.generarSVG(figuras, "output.svg");

            System.out.println("Archivo SVG generado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

