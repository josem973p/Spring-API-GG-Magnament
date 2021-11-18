package com.ociGG.goldengate.Config;

import com.ociGG.goldengate.Entities.Params;
import com.ociGG.goldengate.Entities.StatsEntity;

import java.util.ArrayList;
import java.util.List;

public class StatsEntityConfig {

    public static List<StatsEntity> statsParamsList = new ArrayList<StatsEntity>();
    int conta =0;


    public void setParams( String params){

        if (conta==0){
            StatsEntity statsEntity = new StatsEntity(params);
            statsParamsList.add(statsEntity);
            conta++;
        }else{
            statsParamsList.get(0).setParam(params);

        }
    }

    public static List<StatsEntity> getStatsParamsList() {
        return statsParamsList;
    }

    public static void setStatsParamsList(List<StatsEntity> statsParamsList) {
        StatsEntityConfig.statsParamsList = statsParamsList;
    }
}
