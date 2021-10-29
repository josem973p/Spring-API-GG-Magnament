package com.ociGG.goldengate.controller;

import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Entities.BaseEntitieData;
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



    @CrossOrigin
    @PostMapping
    public void Credentials(@RequestBody BaseEntitieData Data) throws Exception {

        credentialsConfig.Credentials(Data.getUrl(),Data.getUser(),Data.getPassword());

    }

    @CrossOrigin
    @PostMapping("/param")
    public void Param(@RequestBody BaseEntitieData Data) throws Exception {

        CredentialsConfig.getCredentials().get(0).setParametro(Data.getParametro());

    }
}
