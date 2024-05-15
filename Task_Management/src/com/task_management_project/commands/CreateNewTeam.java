package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class CreateNewTeam extends BaseCommand {
    public CreateNewTeam(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() < 1) {
//            return "Insufficient parameters. Please provide a name for the team.";
//        }
//
//        String teamName = parameters.get(0);
//
//        if (taskManagementRepository.teamExists(teamName)) {
//            return "Team with the provided name already exists.";
//        }
//
//        taskManagementRepository.createTeam(teamName);
//
//        return String.format("Team '%s' successfully created.", teamName);
        return null;
    }
}
