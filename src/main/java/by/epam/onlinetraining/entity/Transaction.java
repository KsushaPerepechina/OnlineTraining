package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.OperationType;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.StringJoiner;

public class Transaction extends Entity {
    private int payerId;
    private Date date;
    private OperationType operationType;
    private BigDecimal sum;

    public Transaction(Integer id, int payerId, Date date, OperationType operationType, BigDecimal sum) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return payerId == that.payerId && operationType == that.operationType &&
                getId() != null && getId().equals(that.getId()) &&
                (date == that.date || (date != null && date.equals(that.date))) &&
                (sum == that.sum || (sum != null && sum.equals(that.sum)));
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * payerId;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (date == null ? 0 : date.hashCode());
        result += seed * (operationType == null ? 0 : operationType.hashCode());
        result += seed * (sum == null ? 0 : sum.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("payerId=" + payerId)
                .add("date=" + date)
                .add("operationType=" + operationType)
                .add("sum=" + sum)
                .toString();
    }
}
