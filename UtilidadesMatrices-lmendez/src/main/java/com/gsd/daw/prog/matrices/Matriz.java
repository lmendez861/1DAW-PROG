package com.gsd.daw.prog.matrices;

public class Matriz {

    public static boolean isMatrizValida(int[][] m) {
        if (m == null || m.length == 0) 
            return false; // Comprueba que la matriz no sea nula ni vacía
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null || m[i].length != m[0].length) 
                return false; // Verifica que cada fila tenga el mismo número de columnas
        }
        return true;
    }

    public static int[][] crearSuma(int[][] a, int[][] b) {
        if (!isMatrizValida(a) || !isMatrizValida(b) || a.length != b.length || a[0].length != b[0].length) {
            return null; // Retorna null si las matrices no son válidas o tienen dimensiones diferentes
        }
        int[][] resultado = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                resultado[i][j] = a[i][j] + b[i][j];
            }
        }
        return resultado;
    }

    public static void sumar(int[][] a, int[][] b) {
        if (!isMatrizValida(a) || !isMatrizValida(b) || a.length != b.length || a[0].length != b[0].length) {
            return; // No realiza ninguna operación si las matrices no son válidas o tienen dimensiones diferentes
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] += b[i][j];
            }
        }
    }

    public static String toString(int[][] m) {
        if (!isMatrizValida(m)) 
            return null; // Retorna null si la matriz no es válida
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                resultado.append(m[i][j]).append(" ");
            }
            resultado.append("\n");
        }
        return resultado.toString();
    }

    public static int[][] crearTraspuesta(int[][] m) {
        if (!isMatrizValida(m)) 
            return null; // Retorna null si la matriz no es válida
        int[][] transpuesta = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                transpuesta[j][i] = m[i][j];
            }
        }
        return transpuesta;
    }

    public static void incrementar(int[][] m, int inc) {
        if (!isMatrizValida(m)) 
            return; // No realiza ninguna operación si la matriz no es válida
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] += inc;
            }
        }
    }

    public static int[][] crearIncrementada(int[][] m, int inc) {
        if (!isMatrizValida(m)) 
            return null; // Retorna null si la matriz no es válida
        int[][] incMatriz = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                incMatriz[i][j] = m[i][j] + inc;
            }
        }
        return incMatriz;
    }

    public static int getMaximoElemento(int[][] m) {
        if (!isMatrizValida(m)) 
            return Integer.MIN_VALUE; // Retorna el valor mínimo de un entero si la matriz no es válida
        int max = m[0][0];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] > max) 
                    max = m[i][j];
            }
        }
        return max;
    }

    public static int[] getPosicionMaximoElemento(int[][] m) {
        if (!isMatrizValida(m)) 
            return null; // Retorna null si la matriz no es válida
        int max = m[0][0];
        int[] posicion = {0, 0};
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] > max) {
                    max = m[i][j];
                    posicion[0] = i;
                    posicion[1] = j;
                }
            }
        }
        return posicion;
    }
}
                                                                                                         
	


	
