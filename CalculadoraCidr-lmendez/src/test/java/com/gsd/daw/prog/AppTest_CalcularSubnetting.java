package com.gsd.daw.prog;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppTest_CalcularSubnetting {

    @Test
    public void testMascaraValidaClaseA() {
        assertTrue(App.calcularSubnetting(8, "A"));
    }

    @Test
    public void testMascaraInvalidaClaseA() {
        assertFalse(App.calcularSubnetting(7, "A"));
    }

    @Test
    public void testMascaraValidaClaseB() {
        assertTrue(App.calcularSubnetting(16, "B"));
    }

    @Test
    public void testMascaraInvalidaClaseB() {
        assertFalse(App.calcularSubnetting(15, "B"));
    }

    @Test
    public void testMascaraInvalidaClaseC() {
        assertFalse(App.calcularSubnetting(23, "C"));
    }

    @Test
    public void testMascaraValidaClaseD() {
        assertTrue(App.calcularSubnetting(32, "D"));
    }

    @Test
    public void testMascaraValidaClaseE() {
        assertTrue(App.calcularSubnetting(32, "E"));
    }
    
    @Test
    public void testCalcularSubnettingConBitsInvalidos() {
        // Caso cuando los bits están fuera del rango válido
        assertFalse(App.calcularSubnetting(-1, "A")); // Bits negativos
        assertFalse(App.calcularSubnetting(33, "A")); // Bits mayores a 32
    }

    @Test
    public void testCalcularSubnettingConClaseA() {
        // Clase A necesita al menos 8 bits
        assertFalse(App.calcularSubnetting(7, "A")); // Menos de 8 bits
    }

    @Test
    public void testCalcularSubnettingConClaseB() {
        // Clase B necesita al menos 16 bits
        assertFalse(App.calcularSubnetting(15, "B")); // Menos de 16 bits
    }

    @Test
    public void testCalcularSubnettingConClaseC() {
        // Clase C necesita al menos 24 bits
        assertFalse(App.calcularSubnetting(23, "C")); // Menos de 24 bits
    }
}
