package com.ociGG.goldengate;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GoldengateApplication {

	public GoldengateApplication() throws IOException {
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GoldengateApplication.class, args);

	}


}
