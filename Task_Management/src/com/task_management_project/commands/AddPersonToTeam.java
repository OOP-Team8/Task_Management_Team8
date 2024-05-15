package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class AddPersonToTeam extends BaseCommand {
    public AddPersonToTeam(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() < 2) {
//            return "Insufficient parameters. Please provide the team name and the person's name.";
//        }
//
//        String teamName = parameters.get(0);
//        String personName = parameters.get(1);
//
//        if (!taskManagementRepository.teamExists(teamName)) {
//            return "Team with the provided name does not exist.";
//        }
//
//        if (taskManagementRepository.personExists(personName)) {
//            return "Person with the provided name is already part of a team.";
//        }
//
//        Team team = taskManagementRepository.getTeamByName(teamName);
//        Person person = new Person(personName);
//        team.addMember(person);
//
//        return "Person successfully added to the team.";
        return null;
    }
}
