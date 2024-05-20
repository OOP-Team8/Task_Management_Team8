package com.task_management_project.commands.listing;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.utils.ListingHelpers;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasks extends BaseCommand {

    private String title = null;
    private final int PARAMETER = 1;


    public ListAllTasks(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        List<Task> tasks = getTaskManagementRepository().getTasks();

        Validation.validateArgumentsCount(parameters,PARAMETER);

        title = parameters.get(0);

        return findTasks(title,tasks);
    }

    public String findTasks(String title,List<Task> list){
        if (list.isEmpty()) {
            return "No tasks found with the specified title. Please try a different one.";
        }
        list = list.stream()
                .filter(task -> task.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());

        list.sort(Comparator.comparing(Task::getTitle));

        return ListingHelpers.listElements(list);
    }
}
