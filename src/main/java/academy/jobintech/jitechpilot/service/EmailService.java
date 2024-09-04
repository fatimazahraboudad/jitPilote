package academy.jobintech.jitechpilot.service;

public interface EmailService {
    void sendInvitationEmail(String userEmail, Long workspaceId);
}
