package com.task_management_project.commands.assignAndUnassign;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class AssignTaskToPerson extends BaseCommand {

    public AssignTaskToPerson(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() != 1) {
//            return "Invalid number of parameters. Usage: showPersonActivity <personName>";
//        }
//
//        String personName = parameters.get(0);
//
//        Person person = taskManagementRepository.getPersonByName(personName);
//
//        if (person == null) {
//            return "Person not found.";
//        }
//
//        return person.getActivity();
        return null;
    }
}
