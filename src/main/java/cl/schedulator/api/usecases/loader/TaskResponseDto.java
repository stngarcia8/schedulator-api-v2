package cl.schedulator.api.usecases.loader;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TaskResponseDto {

  @JsonProperty("task_id")
  private String taskId;

  @JsonProperty("task_name")
  private String taskName;

  @JsonProperty("duration")
  private Integer duration;
}
