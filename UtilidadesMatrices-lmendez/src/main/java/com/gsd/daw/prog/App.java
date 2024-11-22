package com.gsd.daw.prog;

import com.gsd.daw.prog.matrices.Matriz;

/**
 * Hola Mundo!
 */
public class App {
	/**
	 * Punto de entrada de la aplicación
	 *
	 * @param args
	 *            no se espera ningún parámetro.
	 */
	 public static void main(String[] args) {
	        int[][] matrizValida = {{4, 9, 7 }, {5, 8, 6 }};
	        int[][] matrizValida2 = {{7, 22, 9}, {10, 11, 2}};
	        int[][] matrizIrregular = {{1, 2}, {3, 4, 5}};
	        int[][] matrizVacia = {};
	        int[][] matrizNula = null;

	        System.out.println("Suma de matrices válidas:");
	        System.out.println(Matriz.toString(Matriz.crearSuma(matrizValida, matrizValida2)));

	        System.out.println("Suma de matriz válida = nula:");
	        System.out.println(Matriz.crearSuma(matrizValida, matrizValida2) == null );

	        System.out.println("Transpuesta de matriz válida:");
	        System.out.println(Matriz.toString(Matriz.crearTraspuesta(matrizValida)));

	        System.out.println("Incrementar matriz válida con 5:");
	        Matriz.incrementar(matrizValida, 5);
	        System.out.println(Matriz.toString(matrizValida));

	        System.out.println("Valor máximo de matriz válida:");
	        System.out.println(Matriz.getMaximoElemento(matrizValida2));

	        System.out.println("Posición del valor máximo en matriz válida:");
	        int[] posMax = Matriz.getPosicionMaximoElemento(matrizValida);
	        System.out.println("Fila: " + posMax[0] + ", Columna: " + posMax[1]);

	        System.out.println("Es matriz irregular válida? " + Matriz.isMatrizValida(matrizIrregular));

	        System.out.println("Es matriz vacía válida? " + Matriz.isMatrizValida(matrizVacia));

	        System.out.println("Es matriz nula válida? " + Matriz.isMatrizValida(matrizNula));
	    }
	}
