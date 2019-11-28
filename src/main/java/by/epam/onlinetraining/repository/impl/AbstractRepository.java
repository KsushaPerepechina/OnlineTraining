package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Entity;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.repository.Repository;
import by.epam.onlinetraining.utils.QueryHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {
    private static final Logger LOGGER = LogManager.getLogger();
    private ProxyConnection connection;

    AbstractRepository(ProxyConnection connection) {
        this.connection = connection;
    }

    List<T> executeQuery(String query, EntityBuilder<T> builder, List<Object> params) throws RepositoryException {//выборка из БД
        List<T> objects = new ArrayList<>();//список резулттатов выборки
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {//подготовка запроса с передачей строки запроса
            QueryHelper.prepare(preparedStatement, params);//подготовка паремтров запролса
            LOGGER.error(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();//выполнение
            while (resultSet.next()) {//для каждой записи выборки
                T item = builder.build(resultSet);//билдим сущность
                objects.add(item);//добавляем в список
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
        return objects;
    }

    Optional<T> executeQueryForSingleResult(String query, EntityBuilder<T> builder, List<Object> params) throws RepositoryException {//выполнение выборки с ед результатом
        List<T> items = executeQuery(query, builder, params);//выполнение
        return items.size() == 1 ?//если результирующая выборка из 1 записи
                Optional.of(items.get(0)) ://возвращаем первый и ед эл-т списка
                Optional.empty();//если нет, то возвращаем пустой опшнл
    }

    @Override
    public void save(T item) throws RepositoryException {//метод добавления новой записи
        try {
            String query;
            Map<String, Object> fields = getFields(item);//поля таблицы
            String tableName = getTableName();//название таблицы
            if (item.getId() != null) {//если id существует
                query = QueryHelper.formUpdateQuery(fields, tableName);//формируем запрос по обновлению данных
            } else {//если id не существует
                query = QueryHelper.formInsertQuery(fields, tableName);//формируем запрос по добавлению данных
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {//подготовка statement-а
                List<Object> params = new ArrayList<>(fields.values());
                params = params.stream().filter(param -> !(param == null)).collect(Collectors.toList());
                QueryHelper.prepare(preparedStatement, params);//установка аргументов ? ? ?
                LOGGER.error(preparedStatement);
                preparedStatement.executeUpdate();//выполнение обновления
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        try {
            String query;
            String tableName = getTableName();
            query = QueryHelper.formDeleteQuery(tableName);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
