package cl.schedulator.api.usecases.optimizer.distributors;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.shared.objectmothers.TaskObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task searcher should")
class TaskSearcherTest {

    TaskSearcher searcher;
    List<Task> actualTaskList;
    Task actualTask;


    @BeforeEach
    void setUp () {
        actualTaskList = new ArrayList<>();
    }


    @Test
    @DisplayName("Verifi that object returned is an instance of TaskSearcher")
    void verifyInstance () {
        // arrange
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
        // act
        searcher = TaskSearcher.prepareSearch(actualTaskList);
        // assert
        assertThat(searcher).isInstanceOf(TaskSearcher.class);
    }


    @Test
    @DisplayName("Return a task with duration one when the time diference is one")
    void getTaskWithDurationOne () {
        // Arrange
        String expectedTaskId = "t2";
        Integer expectedTaskDuration = 1;
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
        searcher = TaskSearcher.prepareSearch(actualTaskList);
        // act
        actualTask = searcher.searchTask(1);
        // assertions
        assertThat(actualTask.getTaskId()).isEqualTo(expectedTaskId);
        assertThat(actualTask.getDuration()).isEqualTo(expectedTaskDuration);
    }


    @Test
    @DisplayName("Return a null when the time diference is zero")
    void getNullValue () {
        // Arrange
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
        searcher = TaskSearcher.prepareSearch(actualTaskList);
        // act
        actualTask = searcher.searchTask(0);
        // assertions
        assertThat(actualTask).isNull();
    }


}