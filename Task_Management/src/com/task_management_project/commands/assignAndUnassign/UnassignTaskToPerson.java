package com.task_management_project.commands.assignAndUnassign;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class UnassignTaskToPerson extends BaseCommand {
    public final int EXPECTED_PARAMS = 2;
    private final String ERROR_MESSAGE = "Wrong Id";

    public UnassignTaskToPerson(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_PARAMS);
        Task task = getTaskManagementRepository().findTaskById(ParsingHelpers.tryParseInt(parameters.get(0),ERROR_MESSAGE));
        Person person1 = task.getPerson();
        Person person2 = getTaskManagementRepository().findPersonByName(parameters.get(1));

        getTaskManagementRepository().removeTaskFromPerson(task, person2);

        return String.format("%s completed his task with name - %s",person2.getName(),task.getTitle());
    }
}
