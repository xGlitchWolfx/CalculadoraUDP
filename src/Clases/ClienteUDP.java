package Clases;

public class ClienteUDP {

    private final ModeloCalculadora modelo = new ModeloCalculadora();

    public String sumar(String host, int puerto, double a, double b) throws Exception {
        return modelo.enviarOperacion(host, puerto, "SUMAR", a, b);
    }

    public String restar(String host, int puerto, double a, double b) throws Exception {
        return modelo.enviarOperacion(host, puerto, "RESTAR", a, b);
    }

    public String multiplicar(String host, int puerto, double a, double b) throws Exception {
        return modelo.enviarOperacion(host, puerto, "MULTIPLICAR", a, b);
    }

    public String dividir(String host, int puerto, double a, double b) throws Exception {
        return modelo.enviarOperacion(host, puerto, "DIVIDIR", a, b);
    }
}
