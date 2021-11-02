package com.ociGG.goldengate.controller;

import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        return result;
    }
}
