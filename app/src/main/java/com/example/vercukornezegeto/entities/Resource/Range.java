package com.example.vercukornezegeto.entities.Resource;

public class Range {
    private Float low;
    private Float high;

    public Range(Float low, Float high) {
        this.low = low;
        this.high = high;
    }

    public Range(){}

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
}
