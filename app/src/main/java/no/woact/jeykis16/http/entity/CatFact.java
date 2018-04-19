package no.woact.jeykis16.http.entity;

import com.google.gson.annotations.Expose;

public class CatFact {
    @Expose private String fact;
    @Expose private Integer length;

    public CatFact() {}

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
