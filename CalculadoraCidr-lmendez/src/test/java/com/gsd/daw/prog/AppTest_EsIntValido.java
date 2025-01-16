package com.gsd.daw.prog;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppTest_EsIntValido {

    @Test
    public void testNumeroPositivo() {
        assertTrue(App.esIntValido("123"));
    }

    @Test
    public void testCadenaConLetras() {
        assertFalse(App.esIntValido("123a"));
    }

    @Test
    public void testCadenaIniciaConLetras() {
        assertFalse(App.esIntValido("a123"));
    }

    @Test
    public void testCadenaVacia() {
        assertFalse(App.esIntValido(""));
    }

    @Test
    public void testNumeroNegativo() {
        assertTrue(App.esIntValido("-123"));
    }

    @Test
    public void testNumeroDecimal() {
        assertFalse(App.esIntValido("123.45"));
    }
}
