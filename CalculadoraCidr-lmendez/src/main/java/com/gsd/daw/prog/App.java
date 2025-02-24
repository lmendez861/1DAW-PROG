package com.gsd.daw.prog;

public class App {
    public static void main(String[] args) {
        // Comprobar número exacto de argumentos
        if (args.length != 2) {
            System.out.println("Error: Se requieren exactamente dos argumentos: IP y mascara CIDR");
            return;
        }

        String ip = args[0];
        String mascaraStr = args[1];

        // Validar la IP
        if (!esIpValida(ip)) {
            return; // El mensaje de error se imprime dentro de esIpValida
        }

        // Validar la máscara
        int mascara;
        try {
            mascara = Integer.parseInt(mascaraStr);
            if (mascara < 1 || mascara > 32) {
                System.out.println("Error: La mascara debe estar entre 1 y 32");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: La mascara debe ser un numero entero");
            return;
        }

        // Determinar la clase
        String clase = determinarClase(ip);

        // Determinar si hay subnetting o supernetting
        boolean haySubnetting = determinarSubnetting(ip, mascara);

        // Imprimir resultados en el formato exacto
        System.out.println(ip + "/" + mascara);
        System.out.println(clase);
        System.out.println("Subnetting:" + haySubnetting);
    }

    private static boolean esIpValida(String ip) {
        // Validar que la IP no sea nula o vacía
        if (ip == null || ip.trim().isEmpty()) {
            System.out.println("Error: La IP no puede estar vacia");
            return false;
        }

        // Dividir en octetos
        String[] octetos = ip.split("\\.");
        if (octetos.length != 4) {
            System.out.println("Error: La IP debe tener 4 octetos separados por puntos");
            return false;
        }

        // Validar cada octeto
        try {
            int primerOcteto = Integer.parseInt(octetos[0]); // Acepta ceros a la izquierda (ej. "001" -> 1)
            if (primerOcteto == 0 || primerOcteto == 255) {
                System.out.println("Error: El primer octeto no puede ser 0 ni 255");
                return false;
            }
            for (String octeto : octetos) {
                int valor = Integer.parseInt(octeto); // Acepta ceros a la izquierda
                if (valor < 0 || valor > 255) {
                    System.out.println("Error: Octeto " + octeto + " fuera de rango (0-255)");
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Uno o mas octetos no son numeros validos");
            return false;
        }
        return true;
    }

    private static String determinarClase(String ip) {
        String[] octetos = ip.split("\\.");
        int primerOcteto = Integer.parseInt(octetos[0]);

        if (primerOcteto >= 1 && primerOcteto <= 126) {
            return "A";
        } else if (primerOcteto >= 128 && primerOcteto <= 191) {
            return "B";
        } else if (primerOcteto >= 192 && primerOcteto <= 223) {
            return "C";
        } else {
            return "OTRA";
        }
    }

    private static boolean determinarSubnetting(String ip, int mascara) {
        String[] octetos = ip.split("\\.");
        int primerOcteto = Integer.parseInt(octetos[0]);

        // Máscaras por defecto según la clase
        int mascaraPorDefecto;
        if (primerOcteto >= 1 && primerOcteto <= 126) {
            mascaraPorDefecto = 8;  // Clase A
        } else if (primerOcteto >= 128 && primerOcteto <= 191) {
            mascaraPorDefecto = 16; // Clase B
        } else if (primerOcteto >= 192 && primerOcteto <= 223) {
            mascaraPorDefecto = 24; // Clase C
        } else {
            mascaraPorDefecto = 32; // Otras (D, E)
        }

        // Hay subnetting si la máscara es mayor (más bits) o supernetting si es menor (menos bits)
        return mascara != mascaraPorDefecto;
    }
}