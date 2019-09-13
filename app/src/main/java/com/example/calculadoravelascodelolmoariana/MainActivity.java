package com.example.calculadoravelascodelolmoariana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ATRIBUTO_COLOR_FONDO = "color_fondo";
    private String etiqueta_app_log;
    private SharedPreferences sharedPreferences;

    private String colorFondoClaveValor;

    private ImageView imagenIbermatica;
    private TextView tvDigitos;
    private LinearLayout calculadoraLayout;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btnAC;
    private Button btnSumar;
    private Button btnRestar;
    private Button btnDividir;
    private Button btnMultiplicar;
    private Button btnDot;
    private Button btnIgual;

    private List<Button> listBtnNumeros;
    private List<Button> listBtnOperaciones;

    private Calculadora calculo;

    private boolean numeroDecimalBoolean;

    private boolean sumando;
    private boolean restando;
    private boolean multiplicando;
    private boolean dividiendo;
    private boolean presionadoIgual;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vincular componentes
        imagenIbermatica = (ImageView) findViewById(R.id.imgIbermatica);
        tvDigitos = (TextView) findViewById(R.id.tvDigitos);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnAC = (Button) findViewById(R.id.btnAC);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnMultiplicar = (Button) findViewById(R.id.btnMultiplicar);
        btnDividir = (Button) findViewById(R.id.btnDividir);
        btnIgual = (Button) findViewById(R.id.btnIgual);
        btnRestar = (Button) findViewById(R.id.btnRestar);
        btnSumar = (Button) findViewById(R.id.btnSumar);

        //color de fondo
        String colorFondo = "";
        calculadoraLayout = (LinearLayout) findViewById(R.id.calculadoraLayoutID);
        sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        colorFondoClaveValor = sharedPreferences.getString(ATRIBUTO_COLOR_FONDO, colorFondo);

        if (colorFondoClaveValor != null) {
            if (colorFondoClaveValor.equals(getString(R.string.red).toString())) {
                calculadoraLayout.setBackgroundColor(getColor(R.color.red));

            }

            if (colorFondoClaveValor.equals(getString(R.string.amber).toString())) {
                calculadoraLayout.setBackgroundColor(getColor(R.color.amber));

            }
            if (colorFondoClaveValor.equals(getString(R.string.blue).toString())) {
                calculadoraLayout.setBackgroundColor(getColor(R.color.blue));

            }
            if (colorFondoClaveValor.equals(getString(R.string.purple).toString())) {
                calculadoraLayout.setBackgroundColor(getColor(R.color.purple));

            }

            if (colorFondoClaveValor.equals(getString(R.string.pink).toString())) {
                calculadoraLayout.setBackgroundColor(getColor(R.color.pink));

            }

            if (colorFondoClaveValor.equals(getString(R.string.grey).toString())) {
                calculadoraLayout.setBackgroundColor(getColor(R.color.grey));

            }

            if (colorFondoClaveValor.equals(getString(R.string.green).toString())) {
                calculadoraLayout.setBackgroundColor(getColor(R.color.green));

            }

            if (colorFondoClaveValor.equals(getString(R.string.white).toString())) {
                calculadoraLayout.setBackgroundColor(getColor(R.color.white));

            }

        } else {
            colorFondoClaveValor = "#FFFFFF";
            calculadoraLayout.setBackgroundColor(getColor(R.color.white));
        }


        //add imagen
        imagenIbermatica.setImageResource(R.drawable.img_ibermatica);

        //asignar listener a los botones:
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAC.setOnClickListener(this);
        btnDividir.setOnClickListener(this);
        btnIgual.setOnClickListener(this);
        btnMultiplicar.setOnClickListener(this);
        btnRestar.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnSumar.setOnClickListener(this);

        //agrupar botones según función
        listBtnNumeros = new ArrayList<Button>();
        listBtnNumeros.add(btn0);
        listBtnNumeros.add(btn1);
        listBtnNumeros.add(btn2);
        listBtnNumeros.add(btn3);
        listBtnNumeros.add(btn4);
        listBtnNumeros.add(btn5);
        listBtnNumeros.add(btn6);
        listBtnNumeros.add(btn7);
        listBtnNumeros.add(btn8);
        listBtnNumeros.add(btn9);

        listBtnOperaciones = new ArrayList<Button>();
        listBtnOperaciones.add(btnAC);
        listBtnOperaciones.add(btnDividir);
        listBtnOperaciones.add(btnDot);
        listBtnOperaciones.add(btnIgual);
        listBtnOperaciones.add(btnRestar);
        listBtnOperaciones.add(btnSumar);
        listBtnOperaciones.add(btnMultiplicar);

        etiqueta_app_log = getString(R.string.aliasApp);

        //inicialización de variables
        calculo = new Calculadora();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferences, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferencias:
                Intent preferenciasIntent = new Intent(this, PrefenciasActivity.class);
                preferenciasIntent.putExtra(ATRIBUTO_COLOR_FONDO, colorFondoClaveValor);
                startActivity(preferenciasIntent);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClick(View v) {

        String msg; //variable para logs
        //ver que muestra la pantalla en ese momento
        String numerosPantalla = tvDigitos.getText().toString();
        //recuperar el botón pulsado
        Button btnPulsado = findViewById(v.getId());
        String txtBoton = btnPulsado.getText().toString();


        //ver de que tipo es el boton:
        if (listBtnNumeros.contains(btnPulsado)) {
            //1. que sea número
            if (presionadoIgual) {
                //si es despues de dar al igual y estas visionando un resultado, debe borrar la pantalla
                numerosPantalla = txtBoton;
                presionadoIgual = false;
            } else {
                //añadirlo a la pantalla
                numerosPantalla = numerosPantalla + txtBoton;
            }
            tvDigitos.setText(numerosPantalla);

        } else {
            if (presionadoIgual) {
                if (numerosPantalla.contains(getString(R.string.infinito))) {
                    numerosPantalla = "0.0"; //caso en el que el resultado anterior sea infinity
                } else {
                    numerosPantalla = "" + calculo.getResultado();
                }
                presionadoIgual = false;
            }

            //2. que sea un operador
            if (numerosPantalla == "") {
                //pantalla vacía.
                if (btnPulsado.equals(btnDot)) {
                    numerosPantalla = numerosPantalla + btn0.getText().toString() + txtBoton;
                    numeroDecimalBoolean = true;

                } else {
                    msg = getString(R.string.operacion_fallida) + " " +
                            getString(R.string.faltan_datos);
                    Log.w(etiqueta_app_log, msg);
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

                }

            } else {
                //REALIZANDO OPERACIÓN
                switch (v.getId()) {
                    case R.id.btnDot:
                        if (!numeroDecimalBoolean) {
                            if (sumando | multiplicando | restando | dividiendo)
                                numerosPantalla = numerosPantalla + btn0.getText().toString() + txtBoton;
                            else {
                                numerosPantalla = numerosPantalla + txtBoton;
                            }
                            numeroDecimalBoolean = true;
                            tvDigitos.setText(numerosPantalla);

                        } else {

                            msg = getString(R.string.operacion_fallida) + " " +
                                    getString(R.string.dos_decimales);
                            Log.w(etiqueta_app_log, msg);
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                        }
                        break;

                    case R.id.btnAC:
                        //borra todo
                        presionadoIgual = false;
                        numeroDecimalBoolean = false;
                        numerosPantalla = "";
                        calculo = new Calculadora();
                        sumando = false;
                        multiplicando = false;
                        dividiendo = false;
                        restando = false;
                        tvDigitos.setText(numerosPantalla);
                        break;

                    case R.id.btnSumar:
                        numeroDecimalBoolean = false;
                        if (multiplicando | restando | dividiendo) {
                            //no tiene que dejar hacer la nueva operacion
                            msg = getString(R.string.operacion_fallida);
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            break;
                        }

                        if (!sumando) {
                            sumando = true;
                            numerosPantalla = numerosPantalla + " " + txtBoton + " ";

                        } else {
                            String[] posiblesValores = numerosPantalla.split("(=)|(\\+)");
                            try {
                                extraerOperandos(posiblesValores);
                                calculo.sumando();
                                msg = getString(R.string.sumar_log) + calculo.getOperando1() +
                                        getString(R.string.suma) + calculo.getOperando2() +
                                        getString(R.string.igual) + calculo.getResultado();
                                Log.i(etiqueta_app_log, msg);
                                numerosPantalla = numerosPantalla + " = " + calculo.getResultado();
                                numerosPantalla = numerosPantalla + " " + txtBoton + " ";

                            } catch (CalculadoraException e) {
                                msg = getString(R.string.operacion_fallida) + " " +
                                        getString(R.string.faltan_datos);
                                Log.w(etiqueta_app_log, msg);
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }
                        }

                        tvDigitos.setText(numerosPantalla);
                        break;

                    case R.id.btnRestar:
                        numeroDecimalBoolean = false;
                        if (multiplicando | sumando | dividiendo) {
                            //no tiene que dejar hacer la nueva operacion
                            msg = getString(R.string.operacion_fallida);
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            break;
                        }

                        if (!restando) {
                            restando = true;
                            numerosPantalla = numerosPantalla + " " + txtBoton + " ";

                        } else {
                            String[] posiblesValores = numerosPantalla.split("(=)|(-)");
                            try {
                                extraerOperandos(posiblesValores);
                                calculo.restando();
                                numerosPantalla = numerosPantalla + " = " + calculo.getResultado();
                                msg = getString(R.string.restar_log) + calculo.getOperando1() +
                                        getString(R.string.resta) + calculo.getOperando2() +
                                        getString(R.string.igual) + calculo.getResultado();
                                Log.i(etiqueta_app_log, msg);
                                numerosPantalla = numerosPantalla + " " + txtBoton + " ";

                            } catch (CalculadoraException e) {
                                msg = getString(R.string.operacion_fallida) + " " +
                                        getString(R.string.faltan_datos);
                                Log.w(etiqueta_app_log, msg);
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }

                        }

                        tvDigitos.setText(numerosPantalla);
                        break;

                    case R.id.btnMultiplicar:
                        numeroDecimalBoolean = false;
                        if (sumando | restando | dividiendo) {
                            //no tiene que dejar hacer la nueva operacion
                            msg = getString(R.string.operacion_fallida);
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            break;
                        }

                        if (!multiplicando) {
                            multiplicando = true;
                            numerosPantalla = numerosPantalla + " " + txtBoton + " ";
                        } else {
                            String[] posiblesValores = numerosPantalla.split("(=)|(x)");
                            try {
                                extraerOperandos(posiblesValores);
                                calculo.multiplicando();
                                numerosPantalla = numerosPantalla + " = " + calculo.getResultado();
                                msg = getString(R.string.multiplicar_log) + calculo.getOperando1() +
                                        getString(R.string.multiplicar) + calculo.getOperando2() +
                                        getString(R.string.igual) + calculo.getResultado();
                                Log.i(etiqueta_app_log, msg);
                                numerosPantalla = numerosPantalla + " " + txtBoton + " ";
                            } catch (CalculadoraException e) {
                                msg = getString(R.string.operacion_fallida) + " " +
                                        getString(R.string.faltan_datos);
                                Log.w(etiqueta_app_log, msg);
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }


                        }

                        tvDigitos.setText(numerosPantalla);
                        break;

                    case R.id.btnDividir:
                        numeroDecimalBoolean = false;
                        if (sumando | restando | multiplicando) {
                            //no tiene que dejar hacer la nueva operacion
                            msg = getString(R.string.operacion_fallida);
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            break;
                        }
                        if (!dividiendo) {
                            dividiendo = true;
                            numerosPantalla = numerosPantalla + " " + txtBoton + " ";
                        } else {
                            String[] posiblesValores = numerosPantalla.split("(=)|(/)");
                            try {
                                extraerOperandos(posiblesValores);
                                if (calculo.getOperando2() == 0) {
                                    //ERROR, NO SE PUEDE DIVIDIR POR 0
                                    numerosPantalla = getString(R.string.infinito);
                                    msg = getString(R.string.operacion_fallida) + getString(R.string.dividir_0);
                                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                    break;
                                } else {
                                    calculo.dividiendo();
                                }
                                numerosPantalla = numerosPantalla + " " + txtBoton + " ";
                            } catch (CalculadoraException e) {
                                msg = getString(R.string.operacion_fallida) + " " +
                                        getString(R.string.faltan_datos);
                                Log.w(etiqueta_app_log, msg);
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }
                        }

                        msg = getString(R.string.dividir_log) + calculo.getOperando1() +
                                getString(R.string.dividir) + calculo.getOperando2() +
                                getString(R.string.igual) + calculo.getResultado();
                        Log.i(etiqueta_app_log, msg);
                        tvDigitos.setText(numerosPantalla);
                        break;

                    case R.id.btnIgual:
                        presionadoIgual = true;
                        if (sumando) {
                            String[] posiblesValores = numerosPantalla.split("(=)|(\\+)");
                            try {
                                extraerOperandos(posiblesValores);
                                calculo.sumando();
                                msg = getString(R.string.sumar_log) + calculo.getOperando1() +
                                        getString(R.string.suma) + calculo.getOperando2() +
                                        getString(R.string.igual) + calculo.getResultado();
                                Log.i(etiqueta_app_log, msg);
                            } catch (CalculadoraException e) {
                                msg = getString(R.string.operacion_fallida) + " " +
                                        getString(R.string.faltan_datos);
                                Log.w(etiqueta_app_log, msg);
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }
                        } else if (multiplicando) {
                            String[] posiblesValores = numerosPantalla.split("(=)|(x)");
                            try {
                                extraerOperandos(posiblesValores);
                                calculo.multiplicando();
                                msg = getString(R.string.multiplicar_log) + calculo.getOperando1() +
                                        getString(R.string.multiplicar) + calculo.getOperando2() +
                                        getString(R.string.igual) + calculo.getResultado();
                                Log.i(etiqueta_app_log, msg);
                            } catch (CalculadoraException e) {
                                msg = getString(R.string.operacion_fallida) + " " +
                                        getString(R.string.faltan_datos);
                                Log.w(etiqueta_app_log, msg);
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }
                        } else if (restando) {
                            String[] posiblesValores = numerosPantalla.split("(=)|(-)");
                            try {
                                extraerOperandos(posiblesValores);
                                calculo.restando();
                                msg = getString(R.string.restar_log) + calculo.getOperando1() +
                                        getString(R.string.resta) + calculo.getOperando2() +
                                        getString(R.string.igual) + calculo.getResultado();
                                Log.i(etiqueta_app_log, msg);
                            } catch (CalculadoraException e) {
                                msg = getString(R.string.operacion_fallida) + " " +
                                        getString(R.string.faltan_datos);
                                Log.w(etiqueta_app_log, msg);
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }
                        } else if (dividiendo) {
                            String[] posiblesValores = numerosPantalla.split("(=)|(/)");
                            try {
                                extraerOperandos(posiblesValores);
                                if (calculo.getOperando2() == 0) {
                                    numerosPantalla = numerosPantalla + " " + txtBoton + " " + getString(R.string.infinito);
                                    msg = getString(R.string.operacion_fallida) + getString(R.string.dividir_0);
                                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                } else {
                                    calculo.dividiendo();
                                    msg = getString(R.string.dividir_log) + calculo.getOperando1() +
                                            getString(R.string.dividir) + calculo.getOperando2() +
                                            getString(R.string.igual) + calculo.getResultado();
                                    Log.i(etiqueta_app_log, msg);
                                }

                            } catch (CalculadoraException e) {
                                msg = getString(R.string.operacion_fallida) + " " +
                                        getString(R.string.faltan_datos);
                                Log.w(etiqueta_app_log, msg);
                                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            }

                        } else {
                            msg = getString(R.string.operacion_fallida) + " " +
                                    getString(R.string.faltan_datos);
                            Log.w(etiqueta_app_log, msg);
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            calculo.setResultado(Double.parseDouble(numerosPantalla));
                        }

                        sumando = false;
                        multiplicando = false;
                        dividiendo = false;
                        restando = false;

                        if (!numerosPantalla.contains(getString(R.string.infinito))) {
                            numerosPantalla = numerosPantalla + " " + txtBoton + " " + calculo.getResultado();
                        }
                        tvDigitos.setText(numerosPantalla);
                        break;
                }


            }

        }


    }

    public void extraerOperandos(String[] posiblesValores) throws CalculadoraException {
        if (posiblesValores.length >= 2) {
            String operando1 = posiblesValores[posiblesValores.length - 2];
            double operando1dbl = Double.parseDouble(operando1);
            calculo.setOperando1(operando1dbl);

            String operando2 = posiblesValores[posiblesValores.length - 1];

            if (operando2.equals(" ")) {
                String msg = getString(R.string.operacion_fallida) + " " + getString(R.string.faltan_datos);
                throw new CalculadoraException(msg);
            } else {
                double operando2dbl = Double.parseDouble(operando2);
                calculo.setOperando2(operando2dbl);
            }
        } else {
            String msg = getString(R.string.operacion_fallida) + " " + getString(R.string.faltan_datos);
            throw new CalculadoraException(msg);
        }
    }


}
