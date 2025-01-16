package com.gsd.daw.prog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testValidarIpYClaseIp() {
        byte[] bytesIp = new byte[4];
        assertTrue(App.validarIp("192.168.1.1", bytesIp));
        assertEquals("C", App.ClaseIp(bytesIp));
    }

    @Test
    public void testComillasYEsIntValido() {
        assertTrue(App.tieneComillas("\"123\""));
        assertTrue(App.esIntValido("123"));
    }

    @Test
    public void testSinComillasYNoEsValido() {
        assertFalse(App.tieneComillas("123abc"));
        assertFalse(App.esIntValido("123abc"));
    }
    
    @Test
    public void testValidarSalidaIpValida() {
        // Redirigir System.out
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Llamar al main con argumentos válidos
        String[] args = {"192.168.1.1", "24"};
        App.main(args);

        // Restaurar System.out
        System.setOut(System.out);

        // Verificar salida
        String salida = output.toString();
        assertTrue(salida.contains("192.168.1.1/24"));
        assertTrue(salida.contains("C")); // Clase de IP
        assertTrue(salida.contains("Subnetting:true"));
    }
}

