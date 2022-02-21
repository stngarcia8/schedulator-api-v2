package cl.schedulator.api.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@ToString
public class Task {

    private String taskId;
    private String taskName;
    private Integer duration;
    private final Boolean active = true;

}
