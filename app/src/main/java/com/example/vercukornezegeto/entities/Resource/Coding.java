package com.example.vercukornezegeto.entities.Resource;

public class Coding {
    private String system = null;//uri
    private String version=null;
    private String code = null; //code
    private String display = null;
    private Boolean userSelected = null;

    public Coding(){}

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        if (system != null) {
            if (system.matches("\\S*")) {
                this.system = system;
            } else {
                System.err.println("Invalid system: " + system + " (must match uri regex)");
            }
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code != null) {
            if (code.matches("[^\\s]+(\\s[^\\s]+)*")) {
                this.code = code;
            } else {
                System.err.println("Invalid code: " + code + " (must match code regex)");
            }
        }
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Boolean getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(Boolean userSelected) {
        this.userSelected = userSelected;
    }
}
