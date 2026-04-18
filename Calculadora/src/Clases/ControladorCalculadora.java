package Clases;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorCalculadora {

    private static final String HOST_SERVIDOR = "127.0.0.1";
    private static final int PUERTO_SERVIDOR = 5000;

    @FXML
    private TextField txtN1;

    @FXML
    private TextField txtN2;

    @FXML
    private Label lblResultado;

    private final ClienteUDP clienteUDP = new ClienteUDP();

    private double getN1() {
        return Double.parseDouble(txtN1.getText());
    }

    private double getN2() {
        return Double.parseDouble(txtN2.getText());
    }

    @FXML
    private void sumar() {
        procesarOperacion("SUMAR");
    }

    @FXML
    private void restar() {
        procesarOperacion("RESTAR");
    }

    @FXML
    private void multiplicar() {
        procesarOperacion("MULTIPLICAR");
    }

    @FXML
    private void dividir() {
        procesarOperacion("DIVIDIR");
    }

    private void procesarOperacion(String operacion) {
        try {
            String resultado;
            switch (operacion) {
                case "SUMAR":
                    resultado = clienteUDP.sumar(HOST_SERVIDOR, PUERTO_SERVIDOR, getN1(), getN2());
                    break;
                case "RESTAR":
                    resultado = clienteUDP.restar(HOST_SERVIDOR, PUERTO_SERVIDOR, getN1(), getN2());
                    break;
                case "MULTIPLICAR":
                    resultado = clienteUDP.multiplicar(HOST_SERVIDOR, PUERTO_SERVIDOR, getN1(), getN2());
                    break;
                case "DIVIDIR":
                    resultado = clienteUDP.dividir(HOST_SERVIDOR, PUERTO_SERVIDOR, getN1(), getN2());
                    break;
                default:
                    resultado = "Operacion no valida";
            }
            lblResultado.setText(resultado);
        } catch (NumberFormatException e) {
            lblResultado.setText("Ingresa numeros validos");
        } catch (Exception e) {
            lblResultado.setText("Error: " + e.getMessage());
        }
    }
}
