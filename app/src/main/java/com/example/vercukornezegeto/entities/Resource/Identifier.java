package com.example.vercukornezegeto.entities.Resource;
import java.time.Period;

public class Identifier {
    private String use; //code
    private CodeableConcept type;
    private String system; //uri
    private String value;
    private Period period;
    private String assigner; //organization assigner

    public Identifier(){}

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        if (use != null) {
            if (use.matches("[^\\s]+(\\s[^\\s]+)*")) {
                this.use = use;
            } else {
                System.err.println("Invalid use: " + use + " (must match code regex)");
            }
        }
    }

    public CodeableConcept getType() {
        return type;
    }

    public void setType(CodeableConcept type) {
        this.type = type;
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getAssigner() {
        return assigner;
    }

    public void setAssigner(String assigner) {
        this.assigner = assigner;
    }
}
