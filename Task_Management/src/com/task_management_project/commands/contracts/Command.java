package com.task_management_project.commands.contracts;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);
}
