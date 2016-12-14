package com.hatcheryhub.service;

import java.io.File;
import java.util.List;

public interface MailService {

    public void sendMail(String to, String sub, String body);
    public void sendMailWithAttachment(String to, String sub, String body, List<File> files);
}
