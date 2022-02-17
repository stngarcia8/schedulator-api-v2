package cl.schedulator.api.infrastructure.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.objenesis.strategy.SingleInstantiatorStrategy;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(testClass: this);
    }

}