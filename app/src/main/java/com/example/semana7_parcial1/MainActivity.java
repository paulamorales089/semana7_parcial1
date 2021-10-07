package com.example.semana7_parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private EditText nombreGrupo, numeroParticulas, posXText, posYText;
    private Button azulBtn, verdeBtn, rojoBtn, crearBtn, borrarBtn;

    private String posX, posY, grupo, cantidad;
    private int r, g, b;
    private boolean azulPresionado, verdePresionado, rojoPresionado, crearPresionado, borrarPresionado;

    //Gson gson = new Gson();
    private String json;

    BufferedReader reader;
    BufferedWriter writer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreGrupo=findViewById(R.id.nombreGrupo_editText);
        numeroParticulas=findViewById(R.id.numeroParticulas_editText);
        posXText=findViewById(R.id.posX_editText);
        posYText=findViewById(R.id.posY_editText);

        //COLORES
        azulBtn=findViewById(R.id.colorAzul_boton);
        verdeBtn=findViewById(R.id.colorVerde_boton);
        rojoBtn=findViewById(R.id.colorRojo_boton);

        //CREAR BORRAR PARTICULAS
        crearBtn=findViewById(R.id.crearParticulas_boton);
        borrarBtn=findViewById(R.id.borrarParticulas_boton);

        //BOOLEANS BOTONES
        azulPresionado=true;
        verdePresionado=true;
        rojoPresionado=true;

        //BOTONES COLORES
        azulBtn.setOnClickListener(
                (v)->{
                    azulPresionado = true;
                    colorCrearParticulas();

                }
        );

        verdeBtn.setOnClickListener(
                (v)->{
                    verdePresionado = true;
                    colorCrearParticulas();

                }
        );
        rojoBtn.setOnClickListener(
                (v)->{
                    rojoPresionado = true;
                    colorCrearParticulas();

                }
        );

        /*azulBtn.setOnClickListener(this);
        verdeBtn.setOnClickListener(this);
        rojoBtn.setOnClickListener(this);*/


        //BOTONES CREAR Y BORRAR PARTICULAS
        crearBtn.setOnClickListener(
                (v)->{
                    Gson gson = new Gson();

                    crearPresionado = true;
                    colorCrearParticulas();
                    grupo = nombreGrupo.getText().toString();
                    cantidad = numeroParticulas.getText().toString();

                    posX = posXText.getText().toString();
                    posY = posYText.getText().toString();

                    if (grupo.isEmpty() || grupo.equals(null) || posX.isEmpty() || posX.equals(null) || posY.isEmpty() || posY.equals(null)
                    || cantidad.isEmpty() || cantidad.equals(null)) {
                        Toast.makeText(this, "Llena los campos vacios corazón", Toast.LENGTH_LONG).show();
                        crearPresionado = false;
                    }

                }
        );

        borrarBtn.setOnClickListener(
                (v)->{

                }
        );
    }

    private void colorCrearParticulas() {

        if (azulPresionado){
            r = 13;
            g = 167;
            b = 250;

        } else if (verdePresionado){
            r = 165;
            g = 251;
            b = 65;

        } else if (rojoPresionado){
            r = 233;
            g = 30;
            b = 99;

        }
    }
    public void initClient()
    {
        new Thread(
                () ->{
                    try {
                        System.out.println("Connecting to server...");
                        Socket socket = new Socket("192.168.1.58", 4000);
                        System.out.println("Established connection to server");

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        reader = new BufferedReader(isr);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        writer = new BufferedWriter(osw);

                        while(true)
                        {
                            System.out.println("Awaiting message...");
                            String line = reader.readLine();
                            System.out.println("Received message: " + line);


                        }
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }

   /* @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.colorAzul_boton:
                azulPresionado = true;
                colorCrearParticulas();
                //String colorAzulCodigo = "#0DA7FA";
                break;

            case R.id.colorVerde_boton:
                verdePresionado = true;
                colorCrearParticulas();
                //String colorVerdeCodigo = "##A5FB41";
                break;

            case R.id.colorRojo_boton:
                rojoPresionado = true;
                colorCrearParticulas();
                //String colorRojoCodigo = "#E91E63";
                break;
        }
    }*/
}