package by.epam.onlinetraining.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryHelper {//TODO refactor QueryConstructor
    private static final String INSERT_QUERY = "INSERT INTO ";
    private static final String UPDATE_QUERY = "UPDATE ";
    private static final String SET = " SET ";
    private static final String VALUES = " VALUES";
    private static final String WHERE = " WHERE ";
    private static final String ID = "id";
    private static final String COMMA = ",";
    private static final String OPENING_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";
    private static final String QUESTION_MARK = "?";
    private static final String EQUAL_MARK = "=";

    public static String formInsertQuery(Map<String, Object> fields, String table) {
        StringBuilder columns = new StringBuilder(OPENING_BRACKET);
        StringBuilder values = new StringBuilder(OPENING_BRACKET);
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String column = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                columns.append(column).append(COMMA);
                values.append(QUESTION_MARK).append(COMMA);
            }
        }
        values.deleteCharAt(values.lastIndexOf(COMMA));
        columns.deleteCharAt(columns.lastIndexOf(COMMA));
        values.append(CLOSING_BRACKET);
        columns.append(CLOSING_BRACKET);
        return INSERT_QUERY + table + columns + VALUES + values;
    }

    public static String formUpdateQuery(Map<String, Object> fields, String table) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String column = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                if (column.equals(ID)) {
                    query.deleteCharAt(query.lastIndexOf(COMMA));
                    query.append(WHERE).append(ID).append(EQUAL_MARK).append(QUESTION_MARK);
                } else {
                    query.append(column).append(EQUAL_MARK).append(QUESTION_MARK).append(COMMA);
                }
            }
        }
        return UPDATE_QUERY + table + SET + query;
    }

    public static void prepare(PreparedStatement preparedStatement, Map<String, Object> fields) throws SQLException {
        int i = 0;
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                preparedStatement.setString(++i, String.valueOf(value));
            }
        }
    }

    public static void prepare(PreparedStatement preparedStatement, List<Object> params) throws SQLException {//TODO
        int length = params.size();
        for (int i = 0; i < length; i++) {
            Object param = params.get(i);
            if (!(param instanceof Integer)) {
                preparedStatement.setString(i + 1, String.valueOf(param));
            } else {
                preparedStatement.setInt(i + 1, (Integer) param);
            }
        }
    }
}
