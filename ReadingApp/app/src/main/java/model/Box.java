        package model;
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
        "uri",
        "name",
        "addr_c",
        "addr_d",
        "status",
        "weight",
        "size"
})
public class Box {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("uri")
    public String uri;
    @JsonProperty("name")
    public String name;
    @JsonProperty("addr_c")
    public AddrC addrC;
    @JsonProperty("addr_d")
    public AddrD addrD;
    @JsonProperty("status")
    public String status;
    @JsonProperty("weight")
    public String weight;
    @JsonProperty("size")
    public String size;
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
        return "ID:"+ this.id + "\n"+
                "Status: " +this.status + "\n" +
                "Weight: "+this.weight + "\n" +
                "Size: "+ this.size + "\n\n" +
                "Destination Adress:" + "\n" +
                this.addrD.name + "\n"+
                this.addrD.strName + " " + this.addrD.strNo + "\n"+
                this.addrD.postCode + " " + this.addrD.city + "\n";

    }


}