package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName()); // Logger para registrar mensajes de error

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Se necesitan dos argumentos: fichero de entrada y fichero de salida.");
            System.exit(1); // Salir si no hay suficientes argumentos
        }

        String ficheroEntrada = args[0]; // Nombre del fichero de entrada
        String ficheroSalida = args[1]; // Nombre del fichero de salida

        List<Figura> figuras = new ArrayList<>(); // Lista para almacenar las figuras
        Map<String, Stroke> trazos = new HashMap<>(); // Mapa para almacenar los trazos

        // Leemos el archivo de entrada
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("ST")) {
                    // Parseamos el Stroke
                    parsearTrazo(linea, trazos);
                } else if (linea.startsWith("CI")) {
                    // Parseamos un Circulo
                    figuras.add(parsearCirculo(linea, trazos));
                } else if (linea.startsWith("EL")) {
                    // Parseamos una Elipse
                    figuras.add(parsearElipse(linea, trazos));
                } else if (linea.startsWith("LI")) {
                    // Parseamos una Linea
                    figuras.add(parsearLinea(linea, trazos));
                } else if (linea.startsWith("LP")) {
                    // Parseamos una Linea Poligonal
                    figuras.add(parsearLineaPoligonal(linea, trazos));
                } else if (linea.startsWith("PO")) {
                    // Parseamos un Poligono
                    figuras.add(parsearPoligono(linea, trazos));
                } else if (linea.startsWith("PU")) {
                    // Parseamos un Punto
                    figuras.add(parsearPunto(linea, trazos));
                } else if (linea.startsWith("RE")) {
                    // Parseamos un Rectangulo
                    figuras.add(parsearRectangulo(linea, trazos));
                } else if (linea.startsWith("TR")) {
                    // Parseamos un Triangulo
                    figuras.add(parsearTriangulo(linea, trazos));
                }
            }
        } catch (IOException e) {
            // Captura y registra cualquier excepción de entrada/salida que ocurra al leer el archivo de entrada
            logger.log(Level.SEVERE, "Error al leer el archivo de entrada", e);
        } catch (IllegalArgumentException e) {
            // Captura y registra cualquier excepción de argumento ilegal que ocurra al parsear una línea del archivo de entrada
            logger.log(Level.SEVERE, "Error en el formato de la línea de entrada", e);
        }

        // Escribimos el SVG
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheroSalida))) {
            writer.write("<svg width=\"600\" height=\"600\" xmlns=\"http://www.w3.org/2000/svg\" style=\"border:1px solid black\">\n");
            for (Figura figura : figuras) {
                writer.write(figura.generarSVG() + "\n"); // Usamos generarSVG para cada figura
            }
            writer.write("</svg>");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al escribir el archivo de salida", e);
        }
    }

    // Método para parsear un Stroke
