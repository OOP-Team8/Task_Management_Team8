package com.task_management_project.core;

import com.task_management_project.commands.*;
import com.task_management_project.commands.contracts.Command;
import com.task_management_project.commands.enums.CommandType;
import com.task_management_project.core.contracts.CommandFactory;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case ADDCOMMENTTOTASK:
                return new AddCommentToTask(taskManagementRepository);
            case ADDPERSONTOTEAM:
                return new AddPersonToTeam(taskManagementRepository);
            case CREATENEWBOARDINTEAM:
                return new CreateNewBoardInTeam(taskManagementRepository);
            case CREATENEWPERSON:
                return new CreateNewPerson(taskManagementRepository);
            case CREATENEWTEAM:
                return new CreateNewTeam(taskManagementRepository);
            case SHOWALLPEOPLE:
                return new ShowAllPeople(taskManagementRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoards(taskManagementRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembers(taskManagementRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeams(taskManagementRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivity(taskManagementRepository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivity(taskManagementRepository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivity(taskManagementRepository);
            default:
                throw new IllegalArgumentException();
        }
    }
}
