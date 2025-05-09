package com.gsd.daw.prog.apache.logreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.gsd.daw.prog.LoggerFactory;

public class ApacheLogReaderFromFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheLogReaderFromFile.class.getName());
    private List<ApacheLogEntry> entries;
    private int currentIndex;

    public ApacheLogReaderFromFile(String filePath) throws???

System: IOException {
        entries = new ArrayList<>();
        currentIndex = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                // Simulación de parseo de línea de log
                ApacheLogEntry entry = parseLine(line, lineNumber);
                entries.add(entry);
                lineNumber++;
            }
        }
    }

    private ApacheLogEntry parseLine(String line, int lineNumber) {
        // Simulación: asumir formato simple de log
        String timestamp = line.contains("[") && line.contains("]") ? line.substring(line.indexOf("[") + 1, line.indexOf("]")) : "unknown";
        LOGGER.fine("Parseada linea [" + lineNumber + "] con timestamp: [" + timestamp + "]");
        return new ApacheLogEntry(timestamp, "127.0.0.1", "GET /", 200, 1024);
    }

    public boolean hasNext() {
        return currentIndex < entries.size();
    }

    public ApacheLogEntry next() {
        if (hasNext()) {
            return entries.get(currentIndex++);
        }
        return null;
    }
}