package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorFiguras {
    public Object[] leerFiguras(String nombreArchivo) throws IOException {
        Object[] figuras = new Object[10]; // Tamaño inicial del array
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[0].equalsIgnoreCase("Linea")) {
                    int x1 = Integer.parseInt(partes[1]);
                    int y1 = Integer.parseInt(partes[2]);
                    int x2 = Integer.parseInt(partes[3]);
                    int y2 = Integer.parseInt(partes[4]);
                    figuras[index++] = new Linea(new Punto(x1, y1), new Punto(x2, y2));
                } else if (partes[0].equalsIgnoreCase("LineaPoligonal")) {
                    Punto[] puntos = new Punto[(partes.length - 1) / 2];
                    for (int i = 1; i < partes.length; i += 2) {
                        puntos[(i - 1) / 2] = new Punto(Integer.parseInt(partes[i]), Integer.parseInt(partes[i + 1]));
                    }
                    figuras[index++] = new LineaPoligonal(puntos);
                }
                // Redimensionar si es necesario
                if (index == figuras.length) {
                    Object[] nuevasFiguras = new Object[figuras.length * 2];
                    System.arraycopy(figuras, 0, nuevasFiguras, 0, figuras.length);
                    figuras = nuevasFiguras;
                }
            }
        }

        Object[] figurasFinales = new Object[index];
        System.arraycopy(figuras, 0, figurasFinales, 0, index);
        return figurasFinales;
    }
}
