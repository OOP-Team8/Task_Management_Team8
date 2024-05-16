package com.task_management_project.models.contracts;

import java.util.List;

public interface Loggable {
    List<EventLog> getLogs();
}
