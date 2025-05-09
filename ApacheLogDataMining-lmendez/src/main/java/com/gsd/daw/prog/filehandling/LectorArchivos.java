package com.gsd.daw.prog.filehandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que lee logs desde un archivo CSV en formato Combined Log Format.
 */
public class LectorArchivos {

    private static final String LOG_ENTRY_PATTERN = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\S+) \"(.*?)\" \"(.*?)\"$";
    private static final Pattern pattern = Pattern.compile(LOG_ENTRY_PATTERN);

    /**
     * Lee un archivo de logs y devuelve sus líneas como un arreglo bidimensional.
     * 
     * @param nombreArchivo Ruta del archivo CSV a leer.
     * @return Arreglo bidimensional con los datos del archivo (cada fila es un arreglo de 6 campos).
     * @throws Exception Si el archivo no se encuentra o hay un error al leerlo.
     */
    public static String[][] leerArchivoLog(String nombreArchivo) throws Exception {
        ArrayList<String[]> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = parseLogLineString(linea);
                if (campos != null) {
                    lineas.add(campos);
                }
            }
        }
        return lineas.toArray(new String[0][]);
    }

    /**
     * Divide una línea de log en sus componentes usando una expresión regular.
     * 
     * @param line Línea de log en formato Combined Log Format.
     * @return Arreglo de 6 elementos con los campos del log, o null si no coincide.
     */
    private static String[] parseLogLineString(String line) {
        String[] res = new String[6];
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            res[0] = matcher.group(1); // IP
            res[1] = matcher.group(4); // Timestamp
            res[2] = matcher.group(5); // Request
            res[3] = matcher.group(6); // Result
            res[4] = matcher.group(7); // Bytes
            res[5] = matcher.group(9); // User Agent
            return res;
        }
        return null;
    }
}