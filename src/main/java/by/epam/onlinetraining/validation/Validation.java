package by.epam.onlinetraining.validation;

import java.util.Map;

public class Validation {
    private static final String ID = "id";
    private static final String MENTOR_ID = "mentorId";
    private static final String TRAINING_ID = "trainingId";
    private static final String STUDENT_ID = "studentId";
    private static final String RECORD_ID = "recordId";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String BIRTH_DATE = "birthDate";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String PASSWORD = "userPassword";
    private static final String SUM = "sum";
    private static final String TRAINING_NAME = "trainingName";
    private static final String ASSIGNMENT_NAME = "assignmentName";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String MARK = "mark";
    private static final String QUALITY = "quality";
    private static final String PERFORMANCE = "performance";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String COST = "cost";
    private static final String LIMIT = "limit";
    private static final String PAGE = "page";

    private static final String ID_PATTERN = "^([1-9]{1}[0-9]{0,10})$";
    private static final String NAME_PATTERN = "^(([A-z]([A-z]){1,16}([\u0020-][A-z]([A-z]){1,16})?))$";
    private static final String TRAINING_NAME_PATTERN = "^[A-z0-9:&+#.,\u0020]{2,30}$";
    private static final String DATE_PATTERN = "^((((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|" +
            "02[-]?(0[1-9]|1[0-9]|2[0-8]))[-]?[0-9]{4}|02[-]?29[-]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|(" +
            "[13579][26]|[02468][048]|0[0-9]|1[0-6])00))|(((0[1-9]|[12][0-9]|30)[.]?(0[13-9]|1[012])|31[.]?(0[13578]|" +
            "1[02])|(0[1-9]|1[0-9]|2[0-8])[.]?02)[.]?[0-9]{4}|29[.]?02[.]?([0-9]{2}(([2468][048]|[02468][48])|[13579]" +
            "[26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00)))$";
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
            "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f" +
            "])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4]" +
            "[0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08" +
            "\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";
    private static final String PHONE_NUMBER_PATTERN =
            "^(\\+?[1-9]{3}((\\([1-9]{2}\\))|([1-9]{2}))[1-9][0-9]{2}([\u0020-]?[0-9]{2}){2})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";
    private static final String SUM_PATTERN = "^([1-9][0-9]{0,2})$";
    private static final String MARK_PATTERN = "^([1-9]|10)$";
    private static final String PAGE_PATTERN = "^([1-9]{1}[0-9]{0,5})$";
    private String invalidData;

    public boolean isValidData(Map<String, String> inputData) {
        for (Map.Entry<String, String> entry : inputData.entrySet()) {
            String value = entry.getValue();
            if (value != null) {
                String key = entry.getKey();
                String pattern = definePattern(key);
                if (!value.matches(pattern)) {
                    invalidData = key;
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isValidData(String key, String value) {
        if (value != null) {
            String pattern = definePattern(key);
            if (!value.matches(pattern)) {
                invalidData = key;
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public String getInvalidData() {
        return invalidData;
    }

    private String definePattern(String parameter) {
        switch (parameter) {
            case ID:
            case STUDENT_ID:
            case MENTOR_ID:
            case TRAINING_ID:
            case RECORD_ID:
                return ID_PATTERN;
            case FIRST_NAME:
            case LAST_NAME:
                return NAME_PATTERN;
            case BIRTH_DATE:
            case START_DATE:
            case END_DATE:
                return DATE_PATTERN;
            case EMAIL:
                return EMAIL_PATTERN;
            case PHONE_NUMBER:
                return PHONE_NUMBER_PATTERN;
            case PASSWORD:
                return PASSWORD_PATTERN;
            case ASSIGNMENT_NAME:
            case TRAINING_NAME:
                return TRAINING_NAME_PATTERN;
            case MARK:
            case PERFORMANCE:
            case QUALITY:
                return MARK_PATTERN;
            case SUM:
            case COST:
                return SUM_PATTERN;
            case LIMIT:
            case PAGE:
            case PAGE_NUMBER:
                return PAGE_PATTERN;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
