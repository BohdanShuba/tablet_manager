package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.service.EmailSenderService;
import com.diploma.tablet_manager.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    private final EmailSenderService emailSenderService;
    private static final String CRON = "0 /5 * * * ?";

    //@Scheduled(cron = CRON)

    @Scheduled(fixedRate = 1000 * 60)
    public void sendMailToUsers() {
        emailSenderService.sendExpirationEmail();
    }
}
