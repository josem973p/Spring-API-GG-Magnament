package com.ociGG.goldengate.Services;

import com.ociGG.goldengate.Config.MyTrustManager;
import com.ociGG.goldengate.Config.ParamsConfig;
import com.ociGG.goldengate.Entities.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;


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

    ParamsConfig paramsConfig = new ParamsConfig();

     public String postData(String urlParaVisitar, String user , String password) throws IOException {

             URL url = new URL(urlParaVisitar);

       MyTrustManager.disableSSL();


         Authenticator au = new Authenticator() {
             @Override
             protected PasswordAuthentication
             getPasswordAuthentication() {
                 return new PasswordAuthentication
                         (user, password.toCharArray());
             }
         };

         Authenticator.setDefault(au);

         Map<String, Object> params = new LinkedHashMap<>();
          //   System.out.println(ParamsConfig.getParamsList().get(0).getSchema());
         //    params.put("$schema",ParamsConfig.getParamsList().get(0).getSchema());


             System.out.println(ParamsConfig.getParamsList().get(0).getName());
             params.put("name", ParamsConfig.getParamsList().get(0).getName());


             System.out.println( ParamsConfig.getParamsList().get(0).getProcessName());
             params.put("processName",  ParamsConfig.getParamsList().get(0).getProcessName());


             System.out.println(  ParamsConfig.getParamsList().get(0).getProcessType());
             params.put("processType",  ParamsConfig.getParamsList().get(0).getProcessType());

             StringBuilder postData = new StringBuilder();

             postData.append("{\"name\":\"").append(ParamsConfig.getParamsList().get(0).getName())
                     .append("\",\"processName\":\"").append(ParamsConfig.getParamsList().get(0).getProcessName())
                     .append("\",\"processType\":\"").append( ParamsConfig.getParamsList().get(0).getProcessType())
                     .append("\"}");
       //  System.out.println("json a enviar : "+postData.toString());
         /**
             for (Map.Entry<String, Object> param : params.entrySet()) {
                 if (postData.length() != 0)
                     postData.append('{');
                 postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                 postData.append('=');
                 postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                         "UTF-8"));

             }
          **/

         System.out.println("este es la cadena del map"+postData.toString());
             byte[] postDataBytes = postData.toString().getBytes("UTF-8");

             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
             conn.setRequestMethod("POST");
             conn.setRequestProperty("Content-Type",
                     "application/json");
         conn.setRequestProperty( "charset", "utf-8");
             conn.setRequestProperty("Content-Length",
                     String.valueOf(postDataBytes.length));
             conn.setDoOutput(true);
             conn.getOutputStream().write(postDataBytes);


         StringBuilder resultado = new StringBuilder();
       //  System.out.println(conn.);
         BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         String linea;
         // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
         while ((linea = rd.readLine()) != null) {
             resultado.append(linea);
        }
         // Cerrar el BufferedReader
         rd.close();

             return resultado.toString();
     }





}
