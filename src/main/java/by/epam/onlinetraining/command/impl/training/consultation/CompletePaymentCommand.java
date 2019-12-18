package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.ConsultationService;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;
import by.epam.onlinetraining.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Optional;

public class CompletePaymentCommand implements Command {
    private static final String CONSULTATION_ID = "consultationId";
    private static final String SHOW_CONSULTATIONS_COMMAND = "controller?command=showConsultations&pageNumber=1&limit=5&message=";
    private static final String OK_MESSAGE = "payed";
    private static final String ERROR_MESSAGE = "notEnoughMoney";
    private static UserService userService = new UserServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int consultationId = Integer.parseInt(request.getParameter(CONSULTATION_ID));
        String message = OK_MESSAGE;
        if (!userService.payForConsultation(consultationId)) {
            message = ERROR_MESSAGE;
        }
        return CommandResult.redirect(SHOW_CONSULTATIONS_COMMAND + message);
    }
}
