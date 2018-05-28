        package model;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({
                "uri",
                "name",
                "status",
                "weight",
                "size",
                "addr_c",
                "addr_d",
                "box_contents"
        })
        public class Box {

            @JsonProperty("uri")
            public String uri;
            @JsonProperty("name")
            public String name;
            @JsonProperty("status")
            public String status;
            @JsonProperty("weight")
            public String weight;
            @JsonProperty("size")
            public String size;
            @JsonProperty("addr_c")
            public AddrC addrC;
            @JsonProperty("addr_d")
            public AddrD addrD;
            @JsonProperty("box_contents")
            public List<OrderContent> boxContents = null;
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
        return  "Status: " +this.status + "\n" +
                "Weight: "+this.weight + "\n" +
                "Size: "+ this.size + "\n\n" +
                "Destination Adress:" + "\n" +
                this.addrD.name + "\n"+
                this.addrD.strName + " " + this.addrD.strNo + "\n"+
                this.addrD.postCode + " " + this.addrD.city + "\n";

    }

    public String getBoxStatusToString(){
        return this.status + "\n" +
                "Weight: " +this.weight +"\n"+
                "Size: "+this.size;
    }

    public String getBoxDestinationAddressToString(){
        return this.addrD.name + "\n"+
                this.addrD.strName + " " + this.addrD.strNo + "\n"+
                this.addrD.postCode + " " + this.addrD.city + "\n"+
                this.addrD.country;
    }
}