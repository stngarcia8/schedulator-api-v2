package cl.schedulator.api.usecases.optimizer.transformers;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.shared.objectmothers.TaskResponseObjectMother;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Transform class should")
class TaskTransformTest {

    List<TaskResponseDto> responseTaskList;
    TaskTransform transformer;
    List<Task> actualTaskList;
    Task actualTask;


    @BeforeEach
    void setUp () {
        responseTaskList = TaskResponseObjectMother.createMother()
                .getTask();
    }


    @Test
    @DisplayName("Verify if the instance is type of TaskTransform")
    void VerifyTypeObject () {
        // act
        TaskTransform actualTransformer = TaskTransform.processList(responseTaskList);
        // assert
        assertThat(actualTransformer).isInstanceOf(TaskTransform.class);
    }


    @Test
    @DisplayName("get a valid task list after transformation")
    void getCorrectTaskList () {
        // arrange
        String taskIdExpected = "t3";
        String taskNameExpected = "Task 3";
        Integer durationExpected = 8;
        transformer = TaskTransform.processList(responseTaskList);
        // act
        actualTaskList = transformer.getTaskList();
        actualTask = actualTaskList.get(0);
        // asertions
        assertThat(actualTask.getTaskId()).isEqualTo(taskIdExpected);
        assertThat(actualTask.getTaskName()).isEqualTo(taskNameExpected);
        assertThat(actualTask.getDuration()).isEqualTo(durationExpected);
        assertThat(actualTask.getActive()).isEqualTo(true);
    }


    @Test
    @DisplayName("get three items in the list after transformation")
    void getSize () {
        // arrange
        Integer expectedSize = 3;
        transformer = TaskTransform.processList(responseTaskList);
        // act
        actualTaskList = transformer.getTaskList();
        Integer actualSize = actualTaskList.size();
        // assert
        assertThat(actualSize).isEqualTo(expectedSize);
    }


    @Test
    @DisplayName("get the duration with value eight in the first element after transformation")
    void getValueEight () {
        // arrange
        Integer expectedDuration = 8;
        transformer = TaskTransform.processList(responseTaskList);
        // act
        actualTaskList = transformer.getTaskList();
        actualTask = actualTaskList.get(0);
        // assert
        assertThat(actualTask.getDuration()).isEqualTo(expectedDuration);
    }


    @Test
    @DisplayName("get the duration with value one in the last element after transformation")
    void getValueOne () {
        // arrange
        Integer expectedDuration = 1;
        transformer = TaskTransform.processList(responseTaskList);
        // act
        actualTaskList = transformer.getTaskList();
        actualTask = actualTaskList.get(actualTaskList.size() - 1);
        // assert
        assertThat(actualTask.getDuration()).isEqualTo(expectedDuration);
    }

}