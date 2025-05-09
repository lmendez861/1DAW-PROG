package com.gsd.daw.prog.model;

/**
 * Clase que convierte datos crudos de logs en objetos del modelo.
 */
public class ConversorLog {

    /**
     * Convierte un arreglo bidimensional de datos en un arreglo de objetos EntradaLog.
     * 
     * @param datos Arreglo bidimensional con las líneas del archivo o registros de la base de datos.
     * @return Arreglo de objetos EntradaLog creados a partir de los datos.
     */
    public static EntradaLog[] convertirAModelo(String[][] datos) {
        EntradaLog[] logs = new EntradaLog[datos.length];
        for (int i = 0; i < datos.length; i++) {
            String ip = datos[i][0];
            String timestamp = datos[i][1];
            String request = datos[i][2];
            String result = datos[i][3];
            int bytes = datos[i][4].equals("-") ? 0 : Integer.parseInt(datos[i][4]);
            String userAgent = datos[i][5];
            logs[i] = new EntradaLog(ip, timestamp, request, result, bytes, userAgent);
        }
        return logs;
    }
}