package com.gsd.daw.prog;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppTest_TieneComillas {

    @Test
    public void testComillasDobles() {
        assertTrue(App.tieneComillas("\"texto\""));
    }

    @Test
    public void testComillasSimples() {
        assertTrue(App.tieneComillas("'texto'"));
    }

    @Test
    public void testSinComillas() {
        assertFalse(App.tieneComillas("texto"));
    }

    @Test
    public void testTextoVacio() {
        assertFalse(App.tieneComillas(""));
    }

    @Test
    public void testComillasDoblesAnidadas() {
        assertTrue(App.tieneComillas("\"'texto'\""));
    }

    @Test
    public void testTextoConEspeciales() {
        assertFalse(App.tieneComillas("text@#$%"));
    }
}


