package cl.schedulator.api.usecases.optimizer.distributors;

import cl.schedulator.api.domain.entities.Task;

import java.util.List;
import java.util.Optional;

public class TaskSearcher {

    private List<Task> availableTaskList;


    private TaskSearcher (List<Task> availableTaskList) {
        this.availableTaskList = availableTaskList;
    }


    public static TaskSearcher prepareSearch (List<Task> availableTaskList) {
        return new TaskSearcher(availableTaskList);
    }


    public Task searchTask (Integer durationDiference) {
        Optional<Task> optionalTask =
                this.availableTaskList.stream()
                        .filter(t -> t.getDuration() <= durationDiference)
                        .findFirst();
        return optionalTask.isPresent() ? optionalTask.get() : null;
    }

}
