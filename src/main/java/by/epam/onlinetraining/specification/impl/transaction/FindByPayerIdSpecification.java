package by.epam.onlinetraining.specification.impl.transaction;

import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class FindByPayerIdSpecification implements SqlSpecification {
    private int payerId;

    public FindByPayerIdSpecification(int payerId) {
        this.payerId = payerId;
    }

    @Override
    public String toSql() {
        return " WHERE payer_id = ?";
    }

    public List<Object> getParameters() {
        return Collections.singletonList(payerId);
    }
}
