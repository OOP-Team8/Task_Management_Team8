package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class ShowAllTeamMembers extends BaseCommand{
    public ShowAllTeamMembers(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() < 1) {
//            return "Usage: show_all_team_members [team_name]";
//        }
//
//        String teamName = parameters.get(0);
//
//        Team team = taskManagementRepository.getTeamByName(teamName);
//
//        if (team == null) {
//            return String.format("Team '%s' not found.", teamName);
//        }
//
//        List<Person> members = team.getMembers();
//
//        StringBuilder result = new StringBuilder();
//        result.append("Team Members:\n");
//        for (Person member : members) {
//            result.append(member.getName()).append("\n");
//        }
//
//        return result.toString();
        return null;
    }
}
