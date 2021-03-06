package com.example.vercukornezegeto.entities.Resource;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Component {
    private String code; //code
    private String valueString;
    private CodeableConcept dataAbsentReason;
    private ArrayList<CodeableConcept> interpretation;

    public Component(){}
    public Component(String code) {
        setCode(code);
    }
    public Component(String code, String valueString) {
        setValueString(valueString);
        setCode(code);
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

    public ArrayList<CodeableConcept> getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(ArrayList<CodeableConcept> interpretation) {
        this.interpretation = interpretation;
    }

    @NotNull
    @Override
    public String toString() {
        return "Component{" +
                "code='" + code + '\'' +
                ", valueString='" + valueString + '\'' +
                '}';
    }
}
