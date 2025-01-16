package com.gsd.daw.prog;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppTest_ValidarIp {

    @Test
    public void testIpValida() {
        byte[] bytesIp = new byte[4];
        assertTrue(App.validarIp("192.168.1.1", bytesIp));
    }

    @Test
    public void testIpConOctetosFueraDeRango() {
        byte[] bytesIp = new byte[4];
        assertFalse(App.validarIp("300.168.1.1", bytesIp));
    }

    @Test
    public void testIpConMenosDeCuatroOctetos() {
        byte[] bytesIp = new byte[4];
        assertFalse(App.validarIp("192.168.1", bytesIp));
    }

    @Test
    public void testIpConMasDeCuatroOctetos() {
        byte[] bytesIp = new byte[4];
        assertFalse(App.validarIp("192.168.1.1.1", bytesIp));
    }

    @Test
    public void testIpConCaracteresNoNumericos() {
        byte[] bytesIp = new byte[4];
        assertFalse(App.validarIp("192.abc.1.1", bytesIp));
    }

    @Test
    public void testIpFormatoInvalido() {
        byte[] bytesIp = new byte[4];
        assertFalse(App.validarIp("192.168.01.1", bytesIp));
    }
}

