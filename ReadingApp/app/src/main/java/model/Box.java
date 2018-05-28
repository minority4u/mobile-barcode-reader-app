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
                "id",
                "name",
                "status",
                "customerStatus",
                "weight",
                "size",
                "addr_c",
                "addr_d",
                "box_contents"
        })
        public class Box {

            @JsonProperty("uri")
            public String uri;
            @JsonProperty("id")
            public String id;
            @JsonProperty("name")
            public String name;
            @JsonProperty("status")
            public String status;
            @JsonProperty("customerStatus")
            public String customerStatus;
            @JsonProperty("weight")
            public String weight;
            @JsonProperty("size")
            public String size;
            @JsonProperty("addr_c")
            public AddrC addrC;
            @JsonProperty("addr_d")
            public AddrD addrD;
            @JsonProperty("box_contents")
            public List<BoxContent> boxContents = null;
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
        return this.customerStatus + "\n";
    }

    public String getBoxContentToString(){

                String returnString = "";

                for(int i = 0; i < this.boxContents.size(); i++){
                    BoxContent boxContent = this.boxContents.get(i);

                    returnString += "Name: " + boxContent.name +"\n";
                    returnString += "Color: " + boxContent.color+"\n";
                    returnString += "Size: " + boxContent.size+"\n";
                    returnString += "Price: " + boxContent.price +"\n\n";
                }

                return returnString;
    }
}