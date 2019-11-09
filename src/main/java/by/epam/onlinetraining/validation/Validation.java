package by.epam.onlinetraining.validation;

import java.util.Map;

public class Validation {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String BIRTH_DATE = "birthDate";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "userPassword";
    //---
    private static final String SUM = "sumUp";//
    private static final String PAGE_NUMBER = "pageNumber";//
    private static final String COST = "cost";
    private static final String LIMIT = "limit";//
    private static final String PAGE = "page";//
    //---
    private static final String NAME_PATTERN = "^([a-zA-Z]){3,44}$";//TODO
    private static final String DATE_PATTERN = "^([1|2]{1}[0-9]{3}-[0-9]{1,2}-[0-9]{1,2})$";//TODO
    private static final String EMAIL_PATTERN = "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
            "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*" +
            "\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|" +
            "[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b" +
            "\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";//TODO
    //---
    private static final String SUM_PATTERN = "^([1-9]{1}[0-9]{0,8}\\.?[0-9]{0,2})$";
    private static final String PAGE_PATTERN = "^([1-9]{1}[0-9]*)$";
    private String invalidData;//некорректные данные

    public boolean isValidData(Map<String, String> inputData) {
        for (Map.Entry<String, String> entry : inputData.entrySet()) {
            String value = entry.getValue();
            if (value != null) {
                String key = entry.getKey();
                String pattern = definePattern(key);
                if (!isValid(pattern, value)) {
                    invalidData = key;
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isValidData(String key, String value) {//TODO
        if (value != null) {
            String pattern = definePattern(key);
            if (!isValid(pattern, value)) {
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
    }//TODO

    private boolean isValid(String pattern, String expression) {
        return expression.matches(pattern);
    }

    private String definePattern(String parameter) {
        switch (parameter) {
            case FIRST_NAME:
            case LAST_NAME:
                return NAME_PATTERN;
            case BIRTH_DATE:
                return DATE_PATTERN;
            case EMAIL:
                return EMAIL_PATTERN;
            case PASSWORD:
                return PASSWORD_PATTERN;
            //----
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
