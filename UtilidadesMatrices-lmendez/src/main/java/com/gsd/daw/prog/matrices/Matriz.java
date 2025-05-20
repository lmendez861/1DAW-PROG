package com.gsd.daw.prog.matrices;

public class Matriz {

    public static boolean isMatrizValida(int[][] m) {
        // Verificar si la matriz es nula o vacía
        if (m == null || m.length == 0) {
            return false;
        }
        // Verificar que la primera fila no sea nula para establecer el número de columnas
        if (m[0] == null) {
            return false;
        }
        int numColumnas = m[0].length;
        // Si la primera fila está vacía, consideramos la matriz inválida
        if (numColumnas == 0) {
            return false;
        }
        // Verificar que cada fila no sea nula y tenga el mismo número de columnas
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null || m[i].length != numColumnas) {
                return false;
            }
        }
        return true;
    }

    public static int[][] crearSuma(int[][] a, int[][] b) {
        // Verificar que ambas matrices sean válidas y tengan las mismas dimensiones
        if (!isMatrizValida(a) || !isMatrizValida(b)) {
            return null;
        }
        if (a.length != b.length || a[0].length != b[0].length) {
            return null;
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
        // Verificar que ambas matrices sean válidas y tengan las mismas dimensiones
        if (!isMatrizValida(a) || !isMatrizValida(b)) {
            return;
        }
        if (a.length != b.length || a[0].length != b[0].length) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] += b[i][j];
            }
        }
    }

    public static String toString(int[][] m) {
        // Devolver null si la matriz no es válida
        if (!isMatrizValida(m)) {
            return null;
        }
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
        // Devolver null si la matriz no es válida
        if (!isMatrizValida(m)) {
            return null;
        }
        int[][] transpuesta = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                transpuesta[j][i] = m[i][j];
            }
        }
        return transpuesta;
    }

    public static void incrementar(int[][] m, int inc) {
        // No hacer nada si la matriz no es válida
        if (!isMatrizValida(m)) {
            return;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] += inc;
            }
        }
    }

    public static int[][] crearIncrementada(int[][] m, int inc) {
        // Devolver null si la matriz no es válida
        if (!isMatrizValida(m)) {
            return null;
        }
        int[][] incMatriz = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                incMatriz[i][j] = m[i][j] + inc;
            }
        }
        return incMatriz;
    }

    public static int getMaximoElemento(int[][] m) {
        // Devolver Integer.MIN_VALUE si la matriz no es válida
        if (!isMatrizValida(m)) {
            return Integer.MIN_VALUE;
        }
        int max = m[0][0];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] > max) {
                    max = m[i][j];
                }
            }
        }
        return max;
    }

    public static int[] getPosicionMaximoElemento(int[][] m) {
        // Devolver null si la matriz no es válida
        if (!isMatrizValida(m)) {
            return null;
        }
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