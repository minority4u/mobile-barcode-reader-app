package model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "current_FirstName",
        "current_LastName",
        "current_Street",
        "current_StreetNo",
        "current_Plz",
        "current_City",
        "current_Country",
        "current_StartDate",
        "current_EndDate",
        "destination_FirstName",
        "destination_LastName",
        "destination_Street",
        "destination_StreetNo",
        "destination_Plz",
        "destination_City",
        "destination_Country",
        "destination_StartDate",
        "destination_EndDate",
        "status",
        "size",
        "weight",
        "uri"
})

public class Box {

    @JsonProperty("current_FirstName")
    public String currentFirstName;
    @JsonProperty("current_LastName")
    public String currentLastName;
    @JsonProperty("current_Street")
    public String currentStreet;
    @JsonProperty("current_StreetNo")
    public String currentStreetNo;
    @JsonProperty("current_Plz")
    public String currentPlz;
    @JsonProperty("current_City")
    public String currentCity;
    @JsonProperty("current_Country")
    public String currentCountry;
    @JsonProperty("current_StartDate")
    public String currentStartDate;
    @JsonProperty("current_EndDate")
    public String currentEndDate;
    @JsonProperty("destination_FirstName")
    public String destinationFirstName;
    @JsonProperty("destination_LastName")
    public String destinationLastName;
    @JsonProperty("destination_Street")
    public String destinationStreet;
    @JsonProperty("destination_StreetNo")
    public String destinationStreetNo;
    @JsonProperty("destination_Plz")
    public String destinationPlz;
    @JsonProperty("destination_City")
    public String destinationCity;
    @JsonProperty("destination_Country")
    public String destinationCountry;
    @JsonProperty("destination_StartDate")
    public String destinationStartDate;
    @JsonProperty("destination_EndDate")
    public String destinationEndDate;
    @JsonProperty("status")
    public String status;
    @JsonProperty("size")
    public String size;
    @JsonProperty("weight")
    public String weight;
    @JsonProperty("uri")
    public String uri;
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

        return this.currentFirstName+ " " + this.currentLastName + "\n"+this.currentStreet + " " + this.currentStreetNo + "\n"+this.currentPlz + " " + this.currentCity + "\n" + this.currentCountry;

    }

}