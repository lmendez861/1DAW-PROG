package com.gsd.daw.prog;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error: Introduce una IP valida y la mascara");
            return;
        }

        String Ip = args[0];
        String formato = args[1];
        byte[] bytesIp = new byte[4];

        if (!validarIp(Ip, bytesIp)) {
            System.out.println("La IP no es válida.");
            return;
        }

        if (tieneComillas(Ip) || tieneComillas(formato)) {
            System.out.println("Error: La dirección IP o el formato no deben tener comillas");
            return;
        }

        if (!esIntValido(formato) || Integer.parseInt(formato) < 0 || Integer.parseInt(formato) > 32
                || (formato.length() > 1 && formato.charAt(0) == '0')) {
            System.out.println("Error: El formato '" + formato + "' no es válido. Debe estar en el rango 0-32 sin ceros a la izquierda.");
            return;
        }

        int bits = Integer.parseInt(formato);
        String claseIp = ClaseIp(bytesIp);

        System.out.println(Ip + "/" + formato);
        System.out.println(claseIp);

        boolean subnetting = calcularSubnetting(bits, claseIp);
        System.out.println("Subnetting:" + subnetting);
    }

    public static boolean tieneComillas(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '\'' || c == '\"') {
                return true;
            }
        }
        return false;
    }

    public static boolean validarIp(String ip, byte[] bytesIp) {
        String[] octetos = ip.split("\\.");
        if (octetos.length != 4) {
            System.out.println("Error: La IP debe tener 4 octetos separados por puntos.");
            return false;
        }

        for (int i = 0; i < octetos.length; i++) {
            if (octetos[i].indexOf('-') != -1) {
                System.out.println("Error: El octeto '" + (i + 1) + "' no puede ser negativo.");
                return false;
            }
            if (!esIntValido(octetos[i])) {
                System.out.println("IP invalida. El byte " + (i + 1) + " debe ser un número");
                return false;
            }
            if (octetos[i].length() > 1 && octetos[i].startsWith("0")) {
                System.out.println("Error: El octeto '" + octetos[i] + "' no debe tener ceros a la izquierda.");
                return false;
            }
            int valor = Integer.parseInt(octetos[i]);
            if (valor < 0 || valor > 255) {
                System.out.println("Error: El octeto '" + octetos[i] + "' está fuera del rango permitido (0-255).");
                return false;
            }

            bytesIp[i] = (byte) valor;
        }
        return true;
    }

    public static boolean esIntValido(String candidato) {
        try {
            Integer.parseInt(candidato);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String ClaseIp(byte[] bytesIp) {
        int primerOcteto = bytesIp[0] & 0xFF;
        if (primerOcteto <= 127)
            return "A";
        if (primerOcteto <= 191)
            return "B";
        if (primerOcteto <= 223)
            return "C";
        if (primerOcteto <= 239)
            return "D";
        return "E";
    }

    public static boolean calcularSubnetting(int bits, String claseIp) {
        if (bits < 0 || bits > 32) {
            System.out.println("Error: El valor de la máscara no es válido para calcular el subnetting.");
            return false;
        }
        
        int mascaraPorDefecto;
        switch (claseIp) {
            case "A":
                mascaraPorDefecto = 8;
                break;
            case "B":
                mascaraPorDefecto = 16;
                break;
            case "C":
                mascaraPorDefecto = 24;
                break;
            case "D":
            case "E":
                return bits == 32;
            default:
                return false;
        }
        return bits >= mascaraPorDefecto;
    }
    
    
}
