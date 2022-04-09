package emented.lab6.client.workWithCommandLine;

import emented.lab6.client.util.CommandValidators;
import emented.lab6.common.entities.Coordinates;
import emented.lab6.common.entities.MusicBand;
import emented.lab6.common.entities.Studio;
import emented.lab6.common.entities.enums.MusicGenre;

import java.util.Locale;
import java.util.Scanner;

/**
 * Класс, предназанченный для генерации новой музыкальной группы
 */
public class MusicBandGenerator {

    /**
     * Новая музыкальная группа
     */
    private final MusicBand generatedMusicBand;

    /**
     * Координаты новой музыкальной группы
     */
    private final Coordinates coordinates = new Coordinates();

    /**
     * Сканер для считывания в интерактином режиме
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Конструктор класса
     */
    public MusicBandGenerator() {
        generatedMusicBand = new MusicBand();
        generatedMusicBand.setCoordinates(coordinates);
    }

    /**
     * Конструктор класса с заданным ID
     *
     * @param id ID новой музыкальной группы
     */
    public MusicBandGenerator(Long id) {
        generatedMusicBand = new MusicBand(id);
        generatedMusicBand.setCoordinates(coordinates);
    }

    /**
     * Метод, устанавливающий имя для новой музыкальной группы
     */
    private void getName() {
        String name = CommandValidators.validateStringInput("Enter the name of the music group",
                false,
                sc);
        generatedMusicBand.setName(name);
    }

    /**
     * Метод, считывающий число учатников с коммандной строки и устанавливающий его для новой муыкальной группы
     */
    private void getNumberOfParticipants() {
        long numberOfParticipants = CommandValidators.validateInput(arg -> ((long) arg) > 0,
                "Enter the number of participants",
                "Error processing the number, repeat the input",
                "The number of participants must be greater than 0, repeat the input",
                Long::parseLong,
                false,
                sc);
        generatedMusicBand.setNumberOfParticipants(numberOfParticipants);
    }

    /**
     * Метод, считывающий координату X с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
    private void getXCoordinate() {
        double x = CommandValidators.validateInput(arg -> ((double) arg) < Coordinates.MAX_X,
                "Enter the X coordinate of the group (its value should be no more than " + Coordinates.MAX_X + ")",
                "Error processing the number, repeat the input",
                "The X coordinate should be no more than " + Coordinates.MAX_X + ", repeat the input",
                Double::parseDouble,
                false,
                sc);
        generatedMusicBand.getCoordinates().setX(x);
    }

    /**
     * Метод, считывающий координату Y с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
    private void getYCoordinate() {
        Float y = CommandValidators.validateInput(arg -> ((Float) arg) < Coordinates.MAX_Y,
                "Enter the Y coordinate of the group (its value should be no more than " + Coordinates.MAX_Y + ")",
                "Error processing the number, repeat the input",
                "The Y coordinate should be no more than " + Coordinates.MAX_Y + ", repeat the input",
                Float::parseFloat,
                false,
                sc);
        generatedMusicBand.getCoordinates().setY(y);
    }

    /**
     * Метод, считывающий описание с коммандной строки и устанавливающий его для новой муыкальной группы
     */
    private void getDescription() {
        String description = CommandValidators.validateStringInput("Enter a description of the group (press ENTER to skip)",
                true,
                sc);
        generatedMusicBand.setDescription(description);
    }

    /**
     * Метод, считывающий жанр с коммандной строки и устанавливающий его для новой муыкальной группы
     */
    private void getMusicGenre() {
        MusicGenre genre = CommandValidators.validateInput(arg -> true,
                "Enter the genre of music from the suggested ones below (to skip, press ENTER)\n" + MusicGenre.show(),
                "There is no such musical genre, repeat the input",
                "Input error",
                string -> MusicGenre.valueOf(string.toUpperCase(Locale.ROOT)),
                true,
                sc);
        generatedMusicBand.setGenre(genre);
    }

    /**
     * Метод, считывающий студию с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
    private void getStudio() {
        Studio studio = CommandValidators.validateInput(arg -> true,
                "Enter the studio address (press ENTER to skip)",
                "Input error",
                "Input error",
                Studio::new,
                true,
                sc);
        generatedMusicBand.setStudio(studio);
    }

    /**
     * Метод, устанавливающий переменные, считанные в интерактивном режиме
     */
    public void setVariables() {
        getName();
        getXCoordinate();
        getYCoordinate();
        getNumberOfParticipants();
        getDescription();
        getMusicGenre();
        getStudio();
    }

    /**
     * Метод, возвращающий новую музыкальную группу
     *
     * @return Новая музыкальная группа
     */
    public MusicBand getGeneratedMusicBand() {
        return generatedMusicBand;
    }
}
