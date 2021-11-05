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
         String cad = stringBuilder.toString();
        System.out.println(cad);
        String hola = "hola-manuel-como-estas";
     //   String separador = Pattern.quote(".");
         logs= cad.split("[a-z]\\.[0-9]");

        System.out.println(logs.length);
         for (int i = 0;i< logs.length;i++){
             System.out.println(logs[i]);
         }


        return stringBuilder.toString();
    }

}
