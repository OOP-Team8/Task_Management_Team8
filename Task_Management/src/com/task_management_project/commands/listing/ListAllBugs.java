package com.task_management_project.commands.listing;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.utils.ListingHelpers;

import java.util.List;

public class ListAllBugs extends BaseCommand {

    private final List<Bug> bugList;
    
    public ListAllBugs(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
        bugList = taskManagementRepository.getBugs();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (bugList.isEmpty()) {
            return "There are no registered journeys.";
        }

        return ListingHelpers.elementsToString(bugList);
    }
    
}
