package com.task_management_project.core.contracts;

import com.task_management_project.commands.contracts.Command;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository);

}
