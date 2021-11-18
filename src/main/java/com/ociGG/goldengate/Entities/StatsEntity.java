package com.ociGG.goldengate.Entities;

public class StatsEntity {

    private  String command;
    private  String arguments;
    private String param;

    public StatsEntity() {

    }

    public StatsEntity(String param) {
        this.command="STATS";
        this.arguments = "HOURLY";
        this.param= param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
