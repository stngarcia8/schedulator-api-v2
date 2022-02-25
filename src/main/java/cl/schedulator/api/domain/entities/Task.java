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
public class Task implements Comparable<Task> {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("taskName")
    private String taskName;

    @JsonProperty("duration")
    private Integer duration;

    @JsonIgnore
    private Boolean active;


    @Override
    public int compareTo (Task comparableTask) {
        if (comparableTask == null || this.duration == null) return 0;
        if (this.duration < comparableTask.duration) return 1;
        if (this.duration > comparableTask.duration) return -1;
        return 0;
    }


    public void changeActiveStatus (Boolean status) {
        this.active = status;
    }

}
