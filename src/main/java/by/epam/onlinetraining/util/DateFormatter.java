package by.epam.onlinetraining.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EN = "EN";
    private static final String RU = "RU";
    private static final String UNSUPPORTED_LANG_MESSAGE = "Unsupported language: ";
    private static final String EN_DATE_FORMAT = "MM-dd-yyyy";
    private static final String RU_DATE_FORMAT = "dd.MM.yyyy";

    public LocalDate format(String stringDate, String language) {
        LocalDate date;
        if (EN.equals(language)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EN_DATE_FORMAT);
            date = LocalDate.parse(stringDate, formatter);
        } else if (RU.equals(language)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RU_DATE_FORMAT);
            date = LocalDate.parse(stringDate, formatter);
        } else {
            LOGGER.error(UNSUPPORTED_LANG_MESSAGE + language);
            throw new UnsupportedOperationException(UNSUPPORTED_LANG_MESSAGE + language);
        }
        return date;
    }
}
