package com.ociGG.goldengate;

import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Entities.ReplicatsEntity;
import com.ociGG.goldengate.Services.HttpMethods;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class GoldengateApplication {

	public GoldengateApplication() throws IOException {
	}

	@Autowired
	HttpMethods httpMethods = new HttpMethods();


	int conta =0;


	@Scheduled(fixedRate = 5000)
	public void checkState() throws Exception {

		if (!CredentialsConfig.credentials.isEmpty() ){
			System.out.println("hola perros");

			//metodo de prueba para llenar solo una vez

			/**
			if (conta==0){
				ReplicatsEntity.llenalist();
				conta++;
			}
**/

			for (int i = 0; i < ReplicatsEntity.getreplicats().size(); i++) {
				String url = CredentialsConfig.getCredentials().get(0).getUrl();
				//System.out.println(CredentialsConfig.getCredentials().get(0).getParametro());
				StringBuilder sb = new StringBuilder ();

				sb.append(url).append("/services/v2/replicats/");
				sb.append(ReplicatsEntity.getreplicats().get(i)).append("/info/status");
				String urlfinal = sb.toString();
				System.out.println(urlfinal);

				String status = HttpMethods.peticionStatus(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

				System.out.println("este es el estatus del proceso "+ReplicatsEntity.getreplicats().get(i)+": "+status);

			}
		}


	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GoldengateApplication.class, args);

	}


}
