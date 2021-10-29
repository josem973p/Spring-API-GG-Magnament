package com.ociGG.goldengate;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.goldengate.GoldenGateClient;
import com.oracle.bmc.goldengate.model.*;
import com.oracle.bmc.goldengate.requests.*;
import com.oracle.bmc.goldengate.responses.*;

import java.io.IOException;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.Arrays;

public class startDeployment {

    final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
    final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);

    /* Create a service client */
    GoldenGateClient client = new GoldenGateClient(provider);

    /* Create a request and dependent object(s). */
    StartDeploymentDetails startDeploymentDetails = DefaultStartDeploymentDetails.builder().build();

    StartDeploymentRequest startDeploymentRequest = StartDeploymentRequest.builder()
            .deploymentId("ocid1.test.oc1..<unique_ID>EXAMPLE-deploymentId-Value")
            .startDeploymentDetails(startDeploymentDetails)
            .ifMatch("EXAMPLE-ifMatch-Value")
            .opcRequestId("GATEB3MHYHJXD0I40GSC<unique_ID>")
            .opcRetryToken("EXAMPLE-opcRetryToken-Value").build();

    /* Send request to the Client */
    StartDeploymentResponse response = client.startDeployment(startDeploymentRequest);


    public startDeployment() throws IOException {
    }
}
