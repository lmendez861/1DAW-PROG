package com.gsd.daw.prog.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase que gestiona la conexión a la base de datos Oracle.
 */
public class ConectorBD {

    /**
     * Establece una conexión a una base de datos Oracle local usando las credenciales proporcionadas.
     * 
     * @param ipBD Dirección IP o nombre del host donde está el servidor Oracle (ej. localhost o 192.168.1.100).
     * @param nombreBD SID o nombre del servicio de la base de datos (ej. orcl o xe).
     * @param usuarioBD Nombre del usuario de la base de datos (ej. system).
     * @param contraseñaBD Contraseña del usuario de la base de datos.
     * @return Objeto Connection para interactuar con la base de datos.
     * @throws Exception Si falla la conexión (credenciales incorrectas, servidor no disponible, etc.).
     */
    public static Connection obtenerConexion(String ipBD, String nombreBD, String usuarioBD, String contraseñaBD) throws Exception {
        String url = "jdbc:oracle:thin:@" + ipBD + ":1521:" + nombreBD; // Formato de la URL de conexión a Oracle.
        return DriverManager.getConnection(url, usuarioBD, contraseñaBD);
    }
}