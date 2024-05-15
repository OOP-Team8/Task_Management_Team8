package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.EventLog;

import java.util.List;

public class ShowTeamActivity extends BaseCommand{
    public ShowTeamActivity(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        StringBuilder result = new StringBuilder();
//
//        List<EventLog> teamActivity = getTaskManagementRepository().getTeamActivity();
//
//        if (teamActivity.isEmpty()) {
//            result.append("There is no activity for the team.");
//        } else {
//            result.append("Team activity:\n");
//            for (EventLog eventLog : teamActivity) {
//                result.append(eventLog.toString()).append("\n");
//            }
//        }
//
//        return result.toString();
        return null;
    }
}
