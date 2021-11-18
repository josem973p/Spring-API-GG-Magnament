package com.ociGG.goldengate.Entities;

import java.util.ArrayList;
import java.util.List;

public class ReplicatsEntity {

    public static List<String> replicats = new ArrayList<String>();


    public static void llenalist(){
        replicats.add("qw");
        replicats.add("er");
        replicats.add("ty");
        replicats.add("ui");
    }




    public static List<String> getreplicats() {
        return replicats;
    }

    public static void setreplicats(List<String> statsParamsList) {
        ReplicatsEntity.replicats = statsParamsList;
    }
}
