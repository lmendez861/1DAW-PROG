package com.gsd.daw.prog.filehandling;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Clase que lee logs desde un archivo CSV.
 */
public class LectorArchivos {

    /**
     * Lee un archivo de logs y devuelve sus líneas como un arreglo bidimensional.
     * 
     * @param nombreArchivo Ruta del archivo CSV a leer.
     * @return Arreglo bidimensional con los datos del archivo (cada fila es un arreglo de campos).
     * @throws Exception Si el archivo no se encuentra o hay un error al leerlo.
     */
    public static String[][] leerArchivoLog(String nombreArchivo) throws Exception {
        java.util.List<String[]> lineas = new java.util.ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(" ", 8); // Divide en 8 partes máximo para respetar el formato
                lineas.add(campos);
            }
        }
        return lineas.toArray(new String[0][]);
    }
}