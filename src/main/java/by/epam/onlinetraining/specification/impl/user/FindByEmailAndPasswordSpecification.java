package by.epam.onlinetraining.specification.impl.user;

import by.epam.onlinetraining.specification.SqlSpecification;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.List;

public class FindByEmailAndPasswordSpecification implements SqlSpecification {
    private String email;
    private String password;

    public FindByEmailAndPasswordSpecification(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toSql() {
        return "WHERE email = ? AND password = ?";
    }

    public List<Object> getParameters() {
        String encryptedPassword = decryptPassword(password);
        return Arrays.asList(email, encryptedPassword);
    }

    private String decryptPassword(String password) {
        return DigestUtils.sha512Hex(password);
    }
}
