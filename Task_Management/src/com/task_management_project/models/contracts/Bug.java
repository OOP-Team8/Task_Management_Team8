package com.task_management_project.models.contracts;

import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.Priority;

import java.io.ObjectInputFilter;
import java.util.List;

public interface Bug extends Task,Identifiable{

    Priority getPriority();

    BugSeverity getSeverity();

    BugStatus getStatus();

    List<String> getSteps();

    Person getPerson();

}
