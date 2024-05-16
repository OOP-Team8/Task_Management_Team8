package com.task_management_project.models.contracts;

import javax.naming.Name;
import java.util.List;

public interface Person extends Names {

    List<Task> getTasks();
    List<EventLog> getEventLog();

}
