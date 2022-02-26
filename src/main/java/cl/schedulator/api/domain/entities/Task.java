package cl.schedulator.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class Task {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("taskName")
    private String taskName;

    @JsonProperty("duration")
    private Integer duration;

    @JsonIgnore
    private Boolean active;


    public void changeActiveStatus (Boolean status) {
        this.active = status;
    }

}
