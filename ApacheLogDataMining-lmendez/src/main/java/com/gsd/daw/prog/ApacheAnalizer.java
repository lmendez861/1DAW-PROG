package com.gsd.daw.prog;

import com.gsd.daw.prog.analysis.AnalizadorLog;
import com.gsd.daw.prog.filehandling.LectorBD;
import com.gsd.daw.prog.model.ConversorLog;
import com.gsd.daw.prog.model.EntradaLog;
import com.gsd.daw.prog.util.ConectorBD;
import java.sql.Connection;
import java.util.Map;

/**
 * Clase principal que analiza logs almacenados en la base de datos Oracle.
 */
public class ApacheAnalizer {

    /**
     * Método principal que ejecuta el análisis de logs.
     * Lee datos de la base de datos y muestra frecuencias de IPs y códigos de estado.
     * 
     * @param args Argumentos de entrada: IP_BD, Nombre_BD, Usuario_BD, Contraseña_BD
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Uso: ApacheAnalizer <IP_BD> <Nombre_BD> <Usuario_BD> <Contraseña_BD>");
            System.exit(1);
        }

        String ipBD = args[0];
        String nombreBD = args[1];
        String usuarioBD = args[2];
        String contraseñaBD = args[3];

        try (Connection conexion = ConectorBD.obtenerConexion(ipBD, nombreBD, usuarioBD, contraseñaBD)) {
            System.out.println("INFO: Conectado a la base de datos.");

            String[][] datos = LectorBD.leerLogsDesdeBD(conexion);
            System.out.println("INFO: Leídas [" + datos.length + "] líneas de la base de datos.");

            EntradaLog[] logs = ConversorLog.convertirAModelo(datos);
            System.out.println("INFO: Creados [" + logs.length + "] objetos del modelo.");

            Map<String, Integer> frecuenciaIPs = AnalizadorLog.obtenerFrecuenciaIPs(logs);
            System.out.println("Listado de IPs que aparecen 10 o más veces:");
            frecuenciaIPs.entrySet().stream()
                .filter(entrada -> entrada.getValue() >= 10)
                .forEach(entrada -> System.out.println(entrada.getKey() + ": " + entrada.getValue()));

            Map<String, Integer> frecuenciaCodigos = AnalizadorLog.obtenerFrecuenciaCodigosEstado(logs);
            System.out.println("Listado de cuántas veces aparece cada código de estado:");
            frecuenciaCodigos.forEach((codigo, cantidad) -> System.out.println(codigo + ": " + cantidad));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}