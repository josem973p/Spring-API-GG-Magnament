package com.ociGG.goldengate.controller;


import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Entities.BaseEntitieData;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessagesController {


    @Autowired
    HttpMethods httpMethods = new HttpMethods();

    @CrossOrigin
    @GetMapping("/getMessages")
    public String getReplicats() throws Exception {
        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/messages");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);
        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        StringBuilder builder = new StringBuilder (result);
        builder.delete(0,775);
        int size = builder.length();
        builder.delete(size-55,size);
        int size2 = builder.length()/1000;


        String[] logs;
        String[] values;
        String cadena;
        StringBuilder json = new StringBuilder ();
        json.append("[ ");

        String cad = builder.toString();

        logs= cad.split("\\.\",");
        System.out.println(logs.length);


        for (int i=0;i<500;i++){
            System.out.println(logs[i]);
           // values= cad.split("0000");
          //  json.append(" { \"date\": \"").append(values[0]).append("\",");
          //  json.append("\"message\": \"").append(values[1]).append("\" },");
         ///   if (i==499){
          //      json.append("\"message\": \"").append(values[1]).append("\" }");
            }



      //  }

        json.append("]");


        System.out.println(json.toString());
        return builder.toString();
    }
}
