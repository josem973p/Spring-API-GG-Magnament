package com.ociGG.goldengate.Config;

import com.ociGG.goldengate.Entities.Params;

import java.util.ArrayList;
import java.util.List;

public class ParamsConfig {

    public static List<Params>  paramsList = new ArrayList<Params>();
    int conta =0;


    public void setParams(String schema,String name, String processName, String processType){

        if (conta==0){
            Params params = new Params(schema, name , processName, processType);
            paramsList.add(params);
            conta++;
        }else{
            paramsList.get(0).setSchema(schema);
            paramsList.get(0).setName(name);
            paramsList.get(0).setProcessName(processName);
            paramsList.get(0).setProcessType(processType);
        }
    }

    public static List<Params> getParamsList() {
        return paramsList;
    }

    public static void setParamsList(List<Params> paramsList) {
        ParamsConfig.paramsList = paramsList;
    }
}
