package com.ociGG.goldengate.controller;

import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Entities.BaseEntitieData;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    HttpMethods httpMethods = new HttpMethods();

    @CrossOrigin
    @GetMapping("/criticalEvents")
    public String getReplicats() throws Exception {
        String url = CredentialsConfig.getCredentials().get(0).getUrl();
      //  System.out.println(url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/logs/events");
        String urlfinal = sb.toString();
   //     System.out.println(urlfinal);
        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());
        StringBuilder stringBuilder = new StringBuilder (result);
        stringBuilder.delete(0,5);
        int tam =stringBuilder.length();
        stringBuilder.delete(tam-6,tam);
         String[] logs;
        String[] values;
         String cad = stringBuilder.toString();
        System.out.println("___________CADENA RESPUESTA___________________");
        System.out.println(cad);
        System.out.println("_______________________________");

         logs= cad.split("[a-zA-Z]\\.[0-9]");
        String cadena;
        StringBuilder json = new StringBuilder ();
        json.append("[ ");
     //   System.out.println("el tamaño del  arreglo es : "+logs.length);
         for (int i = 0;i< logs.length;i++){
             StringBuilder Builder = new StringBuilder ();
            cadena= Builder.append("2").append(logs[i]).toString();
             logs[i]= cadena;
           //  System.out.println("mensaje "+i+"  "+logs[i]);
             values= logs[i].split("0000");

             json.append(" { \"date\": \"").append(values[0]).append("\",");
             if (values[1].contains("\"") | values[1].contains("\"\"") ){

                 char[] aCaracteres = values[1].toCharArray();

                 for (int k=0;k< aCaracteres.length;k++){
                     if (aCaracteres[k]=='\"'){
                         aCaracteres[k]='\'';
                     }
                 }
                 String ncad = aCaracteres.toString();
             //    System.out.println(values[1]);
                 json.append("\"message\": \"").append(ncad).append("\" },");
             }else{

                 json.append("\"message\": \"").append(values[1]).append("\" },");

             }

            // System.out.println(logs[i]);
         }
         int size = json.length();
         json.deleteCharAt(size-1);
         json.append("]");


      //  System.out.println(json.toString());


        return json.toString();
    }

    @CrossOrigin
    @GetMapping("/pruebajson")
    public String getReplicats2() throws Exception {
        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/logs/events");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);
        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());
        StringBuilder stringBuilder = new StringBuilder (result);
        stringBuilder.delete(0,5);
        int tam =stringBuilder.length();
        stringBuilder.delete(tam-6,tam);
        String[] logs;
        String[] values;
        String cad = stringBuilder.toString();
        //corta la cadena cuando  haya uno letra y un punto seguido de un numero  teniendo como resultado
        // la fecja y el mensaje juntos
        logs= cad.split("[a-zA-Z]\\.[0-9]");
        String cadena;
        StringBuilder json = new StringBuilder ();
        json.append("[ ");
        System.out.println("el tamaño del  arreglo es : "+logs.length);
        for (int i = 0;i< logs.length;i++){
            StringBuilder Builder = new StringBuilder ();
            cadena= Builder.append("2").append(logs[i]).toString();
            logs[i]= cadena;
            //  System.out.println("mensaje "+i+"  "+logs[i]);

            //values tendra  2 valores gracias al split, tendra la fecha en la pos 0 y el mensaje en la pos 1
            values= logs[i].split("0000");
           // for (int j=0;j<values.length;j++){
           //     System.out.println(j+"valor de cadena "+values[j]);
           // }

            json.append(" { \"date\": \"").append(values[0]).append("\",");
            if (values[1].contains("\"") | values[1].contains("\"\"") ){

                char[] aCaracteres = values[1].toCharArray();

                for (int k=0;k< aCaracteres.length;k++){
                    if (aCaracteres[k]=='\"'){
                        aCaracteres[k]='^';
                    }
                }
                String ncad = aCaracteres.toString();
                System.out.println(values[1]);
                json.append("\"message\": \"").append(ncad).append("\" },");
            }else{

                json.append("\"message\": \"").append(values[1]).append("\" },");

            }



            // System.out.println(logs[i]);
        }
        int size = json.length();
        json.deleteCharAt(size-1);
        json.append("]");


        System.out.println(json.toString());


        return json.toString();
    }

}
