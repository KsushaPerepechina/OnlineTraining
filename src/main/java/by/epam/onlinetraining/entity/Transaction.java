package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.OperationType;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaction extends Entity {
    private int payerId;
    private Date date;
    private OperationType operationType;
    private BigDecimal sum;

    public Transaction(int payerId, Date date, OperationType operationType, BigDecimal sum) {
        this.payerId = payerId;
        this.date = date;
        this.operationType = operationType;
        this.sum = sum;
    }

    public Transaction(int id, int payerId, Date date, OperationType operationType, BigDecimal sum) {
        super(id);
        this.payerId = payerId;
        this.date = date;
        this.operationType = operationType;
        this.sum = sum;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    //TODO hashcode(), equals() & toString()
}
