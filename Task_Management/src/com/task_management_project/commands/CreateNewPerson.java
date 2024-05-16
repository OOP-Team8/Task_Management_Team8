package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.PersonImpl;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.utils.Validation;

import java.util.List;

public class CreateNewPerson extends  BaseCommand{
    public final int EXPECTED_PARAMS = 1;
    public CreateNewPerson(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        Person person = getTaskManagementRepository().createPerson(parameters.get(0));
        return person.getName();
    }

}
