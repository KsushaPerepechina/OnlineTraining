package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.UserRole;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.StringJoiner;

public class User extends Entity {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String password;
    private boolean blocked;
    private UserRole role;
    private BigDecimal balance;

    public User() {
    }

    public User(int id, String firstName, String lastName, Date birthDate, String email, String password,
                boolean blocked, UserRole role, BigDecimal balance) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.role = role;
        this.balance = balance;
    }

    public User(String firstName, String lastName, Date birthDate, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }

    public User(int id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, boolean blocked) {
        super(id);
        this.blocked = blocked;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return blocked == user.blocked &&
                role != null && role == user.role &&
                (firstName == user.firstName || (firstName != null && firstName.equals(user.firstName))) &&
                (lastName == user.lastName || (lastName != null && lastName.equals(user.lastName))) &&
                (birthDate == user.birthDate || (birthDate != null && birthDate.equals(user.birthDate))) &&
                (email == user.email || (email != null && email.equals(user.email))) &&
                (password == user.password || (password != null && password.equals(user.password)));//TODO
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (firstName == null ? 0 : firstName.hashCode());
        result += seed * (lastName == null ? 0 : lastName.hashCode());
        result += seed * (birthDate == null ? 0 : birthDate.hashCode());
        result += seed * (email == null ? 0 : email.hashCode());
        result += seed * (password == null ? 0 : password.hashCode());//TODO
        result += seed * (role == null ? 0 : role.hashCode());
        result += seed * Boolean.hashCode(blocked);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("birthDate=" + birthDate)
                .add("email='" + email + "'")
                .add("role=" + role)
                .add("blocked=" + blocked)
                .toString();
    }
}
