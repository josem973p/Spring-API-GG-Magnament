package com.ociGG.goldengate.controller;

import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Services.HttpMethods;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.StringTokenizer;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    HttpMethods httpMethods = new HttpMethods();

    @CrossOrigin
    @GetMapping("dotask")
    public String getReplicats() throws Exception {
        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println("url del  task"+url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/commands/execute");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.postData(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());
        StringBuilder stringBuilder = new StringBuilder(result.toString());
        stringBuilder.delete(0,450);
        System.out.println(stringBuilder.toString());
        StringTokenizer name = new StringTokenizer(stringBuilder.toString());
        StringBuilder resp = new StringBuilder();
     //   resp.append("{ \"");
        resp.append(" { \"message\":");

            int bandera= 0;
          while (name.hasMoreTokens() ) {


              if (bandera==0){
                  if (name.nextToken().equals("\"title\":")) {
                  //    resp.append("\"");
                      bandera=1;

                  }

              }
              if (bandera==1){
                  String palabra = name.nextToken();
                  System.out.println(palabra);
                  if (palabra.equals("\"code\":")){
                      break;
                  }else{
                      resp.append(palabra).append(" ");
                      System.out.println(resp.toString());
                  }

              }

              }

        int  tam = resp.length();
        resp.deleteCharAt(tam-1);
        int  tam2 = resp.length();
        resp.deleteCharAt(tam2-1);
        resp.append(" }");



        return resp.toString();
    }
}
