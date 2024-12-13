package com.gsd.daw.prog.figuras;

public class Contenedor {
    private final Integer ancho;
    private final Integer alto;
    private final StringBuilder svgFiguras; 


    public Contenedor(Integer ancho, Integer alto) {
        if (ancho == null || alto == null) {
            throw new IllegalArgumentException("El ancho y el alto no pueden ser null.");
        }
        this.ancho = ancho;
        this.alto = alto;
        this.svgFiguras = new StringBuilder(); // Inicializa el StringBuilder vacío
    }


    public void addCirculo(Circulo figura) {
        if (figura == null) {
            throw new IllegalArgumentException("La figura Circulo no puede ser null.");
        }
        svgFiguras.append(figura.toSvg()).append("\n");
    }


    public void addElipse(Elipse figura) {
        if (figura == null) {
            throw new IllegalArgumentException("La figura Elipse no puede ser null.");
        }
        svgFiguras.append(figura.toSvg()).append("\n");
    }


    public void addLinea(Linea figura) {
        if (figura == null) {
            throw new IllegalArgumentException("La figura Linea no puede ser null.");
        }
        svgFiguras.append(figura.toSvg()).append("\n");
    }


    public void addLineaPoligonal(LineaPoligonal figura) {
        if (figura == null) {
            throw new IllegalArgumentException("La figura LineaPoligonal no puede ser null.");
        }
        svgFiguras.append(figura.toSvg()).append("\n");
    }


    public void addPoligono(Poligono figura) {
        if (figura == null) {
            throw new IllegalArgumentException("La figura Poligono no puede ser null.");
        }
        svgFiguras.append(figura.toSvg()).append("\n");
    }

    public void addRectangulo(Rectangulo figura) {
        if (figura == null) {
            throw new IllegalArgumentException("La figura Rectangulo no puede ser null.");
        }
        svgFiguras.append(figura.toSvg()).append("\n");
    }


    public String toSvg() {
        StringBuilder svg = new StringBuilder();
        // Apertura del contenedor SVG
        svg.append(String.format("<svg width=\"%d\" height=\"%d\" xmlns=\"http://www.w3.org/2000/svg\">\n", ancho, alto));
        // Borde del contenedor para depuración
        svg.append(String.format("<rect width=\"%d\" height=\"%d\" fill=\"none\" stroke=\"black\" stroke-width=\"1\" />\n", ancho, alto));
        // Añadir las figuras directamente desde el StringBuilder
        svg.append(svgFiguras);
        // Cierre del contenedor
        svg.append("</svg>");
        return svg.toString();
    }
}

