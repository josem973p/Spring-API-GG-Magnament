package com.ociGG.goldengate.Services;

import com.ociGG.goldengate.Config.MyTrustManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;


@Service
public class HttpMethods {

    @Autowired
    MyTrustManager myTrustManager = new MyTrustManager();

    public  String peticionHttpGet(String urlParaVisitar, String user , String password) throws Exception {
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL

        System.out.println("esta es la url: "+urlParaVisitar);

        URL url = new URL(urlParaVisitar.toString());

        MyTrustManager.disableSSL();

        System.out.println("dentro del metodo ");
        Authenticator au = new Authenticator() {
            @Override
            protected PasswordAuthentication
            getPasswordAuthentication() {
                return new PasswordAuthentication
                        (user, password.toCharArray());
            }
        };

        Authenticator.setDefault(au);
        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

        conexion.setRequestMethod("GET");

        // Búferes para leer

            BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
            }
            // Cerrar el BufferedReader
            rd.close();
            // Regresar resultado, pero como cadena, no como StringBuilder



        return resultado.toString();
    }


}
