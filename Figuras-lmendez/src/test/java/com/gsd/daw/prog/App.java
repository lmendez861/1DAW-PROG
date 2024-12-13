package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;

import java.util.Random;

public class App {

    private static final Random random = new Random();

    public App() {
        // Crear un contenedor de tamaño fijo
        Contenedor contenedor = new Contenedor(500, 500);

        // Generar un número aleatorio de figuras
        int numFiguras = random.nextInt(10) + 1;  // Entre 1 y 10 figuras

        for (int i = 0; i < numFiguras; i++) {
            // Generar un tipo de figura aleatorio
            int tipoFigura = random.nextInt(6);  // 6 tipos de figuras

            // Generar valores aleatorios para las figuras
            int x = random.nextInt(400) + 50;
            int y = random.nextInt(400) + 50;
            int tamaño = random.nextInt(50) + 20;  // Tamaño de la figura (entre 20 y 70)

            // Crear figura con atributos aleatorios
            switch (tipoFigura) {
                case 0: // Círculo
                    contenedor.addCirculo(new Circulo(new Punto(x, y), tamaño));
                    break;
                case 1: // Rectángulo
                    contenedor.addRectangulo(new Rectangulo(new Punto(x, y), tamaño, tamaño));
                    break;
                case 2: // Elipse
                    contenedor.addElipse(new Elipse(new Punto(x, y), tamaño, tamaño / 2));
                    break;
                case 3: // Línea
                    contenedor.addLinea(new Linea(new Punto(x, y), new Punto(x + tamaño, y + tamaño)));
                    break;
                case 4: // Polígono (triángulo)
                    Punto[] puntosTriangulo = {
                        new Punto(x, y),
                        new Punto(x + tamaño, y),
                        new Punto(x + tamaño / 2, y - tamaño)
                    };
                    contenedor.addPoligono(new Poligono(puntosTriangulo));
                    break;
                case 5: // Triángulo equilátero
                    contenedor.addPoligono(new TrianguloEquilatero(new Punto(x, y), tamaño));
                    break;
            }

            // Asignar un color aleatorio y grosor de trazo
            Stroke stroke = new Stroke(getRandomColor(), random.nextInt(5) + 1);  // Grosor entre 1 y 5
            contenedor.getFiguras().get(i).setStroke(stroke);
        }

        // Mostrar el código SVG generado para todas las figuras en el contenedor
        System.out.println(contenedor.toSvg());
    }

    public static void main(String[] args) {
        // Ejecutar la aplicación
        new App();
    }

    // Método para generar colores aleatorios en formato RGB
    private String getRandomColor() {
        int r = random.nextInt(256);  // Rojo entre 0 y 255
        int g = random.nextInt(256);  // Verde entre 0 y 255
        int b = random.nextInt(256);  // Azul entre 0 y 255
        return String.format("rgb(%d,%d,%d)", r, g, b);
    }
}
