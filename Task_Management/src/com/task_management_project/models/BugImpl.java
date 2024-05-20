package com.task_management_project.models;

import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    private Priority priority;
    private BugSeverity bugSeverity;
    private BugStatus bugStatus;
    private List<String> steps;

    public BugImpl(int id, String title, String description, Priority priority, BugStatus bugStatus, BugSeverity bugSeverity,Person person) {
        super(id, title, description,person, TaskType.BUG);
        setPriority(priority);
        setBugSeverity(bugSeverity);
        setBugStatus(bugStatus);
        steps = new ArrayList<>();
        eventList.add(new EventLogImpl(String.format("New bug was initialized with name - %s",getTitle())));
    }
    @Override
    public Priority getPriority() {
        return priority;
    }
    @Override
    public BugSeverity getSeverity() {
        return bugSeverity;
    }
    @Override
    public BugStatus getStatus() {
        return bugStatus;
    }

    @Override
    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }
    private void setBugStatus(BugStatus bugStatus) {
        this.bugStatus = bugStatus;
    }
    private void setBugSeverity(BugSeverity bugSeverity) {
        this.bugSeverity = bugSeverity;
    }

    @Override
    public String getAsString() {
        return String.format("%s\n%s\n%s\n%s",super.getAsString(),getPriority(),getStatus(),getSeverity());
    }
}
