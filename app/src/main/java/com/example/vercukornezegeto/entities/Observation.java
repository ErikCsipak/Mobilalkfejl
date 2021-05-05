package com.example.vercukornezegeto.entities;

import com.example.vercukornezegeto.entities.Resource.Annotation;
import com.example.vercukornezegeto.entities.Resource.Component;
import com.example.vercukornezegeto.entities.Resource.ReferenceRange;
import com.example.vercukornezegeto.entities.Resource.CodeableConcept;
import com.example.vercukornezegeto.entities.Resource.Extension;
import com.example.vercukornezegeto.entities.Resource.Identifier;
import com.example.vercukornezegeto.entities.Resource.Meta;
import com.example.vercukornezegeto.entities.Resource.Narrative;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Observation {
    /*private String id = null; //id
    private String implicitRules = null; //uri
    private String language = null;
    private Meta meta = null;
    private Extension[] extension = null;
    private Extension[] modifierExtension = null;
    private Narrative text = null;
    private Identifier[] identifier;*/
    private ArrayList<String> basedOn; //Fullfills plan, proposal or order
    //private String[] partOf; //part of referenced event
    private String status; //code
    //private CodeableConcept[] category;
    private CodeableConcept code; //type of observation
    private String subject; //who the obs is about
    private ArrayList<String> focus; // what the obs is about
    /*private String encounter; //healthcare event during obs was made
    private Instant effectiveInstant; //clinically relevant time of obs
    private Instant issued; //time this version was made available
    private String[] performer; //who is responsible for obs
    private String valueString; // actual result
    private CodeableConcept dataAbsentReason; //why result is missing
    private CodeableConcept[] interpretation; //high, low, normal,...
    private Annotation[] note; // comments about obs
    private CodeableConcept bodySite; // observed bodypart
    private CodeableConcept method; //how it was done
    private String specimen; //specimen used for obs
    private String device; //device used
    private ReferenceRange[] referenceRange;
    private String[] hasMember; // Related resource that belongs to the obs group
    private String[] derivedFrom; //Related measurements the obs is made from*/
    private ArrayList<Component> component; //component result

    /*public Identifier[] getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier[] identifier) {
        this.identifier = identifier;
    }*/


   /* public String[] getPartOf() {
        return partOf;
    }

    public void setPartOf(String[] partOf) {
        this.partOf = partOf;
    }

    public CodeableConcept[] getCategory() {
        return category;
    }

    public void setCategory(CodeableConcept[] category) {
        this.category = category;
    }*/

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    /*public String getEncounter() {
        return encounter;
    }

    public void setEncounter(String encounter) {
        this.encounter = encounter;
    }

    public Instant getEffectiveInstant() {
        return effectiveInstant;
    }

    public void setEffectiveInstant(Instant effectiveInstant) {
        this.effectiveInstant = effectiveInstant;
    }

    public Instant getIssued() {
        return issued;
    }

    public void setIssued(Instant issued) {
        this.issued = issued;
    }

    public String[] getPerformer() {
        return performer;
    }

    public void setPerformer(String[] performer) {
        this.performer = performer;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public CodeableConcept getDataAbsentReason() {
        return dataAbsentReason;
    }

    public void setDataAbsentReason(CodeableConcept dataAbsentReason) {
        this.dataAbsentReason = dataAbsentReason;
    }

    public CodeableConcept[] getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(CodeableConcept[] interpretation) {
        this.interpretation = interpretation;
    }

    public Annotation[] getNote() {
        return note;
    }

    public void setNote(Annotation[] note) {
        this.note = note;
    }

    public CodeableConcept getBodySite() {
        return bodySite;
    }

    public void setBodySite(CodeableConcept bodySite) {
        this.bodySite = bodySite;
    }

    public CodeableConcept getMethod() {
        return method;
    }

    public void setMethod(CodeableConcept method) {
        this.method = method;
    }

    public String getSpecimen() {
        return specimen;
    }

    public void setSpecimen(String specimen) {
        this.specimen = specimen;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public ReferenceRange[] getReferenceRange() {
        return referenceRange;
    }

    public void setReferenceRange(ReferenceRange[] referenceRange) {
        this.referenceRange = referenceRange;
    }

    public String[] getHasMember() {
        return hasMember;
    }

    public void setHasMember(String[] hasMember) {
        this.hasMember = hasMember;
    }

    public String[] getDerivedFrom() {
        return derivedFrom;
    }

    public void setDerivedFrom(String[] derivedFrom) {
        this.derivedFrom = derivedFrom;
    }*/

    public ArrayList<String> getBasedOn() {
        return basedOn;
    }

    public void setBasedOn(ArrayList<String> basedOn) {
        this.basedOn = basedOn;
    }

    public ArrayList<String> getFocus() {
        return focus;
    }

    public void setFocus(ArrayList<String> focus) {
        this.focus = focus;
    }

    public ArrayList<Component> getComponent() {
        return component;
    }

    public void setComponent(ArrayList<Component> component) {
        this.component = component;
    }

    public Observation(String status, CodeableConcept code){
        setStatus(status);
        setCode(code);
    }

    public CodeableConcept getCode() {
        return code;
    }

    public void setCode(CodeableConcept code) {
        this.code = code;
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

    /*public String getId() {
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
    }*/
}
