package com.example.calculadoravelascodelolmoariana;



public class Calculadora {

    private double operando1;
    private double operando2;
    private double resultado;

    Calculadora() {

    }


    public double getOperando1() {
        return operando1;
    }

    public double getOperando2() {
        return operando2;
    }

    public double getResultado() {
        return resultado;
    }

    public void setOperando1(double operando1) {
        this.operando1 = operando1;
    }

    public void setOperando2(double operando2) {
        this.operando2 = operando2;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public void sumando() {
        resultado = operando1 + operando2;
    }

    public void restando() {
        resultado = operando1 - operando2;
    }

    public void multiplicando() {
        resultado = operando1 * operando2;
    }

    public void dividiendo() {
        resultado = operando1 / operando2;
    }

}
