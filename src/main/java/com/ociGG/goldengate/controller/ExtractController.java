package com.ociGG.goldengate.controller;


import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Entities.BaseEntitieData;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    HttpMethods httpMethods = new HttpMethods();
    @Autowired
    CredentialsConfig credentialsConfig = new CredentialsConfig();




    @CrossOrigin
    @GetMapping("/listExtract")
    public String getExtracts() throws Exception {
        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/extracts");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.GetExtractAndReplicats(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }

    @CrossOrigin
    @PostMapping
    public String ExtractList(@RequestBody BaseEntitieData baseEntitieData) throws Exception {
        System.out.println(baseEntitieData.getUrl());
        System.out.println(CredentialsConfig.credentials.get(0).getUser());
        System.out.println(CredentialsConfig.credentials.get(0).getPassword());
        String result=   httpMethods.peticionHttpGet(baseEntitieData.getUrl(),CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());
   // return  new ResponseEntity<String>(httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/extracts",baseEntitieData.getUser(),baseEntitieData.getPassword()), HttpStatus.ACCEPTED);
        System.out.println(result);
        return result;
    }

    @CrossOrigin
    @GetMapping("/ListReport")
    public String getReplicatReport() throws Exception {

        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/extracts/");
        sb.append(CredentialsConfig.getCredentials().get(0).getParametro()).append("/info/reports");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/extractDetail")
    public String getReplicatDetail() throws Exception {

        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/extracts/");
        sb.append(CredentialsConfig.getCredentials().get(0).getParametro());
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/extractStatus")
    public String getReplicatStatus() throws Exception {

        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/extracts/");
        sb.append(CredentialsConfig.getCredentials().get(0).getParametro()).append("/info/status");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/data")
    public String getReplicatStatusData() throws Exception {

     //   String url = CredentialsConfig.getCredentials().get(0).getUrl();
   //     System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
    //    StringBuilder sb = new StringBuilder ();

     //   sb.append(url).append("/services/v2/extracts/");
      //  sb.append(CredentialsConfig.getCredentials().get(0).getParametro()).append("/info/status");
    //    String urlfinal = sb.toString();
       // System.out.println(urlfinal);


        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/extracts");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.GetExtractAndReplicats(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        System.out.println(result);

        String datos = httpMethods.data(result);

        return datos;
    }
}
