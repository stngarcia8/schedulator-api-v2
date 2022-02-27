package cl.schedulator.api.usecases.optimizer;

import cl.schedulator.api.shared.objectmothers.TaskResponseObjectMother;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import cl.schedulator.api.usecases.shared.DailyTask;
import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@DisplayName("Task optimizer should")
class TaskOptimizerImplTest {

    @Autowired
    TaskOptimizer optimizer;

    TaskSummary summary;
    List<TaskResponseDto> actualResponseList;


    @BeforeEach
    void setUp () {
        actualResponseList = new ArrayList<>();
        actualResponseList = TaskResponseObjectMother.createMother()
                .getTask();
    }


    @Test
    @DisplayName("get 3 task and 2 day when optimizer is completed")
    void getOptimizerResults () {
        // arrange
        Integer expectedTotalTask = 3;
        Integer expectedDaysQuantity = 2;
        // act
        optimizer.transformToEntityTaskList(actualResponseList);
        summary = optimizer.getSummary(TaskSorterEnum.BY_TASK_DURATION);
        // assertions
        assertThat(summary.getTotalTasks()).isEqualTo(expectedTotalTask);
        assertThat(summary.getTotalDays()).isEqualTo(expectedDaysQuantity);
    }


    @Test
    @DisplayName("Verify if result are ordered by task duration")
    void verifiOrderByTaskDuration () {
        // arrange
        Integer expectedTaskQuantityDayOne = 1;
        Integer expectedTaskQuantityDayTwo = 2;
        // act
        optimizer.transformToEntityTaskList(actualResponseList);
        summary = optimizer.getSummary(TaskSorterEnum.BY_TASK_DURATION);
        DailyTask dayOne = summary.getDays()
                .get(0);
        DailyTask dayTwo = summary.getDays()
                .get(1);
        // assertions: the first day have one task and second day have two task.
        assertThat(dayOne.getTaskPerDay()).isEqualTo(expectedTaskQuantityDayOne);
        assertThat(dayTwo.getTaskPerDay()).isEqualTo(expectedTaskQuantityDayTwo);
    }


    @Test
    @DisplayName("Verify if result are ordered by quantity of task per day")
    void verifiOrderByTaskPerDay () {
        // arrange
        Integer expectedTaskQuantityDayOne = 2;
        Integer expectedTaskQuantityDayTwo = 1;
        // act
        optimizer.transformToEntityTaskList(actualResponseList);
        summary = optimizer.getSummary(TaskSorterEnum.BY_TASKS_QUANTITY_PER_DAY);
        DailyTask dayOne = summary.getDays()
                .get(0);
        DailyTask dayTwo = summary.getDays()
                .get(1);
        // assertions: the first day have one task and second day have two task.
        assertThat(dayOne.getTaskPerDay()).isEqualTo(expectedTaskQuantityDayOne);
        assertThat(dayTwo.getTaskPerDay()).isEqualTo(expectedTaskQuantityDayTwo);
    }

}