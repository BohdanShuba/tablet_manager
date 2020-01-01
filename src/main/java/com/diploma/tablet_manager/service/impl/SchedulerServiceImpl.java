package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.UserDrug;
import com.diploma.tablet_manager.repos.UserDrugRepository;
import com.diploma.tablet_manager.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    private final UserDrugRepository userDrugRepository;
//    private static final String CRON = "20 * * * *";
//    private final UserRepository userRepository;

    //    @Scheduled(cron = CRON)
//    public void sendMailToUsers() {
//        LocalDate date = LocalDate.now();
//        userRepository.findByLogin("user1");
//    }
    @Scheduled(fixedRate = 1000)
    public void sendMailToUsers() {
        LocalDate date = LocalDate.now();
        List<UserDrug> uds = userDrugRepository.findAllByExpirationDate(date);
        for (UserDrug usdr : uds) {
            System.out.println(usdr.getUser().getEmail() + " " + usdr.getDrug().getName());
        }
    }
}
