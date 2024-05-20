package com.task_management_project.utils;

import com.task_management_project.models.enums.BugStatus;

public class ParsingHelpers {
    public static final String NO_SUCH_ENUM = "There is no %s in %ss.";

    public static int tryParseInt(String valueToParse, String errorMessage) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type) {
        try {
            return Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_ENUM, valueToParse, type.getSimpleName()));
        }
    }

    public static  <E extends Enum<E>> boolean isItStatus(String parameter, Class<E> statusEnum) {
        try {
            Enum.valueOf(statusEnum, parameter.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
