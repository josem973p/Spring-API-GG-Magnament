package com.ociGG.goldengate.controller;


import com.ociGG.goldengate.Config.CredentialsConfig;
import com.ociGG.goldengate.Config.StatsEntityConfig;
import com.ociGG.goldengate.Services.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.StringTokenizer;



@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    HttpMethods httpMethods = new HttpMethods();

    String respuesta = "{ \"$schema\": \"api:standardResponse\", \"links\": [ {     \"rel\": \"canonical\", \"href\": \"https://172.19.16.252/services/v2/replicats/ROCAT2/command\",\"mediaType\": \"application/json\"},{ \"rel\": \"self\",\"href\": \"https://172.19.16.252/services/v2/replicats/ROCAT2/command\",\"mediaType\": \"application/json\"}],\"messages\": [],\"response\": { \"$schema\": \"er:commandResult\",\"reply\": \"OKNODOT Start of statistics at 2021-11-16 17:13:57. Integrated Replicat statistics:Total transactions                      4.00  Redirected                    0.00  Replicated procedures                   0.00 DDL operations                0.00 Stored procedures                0.00 Datatype functionality                 0.00 Operation type functionality    0.00 Event actions                           0.00 Direct transactions ratio         0.00% Replicating from CDOCPRBD.OMNICANAL.VARIABLES_AMBIENTE to CDOCPRBD.OMNICANAL.VARIABLES_AMBIENTE: *** Hourly statistics since 2021-11-16 17:00:00 *** No database operations have been performed. Replicating from CDOCPRBD.OMNICANAL.VARIABLES_AMBIENTE to CDOCPRBD.C##GGADMIN.EXCEPTION_ROCATLOG: *** Hourly statistics since 2021-11-16 17:00:00 *** No database operations have been performed. End of statistics.,\"replyData\": {\"$schema\": \"ogg:stats\",\"stats\": [{\"integratedReplicat\": true,\"since\": \"startup\", \"operations\":  [{ \"operation\": \"transactions\", \"count\": 4 },{ \"operation\": \"redirected\",\"count\": 0},{\"operation\": \"replicatedProcedures\",\"count\": 0},{\"operation\": \"ddlOperations\",\"count\": 0},{\"operation\": \"storedProcedures\",\"count\": 0\n},{\"operation\": \"datatypeFunctionality\",\"count\": 0},{\"operation\": \"optypeFunctionality\",\"count\": 0},{\"operation\": \"eventActions\",\"count\": 0},{\"operation\": \"directTransactionRatio\",\"count\": 0.0 } ] },{\"since\": \"2021-11-16T17:00:00.000Z\",\"sourceTable\": \"CDOCPRBD.OMNICANAL.VARIABLES_AMBIENTE\",\"targetTable\": \"CDOCPRBD.OMNICANAL.VARIABLES_AMBIENTE\"},\"since\": \"2021-11-16T17:00:00.000Z\",\"sourceTable\": \"CDOCPRBD.OMNICANAL.VARIABLES_AMBIENTE\",\"targetTable\": \"CDOCPRBD.C##GGADMIN.EXCEPTION_ROCATLOG\"}]}}}";



    @CrossOrigin
    @GetMapping("/repStats")
    public String getRepStats() throws Exception {
        StatsEntityConfig statsEntityConfig = new StatsEntityConfig();

        String url = CredentialsConfig.getCredentials().get(0).getUrl();
        System.out.println("url del  task"+url);
        StringBuilder sb = new StringBuilder ();

        sb.append(url).append("/services/v2/replicats/").append(StatsEntityConfig.getStatsParamsList().get(0).getParam()).append("/command");
        String urlfinal = sb.toString();
        System.out.println(urlfinal);
        String result=   httpMethods.getStatsData(urlfinal,CredentialsConfig.credentials.get(0).getUser(),CredentialsConfig.credentials.get(0).getPassword());

        /**
         * Solo cambia la variable respuesta por la variable result del  metodo invocado
         *
         * **/
        StringBuilder stringBuilder = new StringBuilder (respuesta);
        StringBuilder json = new StringBuilder ();




        stringBuilder.delete(1,420);
        StringTokenizer st = new StringTokenizer(respuesta);
        String palabra = null;
        String token= null;
        while (st.hasMoreTokens()) {
            palabra= st.nextToken();
          //  System.out.println(palabra);
            if (palabra.equals("\"operations\":")) {
                System.out.println("SOY LA PALABRA!!!!!!"+palabra);
                token=st.nextToken();
                json.append(token);

                while (!token.equals("]")){
                 token=   st.nextToken();
                 json.append(" "+token);

                }


            }
        }

            return json.toString();
      //  return stringBuilder.toString();
    }
}
