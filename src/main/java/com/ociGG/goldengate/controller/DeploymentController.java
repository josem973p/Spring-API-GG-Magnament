package com.ociGG.goldengate.controller;

import com.ociGG.goldengate.Services.HttpMethods;
import com.ociGG.goldengate.model.deployment;
import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.goldengate.GoldenGateClient;
import com.oracle.bmc.goldengate.model.DefaultStartDeploymentDetails;
import com.oracle.bmc.goldengate.model.DefaultStopDeploymentDetails;
import com.oracle.bmc.goldengate.model.StartDeploymentDetails;
import com.oracle.bmc.goldengate.model.StopDeploymentDetails;
import com.oracle.bmc.goldengate.requests.StartDeploymentRequest;
import com.oracle.bmc.goldengate.requests.StopDeploymentRequest;
import com.oracle.bmc.goldengate.responses.StartDeploymentResponse;
import com.oracle.bmc.goldengate.responses.StopDeploymentResponse;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/deployment")
public class DeploymentController {

    @Autowired
    HttpMethods httpMethods = new HttpMethods();

    @GetMapping("/start")
    public String start() throws IOException {

        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);

        /* Create a service client */
        GoldenGateClient client = new GoldenGateClient(provider);


        StartDeploymentDetails startDeploymentDetails = DefaultStartDeploymentDetails.builder().build();

        StartDeploymentRequest startDeploymentRequest = StartDeploymentRequest.builder()
                .deploymentId("ocid1.goldengatedeployment.oc1.iad.amaaaaaa7rhktwqaqacgp7gdowoizzvdxevn2jl3qlfqdgcqo2cnk7zxvzaq")
                .startDeploymentDetails(startDeploymentDetails).build();

        /* Send request to the Client */
        StartDeploymentResponse response = client.startDeployment(startDeploymentRequest);

        return "ok";
    }

    @GetMapping("/stop")
    public String stop() throws IOException {

        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);

        /* Create a service client */
        GoldenGateClient client = new GoldenGateClient(provider);


        StopDeploymentDetails stopDeploymentDetails = DefaultStopDeploymentDetails.builder().build();

        StopDeploymentRequest stopDeploymentRequest = StopDeploymentRequest.builder()
                .deploymentId("ocid1.goldengatedeployment.oc1.iad.amaaaaaa7rhktwqaqacgp7gdowoizzvdxevn2jl3qlfqdgcqo2cnk7zxvzaq")
                .stopDeploymentDetails(stopDeploymentDetails)
                .build();

        StopDeploymentResponse response = client.stopDeployment(stopDeploymentRequest);



        return "deteniendo";
    }

    @CrossOrigin
    @PostMapping("/start2")
    public String startPost(@RequestBody deployment dep) throws IOException {
        String deploy = dep.getDeploymentId();
        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);

        /* Create a service client */
        GoldenGateClient client = new GoldenGateClient(provider);


        StartDeploymentDetails startDeploymentDetails = DefaultStartDeploymentDetails.builder().build();

        StartDeploymentRequest startDeploymentRequest = StartDeploymentRequest.builder()
                .deploymentId(deploy)
                .startDeploymentDetails(startDeploymentDetails).build();

        /* Send request to the Client */
        StartDeploymentResponse response = client.startDeployment(startDeploymentRequest);

    return response.toString();
    }
    @CrossOrigin
    @PostMapping("/stop2")
    public String stopPost(@RequestBody deployment dep) throws IOException {
        String deploy = dep.getDeploymentId();

        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);

        /* Create a service client */
        GoldenGateClient client = new GoldenGateClient(provider);


        StopDeploymentDetails stopDeploymentDetails = DefaultStopDeploymentDetails.builder().build();

        StopDeploymentRequest stopDeploymentRequest = StopDeploymentRequest.builder()
                .deploymentId(deploy)
                .stopDeploymentDetails(stopDeploymentDetails)
                .build();
        try {
            StopDeploymentResponse response = client.stopDeployment(stopDeploymentRequest);
            System.out.println(response.toString());
        }catch (Exception e){

        }

        return "deteniendo con post";
    }


}
