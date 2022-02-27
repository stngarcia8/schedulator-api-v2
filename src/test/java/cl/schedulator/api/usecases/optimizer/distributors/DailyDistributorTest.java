package cl.schedulator.api.usecases.optimizer.distributors;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.shared.objectmothers.TaskObjectMother;
import cl.schedulator.api.usecases.shared.DailyTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Daily distributor should")
class DailyDistributorTest {

    List<Task> actualTaskList;
    Task actualTask;
    TaskChecker checker;
    DailyDistributor distributor;
    Integer dayInProgress;
    DailyTask actualDailyTask;


    @BeforeEach
    void setUp () {
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
        checker = TaskChecker.createChecker(actualTaskList);
        dayInProgress = 1;
    }


    @Test
    @DisplayName("Verify instance returned that is DailyDistributor")
    void verifyInstance () {
        // act
        distributor = DailyDistributor.processDay(dayInProgress, checker);
        // assert
        assertThat(distributor).isInstanceOf(DailyDistributor.class);
    }


    @Test
    @DisplayName("Return a daily task with one task when duration is 8")
    void getDailyTaskWithOneTaskOnly () {
        // arrange
        Integer expectedTaskPerDay = 1;
        actualTask = actualTaskList.get(2);
        // act
        distributor = DailyDistributor.processDay(dayInProgress, checker);
        distributor.addTaskToDay(actualTask);
        actualDailyTask = distributor.getDailyTask();
        // assertions
        assertThat(actualDailyTask.getTaskPerDay()).isEqualTo(expectedTaskPerDay);
        assertThat(actualDailyTask.getTasks()
                .size()).isEqualTo(expectedTaskPerDay);
    }


    @Test
    @DisplayName("Return a daily task with two task when duration is less than 8")
    void getDailyTaskWithTwoTask () {
        // arrange
        Integer expectedTaskPerDay = 2;
        actualTask = actualTaskList.get(0);
        // act
        distributor = DailyDistributor.processDay(dayInProgress, checker);
        distributor.addTaskToDay(actualTask);
        actualDailyTask = distributor.getDailyTask();
        // assertions
        assertThat(actualDailyTask.getTaskPerDay()).isEqualTo(expectedTaskPerDay);
        assertThat(actualDailyTask.getTasks()
                .size()).isEqualTo(expectedTaskPerDay);
    }

}