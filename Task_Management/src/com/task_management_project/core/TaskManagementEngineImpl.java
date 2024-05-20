package com.task_management_project.core;

import com.task_management_project.commands.contracts.Command;
import com.task_management_project.core.contracts.CommandFactory;
import com.task_management_project.core.contracts.TaskManagementEngine;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.*;

public class TaskManagementEngineImpl implements TaskManagementEngine {
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";
    private static final String MAIN_SPLIT_SYMBOL = " ";
    private static final String COMMENT_OPEN_SYMBOL = "{";
    private static final String COMMENT_CLOSE_SYMBOL = "}";
    private static final String REPORT_SEPARATOR = "####################";

    private final CommandFactory commandFactory;
    private final TaskManagementRepository taskManagementRepository;


    public TaskManagementEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    print(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    print(ex.getMessage());
                } else {
                    print(ex.toString());
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        List<String> parameters = extractCommandParameters(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, taskManagementRepository);
        String executionResult = command.execute(parameters);
        print(executionResult);
    }

    /**
     * Receives a full line and extracts the command to be executed from it.
     * For example, if the input line is "FilterBy Assignee John", the method will return "FilterBy".
     *
     * @param inputLine A complete input line
     * @return The name of the command to be executed
     */
    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }

    /**
     * Receives a full line and extracts the parameters that are needed for the command to execute.
     * For example, if the input line is "FilterBy Assignee John",
     * the method will return a list of ["Assignee", "John"].
     *
     * @param inputLine A complete input line
     * @return A list of the parameters needed to execute the command
     */
    private List<String> extractCommandParameters(String inputLine) {
        if (inputLine.contains(COMMENT_OPEN_SYMBOL)) {
            return extractCommentParameters(inputLine);
        }
        String[] commandParts = inputLine.split(" ");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

    public List<String> extractCommentParameters(String fullCommand) {
        int indexOfOpenComment = fullCommand.indexOf(COMMENT_OPEN_SYMBOL);
        String[] commandParts = fullCommand.split(" ");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < fullCommand.length() - 1 ; i++) {
            if (commandParts[i].contains(COMMENT_OPEN_SYMBOL)){
                StringBuilder builder = new StringBuilder();
                for (int j = 5; j < commandParts.length; j++) {
                    commandParts[j] = commandParts[j].replaceAll("[{}]","");
                    builder.append(commandParts[j]).append((Arrays.asList(commandParts).indexOf(commandParts[j]) < commandParts.length - 1) ? " " : "");
                }
                parameters.add(builder.toString());
                break;
            }
            else {
                parameters.add(commandParts[i]);
            }
        }
        return parameters;
    }

    private void print(String result) {
        System.out.println(result);
        System.out.println(REPORT_SEPARATOR);
    }
}
