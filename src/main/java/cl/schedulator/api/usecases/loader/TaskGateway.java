package cl.schedulator.api.usecases.loader;

import java.util.List;

public interface TaskGateway {

    List<TaskResponseDto> getTaskList ();

}
