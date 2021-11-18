package com.ociGG.goldengate.controller;

import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Config.ParamsConfig;
import com.ociGG.goldengate.Config.StatsEntityConfig;
import com.ociGG.goldengate.Entities.BaseEntitieData;
import com.ociGG.goldengate.Entities.Params;
import com.ociGG.goldengate.Entities.StatsEntity;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credentials")
public class CredentialsController {

    @Autowired
    HttpMethods httpMethods = new HttpMethods();

    @Autowired
    CredentialsConfig credentialsConfig = new CredentialsConfig();


    ParamsConfig paramsConfig = new ParamsConfig();

    StatsEntityConfig statsEntityConfig = new StatsEntityConfig();



    @CrossOrigin
    @PostMapping
    public void Credentials(@RequestBody BaseEntitieData Data) throws Exception {

        credentialsConfig.Credentials(Data.getUrl(),Data.getUser(),Data.getPassword());

    }

    @CrossOrigin
    @PostMapping("/param")
    public void Param(@RequestBody BaseEntitieData Data) throws Exception {

        CredentialsConfig.getCredentials().get(0).setParametro(Data.getParametro());
        System.out.println("hola soy el parametro que enviaste"+Data.getParametro());
        System.out.println("este es el parametro que se estalecio"+CredentialsConfig.getCredentials().get(0).getParametro());

    }

    @CrossOrigin
    @PostMapping("/taskParams")
    public void taskParams(@RequestBody Params Data) throws Exception {

        paramsConfig.setParams(Data.getSchema(),Data.getName(),Data.getProcessName(),Data.getProcessType());

    }

    @CrossOrigin
    @PostMapping("/statsParams")
    public void statsParams(@RequestBody StatsEntity Data) throws Exception {
        statsEntityConfig.setParams(Data.getParam());

    }


}
