package com.task_management_project.commands.listing;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.BugImpl;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.utils.ListingHelpers;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListAllBugs extends BaseCommand {

    private BugStatus status = null;
    private String personName = null;
    private String sortBy = null;

    public ListAllBugs(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {

        List<Bug> bugs = getTaskManagementRepository().getBugs();

        for (String param : parameters) {
            if (checkStatus(param)) {
                status = ParsingHelpers.tryParseEnum(param,BugStatus.class);
            } else if(checkSortBy(param)) {
                sortBy = param;
            } else {
                personName = param;
            }
        }

        return findBug(status,personName,sortBy,bugs);
    }

    public String findBug(BugStatus status, String personName, String sortBy, List<Bug> list){

        if (list.isEmpty()) {
            return "No bugs found with the specified criteria.";
        }

        if(status != null){
            list = list.stream()
                    .filter(bug -> bug.getStatus() == status)
                    .collect(Collectors.toList());
        }
        if (personName != null){
            list = list.stream()
                    .filter(bug -> bug.getPerson().getName().equals(personName))
                    .collect(Collectors.toList());
        }

        if (sortBy != null) {
            switch (sortBy.toLowerCase()) {
                case "title" -> list.sort(Comparator.comparing(Bug::getTitle));
                case "priority" -> list.sort(Comparator.comparing(Bug::getPriority));
                case "severity" -> list.sort(Comparator.comparing(Bug::getSeverity));
                default -> throw new IllegalArgumentException("Invalid sort parameter.");
            }
        }

        StringBuilder result = new StringBuilder();
        for (Bug bug : list) {
            result.append(bug.getAsString()).append(System.lineSeparator());
        }

        return result.toString().trim();
    }


    private boolean checkStatus(String param){
        try {
            BugStatus.valueOf(param);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean checkSortBy(String param) {
        if (param.equalsIgnoreCase("title")){
            return true;
        }
        if(param.equalsIgnoreCase("severity")){
            return true;
        }
        if(param.equalsIgnoreCase("priority")){
            return true;
        }
        return false;
    }
}
