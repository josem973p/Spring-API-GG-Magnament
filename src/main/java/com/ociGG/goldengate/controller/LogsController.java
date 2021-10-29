package com.ociGG.goldengate.controller;

import com.ociGG.goldengate.Entities.BaseEntitieData;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    HttpMethods httpMethods = new HttpMethods();

    @CrossOrigin
    @GetMapping("/criticalEvents")
    public String getReplicats(@RequestBody BaseEntitieData baseEntitieData) throws Exception {
        String result=   httpMethods.peticionHttpGet("https://147.154.7.27/services/v2/logs/events",baseEntitieData.getUser(),baseEntitieData.getPassword());

        return result;
    }

}
