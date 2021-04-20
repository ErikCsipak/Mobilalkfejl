package com.example.vercukornezegeto.entities.Resource;

public class Narrative {
    private String status;
    private String xhtml;

    public Narrative(String status, String xhtml) {
        if (status.matches("[^\\s]+(\\s[^\\s]+)*")){
            this.status = status;
        } else {
            System.err.println("Invalid status: " + status + " (must match code regex)");
            this.status = null;
        }
        this.xhtml = xhtml;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.matches("[^\\s]+(\\s[^\\s]+)*")){
            this.status = status;
        } else {
            System.err.println("Invalid status: " + status + " (must match code regex)");
        }
    }

    public String getXhtml() {
        return xhtml;
    }

    public void setXhtml(String xhtml) {
        this.xhtml = xhtml;
    }
}
