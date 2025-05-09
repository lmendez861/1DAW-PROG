package com.gsd.daw.prog;

import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public class LoggerFactory {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger("com.gsd.daw.prog");
        for (java.util.logging.Handler handler : LOGGER.getHandlers()) {
            LOGGER.removeHandler(handler);
        }
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        LOGGER.addHandler(consoleHandler);
        LOGGER.setLevel(Level.INFO);
        LOGGER.setUseParentHandlers(false);
    }

    public static Logger getLogger(String className) {
        return LOGGER;
    }
}