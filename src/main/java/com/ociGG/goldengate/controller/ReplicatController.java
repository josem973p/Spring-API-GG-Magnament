package com.ociGG.goldengate.controller;


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
    @GetMapping("/replicat")
    public String getReplicats(@RequestBody BaseEntitieData baseEntitieData) throws Exception {
        String result=   httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/replicats",baseEntitieData.getUser(),baseEntitieData.getPassword());

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
