package com.gsd.daw.prog.analysis;

import com.gsd.daw.prog.model.EntradaLog;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que analiza logs y calcula frecuencias de IPs y códigos de estado.
 */
public class AnalizadorLog {

    /**
     * Calcula la frecuencia de aparición de cada IP en un arreglo de logs.
     * 
     * @param logs Arreglo de objetos EntradaLog que contienen los datos de los logs.
     * @return Mapa con las IPs como claves y su frecuencia como valores.
     */
    public static Map<String, Integer> obtenerFrecuenciaIPs(EntradaLog[] logs) {
        Map<String, Integer> frecuencias = new HashMap<>();
        for (EntradaLog log : logs) {
            String ip = log.obtenerIP();
            frecuencias.put(ip, frecuencias.getOrDefault(ip, 0) + 1);
        }
        return frecuencias;
    }

    /**
     * Calcula la frecuencia de aparición de cada código de estado en un arreglo de logs.
     * 
     * @param logs Arreglo de objetos EntradaLog que contienen los datos de los logs.
     * @return Mapa con los códigos de estado como claves y su frecuencia como valores.
     */
    public static Map<String, Integer> obtenerFrecuenciaCodigosEstado(EntradaLog[] logs) {
        Map<String, Integer> frecuencias = new HashMap<>();
        for (EntradaLog log : logs) {
            String codigo = log.obtenerResultado();
            frecuencias.put(codigo, frecuencias.getOrDefault(codigo, 0) + 1);
        }
        return frecuencias;
    }
}