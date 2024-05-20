package com.task_management_project.commands.show;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowPersonActivity extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;

    public ShowPersonActivity(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        Person person = getTaskManagementRepository().findPersonByName(parameters.get(0));
        StringBuilder builder = new StringBuilder(String.format("--%s - ACTIVITIES--",person.getName())).append(System.lineSeparator());
        Validation.message(builder, person,person.getTasks());
        return builder.toString();
    }
}
