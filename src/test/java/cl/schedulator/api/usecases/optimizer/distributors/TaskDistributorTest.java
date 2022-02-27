package cl.schedulator.api.usecases.optimizer.distributors;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.shared.objectmothers.TaskObjectMother;
import cl.schedulator.api.usecases.shared.TaskSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Task distributor should")
class TaskDistributorTest {

    List<Task> actualTaskList;
    TaskDistributor distributor;


    @BeforeEach
    void setUp () {
        actualTaskList = TaskObjectMother.create()
                .getValidTaskList();
    }


    @Test
    @DisplayName("Verify if returned a TaskDistributor instance")
    void verifyTaskDistributorInstance () {
        // act
        distributor = TaskDistributor.distributeTasks(actualTaskList);
        // assert
        assertThat(distributor).isInstanceOf(TaskDistributor.class);
    }


    @Test
    @DisplayName("Verify if return a TaskSummary instance after the execution of the distribution")
    void verifyTaskSummaryInstance () {
        // act
        distributor = TaskDistributor.distributeTasks(actualTaskList);
        // assert
        assertThat(distributor.getSummary()).isInstanceOf(TaskSummary.class);
    }


    @Test
    @DisplayName("get two day with the distribution is correct")
    void getTwoDays () {
        // arrange
        Integer expectedDaysQuantity = 2;
        distributor = TaskDistributor.distributeTasks(actualTaskList);
        // act
        TaskSummary summary = distributor.getSummary();
        // assertions
        assertThat((summary.getTotalDays())).isEqualTo(expectedDaysQuantity);
        assertThat(summary.getDays()
                .size()).isEqualTo(expectedDaysQuantity);
    }

}