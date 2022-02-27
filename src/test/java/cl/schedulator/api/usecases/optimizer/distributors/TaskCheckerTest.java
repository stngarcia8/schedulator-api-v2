package cl.schedulator.api.usecases.optimizer.distributors;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.shared.objectmothers.TaskObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Task checker should")
class TaskCheckerTest {

    List<Task> actualTaskList;
    Task actualTask;
    TaskChecker checker;


    @BeforeEach
    void setUp () {
        actualTaskList = new ArrayList<>();
    }


    @Test
    @DisplayName("Verify if instance returned is TaskChecker")
    void verifyReturnIntance () {
        // arrange
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
        // act
        checker = TaskChecker.createChecker(actualTaskList);
        // assert
        assertThat(checker).isInstanceOf(TaskChecker.class);
    }


    @Test
    @DisplayName("Get a true value when the task can check")
    void verifyCanCheckMethod () {
        // arrange
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
        actualTask = actualTaskList.get(0);
        // act
        checker = TaskChecker.createChecker(actualTaskList);
        // assert
        assertThat(checker.canProcessThisTask(actualTask)).isTrue();
    }


    @Test
    @DisplayName("Verify if task checkit method is correct")
    void verifyCheckItMethod () {
        // arrange
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
        actualTask = actualTaskList.get(0);
        checker = TaskChecker.createChecker(actualTaskList);
        // act
        checker.checkIt(actualTask);
        // assert
        assertThat(checker.canProcessThisTask(actualTask)).isFalse();
    }


    @Test
    @DisplayName("Get two elements when one of tree task is checkit")
    void getTwoElements () {
        // arrange
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
        actualTask = actualTaskList.get(0);
        List<Task> expectedTaskList = new ArrayList<>();
        // act
        checker = TaskChecker.createChecker(actualTaskList);
        checker.checkIt(actualTask);
        expectedTaskList = checker.getAvailableTask();
        // assert
        assertThat(expectedTaskList.size()).isEqualTo(2);
    }

}