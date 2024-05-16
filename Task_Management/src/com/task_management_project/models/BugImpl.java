package com.task_management_project.models;

import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.Priority;

public class BugImpl extends TaskImpl implements Bug {

    private BugSeverity bugSeverity;

    public BugImpl(int id, String title, String description) {
        super(id, title, description);

    }

    public Priority getPriority() {
        return null;
    }

    public BugSeverity getSeverity() {
        return bugSeverity;
    }

    public BugStatus getStatus() {
        return null;
    }

    @Override
    public String getAsString() {
        return null;
    }
}
