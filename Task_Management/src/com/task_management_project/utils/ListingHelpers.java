package com.task_management_project.utils;

import com.task_management_project.commands.CommandsConstants;
import com.task_management_project.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers{

    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.getAsString());
        }

        return String.join(CommandsConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();

    }
}