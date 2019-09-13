package com.example.calculadoravelascodelolmoariana;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Map;
import java.util.TreeMap;

public class PrefenciasActivity extends Activity implements View.OnClickListener {
    private final static String ATRIBUTO_COLOR_FONDO = "color_fondo";
    private final static int NUMERO_COLORES = 8;
    private SharedPreferences sharedPreferences;
    private String colorFondo;
    private RadioGroup colorBackRG;
    private Map<String, RadioButton> mapRadioButtons;
    private RadioButton radioButtonChecked;
    private Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefencias_layout);
        colorBackRG = (RadioGroup) findViewById(R.id.colorRadioButtonGroup);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);

        //coleccion map con botones clave (color en hex) y valor el radiobutton)
        mapRadioButtons = new TreeMap<String, RadioButton>();

        //obtener hijos de radioGroup
        for (int i = 0; i < NUMERO_COLORES; i++) {
            RadioButton rb = (RadioButton) colorBackRG.getChildAt(i);
            rb.setChecked(false);
            ColorDrawable buttonColor = (ColorDrawable) rb.getBackground();
            int colorId = buttonColor.getColor();
            mapRadioButtons.put(transformarColorIntTexto(this, colorId), rb);
        }

        Intent intent = getIntent();

        sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        colorFondo = intent.getStringExtra(MainActivity.ATRIBUTO_COLOR_FONDO);
        //hacer al radiobutton recuperado que esté marcado: (mediante un for buscar el que corresponde)
        RadioButton rbSeleccionadoUser = mapRadioButtons.get(colorFondo);
        rbSeleccionadoUser.setChecked(true);

    }

    public void onRadioButtonClicked(View view) {
        radioButtonChecked = (RadioButton) findViewById(view.getId());

    }


    public String transformarColorIntTexto(Context cntxt, int colorInt) {

        if (colorInt == cntxt.getColor(R.color.pink)) {
            return getString(R.string.pink);

        } else if (colorInt == cntxt.getColor(R.color.red)) {
            return getString(R.string.red);

        } else if (colorInt == cntxt.getColor(R.color.amber)) {
            return getString(R.string.amber);

        } else if (colorInt == cntxt.getColor(R.color.blue)) {
            return getString(R.string.blue);

        } else if (colorInt == cntxt.getColor(R.color.green)) {
            return getString(R.string.green);

        } else if (colorInt == cntxt.getColor(R.color.purple)) {
            return getString(R.string.purple);

        } else if (colorInt == cntxt.getColor(R.color.grey)) {
            return getString(R.string.grey);

        } else {
            return getString(R.string.white);
        }


    }


    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String colorSeleccionado = "";
        switch (radioButtonChecked.getId()) {
            case R.id.radioButtonRed:
                colorSeleccionado = getString(R.string.red);
                break;
            case R.id.radioButtonAmber:
                colorSeleccionado = getString(R.string.amber);
                break;
            case R.id.radioButtonBlue:
                colorSeleccionado = getString(R.string.blue);
                break;
            case R.id.radioButtonGreen:
                colorSeleccionado = getString(R.string.green);
                break;
            case R.id.radioButtonGrey:
                colorSeleccionado = getString(R.string.grey);
                break;
            case R.id.radioButtonPink:
                colorSeleccionado = getString(R.string.pink);
                break;
            case R.id.radioButtonPurple:
                colorSeleccionado = getString(R.string.purple);
                break;
            default:
                colorSeleccionado = getString(R.string.white);

        }

        editor.putString(ATRIBUTO_COLOR_FONDO, colorSeleccionado);
        editor.commit();

        Intent preferenciasIntent = new Intent(this, MainActivity.class);
        startActivity(preferenciasIntent);

    }
}
