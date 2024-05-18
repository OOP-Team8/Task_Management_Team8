package com.task_management_project.commands.create;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.PersonImpl;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class CreateNewPerson extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;
    private final static String PERSON_CREATED_SUCCESSFULLY = "Person %s created successfully!";
    public CreateNewPerson(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        String name = parameters.get(0);

        return createPerson(name);
    }

    public String createPerson(String name){
        Person person = getTaskManagementRepository().createPerson(name);

        getTaskManagementRepository().addPerson(person);

        return String.format(PERSON_CREATED_SUCCESSFULLY,name);
    }
}
