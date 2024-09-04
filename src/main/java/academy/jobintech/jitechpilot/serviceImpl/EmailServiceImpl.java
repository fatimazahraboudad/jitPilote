package academy.jobintech.jitechpilot.serviceImpl;

import academy.jobintech.jitechpilot.service.EmailService;
import academy.jobintech.jitechpilot.service.WorkspaceService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final WorkspaceService workspaceService;

    @Override
    public void sendInvitationEmail(String userEmail, Long workspaceId) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(userEmail);
            helper.setSubject("You have been added to a workspace");
            String workspaceName = workspaceService.getWorkspaceNameById(workspaceId);
            helper.setText("Hello,\n\nYou have been added as a member to the workspace: " + workspaceName + ".\n\nBest Regards,\nYour Team");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Envoi de l'e-mail
        emailSender.send(message);
    }


}
