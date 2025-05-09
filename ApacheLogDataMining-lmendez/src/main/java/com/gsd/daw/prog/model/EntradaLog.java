package com.gsd.daw.prog.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Clase que representa una entrada de log en el modelo de datos.
 */
public class EntradaLog {
    private String ip;
    private String timestamp;
    private String request;
    private String result;
    private int bytes;
    private String userAgent;

    /**
     * Constructor que inicializa una entrada de log con los datos proporcionados.
     * 
     * @param ip Dirección IP del cliente.
     * @param timestamp Fecha y hora del log.
     * @param request Solicitud HTTP realizada.
     * @param result Código de estado HTTP.
     * @param bytes Cantidad de bytes transferidos.
     * @param userAgent Agente de usuario del cliente.
     */
    public EntradaLog(String ip, String timestamp, String request, String result, int bytes, String userAgent) {
        this.ip = ip;
        this.timestamp = timestamp;
        this.request = request;
        this.result = result;
        this.bytes = bytes;
        this.userAgent = userAgent;
    }

    /**
     * Guarda la entrada de log en la base de datos Oracle.
     * 
     * @param conexion Conexión activa a la base de datos.
     * @throws Exception Si hay un error al insertar el registro.
     */
    public void guardarEnBD(Connection conexion) throws Exception {
        String sql = "INSERT INTO APACHE_LOG_TBL (IP, TIMESTAMP, REQUEST, RESULT, BYTES, UA) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, ip);
            stmt.setString(2, timestamp);
            stmt.setString(3, request);
            stmt.setString(4, result);
            stmt.setInt(5, bytes);
            stmt.setString(6, userAgent);
            stmt.executeUpdate();
        }
    }

    /** @return Dirección IP del log. */
    public String obtenerIP() { return ip; }

    /** @return Código de estado del log. */
    public String obtenerResultado() { return result; }
}