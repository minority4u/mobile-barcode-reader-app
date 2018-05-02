package com.example.crist.writingapp.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "str_name",
        "str_no",
        "city",
        "post_code",
        "country",
        "start_date",
        "end_date"
})
public class Address {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("str_name")
    public String strName;
    @JsonProperty("str_no")
    public String strNo;
    @JsonProperty("city")
    public String city;
    @JsonProperty("post_code")
    public String postCode;
    @JsonProperty("country")
    public String country;
    @JsonProperty("start_date")
    public String startDate;
    @JsonProperty("end_date")
    public String endDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    @Override
    public String toString() {
        return "ID Address:"+ this.id + "\n"+
                "Destination Adress:" + "\n" +
                this.name + "\n"+
                this.strName + " " + this.strNo + "\n"+
                this.postCode + " " + this.city + "\n";
    }
}