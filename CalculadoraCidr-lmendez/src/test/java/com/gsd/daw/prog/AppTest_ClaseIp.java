package com.gsd.daw.prog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppTest_ClaseIp {

    @Test
    public void testClaseA() {
        assertEquals("A", App.ClaseIp(new byte[]{10, 0, 0, 1}));
    }

    @Test
    public void testClaseB() {
        assertEquals("B", App.ClaseIp(new byte[]{(byte) 172, 16, 0, 1}));
    }

    @Test
    public void testClaseC() {
        assertEquals("C", App.ClaseIp(new byte[]{(byte) 192, (byte) 168, 0, 1}));
    }

    @Test
    public void testClaseD() {
        assertEquals("D", App.ClaseIp(new byte[]{(byte) 224, 0, 0, 1}));
    }

    @Test
    public void testClaseE() {
        assertEquals("E", App.ClaseIp(new byte[]{(byte) 240, 0, 0, 1}));
    }

    @Test
    public void testClaseFronteraClaseA() {
        assertEquals("A", App.ClaseIp(new byte[]{1, 0, 0, 0}));
    }
}
