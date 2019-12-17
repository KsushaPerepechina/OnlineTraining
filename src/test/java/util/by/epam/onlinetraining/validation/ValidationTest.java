package util.by.epam.onlinetraining.validation;

import by.epam.onlinetraining.validation.Validation;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidationTest {
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String BIRTH_DATE = "birthDate";
    private static final String PASSWORD = "userPassword";
    private static final String MARK = "mark";
    private static final String SUM = "sum";
    private Validation validation;

    @BeforeClass
    public void setUp() {
        validation = new Validation();
    }

    @DataProvider(name = "isValidPhoneNumberPositiveTest")
    public Object[][] dataForPhoneNumberValidationPositiveTest() {
        return new Object[][]{
                {"+375(29)3873342"}, {"+375(29)387-33-42"}, {"+375(29)387 33 42"},
                {"+375293873342"}, {"+37529387-33-42"}, {"+37529387 33 42"},
                {"375(29)3873342"}, {"375(29)387-33-42"}, {"375(29)387 33 42"},
                {"375293873342"}, {"37529387-33-42"}, {"37529387 33 42"}
        };
    }

    @DataProvider(name = "isValidPhoneNumberNegativeTest")
    public Object[][] dataForPhoneNumberValidationNegativeTest() {
        return new Object[][]{
                {"+375(29)38733425"}, {"+375(29)387334"}, {"+375(29))387-33-42"},
                {"+37529)387 33 42"}, {"+37529)387  33  42"}, {"+375(29)387--33--42"},
                {"3873342"}, {"387-33-42"}, {"387 33 42"}
        };
    }

    @DataProvider(name = "isValidEmailPositiveTest")
    public Object[][] dataForEmailValidationPositiveTest() {
        return new Object[][]{
                {"ksusha.perepechina@gmail.com"}, {"ksusha.perepechina@mailer.ru"},
                {"ksusha_perepechina@mailer.ru"}, {"ksusha_perepechina98@mailer.ru"}
        };
    }

    @DataProvider(name = "isValidEmailNegativeTest")
    public Object[][] dataForEmailValidationNegativeTest() {
        return new Object[][]{
                {"ksusha.perepechina@gmail,com"}, {"ksusha.perepechina.gmail.com"}, {"ksusha_perepechina@gmailcom"},
                {".ksusha.perepechina@gmail.com"}, {"ksusha.perepechina@gmail.com@gmail.com"}, {"ksusha_perepechina"}
        };
    }

    @DataProvider(name = "isValidBirthDatePositiveTest")
    public Object[][] dataForBirthDateValidationPositiveTest() {
        return new Object[][]{
                {"11-23-1998"}, {"12-31-2019"}, {"23.11.1998"}, {"31.12.2019"}
        };
    }

    @DataProvider(name = "isValidBirthDateNegativeTest")
    public Object[][] dataForBirthDateValidationNegativeTest() {
        return new Object[][]{
                {"11.23.1998"}, {"11-23-98"}, {"11.23.98"}, {"31.11.2019"}, {"30.02.2019"}, {"23-11-1998"}
        };
    }

    @DataProvider(name = "isValidPasswordPositiveTest")
    public Object[][] dataForPasswordValidationPositiveTest() {
        return new Object[][]{
                {"Password0"}, {"Paaaaaaasswoooooord0"}, {"pAsSwOrD3210"}
        };
    }

    @DataProvider(name = "isValidPasswordNegativeTest")
    public Object[][] dataForPasswordValidationNegativeTest() {
        return new Object[][]{
                {"password0"}, {"Password"}, {"Passwd0"}, {"Passwooooooooooooord0"}, {"Password-0"}, {"Пароль00"}
        };
    }

    @DataProvider(name = "isValidMarkPositiveTest")
    public Object[][] dataForMarkValidationPositiveTest() {
        return new Object[][]{
                {"1"}, {"2"}, {"3"}, {"4"}, {"5"}, {"6"}, {"7"}, {"8"}, {"9"}, {"10"},
        };
    }

    @DataProvider(name = "isValidMarkNegativeTest")
    public Object[][] dataForMarkValidationNegativeTest() {
        return new Object[][]{
                {"0"}, {"11"}, {"5.5"}, {"-1"}, {"five"}
        };
    }

    @DataProvider(name = "isValidSumPositiveTest")
    public Object[][] dataForSumValidationPositiveTest() {
        return new Object[][]{
                {"1"}, {"55"}, {"100"}
        };
    }

    @DataProvider(name = "isValidSumNegativeTest")
    public Object[][] dataForSumValidationNegativeTest() {
        return new Object[][]{
                {"0"}, {"5.5"}, {"101"}, {"-5"}
        };
    }

    @Test(dataProvider = "isValidPhoneNumberPositiveTest")
    public void isValidPhoneNumberPositiveTest(String phoneNumber) {
        boolean actual = validation.isValidData(PHONE_NUMBER, phoneNumber);
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "isValidPhoneNumberNegativeTest")
    public void isValidPhoneNumberNegativeTest(String phoneNumber) {
        boolean actual = validation.isValidData(PHONE_NUMBER, phoneNumber);
        Assert.assertFalse(actual);
    }

    @Test(dataProvider = "isValidEmailPositiveTest")
    public void isValidEmailPositiveTest(String email) {
        boolean actual = validation.isValidData(EMAIL, email);
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "isValidEmailNegativeTest")
    public void isValidEmailNegativeTest(String email) {
        boolean actual = validation.isValidData(EMAIL, email);
        Assert.assertFalse(actual);
    }

    @Test(dataProvider = "isValidBirthDatePositiveTest")
    public void isValidBirthDatePositiveTest(String birthDate) {
        boolean actual = validation.isValidData(BIRTH_DATE, birthDate);
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "isValidBirthDateNegativeTest")
    public void isValidBirthDateNegativeTest(String birthDate) {
        boolean actual = validation.isValidData(BIRTH_DATE, birthDate);
        Assert.assertFalse(actual);
    }

    @Test(dataProvider = "isValidPasswordPositiveTest")
    public void isValidPasswordPositiveTest(String password) {
        boolean actual = validation.isValidData(PASSWORD, password);
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "isValidPasswordNegativeTest")
    public void isValidPasswordNegativeTest(String password) {
        boolean actual = validation.isValidData(PASSWORD, password);
        Assert.assertFalse(actual);
    }

    @Test(dataProvider = "isValidMarkPositiveTest")
    public void isValidMarkPositiveTest(String mark) {
        boolean actual = validation.isValidData(MARK, mark);
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "isValidMarkNegativeTest")
    public void isValidMarkNegativeTest(String mark) {
        boolean actual = validation.isValidData(MARK, mark);
        Assert.assertFalse(actual);
    }

    @Test(dataProvider = "isValidSumPositiveTest")
    public void isValidSumPositiveTest(String sum) {
        boolean actual = validation.isValidData(SUM, sum);
        Assert.assertTrue(actual);
    }

    @Test(dataProvider = "isValidSumNegativeTest")
    public void isValidSumNegativeTest(String sum) {
        boolean actual = validation.isValidData(SUM, sum);
        Assert.assertFalse(actual);
    }
}

