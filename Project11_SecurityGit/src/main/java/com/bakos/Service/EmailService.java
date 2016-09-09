package com.bakos.Service;

public interface EmailService {
    public boolean sendEmail(String recipientEmail, String subject, String content, String login, String password);
}
