package cl.schedulator.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Task {

    @JsonProperty("task_id")
    private String taskId;

    @JsonProperty("task_name")
    private String taskName;

    @JsonProperty("duration")
    private Integer duration;

    private final Boolean active = true;

}
