package com.task_management_project.core;

import com.task_management_project.commands.add.AddCommentToTask;
import com.task_management_project.commands.add.AddPersonToTeam;
import com.task_management_project.commands.assignAndUnassign.AssignTaskToPerson;
import com.task_management_project.commands.assignAndUnassign.UnassignTaskToPerson;
import com.task_management_project.commands.change.*;
import com.task_management_project.commands.contracts.Command;
import com.task_management_project.commands.create.*;
import com.task_management_project.commands.enums.CommandType;
import com.task_management_project.commands.listing.*;
import com.task_management_project.commands.show.*;
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
            case ASSIGNTASKTOPERSON:
                return new AssignTaskToPerson(taskManagementRepository);
            case CHANGEPRIORITYINBUG:
                return new ChangePriorityInBug(taskManagementRepository);
            case CHANGEPRIORITYINSTORY:
                return new ChangePriorityInStory(taskManagementRepository);
            case CHANGERATINGINFEEDBACK:
                return new ChangeRatingInFeedback(taskManagementRepository);
            case CHANGESEVERITYINBUG:
                return new ChangeSeverityInBug(taskManagementRepository);
            case CHANGESIZEINSTORY:
                return new ChangeSizeInStory(taskManagementRepository);
            case CHANGESTATUSINBUG:
                return new ChangeStatusInBug(taskManagementRepository);
            case CHANGESTATUSINFEEDBACK:
                return new ChangeStatusInFeedback(taskManagementRepository);
            case CHANGESTATUSINSTORY:
                return new ChangeStatusInStory(taskManagementRepository);
            case CREATEBUGINBOARD:
                return new CreateBugInBoard(taskManagementRepository);
            case CREATEFEEDBACKINBOARD:
                return new CreateFeedbackInBoard(taskManagementRepository);
            case CREATESTORYINBOARD:
                return new CreateStoryInBoard(taskManagementRepository);
            case UNASSIGNTASKTOPERSON:
                return new UnassignTaskToPerson(taskManagementRepository);
            case LISTALLBUGS:
                return new ListAllBugs(taskManagementRepository);
            case LISTALLFEEDBACK:
                return new ListAllFeedback(taskManagementRepository);
            case LISTALLSTORIES:
                return new ListAllStories(taskManagementRepository);
            case LISTALLTASKS:
                return new ListAllTasks(taskManagementRepository);
            case LISTTASKSWITHASSIGNEE:
                return new ListTasksWithAssignee(taskManagementRepository);
            default:
                throw new IllegalArgumentException();
        }
    }
}
