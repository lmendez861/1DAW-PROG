package com.gsd.daw.prog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorFichero {
    public void escribirSVG(String ruta, String contenido) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(contenido);
        }
    }
}
