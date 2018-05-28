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
        "uri",
        "name",
        "box_id",
        "style",
        "color",
        "size",
        "prize",
        "condition"
})
public class OrderContent {

        @JsonProperty("uri")
        public String uri;
        @JsonProperty("name")
        public String name;
        @JsonProperty("box_id")
        public Integer boxId;
        @JsonProperty("style")
        public String style;
        @JsonProperty("color")
        public String color;
        @JsonProperty("size")
        public String size;
        @JsonProperty("prize")
        public String prize;
        @JsonProperty("condition")
        public String condition;
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
            return "";
        }

}
