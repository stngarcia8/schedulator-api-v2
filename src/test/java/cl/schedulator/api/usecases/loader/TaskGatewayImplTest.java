package cl.schedulator.api.usecases.loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@DisplayName("Task loader should")
class TaskGatewayImplTest {

    @Autowired
    TaskGateway gateway;

    List<TaskResponseDto> actualResponseList;


    @BeforeEach
    void setup () {
        actualResponseList = new ArrayList<>();
    }


    @Test
    @DisplayName("Get task the summary should be greater than 1")
    void getTaskFromMicroservice () {
        // arrange
        Integer expectedMinimumTaskQuantity = 1;
        // act
        actualResponseList = gateway.getTaskList();
        // assert
        assertThat(actualResponseList.size()).isGreaterThan(expectedMinimumTaskQuantity);
    }


}