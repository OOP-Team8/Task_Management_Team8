package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class ShowAllPeople extends BaseCommand{

    public ShowAllPeople(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        List<Person> people = taskManagementRepository.getAllPeople();
//
//        if (people.isEmpty()) {
//            return "There are no people in the system.";
//        }
//
//        StringBuilder result = new StringBuilder();
//        result.append("People:\n");
//        for (Person person : people) {
//            result.append(person.getName()).append("\n");
//        }
//
//        return result.toString();
        return null;
    }
}
