package emented.lab6.client.util;

import emented.lab6.client.ClientConfig;
import emented.lab6.common.exceptions.WrongAmountOfArgsException;
import emented.lab6.common.exceptions.WrongArgException;
import emented.lab6.common.util.TextColoring;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public final class CommandValidators {

    private CommandValidators() {
    }

    public static <T> T validateInput(Predicate<Object> predicate,
                                      String message,
                                      String error,
                                      String wrong,
                                      Function<String, T> function,
                                      Boolean nullable,
                                      Scanner sc) {
        ClientConfig.getTextPrinter().printlnText(message);
        String input;
        T value;
        do {
            try {
                input = sc.nextLine();
                if ("".equals(input) && Boolean.TRUE.equals(nullable)) {
                    return null;
                } else if ("".equals(input) && !Boolean.TRUE.equals(nullable)) {
                    ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("This field cannot be null, repeat the input"));
                    continue;
                }
                value = function.apply(input);
            } catch (IllegalArgumentException e) {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText(error));
                continue;
            } catch (NoSuchElementException e) {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Invalid character entered"));
                System.exit(1);
                return null;
            }
            if (predicate.test(value)) {
                return value;
            } else {
                System.out.println(TextColoring.getRedText(wrong));
            }
        } while (true);
    }

    public static void validateAmountOfArgs(String[] args, int amountOfArgs) throws WrongAmountOfArgsException {
        if (args.length != amountOfArgs) {
            throw new WrongAmountOfArgsException("Wrong amount of args, this command needs " + amountOfArgs + " args");
        }
    }

    public static <T> T validateArg(Predicate<Object> predicate,
                                    String wrong,
                                    Function<String, T> function,
                                    String argument) throws WrongArgException, IllegalArgumentException {
        T value = function.apply(argument);
        if (predicate.test(value)) {
            return value;
        } else {
            throw new WrongArgException(wrong);
        }
    }

    public static String validateStringInput(String message,
                                             Boolean nullable,
                                             Scanner sc) {
        System.out.println(message);
        String input;
        String value;
        do {
            try {
                input = sc.nextLine();
                if ("".equals(input) && Boolean.TRUE.equals(nullable)) {
                    return null;
                } else if ("".equals(input) && !Boolean.TRUE.equals(nullable)) {
                    ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("This field cannot be null, repeat the input"));
                    continue;
                }
                value = input;
            } catch (NoSuchElementException e) {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Invalid character entered"));
                System.exit(1);
                return null;
            }
            return value;
        } while (true);
    }
}
