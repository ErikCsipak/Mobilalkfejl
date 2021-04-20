package com.example.vercukornezegeto.entities.Resource;

public class Meta {
    private String versionId = null; //id
    private String lastUpdated = null; //instant
    private String source = null; //uri
    private String[] profile = null;//canonical
    private Coding[] security = null;
    private Coding[] tag = null;

    public Meta(){}

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        if (versionId.matches("[A-Za-z0-9\\-\\.]{1,64}")){
            this.versionId = versionId;
        } else{
            System.err.println("Invalid versionId: " + versionId + " (must match id regex)");
        }
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        if (lastUpdated.matches("([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)(\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))")) {
            this.lastUpdated = lastUpdated;
        } else {
            System.err.println("Invalid update format: " + lastUpdated + " (must match instant regex)");
        }
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        if (source.matches("\\S*")) {
            this.source = source;
        } else {
            System.err.println("Invalid source: " + source + " (must match uri regex)");
        }
    }

    public String[] getProfile() {
        return profile;
    }

    public void setProfile(String[] profile) {
        int i = 0;
        for (String s: profile){
            if (s.matches("[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")){
                this.profile[i] = s;
                i++;
            } else {
                System.err.println("Invalid profile: " + s + " (must match canonical regex)");
                this.profile = new String[0];
            }
        }
    }

    public Coding[] getSecurity() {
        return security;
    }

    public void setSecurity(Coding[] security) {
        this.security = security;
    }

    public Coding[] getTag() {
        return tag;
    }

    public void setTag(Coding[] tag) {
        this.tag = tag;
    }
}
