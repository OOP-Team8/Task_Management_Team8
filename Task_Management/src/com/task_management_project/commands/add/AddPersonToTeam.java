package com.task_management_project.commands.add;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.utils.Validation;

import java.util.List;

public class AddPersonToTeam extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;
    public AddPersonToTeam(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        Person person = getTaskManagementRepository().findPersonByName(parameters.get(0));
        getTaskManagementRepository().addPerson(person);
        return String.format("%s was added to the team", person.getName());
    }
}
