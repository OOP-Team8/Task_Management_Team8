package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowAllPeople extends BaseCommand{
    public final int EXPECTED_PARAMS = 0;

    public ShowAllPeople(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        StringBuilder builder = new StringBuilder("--EMPLOYEES--" + System.lineSeparator());
        for (Person person : getTaskManagementRepository().getPeople()) {
            builder.append(getTaskManagementRepository().getPeople().indexOf(person)+1 + ". " + person.getName() +
                    ((getTaskManagementRepository().getPeople().indexOf(person)+1 < getTaskManagementRepository().getPeople().size()) ? System.lineSeparator() : ""));
        }
        return builder.toString();
    }
}
