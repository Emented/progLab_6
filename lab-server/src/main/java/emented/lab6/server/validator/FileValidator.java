package emented.lab6.server.validator;

import emented.lab6.common.entities.Coordinates;
import emented.lab6.common.entities.MusicBand;
import emented.lab6.common.entities.Studio;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.Server;
import emented.lab6.server.ServerConfig;
import emented.lab6.server.util.CollectionManager;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * Класс, содержащий в себе методы валидации
 */
public final class FileValidator {

    static {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    }

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    public static void validateClass(CollectionManager collectionInWork) {
        for (MusicBand m : collectionInWork.getMusicBands()) {
            Set<ConstraintViolation<Coordinates>> validatedCoordinates = VALIDATOR.validate(m.getCoordinates());
            Set<ConstraintViolation<Studio>> validatedStudio = new HashSet<>();
            if (m.getStudio() != null) {
                validatedStudio = VALIDATOR.validate(m.getStudio());
            }
            Set<ConstraintViolation<MusicBand>> validatedBand = VALIDATOR.validate(m);
            if (!validatedBand.isEmpty() || !validatedCoordinates.isEmpty() || !validatedStudio.isEmpty()) {
                ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("Errors were found in the source file"));
                validatedBand.stream().map(ConstraintViolation::getMessage).map(TextColoring::getRedText)
                        .forEach(ServerConfig.getTextPrinter()::printlnText);
                validatedCoordinates.stream().map(ConstraintViolation::getMessage).map(TextColoring::getRedText)
                        .forEach(ServerConfig.getTextPrinter()::printlnText);
                validatedStudio.stream().map(ConstraintViolation::getMessage).map(TextColoring::getRedText)
                        .forEach(ServerConfig.getTextPrinter()::printlnText);
                System.exit(1);
            }
        }
        ServerConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Transfer data from the file to the collection, the application is successfully launched"));
    }
}
