package com.example.vercukornezegeto.entities.Resource;

public class Extension {
    private String url; //uri
    private String value=null;
    public Extension(String url){
        if (url.matches("\\S*")) {
            this.url = url;
        } else {
            System.err.println("Invalid url: " + url + " (must match uri regex)");
            this.url = null;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url.matches("\\S*")) {
            this.url = url;
        } else {
            System.err.println("Invalid url: " + url + " (must match uri regex)");
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
