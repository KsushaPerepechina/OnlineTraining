package util.by.epam.onlinetraining.util;

import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.util.PagesDelimiter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PagesDelimiterTest {
    private PagesDelimiter<Object> pagesDelimiter;

    @BeforeClass
    public void setUp() {
        pagesDelimiter = new PagesDelimiter<>();
    }

    @DataProvider(name = "composePageNumbersListTest")
    public Object[][] dataForComposePageNumbersListTest() {
        return new Object[][]{
                {
                        Arrays.asList(
                                new Object(),
                                new Object(),
                                new Object(),
                                new Object(),
                                new Object(),
                                new Object()
                        ),
                        5,
                        Arrays.asList(1, 2)
                },
                {
                        Arrays.asList(
                                new Object(),
                                new Object(),
                                new Object(),
                                new Object(),
                                new Object()
                        ),
                        5,
                        Collections.singletonList(1)
                }
        };
    }

    @DataProvider(name = "composePageListTest")
    public Object[][] dataForComposePageListTest() {
        return new Object[][]{
                {
                        Arrays.asList(
                                new User(1),
                                new User(2),
                                new User(3),
                                new User(4),
                                new User(5)
                        ),
                        2,
                        2,
                        Arrays.asList(
                                new User(3),
                                new User(4)
                        )
                },
                {
                        Arrays.asList(
                                new User(1),
                                new User(2),
                                new User(3),
                                new User(4),
                                new User(5),
                                new User(6)
                        ),
                        5,
                        5,
                        Collections.singletonList(new User(6))
                }
        };
    }

    @Test(dataProvider = "composePageNumbersListTest")
    public void composePageNumbersListTest(List<Object> itemList, int limit, List<Integer> expected) {
        List<Integer> actual = pagesDelimiter.composePageNumbersList(itemList, limit);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "composePageListTest")
    public void composePageListTest(List<Object> fullList, int limit, int offset, List<Integer> expected) {
        List<Object> actual = pagesDelimiter.composePageList(fullList, limit, offset);
        Assert.assertEquals(actual, expected);
    }
}
