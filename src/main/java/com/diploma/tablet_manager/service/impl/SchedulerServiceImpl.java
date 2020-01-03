package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.UserDrug;
import com.diploma.tablet_manager.repos.UserDrugRepository;
import com.diploma.tablet_manager.service.EmailSenderService;
import com.diploma.tablet_manager.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final EmailSenderService emailSenderService;
//    private static final String CRON = "0 0 6 * * ?";

    //    @Scheduled(cron = CRON)

    @Scheduled(fixedRate = 1000)
    public void sendMailToUsers() {
        emailSenderService.sendExpirationEmail();
    }
}