private static void parsearTrazo(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 6) {
        throw new IllegalArgumentException("Formato de línea de Stroke incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    String nombre = partes[1]; // Obtiene el nombre del Stroke
    int rojo = Integer.parseInt(partes[2]); // Convierte el valor del color rojo a entero
    int verde = Integer.parseInt(partes[3]); // Convierte el valor del color verde a entero
    int azul = Integer.parseInt(partes[4]); // Convierte el valor del color azul a entero
    int ancho = Integer.parseInt(partes[5]); // Convierte el valor del ancho a entero
    trazos.put(nombre, new Stroke(nombre, rojo, verde, azul, ancho)); // Crea un nuevo objeto Stroke y lo añade al mapa de trazos
}

// Método para parsear un Circulo
private static Circulo parsearCirculo(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 4) {
        throw new IllegalArgumentException("Formato de línea de Circulo incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    String[] punto = partes[1].split(","); // Divide la parte que contiene las coordenadas del centro
    int cx = Integer.parseInt(punto[0]); // Convierte la coordenada x del centro a entero
    int cy = Integer.parseInt(punto[1]); // Convierte la coordenada y del centro a entero
    int r = Integer.parseInt(partes[2]); // Convierte el radio a entero
    Stroke trazo = trazos.get(partes[3]); // Obtiene el objeto Stroke usando el nombre
    return new Circulo(cx, cy, r, trazo); // Crea y retorna un nuevo objeto Circulo
}

// Método para parsear una Elipse
private static Elipse parsearElipse(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 5) {
        throw new IllegalArgumentException("Formato de línea de Elipse incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    String[] punto = partes[1].split(","); // Divide la parte que contiene las coordenadas del centro
    int cx = Integer.parseInt(punto[0]); // Convierte la coordenada x del centro a entero
    int cy = Integer.parseInt(punto[1]); // Convierte la coordenada y del centro a entero
    int rx = Integer.parseInt(partes[2]); // Convierte el radio x a entero
    int ry = Integer.parseInt(partes[3]); // Convierte el radio y a entero
    Stroke trazo = trazos.get(partes[4]); // Obtiene el objeto Stroke usando el nombre
    return new Elipse(cx, cy, rx, ry, trazo); // Crea y retorna un nuevo objeto Elipse
}

// Método para parsear una Linea
private static Linea parsearLinea(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 4) {
        throw new IllegalArgumentException("Formato de línea de Linea incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    String[] punto1 = partes[1].split(","); // Divide la parte que contiene las coordenadas del primer punto
    int x1 = Integer.parseInt(punto1[0]); // Convierte la coordenada x del primer punto a entero
    int y1 = Integer.parseInt(punto1[1]); // Convierte la coordenada y del primer punto a entero
    String[] punto2 = partes[2].split(","); // Divide la parte que contiene las coordenadas del segundo punto
    int x2 = Integer.parseInt(punto2[0]); // Convierte la coordenada x del segundo punto a entero
    int y2 = Integer.parseInt(punto2[1]); // Convierte la coordenada y del segundo punto a entero
    Stroke trazo = trazos.get(partes[3]); // Obtiene el objeto Stroke usando el nombre
    return new Linea(x1, y1, x2, y2, trazo); // Crea y retorna un nuevo objeto Linea
}

// Método para parsear una Linea Poligonal
private static LineaPoligonal parsearLineaPoligonal(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 3) {
        throw new IllegalArgumentException("Formato de línea de Linea Poligonal incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    List<int[]> puntos = new ArrayList<>(); // Crea una lista para almacenar los puntos
    String[] puntosStr = partes[1].split(","); // Divide la parte que contiene las coordenadas de los puntos
    for (int i = 0; i < puntosStr.length; i += 2) {
        int x = Integer.parseInt(puntosStr[i]); // Convierte la coordenada x a entero
        int y = Integer.parseInt(puntosStr[i + 1]); // Convierte la coordenada y a entero
        puntos.add(new int[]{x, y}); // Añade el punto a la lista
    }
    Stroke trazo = trazos.get(partes[2]); // Obtiene el objeto Stroke usando el nombre
    return new LineaPoligonal(puntos, trazo); // Crea y retorna un nuevo objeto LineaPoligonal
}

// Método para parsear un Poligono
private static Poligono parsearPoligono(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 3) {
        throw new IllegalArgumentException("Formato de línea de Poligono incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    List<int[]> puntos = new ArrayList<>(); // Crea una lista para almacenar los puntos
    String[] puntosStr = partes[1].split(","); // Divide la parte que contiene las coordenadas de los puntos
    for (int i = 0; i < puntosStr.length; i += 2) {
        int x = Integer.parseInt(puntosStr[i]); // Convierte la coordenada x a entero
        int y = Integer.parseInt(puntosStr[i + 1]); // Convierte la coordenada y a entero
        puntos.add(new int[]{x, y}); // Añade el punto a la lista
    }
    Stroke trazo = trazos.get(partes[2]); // Obtiene el objeto Stroke usando el nombre
    return new Poligono(puntos, trazo); // Crea y retorna un nuevo objeto Poligono
}

// Método para parsear un Punto
private static Punto parsearPunto(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 3) {
        throw new IllegalArgumentException("Formato de línea de Punto incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    String[] punto = partes[1].split(","); // Divide la parte que contiene las coordenadas del punto
    int x = Integer.parseInt(punto[0]); // Convierte la coordenada x a entero
    int y = Integer.parseInt(punto[1]); // Convierte la coordenada y a entero
    Stroke trazo = trazos.get(partes[2]); // Obtiene el objeto Stroke usando el nombre
    return new Punto(x, y, trazo); // Crea y retorna un nuevo objeto Punto
}

// Método para parsear un Rectangulo
private static Rectangulo parsearRectangulo(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 5) {
        throw new IllegalArgumentException("Formato de línea de Rectangulo incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    String[] punto = partes[1].split(","); // Divide la parte que contiene las coordenadas del punto superior izquierdo
    int x = Integer.parseInt(punto[0]); // Convierte la coordenada x a entero
    int y = Integer.parseInt(punto[1]); // Convierte la coordenada y a entero
    int ancho = Integer.parseInt(partes[2]); // Convierte el ancho a entero
    int alto = Integer.parseInt(partes[3]); // Convierte el alto a entero
    Stroke trazo = trazos.get(partes[4]); // Obtiene el objeto Stroke usando el nombre
    return new Rectangulo(x, y, ancho, alto, trazo); // Crea y retorna un nuevo objeto Rectangulo
}

// Método para parsear un Triangulo
private static Triangulo parsearTriangulo(String linea, Map<String, Stroke> trazos) {
    String[] partes = linea.split(" "); // Divide la línea en partes usando el espacio como delimitador
    if (partes.length != 5) {
        throw new IllegalArgumentException("Formato de línea de Triangulo incorrecto: " + linea); // Verifica que la línea tenga el formato correcto
    }
    String[] punto1 = partes[1].split(","); // Divide la parte que contiene las coordenadas del primer punto
    int x1 = Integer.parseInt(punto1[0]); // Convierte la coordenada x del primer punto a entero
    int y1 = Integer.parseInt(punto1[1]); // Convierte la coordenada y del primer punto a entero
    String[] punto2 = partes[2].split(","); // Divide la parte que contiene las coordenadas del segundo punto
    int x2 = Integer.parseInt(punto2[0]); // Convierte la coordenada x del segundo punto a entero
    int y2 = Integer.parseInt(punto2[1]); // Convierte la coordenada y del segundo punto a entero
    String[] punto3 = partes[3].split(","); // Divide la parte que contiene las coordenadas del tercer punto
    int x3 = Integer.parseInt(punto3[0]); // Convierte la coordenada x del tercer punto a entero
    int y3 = Integer.parseInt(punto3[1]); // Convierte la coordenada y del tercer punto a entero
    Stroke trazo = trazos.get(partes[4]); // Obtiene el objeto Stroke usando el nombre
    return new Triangulo(x1, y1, x2, y2, x3, y3, trazo); // Crea y retorna un nuevo objeto Triangulo
}
}