package by.epam.onlinetraining.utils;

import java.util.ArrayList;
import java.util.List;

public class PagesDelimiter<T> {
    public List<Integer> composePageNumbersList(List<T> itemList, int limit) {
        List<Integer> pageNumbersList = new ArrayList<>();
        int listSize = itemList.size();
        int counter;
        if (listSize % limit != 0) {
            counter = (listSize / limit) + 1;
        } else {
            counter = (listSize / limit);
        }
        for (int i = 1; i <= counter; i++) {
            pageNumbersList.add(i);
        }
        return pageNumbersList;
    }
}
