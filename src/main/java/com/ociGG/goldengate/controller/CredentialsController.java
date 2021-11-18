package com.ociGG.goldengate.controller;

import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Config.ParamsConfig;
import com.ociGG.goldengate.Config.StatsEntityConfig;
import com.ociGG.goldengate.Entities.BaseEntitieData;
import com.ociGG.goldengate.Entities.Params;
import com.ociGG.goldengate.Entities.ReplicatsEntity;
import com.ociGG.goldengate.Entities.StatsEntity;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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




        String url = Data.getUrl();
        System.out.println(url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/replicats");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);
        //cambia la variable por result y descomenta el metodo
        //   String result2=   httpMethods.GetExtractAndReplicats(urlfinal,Data.getUser(),Data.getPassword());
        String result = "{ \"$schema\":\"api:standardResponse\",\"links\":[ { \"href\":\"http://localhost:11001/services/v2/replicats\",\"mediaType\":\"application/json\",\"rel\":\"canonical\" }, { \"href\":\"http://localhost:11001/services/v2/replicats\",\"mediaType\":\"application/json\",\"rel\":\"self\" },{\"href\":\"http://localhost:11001/services/v2/metadata-catalog/replicats\",\"mediaType\":\"application/schema+json\",\"rel\":\"describedby\"} ], \"messages\":[ ],\"response\":{ \"$schema\":\"ogg:collection\", \"items\":[ { \"$schema\":\"ogg:collectionItem\",\"links\":[ { \"href\":\"http://localhost:11001/services/v2/replicats\",\"mediaType\":\"application/json\",\"rel\":\"parent\" },{ \"href\":\"http://localhost:11001/services/v2/replicats/REP2\",\"mediaType\":\"application/json\", \"rel\":\"canonical\" } ], \"name\": \"REP2\" },{ \"$schema\":\"ogg:collectionItem\",\"links\":[ {\"href\":\"http://localhost:11001/services/v2/replicats\", \"mediaType\":\"application/json\",\"rel\":\"parent\"},{ \"href\":\"http://localhost:11001/services/v2/replicats/REP2\",\"mediaType\":\"application/json\",\"rel\":\"canonical\" } ], \"name\": \"RDS\"  }, {\"$schema\":\"ogg:collectionItem\",\"links\":[ { \"href\":\"http://localhost:11001/services/v2/replicats\",\"mediaType\":\"application/json\",\"rel\":\"parent\" },{\"href\":\"http://localhost:11001/services/v2/replicats/REP2\",\"mediaType\":\"application/json\",\"rel\":\"canonical\" } ], \"name\": \"AWS\"  } ] }}";
        StringBuilder replicats = new StringBuilder (result);
        System.out.println(result);
        replicats.delete(0,400);
        String palabra;
        String nombreRep;
        StringTokenizer names = new StringTokenizer(replicats.toString());
        List<String> replist = new ArrayList<String>();
        while (names.hasMoreTokens()){
            palabra= names.nextToken();
            if(palabra.equals("\"name\":")){
                nombreRep= names.nextToken();
                StringBuilder name = new StringBuilder (nombreRep.toString());
                name.delete(0,1);
                int tam = name.length();
                name.delete(tam-1,tam);

                System.out.println("este es el replicat"+name.toString());
                replist.add(name.toString());

            }

        }
        ReplicatsEntity.setreplicats(replist);



        //________

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
