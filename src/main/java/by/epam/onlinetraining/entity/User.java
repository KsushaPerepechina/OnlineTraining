package by.epam.onlinetraining.entity;

import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.UserRole;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class User extends Entity {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String password;
    private BlockingStatus blockingStatus;
    private UserRole role;
    private BigDecimal balance;

    public User() {
    }

    public User(Integer id, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber,
                BlockingStatus blockingStatus, UserRole role, BigDecimal balance) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.blockingStatus = blockingStatus;
        this.role = role;
        this.balance = balance;
    }

    public User(int id, String firstName, String lastName, LocalDate birthDate, String phoneNumber) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public User(String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber, String password, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.balance = balance;
    }

    public User(int id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }

    public User(int id, BlockingStatus blockingStatus) {
        super(id);
        this.blockingStatus = blockingStatus;
    }

    public User(int id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int id) {
        super(id);
    }

    public User(int id, UserRole role) {
        super(id);
        this.role = role;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BlockingStatus getBlockingStatus() {
        return blockingStatus;
    }

    public void setBlockingStatus(BlockingStatus blockingStatus) {
        this.blockingStatus = blockingStatus;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
        return blockingStatus == user.blockingStatus &&
                role == user.role &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(password, user.password) &&
                Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 0;
        result += seed * (getId() == null ? 0 : getId().hashCode());
        result += seed * (firstName == null ? 0 : firstName.hashCode());
        result += seed * (lastName == null ? 0 : lastName.hashCode());
        result += seed * (birthDate == null ? 0 : birthDate.hashCode());
        result += seed * (email == null ? 0 : email.hashCode());
        result += seed * (phoneNumber == null ? 0 : phoneNumber.hashCode());
        result += seed * (password == null ? 0 : password.hashCode());
        result += seed * (role == null ? 0 : role.hashCode());
        result += seed * (blockingStatus == null ? 0 : blockingStatus.hashCode());
        result += seed * (balance == null ? 0 : balance.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("first name='" + firstName + "'")
                .add("last name='" + lastName + "'")
                .add("birth date=" + birthDate)
                .add("email='" + email + "'")
                .add("phone number='" + phoneNumber + "'")
                .add("password='" + password + "'")
                .add("blocking status=" + blockingStatus)
                .add("role=" + role)
                .add("balance=" + balance)
                .toString();
    }
}
