package com.gsd.daw.prog.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase que gestiona la conexión a la base de datos Oracle.
 */
public class ConectorBD {

    /**
     * Establece una conexión a una base de datos Oracle usando las credenciales proporcionadas.
     * Soporta tanto SID como nombres de servicio para mayor compatibilidad.
     * 
     * @param ipBD Dirección IP o nombre del host del servidor Oracle (ej. 127.0.0.1).
     * @param nombreBD SID o nombre del servicio de la base de datos (ej. XEPDB1).
     * @param usuarioBD Nombre del usuario de la base de datos (ej. lantolin).
     * @param contraseñaBD Contraseña del usuario de la base de datos (ej. changeme).
     * @return Objeto Connection para interactuar con la base de datos.
     * @throws Exception Si falla la conexión (credenciales incorrectas, servidor no disponible, etc.).
     */
    public static Connection obtenerConexion(String ipBD, String nombreBD, String usuarioBD, String contraseñaBD) throws Exception {
        // Intenta con formato SID
        String urlSID = "jdbc:oracle:thin:@" + ipBD + ":1521:" + nombreBD;
        try {
            return DriverManager.getConnection(urlSID, usuarioBD, contraseñaBD);
        } catch (Exception e) {
            // Si falla con SID, intenta con nombre de servicio
            String urlService = "jdbc:oracle:thin:@//" + ipBD + ":1521/" + nombreBD;
            return DriverManager.getConnection(urlService, usuarioBD, contraseñaBD);
        }
    }
}