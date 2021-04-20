package com.example.vercukornezegeto.entities;

import com.example.vercukornezegeto.entities.Resource.Extension;
import com.example.vercukornezegeto.entities.Resource.Meta;
import com.example.vercukornezegeto.entities.Resource.Narrative;

public class Observation {
    private String id = null; //id
    private String implicitRules = null; //uri
    private String language = null;
    private Meta meta = null;
    private Extension[] extension = null;
    private Extension[] modifierExtension = null;
    private Narrative text = null;

    public Observation(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.matches("[A-Za-z0-9\\-\\.]{1,64}")){
            this.id = id;
        } else {
            System.err.println("Invalid id: "+id+" (character limit is 64)");
        }
    }

    public String getImplicitRules() {
        return implicitRules;
    }

    public void setImplicitRules(String implicitRules) {
        if (implicitRules.matches("\\S*")){
            this.implicitRules = implicitRules;
        } else {
            System.err.println("Invalid implicitRules: "+implicitRules+" (must match uri regex)");
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if (language.matches("[^\\s]+(\\s[^\\s]+)*")){
            this.language = language;
        } else {
            System.err.println("Invalid language: " + language + " (must match code regex)");
        }
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Extension[] getExtension() {
        return extension;
    }

    public void setExtension(Extension[] extension) {
        this.extension = extension;
    }

    public Extension[] getModifierExtension() {
        return modifierExtension;
    }

    public void setModifierExtension(Extension[] modifierExtension) {
        this.modifierExtension = modifierExtension;
    }

    public Narrative getText() {
        return text;
    }

    public void setText(Narrative text) {
        this.text = text;
    }
}
