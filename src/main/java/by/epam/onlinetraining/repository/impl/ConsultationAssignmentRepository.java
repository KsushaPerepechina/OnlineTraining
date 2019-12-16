package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.ConsultationAssignmentBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.ConsultationAssignment;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConsultationAssignmentRepository extends AbstractRepository<ConsultationAssignment> {
    private static final String TABLE_NAME = "consultation_assignments";
    private static final String SELECT_QUERY = "SELECT consultation_assignments.id, assignments.id, assignments.name, " +
            "assignments.type, trainings.id, trainings.name, trainings.mentor_id, consultations.id " +
            "FROM consultation_assignments JOIN assignments ON consultation_assignments.assignment_id = assignments.id " +
            "JOIN consultations ON consultation_assignments.consultation_id = consultations.id " +
            "JOIN trainings ON assignments.training_id = trainings.id ";
    private static final String ID = "id";
    private static final String CONSULTATION_ID = "consultation_id";
    private static final String ASSIGNMENT_ID = "assignment_id";

    public ConsultationAssignmentRepository(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(ConsultationAssignment consultationAssignment) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(CONSULTATION_ID, consultationAssignment.getConsultation().getId());
        values.put(ASSIGNMENT_ID, consultationAssignment.getAssignment().getId());
        values.put(ID, consultationAssignment.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<ConsultationAssignment> query(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<ConsultationAssignment> builder = new ConsultationAssignmentBuilder();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<ConsultationAssignment> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<ConsultationAssignment> builder = new ConsultationAssignmentBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
