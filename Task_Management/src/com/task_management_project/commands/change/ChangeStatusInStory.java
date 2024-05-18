package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class ChangeStatusInStory extends BaseCommand {
    public ChangeStatusInStory(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() < 1) {
//            return "Insufficient parameters. Please provide a name for the person.";
//        }
//
//        String personName = parameters.get(0);
//
//        if (taskManagementRepository.personExists(personName)) {
//            return "Person with the provided name already exists.";
//        }
//
//        Person newPerson = new Person(personName);
//        taskManagementRepository.addPerson(newPerson);
//
//        return String.format("Person '%s' successfully created.", personName);
        return null;
    }
}
