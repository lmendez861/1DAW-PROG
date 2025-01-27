package com.gsd.daw.prog.figuras;

/**
 * Hola Mundo!
 */
public class Circulo {

    // Atributos
    private final Punto centro; 
    private final Integer radio; 
    private Stroke stroke;

    
    public Circulo(Punto centro, Integer radio) {
        if (centro == null || radio == null) {
            throw new IllegalArgumentException("El centro y el radio no pueden ser nulos.");
        }
        this.centro = centro;
        this.radio = radio;
        this.stroke = new Stroke("black", 1); // Inicialización por defecto
    }


    public Circulo(int x, int y, int radio2, String color) {
		this.centro = null;
		this.radio = null;
		// TODO Auto-generated constructor stub
	}


	public void setStroke(Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("El Stroke no puede ser nulo.");
        }
        this.stroke = stroke;
    }

    public String toSvg() {
        return String.format(
            "<circle cx=\"%d\" cy=\"%d\" r=\"%d\" stroke=\"%s\" stroke-width=\"%d\" fill=\"none\"/>",
            centro.getX(), centro.getY(), radio, stroke.getColor(), stroke.getWidth()
        );
    }


	
    
}
