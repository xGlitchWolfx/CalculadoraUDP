package Clases;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorCalculadora {

    @FXML
    private TextField txtN1;

    @FXML
    private TextField txtN2;

    @FXML
    private Label lblResultado;

    private ModeloCalculadora calc = new ModeloCalculadora();

    private double getN1() {
        return Double.parseDouble(txtN1.getText());
    }

    private double getN2() {
        return Double.parseDouble(txtN2.getText());
    }

    @FXML
    private void sumar() {
        lblResultado.setText(String.valueOf(calc.sumar(getN1(), getN2())));
    }

    @FXML
    private void restar() {
        lblResultado.setText(String.valueOf(calc.restar(getN1(), getN2())));
    }

    @FXML
    private void multiplicar() {
        lblResultado.setText(String.valueOf(calc.multiplicar(getN1(), getN2())));
    }

    @FXML
    private void dividir() {
        try {
            lblResultado.setText(String.valueOf(calc.dividir(getN1(), getN2())));
        } catch (Exception e) {
            lblResultado.setText(e.getMessage());
        }
    }
}