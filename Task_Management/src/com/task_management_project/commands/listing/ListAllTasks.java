package com.task_management_project.commands.listing;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class ListAllTasks extends BaseCommand {

    public ListAllTasks(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        List<Team> teams = taskManagementRepository.getAllTeams();
//
//        if (teams.isEmpty()) {
//            return "No teams found.";
//        }
//
//        StringBuilder result = new StringBuilder();
//        result.append("Teams:\n");
//        for (Team team : teams) {
//            result.append(team.getName()).append("\n");
//        }
//
//        return result.toString();
//    }
        return null;
    }
}
