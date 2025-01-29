package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Se necesitan dos argumentos: fichero de entrada y fichero de salida.");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        List<Figura> figuras = new ArrayList<>();
        Map<String, Stroke> strokes = new HashMap<>();

        // Leemos el archivo de entrada
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("ST")) {
                    // Parseamos el Stroke
                    parseStroke(line, strokes);
                } else if (line.startsWith("CI")) {
                    // Parseamos un Circulo
                    figuras.add(parseCirculo(line, strokes));
                } else if (line.startsWith("EL")) {
                    // Parseamos una Elipse
                    figuras.add(parseElipse(line, strokes));
                } else if (line.startsWith("LI")) {
                    // Parseamos una Linea
                    figuras.add(parseLinea(line, strokes));
                } else if (line.startsWith("LP")) {
                    // Parseamos una Linea Poligonal
                    figuras.add(parseLineaPoligonal(line, strokes));
                } else if (line.startsWith("PO")) {
                    // Parseamos un Poligono
                    figuras.add(parsePoligono(line, strokes));
                } else if (line.startsWith("PU")) {
                    // Parseamos un Punto
                    figuras.add(parsePunto(line, strokes));
                } else if (line.startsWith("RE")) {
                    // Parseamos un Rectangulo
                    figuras.add(parseRectangulo(line, strokes));
                } else if (line.startsWith("TR")) {
                    // Parseamos un Triangulo
                    figuras.add(parseTriangulo(line, strokes));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escribimos el SVG
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("<svg width=\"600\" height=\"600\" xmlns=\"http://www.w3.org/2000/svg\" style=\"border:1px solid black\">\n");
            for (Figura figura : figuras) {
                writer.write(figura.generarSVG() + "\n"); // Usamos generarSVG
            }
            writer.write("</svg>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseStroke(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        String nombre = parts[1];
        int rojo = Integer.parseInt(parts[2]);
        int verde = Integer.parseInt(parts[3]);
        int azul = Integer.parseInt(parts[4]);
        int ancho = Integer.parseInt(parts[5]);
        strokes.put(nombre, new Stroke(nombre, rojo, verde, azul, ancho));
    }

    private static Circulo parseCirculo(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        String[] punto = parts[1].split(",");
        int cx = Integer.parseInt(punto[0]);
        int cy = Integer.parseInt(punto[1]);
        int r = Integer.parseInt(parts[2]);
        Stroke stroke = strokes.get(parts[3]); // Obtener el objeto Stroke usando el nombre
        return new Circulo(cx, cy, r, stroke); // Pasar el objeto Stroke
    }

    private static Elipse parseElipse(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        String[] punto = parts[1].split(",");
        int cx = Integer.parseInt(punto[0]);
        int cy = Integer.parseInt(punto[1]);
        int rx = Integer.parseInt(parts[2]);
        int ry = Integer.parseInt(parts[3]);
        Stroke stroke = strokes.get(parts[4]); // Obtener el objeto Stroke
        return new Elipse(cx, cy, rx, ry, stroke); // Pasar el objeto Stroke
    }

    private static Linea parseLinea(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        String[] punto1 = parts[1].split(",");
        int x1 = Integer.parseInt(punto1[0]);
        int y1 = Integer.parseInt(punto1[1]);
        String[] punto2 = parts[2].split(",");
        int x2 = Integer.parseInt(punto2[0]);
        int y2 = Integer.parseInt(punto2[1]);
        Stroke stroke = strokes.get(parts[3]); // Obtener el objeto Stroke
        return new Linea(x1, y1, x2, y2, stroke);
    }

    private static LineaPoligonal parseLineaPoligonal(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        List<int[]> puntos = new ArrayList<>();
        String[] puntosStr = parts[1].split(",");
        for (int i = 0; i < puntosStr.length; i += 2) {
            int x = Integer.parseInt(puntosStr[i]);
            int y = Integer.parseInt(puntosStr[i + 1]);
            puntos.add(new int[]{x, y});
        }
        Stroke stroke = strokes.get(parts[2]);
        return new LineaPoligonal(puntos, stroke);
    }

    private static Poligono parsePoligono(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        List<int[]> puntos = new ArrayList<>();
        String[] puntosStr = parts[1].split(",");
        for (int i = 0; i < puntosStr.length; i += 2) {
            int x = Integer.parseInt(puntosStr[i]);
            int y = Integer.parseInt(puntosStr[i + 1]);
            puntos.add(new int[]{x, y});
        }
        Stroke stroke = strokes.get(parts[2]);
        return new Poligono(puntos, stroke);
    }

    private static Punto parsePunto(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        String[] punto = parts[1].split(",");
        int x = Integer.parseInt(punto[0]);
        int y = Integer.parseInt(punto[1]);
        Stroke stroke = strokes.get(parts[2]);
        return new Punto(x, y, stroke);
    }

    private static Rectangulo parseRectangulo(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        String[] punto = parts[1].split(",");
        int x = Integer.parseInt(punto[0]);
        int y = Integer.parseInt(punto[1]);
        int width = Integer.parseInt(parts[2]);
        int height = Integer.parseInt(parts[3]);
        Stroke stroke = strokes.get(parts[4]);
        return new Rectangulo(x, y, width, height, stroke);
    }

    private static Triangulo parseTriangulo(String line, Map<String, Stroke> strokes) {
        String[] parts = line.split(" ");
        String[] punto1 = parts[1].split(",");
        int x1 = Integer.parseInt(punto1[0]);
        int y1 = Integer.parseInt(punto1[1]);
        String[] punto2 = parts[2].split(",");
        int x2 = Integer.parseInt(punto2[0]);
        int y2 = Integer.parseInt(punto2[1]);
        String[] punto3 = parts[3].split(",");
        int x3 = Integer.parseInt(punto3[0]);
        int y3 = Integer.parseInt(punto3[1]);
        Stroke stroke = strokes.get(parts[4]);
        return new Triangulo(x1, y1, x2, y2, x3, y3, stroke);
    }
}

