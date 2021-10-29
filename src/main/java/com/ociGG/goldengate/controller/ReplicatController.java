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

        String result=   httpMethods.peticionHttpGet(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/replicatListReport")
    public String getReplicatReport(@RequestBody BaseEntitieData baseEntitieData) throws Exception {
        String result=   httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/replicats/RDEM0/info/reports",baseEntitieData.getUser(),baseEntitieData.getPassword());

        return result;
    }

    @CrossOrigin
    @GetMapping("/replicatDetail")
    public String getReplicatDetail(@RequestBody BaseEntitieData baseEntitieData) throws Exception {
        String result=   httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/replicats/RDEM0",baseEntitieData.getUser(),baseEntitieData.getPassword());

        return result;
    }


    @CrossOrigin
    @GetMapping("/replicatStatus")
    public String getReplicatStatus(@RequestBody BaseEntitieData baseEntitieData) throws Exception {
        String result=   httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/replicats/RDEM0/info/status",baseEntitieData.getUser(),baseEntitieData.getPassword());

        return result;
    }
}
