package com.ociGG.goldengate.Services;

import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Config.MyTrustManager;
import com.ociGG.goldengate.Config.ParamsConfig;
import com.ociGG.goldengate.Entities.Params;
import com.ociGG.goldengate.Entities.StatsEntity;
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
import java.util.*;
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
    CredentialsConfig credentialsConfig = new CredentialsConfig();

    public  String peticionHttpGet(String urlParaVisitar, String user , String password) throws Exception {

        StringBuilder resultado = new StringBuilder();

        URL url = new URL(urlParaVisitar.toString());
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

        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Content-Type",
                "application/json");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
            }
            rd.close();


        return resultado.toString();
    }

    //------------------------METODO GET PARA EXTRACT Y REPLICAT --------------------------------------------

    public  String GetExtractAndReplicats(String urlParaVisitar, String user , String password) throws Exception {

        StringBuilder resultado = new StringBuilder();

        URL url = new URL(urlParaVisitar.toString());
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

        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Content-Type",
                "application/json");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        rd.close();

        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer st = new StringTokenizer(resultado.toString());
        stringBuilder.append("{ ");

        while (st.hasMoreTokens()) {


            if (st.nextToken().equals("\"name\":")){
                stringBuilder.append("\"name\": ").append(st.nextToken()).append(" , ");

            }

        }
        int size =stringBuilder.length();

        stringBuilder.replace(size-1,size-1," }");
        System.out.println(stringBuilder.toString());
        stringBuilder.deleteCharAt(size);

        return stringBuilder.toString();
    }
    //------------------------METODO GET PARA status del EXTRACT Y REPLICAT --------------------------------------------

    public  String GetStatusExtractAndReplicats(String urlParaVisitar, String user , String password ,List<String> datos,  int iterator, String type) throws Exception {

        StringBuilder resultado = new StringBuilder();

        URL url = new URL(urlParaVisitar.toString());
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

        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Content-Type",
                "application/json");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        rd.close();

        resultado.delete(0,350);

        StringBuilder stringBuilder = new StringBuilder();



        stringBuilder.append("{ ");
            stringBuilder.append("\"name\": \"").append(datos.get(iterator)).append("\",");




        StringTokenizer status = new StringTokenizer(resultado.toString());
        while (status.hasMoreTokens()) {
            if (status.nextToken().equals("\"status\":")) {
                stringBuilder.append("\"status\":").append(status.nextToken());
                break;
            }
        }
        StringTokenizer lastStarted = new StringTokenizer(resultado.toString());
        while (lastStarted.hasMoreTokens()) {
            if (lastStarted.nextToken().equals("\"lastStarted\":")) {
                stringBuilder.append("\"lastStarted\":").append(lastStarted.nextToken());
                break;
            }
        }
        StringTokenizer lag = new StringTokenizer(resultado.toString());
        while (lag.hasMoreTokens()) {
            if (lag.nextToken().equals("\"lag\":")) {
                stringBuilder.append("\"lag\":").append(lag.nextToken());
                break;
            }
        }
        StringTokenizer sinceLagReported = new StringTokenizer(resultado.toString());
        while (sinceLagReported.hasMoreTokens()) {
            if (sinceLagReported.nextToken().equals("\"sinceLagReported\":")) {
                stringBuilder.append("\"sinceLagReported\":").append(sinceLagReported.nextToken());
                break;
            }
        }
        StringTokenizer position = new StringTokenizer(resultado.toString());
        while (position.hasMoreTokens()) {
            if (position.nextToken().equals("\"position\":")) {

                if (type.equals("extract")){
                    stringBuilder.append("\"position\":").append(position.nextToken()).append(",");
                }else{
                    stringBuilder.append("\"position\":").append("0").append(",");
                }


                break;
            }
        }
        int size =stringBuilder.length();

        stringBuilder.replace(size-1,size-1,"}");
        System.out.println(stringBuilder.toString());
        stringBuilder.deleteCharAt(size);

        return stringBuilder.toString();
    }

    //_________________________________________METODO POST_______________________________________________

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

             StringBuilder postData = new StringBuilder();

             postData.append("{\"name\":\"").append(ParamsConfig.getParamsList().get(0).getName())
                     .append("\",\"processName\":\"").append(ParamsConfig.getParamsList().get(0).getProcessName())
                     .append("\",\"processType\":\"").append( ParamsConfig.getParamsList().get(0).getProcessType())
                     .append("\"}");

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

    //_____________Metodo Para  El DataTable______________________________________


    public String   data(String extracts,String type) throws Exception {

        System.out.println("hola soy la data "+extracts);

        List<String> extractos = new ArrayList<String>();


        StringTokenizer st = new StringTokenizer(extracts.toString());
        while (st.hasMoreTokens()) {

            if (st.nextToken().equals("\"name\":")) {
                StringBuilder palabra = new StringBuilder (st.nextToken());
                palabra.deleteCharAt(0);
                int tam =palabra.length();
                palabra.deleteCharAt(tam-1);
                System.out.println(palabra.toString());
                extractos.add(palabra.toString());

            }
        }


        System.out.println(extractos.get(0));
        System.out.println(extractos.get(1));
        System.out.println(extractos.get(2));

        StringBuilder datos = new StringBuilder ();
        datos.append("[");

        System.out.println(extractos.size());
        for (int i=0; i<extractos.size();i++){

            String url = CredentialsConfig.getCredentials().get(0).getUrl();
            System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
            StringBuilder sb = new StringBuilder ();

            if (type.equals("extract")){
                sb.append(url).append("/services/v2/extracts/");
            }else{
                sb.append(url).append("/services/v2/replicats/");
            }


            sb.append(extractos.get(i)).append("/info/status");
            String urlfinal = sb.toString();
            System.out.println(urlfinal);


           String extracto = GetStatusExtractAndReplicats(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword(),extractos,i,type);
            datos.append(extracto).append(",");

        }
        int r = datos.length();

        datos.deleteCharAt(r-1).append("]");


        System.out.println(datos.toString());
        return datos.toString();
    }


    //______________________________________METODO PARA LOS STATS___________________________________________

    public String getStatsData(String urlParaVisitar, String user , String password) throws IOException {

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
        StringBuilder postData = new StringBuilder();
        StatsEntity statsEntity = new StatsEntity();
        postData.append("{\"command\":\"").append(statsEntity.getCommand())
                .append("\",\"arguments\":\"").append(statsEntity.getArguments())
                .append("\"}");

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


    //__________________ Metodo para validar el estado de los replicats____________________________

    public  static  String peticionStatus(String urlParaVisitar, String user , String password) throws Exception {

         /**
        StringBuilder resultado = new StringBuilder();

        URL url = new URL(urlParaVisitar.toString());
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

        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Content-Type",
                "application/json");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        rd.close();

        resultado.delete(0,350);

          **/

        String resultado2 = "{ \"$schema\": \"api:standardResponse\",  \"links\": [  {  \"href\": \"http://localhost:11001/services/v2/replicats/REP2/info/status\",  \"mediaType\":\"application/json\",  \"rel\":\"canonical\" }, { \"href\":\"http://localhost:11001/services/v2/replicats/REP2/info/status\",\"mediaType\":\"application/json\", \"rel\": \"self\"   }, { \"href\":\"http://localhost:11001/services/v2/metadata-catalog/replicatStatus\", \"mediaType\":\"application/schema+json\",\"rel\":\"describedby\" } ], \"messages\":[], \"response\":{ \"$schema\":\"ogg:replicatStatus\", \"lag\":0, \"lastStarted\":\"2017-12-14T12:43:59.181Z\" \"position\":{\"name\":\"X2\",\"offset\":3987,\"path\": \"/u02/ogg/Local/var/lib/data\", \"sequence\":0 }, \"processId\":1699, \"sinceLagReported\":2, \"status\": \"running\"  }  }";

        StringBuilder stringBuilder = new StringBuilder();

        StringTokenizer status = new StringTokenizer(resultado2.toString());
        while (status.hasMoreTokens()) {
            if (status.nextToken().equals("\"status\":")) {
                stringBuilder.append(status.nextToken());
                break;
            }
        }

        return stringBuilder.toString();
    }


}
