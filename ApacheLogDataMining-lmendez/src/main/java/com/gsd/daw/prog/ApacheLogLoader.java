package com.gsd.daw.prog;

import com.gsd.daw.prog.filehandling.LectorArchivos;
import com.gsd.daw.prog.model.ConversorLog;
import com.gsd.daw.prog.model.EntradaLog;
import com.gsd.daw.prog.util.ConectorBD;
import java.sql.Connection;

/**
 * Clase principal que carga logs desde un archivo y los inserta en la base de datos Oracle.
 */
public class ApacheLogLoader {

    /**
     * Método principal que ejecuta el proceso de carga de logs.
     * Lee un archivo CSV, convierte sus líneas en objetos y los guarda en la base de datos.
     * 
     * @param args Argumentos de entrada: IP_BD, Nombre_BD, Usuario_BD, Contraseña_BD, Archivo_CSV
     */
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Uso: ApacheLogLoader <IP_BD> <Nombre_BD> <Usuario_BD> <Contraseña_BD> <Archivo_CSV>");
            System.exit(1);
        }

        String ipBD = args[0];
        String nombreBD = args[1];
        String usuarioBD = args[2];
        String contraseñaBD = args[3];
        String nombreArchivo = args[4];

        try (Connection conexion = ConectorBD.obtenerConexion(ipBD, nombreBD, usuarioBD, contraseñaBD)) {
            System.out.println("INFO: Conectado a la base de datos.");

            String[][] datos = LectorArchivos.leerArchivoLog(nombreArchivo);
            System.out.println("INFO: Leídas [" + datos.length + "] líneas del archivo.");

            EntradaLog[] logs = ConversorLog.convertirAModelo(datos);
            System.out.println("INFO: Creados [" + logs.length + "] objetos del modelo.");

            for (int i = 0; i < logs.length; i++) {
                logs[i].guardarEnBD(conexion);
                System.out.println("INFO: Insertadas [" + (i + 1) + "] filas en la base de datos.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}