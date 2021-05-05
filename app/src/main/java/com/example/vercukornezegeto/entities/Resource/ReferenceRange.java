package com.example.vercukornezegeto.entities.Resource;

public class ReferenceRange {
    private Float low; //Range low end
    private Float high;//range high end
    private CodeableConcept type; //reference range qualifier
    private CodeableConcept[] appliesTo; //reference range population
    private Range age;
    private String text; // text based reference range in an obs

    public ReferenceRange(){}

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public CodeableConcept getType() {
        return type;
    }

    public void setType(CodeableConcept type) {
        this.type = type;
    }

    public CodeableConcept[] getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(CodeableConcept[] appliesTo) {
        this.appliesTo = appliesTo;
    }

    public Range getAge() {
        return age;
    }

    public void setAge(Range age) {
        this.age = age;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
