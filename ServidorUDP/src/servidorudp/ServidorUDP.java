package servidorudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ServidorUDP {

    private static final int PUERTO = 5000;
    private static final int TAMANIO_BUFFER = 1024;

    public static void main(String[] args) {
        System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            byte[] buffer = new byte[TAMANIO_BUFFER];

            while (true) {
                DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteEntrada);

                String mensaje = new String(
                        paqueteEntrada.getData(),
                        0,
                        paqueteEntrada.getLength(),
                        StandardCharsets.UTF_8
                );

                String respuesta = resolverOperacion(mensaje);
                byte[] datosRespuesta = respuesta.getBytes(StandardCharsets.UTF_8);

                DatagramPacket paqueteSalida = new DatagramPacket(
                        datosRespuesta,
                        datosRespuesta.length,
                        paqueteEntrada.getAddress(),
                        paqueteEntrada.getPort()
                );
                socket.send(paqueteSalida);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static String resolverOperacion(String mensaje) {
        try {
            String[] partes = mensaje.split(";");
            if (partes.length != 3) {
                return "Solicitud invalida";
            }

            String operacion = partes[0].trim().toUpperCase();
            double a = Double.parseDouble(partes[1].trim());
            double b = Double.parseDouble(partes[2].trim());

            switch (operacion) {
                case "SUMAR":
                    return String.valueOf(a + b);
                case "RESTAR":
                    return String.valueOf(a - b);
                case "MULTIPLICAR":
                    return String.valueOf(a * b);
                case "DIVIDIR":
                    if (b == 0) {
                        return "No se puede dividir para 0";
                    }
                    return String.valueOf(a / b);
                default:
                    return "Operacion no soportada";
            }
        } catch (NumberFormatException e) {
            return "Numeros invalidos";
        } catch (Exception e) {
            return "Error al procesar la solicitud";
        }
    }
}
