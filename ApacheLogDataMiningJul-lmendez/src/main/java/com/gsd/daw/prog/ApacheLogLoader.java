package com.gsd.daw.prog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gsd.daw.prog.apache.logreader.ApacheLogReaderFromFile;
import com.gsd.daw.prog.apache.logreader.ApacheLogEntry;

public class ApacheLogLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheLogLoader.class.getName());

    public static void main(String[] args) {
        configureLoggingLevel();

        if (args.length != 5) {
            LOGGER.severe("Se requieren 5 argumentos: ddbbIp, ddbbName, ddbbUser, ddbbPasswd, file");
            return;
        }

        LOGGER.fine("Argumentos de linea de comandos: 1:ddbbIp->[" + args[0] + "] 2:ddbbName->[" + args[1] +
                    "] 3:ddbbUser->[" + args[2] + "] 4:ddbbPasswd->[XXXXXX] 5:file->[" + args[4] + "]");

        try {
            LOGGER.info("Comienza lectura del fichero de datos");
            List<ApacheLogEntry> entries = readLogFile(args[4]);
            LOGGER.info("Leidos [" + entries.size() + "] lineas del fichero");

            LOGGER.info("Comienza creacion de estructuras del modelo");
            List<ApacheLogEntry> modelObjects = createModelObjects(entries);
            LOGGER.info("Creados [" + modelObjects.size() + "] objetos del modelo");

            LOGGER.info("Comienza guardado de objetos en BBDD");
            int insertedRows = saveToDatabase(args[0], args[1], args[2], args[3], modelObjects);
            LOGGER.info("Insertadas [" + insertedRows + "] filas en BBDD");

        } catch (IOException e) {
            LOGGER.severe("Fichero no encontrado [" + args[4] + "]. Excepción: [" + e.getMessage() + "]");
        } catch (SQLException e) {
            LOGGER.severe("Excepción SQL: " + e.getMessage());
        }
    }

    private static void configureLoggingLevel() {
        String logLevel = System.getenv("LOG_LEVEL");
        if (logLevel != null && !logLevel.isEmpty()) {
            try {
                Level level = Level.parse(logLevel.toUpperCase());
                LOGGER.setLevel(level);
                for (java.util.logging.Handler handler : LOGGER.getHandlers()) {
                    handler.setLevel(level);
                }
                System.out.println("LogLevel forzado a [" + level + "]");
            } catch (IllegalArgumentException e) {
                System.err.println("Nivel de log inválido: [" + logLevel + "]. Se mantiene el nivel por defecto.");
            }
        }
    }

    private static List<ApacheLogEntry> readLogFile(String filePath) throws IOException {
        ApacheLogReaderFromFile reader = new ApacheLogReaderFromFile(filePath);
        List<ApacheLogEntry> entries = new ArrayList<>();
        int lineNumber = 0;

        while (reader.hasNext()) {
            ApacheLogEntry entry = reader.next();
            LOGGER.fine("Parseada linea [" + lineNumber + "] con timestamp: [" + entry.getTimestamp() + "]");
            entries.add(entry);
            lineNumber++;
        }

        return entries;
    }

    private static List<ApacheLogEntry> createModelObjects(List<ApacheLogEntry> entries) {
        return new ArrayList<>(entries);
    }

    private static int saveToDatabase(String dbIp, String dbName, String dbUser, String dbPasswd, List<ApacheLogEntry> entries)
            throws SQLException {
        String url = "jdbc:oracle:thin:@" + dbIp + ":1521:" + dbName;
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPasswd)) {
            String sql = "INSERT INTO apache_logs (timestamp, client_ip, request, status, bytes) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                int lineNumber = 0;
                for (ApacheLogEntry entry : entries) {
                    LOGGER.fine("Insertando linea [" + lineNumber + "] con timestamp: [" + entry.getTimestamp() + "]");
                    stmt.setString(1, entry.getTimestamp());
                    stmt.setString(2, entry.getClientIp());
                    stmt.setString(3, entry.getRequest());
                    stmt.setInt(4, entry.getStatus());
                    stmt.setLong(5, entry.getBytes());
                    stmt.executeUpdate();
                    lineNumber++;
                }
            }
            return entries.size();
        }
    }
}