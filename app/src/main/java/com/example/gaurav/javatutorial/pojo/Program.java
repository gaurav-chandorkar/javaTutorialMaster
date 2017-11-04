package com.example.gaurav.javatutorial.pojo;

/**
 * Created by gaurav on 9/3/17.
 */

public class Program {

    int programID;
    String programName;
    String programStr;
    int status;
    String output;



    public Program() {
    }

    public Program(int programID, String programName, String programStr, int status, String output) {
        this.programID = programID;
        this.programName = programName;
        this.programStr = programStr;
        this.status = status;
        this.output = output;
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramStr() {
        return programStr;
    }

    public void setProgramStr(String programStr) {
        this.programStr = programStr;
    }

    public int getStatus() {
        return status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
