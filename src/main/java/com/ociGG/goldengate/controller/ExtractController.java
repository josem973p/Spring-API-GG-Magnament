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




    @CrossOrigin
    @GetMapping("/listExtract")
    public String getReplicats() throws Exception {
        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println(url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/extracts");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);

        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

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
        String result=   httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/extracts/EDEM0/info/reports",CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/extractDetail")
    public String getReplicatDetail(@RequestBody BaseEntitieData baseEntitieData) throws Exception {
        String result=   httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/extracts/EDEM0",baseEntitieData.getUser(),baseEntitieData.getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/extractStatus")
    public String getReplicatStatus(@RequestBody BaseEntitieData baseEntitieData) throws Exception {
        String result=   httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/extracts/EDEM0/info/status",baseEntitieData.getUser(),baseEntitieData.getPassword());

        return result;
    }

}
