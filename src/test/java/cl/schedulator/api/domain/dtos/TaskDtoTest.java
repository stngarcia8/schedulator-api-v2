package cl.schedulator.api.domain.dtos;

import cl.schedulator.api.shared.objectmothers.TaskDtoMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TaskDtoTest {
    private TaskDto taskDtoActual;

    @Test
    @DisplayName("Test create new taskDto instance is success")
    public void newInstance() {
        // arrange
        String taskIdExpected = "1";
        String taskNameExpected = "Task name";
        Integer durationExpected = 5;
        // act
        this.taskDtoActual = TaskDtoMother.create().getValidTask();
        // assertions
        Assertions.assertThat(this.taskDtoActual.getTaskId()).isEqualTo(taskIdExpected);
        Assertions.assertThat(this.taskDtoActual.getTaskName()).isEqualTo(taskNameExpected);
        Assertions.assertThat(this.taskDtoActual.getDuration()).isEqualTo(durationExpected);
    }


}