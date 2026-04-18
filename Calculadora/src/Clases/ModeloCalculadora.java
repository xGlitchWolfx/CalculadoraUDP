package Clases;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ModeloCalculadora {

    private static final int TAMANIO_BUFFER = 1024;
    private static final int TIMEOUT_MS = 3000;

    public String enviarOperacion(String host, int puerto, String operacion, double a, double b) throws Exception {
        String mensaje = operacion + ";" + a + ";" + b;
        byte[] datosEnvio = mensaje.getBytes(StandardCharsets.UTF_8);

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(TIMEOUT_MS);

            InetAddress direccion = InetAddress.getByName(host);
            DatagramPacket paqueteEnvio = new DatagramPacket(
                    datosEnvio,
                    datosEnvio.length,
                    direccion,
                    puerto
            );
            socket.send(paqueteEnvio);

            byte[] bufferRespuesta = new byte[TAMANIO_BUFFER];
            DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
            socket.receive(paqueteRespuesta);

            return new String(
                    paqueteRespuesta.getData(),
                    0,
                    paqueteRespuesta.getLength(),
                    StandardCharsets.UTF_8
            );
        }
    }
}
