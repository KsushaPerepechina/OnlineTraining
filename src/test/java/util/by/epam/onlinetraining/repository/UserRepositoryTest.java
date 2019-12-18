package util.by.epam.onlinetraining.repository;

import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.repository.impl.RepositoryCreator;
import by.epam.onlinetraining.repository.impl.UserRepository;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class UserRepositoryTest {
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIRTH_DATE = "birth_date";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String PASSWORD = "password";
    private static final String BALANCE = "balance";
    private UserRepository userRepository;
    private User user;
    private Map<String, Object> expected;

    @BeforeClass
    public void setUp() {
        RepositoryCreator repositoryCreator = new RepositoryCreator();
        userRepository = repositoryCreator.getUserRepository();
        String firstName = "Kseniya";
        String lastName = "Perepechina";
        LocalDate birthDate = LocalDate.of(1998, 11, 23);
        String email = "kseniya.perepechina0@gmail.com";
        String phoneNumber = "+375293873342";
        String password = "Password0";
        user = new User(firstName, lastName, birthDate, email, phoneNumber, password, null);
        expected = new LinkedHashMap<>(8);
        expected.put(FIRST_NAME, firstName);
        expected.put(LAST_NAME, lastName);
        expected.put(BIRTH_DATE, birthDate);
        expected.put(EMAIL, email);
        expected.put(PHONE_NUMBER, phoneNumber);
        expected.put(PASSWORD, password);
        expected.put(BALANCE, null);
        expected.put(ID, null);

    }

    @Test
    public void getFieldsTest() {
        Map<String, Object> actual = userRepository.getFields(user);
        Assert.assertEquals(actual, expected);
    }
}
