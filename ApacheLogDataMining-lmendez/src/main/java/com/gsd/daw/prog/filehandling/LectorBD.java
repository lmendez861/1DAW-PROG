package com.gsd.daw.prog.filehandling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Clase que lee logs desde la base de datos Oracle.
 */
public class LectorBD {

    /**
     * Lee todos los registros de la tabla APACHE_LOG_TBL en la base de datos.
     * 
     * @param conexion Conexión activa a la base de datos Oracle.
     * @return Arreglo bidimensional con los datos de la tabla.
     * @throws Exception Si hay un error al consultar la base de datos.
     */
    public static String[][] leerLogsDesdeBD(Connection conexion) throws Exception {
        java.util.List<String[]> registros = new java.util.ArrayList<>();
        String sql = "SELECT IP, TIMESTAMP, REQUEST, RESULT, BYTES, UA FROM APACHE_LOG_TBL";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String[] registro = new String[6];
                registro[0] = rs.getString("IP");
                registro[1] = rs.getString("TIMESTAMP");
                registro[2] = rs.getString("REQUEST");
                registro[3] = rs.getString("RESULT");
                registro[4] = String.valueOf(rs.getInt("BYTES"));
                registro[5] = rs.getString("UA");
                registros.add(registro);
            }
        }
        return registros.toArray(new String[0][]);
    }
}