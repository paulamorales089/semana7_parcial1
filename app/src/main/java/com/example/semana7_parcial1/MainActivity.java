package com.example.semana7_parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import model.GenesisParticulas;

public class MainActivity extends AppCompatActivity {

    private EditText nombreGrupo, numeroParticulas, posXText, posYText;
    private Button azulBtn, verdeBtn, rojoBtn, crearBtn, borrarBtn;

    private String  grupo;
    private int r, g, b, posX, posY, cantidad;
    private boolean azulPresionado, verdePresionado, rojoPresionado, crearPresionado, borrarPresionado;

    Gson gson;
    private String json;

    BufferedReader bfr;
    BufferedWriter bfw;

    GenesisParticulas genesisParticulas;




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
        borrarPresionado=false;


        iniciarCliente();
        //colorCrearParticulas();



        //BOTONES COLORES
        azulBtn.setOnClickListener(
                (v)->{
                    azulPresionado=true;
                    verdePresionado = false;
                    rojoPresionado = false;
                  colorCrearParticulas();
                }
        );

        verdeBtn.setOnClickListener(
                (v)->{
                    verdePresionado=true;
                    rojoPresionado = false;
                    azulPresionado = false;
                    colorCrearParticulas();

                }
        );
        rojoBtn.setOnClickListener(
                (v)->{
                    rojoPresionado=true;
                    verdePresionado = false;
                    azulPresionado = false;
                  colorCrearParticulas();

                }
        );
       //BOTONES CREAR Y BORRAR PARTICULAS ENVIO DE DATOS
        crearBtn.setOnClickListener(
                (v)->{
                    crearPresionado = true;

                    // Gson gson;
                    gson = new Gson();

                    colorCrearParticulas();
                    grupo = nombreGrupo.getText().toString();
                    cantidad = Integer.parseInt(numeroParticulas.getText().toString());

                    posX = Integer.parseInt(posXText.getText().toString());
                    posY = Integer.parseInt(posYText.getText().toString());

                    /*if (grupo.isEmpty() || cantidad.isEmpty() || posX.isEmpty() || posY.isEmpty() ) {
                        Toast.makeText(this, "Llena los campos vacios corazón", Toast.LENGTH_SHORT).show();
                        crearPresionado = false;
                    }*/

                    //GenesisParticulas particulasNacen;
                    genesisParticulas = new GenesisParticulas(grupo,cantidad, posX, posY, r, g, b);

                    //private String json;
                    json = gson.toJson(genesisParticulas);

                    enviarParticulas(json);

                }
        );

        borrarBtn.setOnClickListener(
                (v)->{

                    borrarPresionado=true;
                    matarParticulas(json);
                }
        );
    }


    private void colorCrearParticulas() {

        if (azulPresionado ){

            r = 13;
            g = 167;
            b = 250;

        }
        if (verdePresionado){


            r = 165;
            g = 251;
            b = 65;

        }
        if (rojoPresionado){


            r = 233;
            g = 30;
            b = 99;

        }
    }
    public void iniciarCliente() {
        new Thread(
                () ->{
                    try {
                        System.out.println("Connecting to server...");
                        Socket socket = new Socket("192.168.1.3", 4000);
                        System.out.println("Established connection to server");

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        bfr = new BufferedReader(isr);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bfw = new BufferedWriter(osw);

                        while(true)
                        {
                            System.out.println("Awaiting message...");
                            String line = bfr.readLine();
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

    private void enviarParticulas(String mensajito) {
        new Thread(
                () -> {
                    try {
                        bfw.write(mensajito + "\n");
                        bfw.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    private void matarParticulas(String noMensajito) {
        new Thread(
                () -> {
                    try {
                        bfw.write(noMensajito + "\n");
                        bfw.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }



}