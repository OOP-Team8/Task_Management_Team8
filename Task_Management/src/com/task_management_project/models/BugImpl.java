package com.task_management_project.models;

import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.Priority;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    private Priority priority;
    private BugSeverity bugSeverity;
    private BugStatus bugStatus;
    private List<String> steps;
    private Person person;

    public BugImpl(int id, String title, String description, Priority priority, BugStatus bugStatus, BugSeverity bugSeverity,Person person) {
        super(id, title, description);
        setPriority(priority);
        setBugSeverity(bugSeverity);
        setBugStatus(bugStatus);
        steps = new ArrayList<>();
        this.person = person;
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
    @Override
    public Person getPerson() {
        return person;
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


    //TODO
    @Override
    public String getAsString() {
        return null;
    }
}
