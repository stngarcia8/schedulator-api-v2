package cl.schedulator.api.domain.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Task {

    private String taskId;
    private String taskName;
    private Integer duration;
    private final Boolean active = true;

}
