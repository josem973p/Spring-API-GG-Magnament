package com.ociGG.goldengate.controller;


import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Entities.BaseEntitieData;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.StringTokenizer;

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

        StringBuilder stringBuilder = new StringBuilder (result);
        stringBuilder.delete(0 ,795);
        stringBuilder.replace(0,0,"{ ");
        int tam = stringBuilder.length();
        stringBuilder.delete(tam-7,tam);

        StringTokenizer st = new StringTokenizer(stringBuilder.toString());
        StringBuilder json = new StringBuilder ();
        String palabra, SigPalabra;
        json.append("{ ");
        while (st.hasMoreTokens()){

            palabra= st.nextToken();
            if (palabra.equals("{") || palabra.equals("},") || palabra.equals("}") || palabra.equals("],")){
                continue;
            }else if(palabra.equals("\"alias\":") || palabra.equals("\"domain\":") ||  palabra.equals("\"intent\":") || palabra.equals("\"encryptionProfile\":")||  palabra.equals("\"name\":") || palabra.equals("\"path\":") || palabra.equals("\"sizeMB\":") || palabra.equals("\"sequenceLength\":") || palabra.equals("\"sequence\":") || palabra.equals("\"offset\":") || palabra.equals("\"type\":") || palabra.equals("\"csn\":") || palabra.equals("\"status\":")){
                if (st.hasMoreTokens()){
                    SigPalabra = st.nextToken();
                    System.out.println(SigPalabra);
                    if (SigPalabra.endsWith(",")){



                        json.append(palabra).append(SigPalabra);
                    }else{
                        StringBuilder addComma = new StringBuilder (SigPalabra);
                        System.out.println("soy el addComma " +addComma);
                        addComma.append(", ");
                        json.append(palabra).append(addComma.toString());
                    }


                }else {
                    break;
                }


            }else{
                continue;
            }

        }
        int  size = json.length();
        json.deleteCharAt(size-2);
        json.append(" }");
        System.out.println(json.toString());


        return json.toString();
    }

    @CrossOrigin
    @GetMapping("/replicatDetailFull")
    public String getReplicatDetailFull() throws Exception {

        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/replicats/");
        sb.append(CredentialsConfig.getCredentials().get(0).getParametro());
        String urlfinal = sb.toString();
        System.out.println("esta es la url para el detalles"+urlfinal);

        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        StringBuilder stringBuilder = new StringBuilder (result);
        stringBuilder.delete(0 ,795);
        stringBuilder.replace(0,0,"{ ");
        int tam = stringBuilder.length();
        stringBuilder.delete(tam-7,tam);


        return stringBuilder.toString();
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
