package cl.schedulator.api.usecases;

import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@DisplayName("Task generator should")
class TaskGeneratorImplTest {

    @Autowired
    TaskGenerator generator;
    TaskSummary summary;


    @Test
    @DisplayName("load task, the total task in summary should be greater than 1")
    void getTaskOrderedByTaskDuration () {
        // arrange
        Integer expectedMinimunTask = 1;
        // act
        summary = generator.getSummary(TaskSorterEnum.BY_TASK_DURATION);
        // assertions
        assertThat(summary.getTotalTasks()).isGreaterThan(expectedMinimunTask);
    }

}