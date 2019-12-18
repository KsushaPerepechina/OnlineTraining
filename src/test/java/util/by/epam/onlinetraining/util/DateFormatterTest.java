package util.by.epam.onlinetraining.util;

import by.epam.onlinetraining.util.DateFormatter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class DateFormatterTest {
    private static final String EN = "EN";
    private static final String RU = "RU";
    private static final String UK = "UK";
    private static final String DATE_UK = "31/12/2019";
    private DateFormatter dateFormatter;

    @BeforeClass
    public void setUp() {
        dateFormatter = new DateFormatter();
    }

    @DataProvider(name = "formatPositiveTest")
    public Object[][] dataForFormatPositiveTest() {
        return new Object[][]{
                {"11-23-1998", EN, LocalDate.of(1998, 11, 23)},
                {"18.10.1995", RU, LocalDate.of(1995, 10, 18)}
        };
    }

    @Test(dataProvider = "formatPositiveTest")
    public void formatPositiveTest(String date, String language, LocalDate expected) {
        LocalDate actual = dateFormatter.format(date, language);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void formatNegativeTest() {
        dateFormatter.format(DATE_UK, UK);
    }
}
