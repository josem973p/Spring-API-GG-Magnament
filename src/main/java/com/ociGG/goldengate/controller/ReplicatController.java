package com.ociGG.goldengate.controller;


import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Entities.BaseEntitieData;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replicat")
public class ReplicatController {

    @Autowired
    HttpMethods httpMethods = new HttpMethods();

    @CrossOrigin
    @GetMapping("/listReplicat")
    public String getReplicats() throws Exception {
        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/replicats");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.GetExtractAndReplicats(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/replicatListReport")
    public String getReplicatReport() throws Exception {

        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/replicats/");
        sb.append(CredentialsConfig.getCredentials().get(0).getParametro()).append("/info/reports");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/replicatDetail")
    public String getReplicatDetail() throws Exception {

        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/replicats/");
        sb.append(CredentialsConfig.getCredentials().get(0).getParametro());
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }


    @CrossOrigin
    @GetMapping("/replicatStatus")
    public String getReplicatStatus() throws Exception {

        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/replicats/");
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

        sb.append(url).append("/services/v2/replicats");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.GetExtractAndReplicats(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        System.out.println(result);

        String datos = httpMethods.data(result,"replicat");
        System.out.println("resultado"+datos);

        return datos;
    }
}
