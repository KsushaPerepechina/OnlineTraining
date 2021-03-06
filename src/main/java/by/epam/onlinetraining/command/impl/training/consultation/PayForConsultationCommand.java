package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.ConsultationService;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PayForConsultationCommand implements Command {
    private static final String CONSULTATION_ID = "consultationId";
    private static final String CONSULTATION = "consultation";
    private static final String PAYMENT_PAGE = "/WEB-INF/page/user/payment.jsp";
    private static ConsultationService consultationService = new ConsultationServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int consultationId = Integer.parseInt(request.getParameter(CONSULTATION_ID));
        Optional<Consultation> consultation = consultationService.findById(consultationId);
        consultation.ifPresent(c -> request.setAttribute(CONSULTATION, c));
        return CommandResult.forward(PAYMENT_PAGE);
    }
}
