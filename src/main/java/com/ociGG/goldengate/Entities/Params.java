package com.ociGG.goldengate.Entities;

public class Params {

    String schema;
    String name;
    String processName;
    String processType;

    public Params() {
    }

    public Params(String schema, String name, String processName, String processType) {
        this.schema = schema;
        this.name = name;
        this.processName = processName;
        this.processType = processType;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }
}
