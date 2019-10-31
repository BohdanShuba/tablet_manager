package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.*;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.dto.PageDto;
import com.diploma.tablet_manager.repos.ClassificationRepository;
import com.diploma.tablet_manager.repos.DrugRepository;
import com.diploma.tablet_manager.repos.UserDrugQuantityRepository;
import com.diploma.tablet_manager.repos.UserDrugRepository;
import com.diploma.tablet_manager.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final ClassificationRepository classificationRepository;
    private final UserDrugQuantityRepository userDrugQuantityRepository;
    private final UserDrugRepository userDrugRepository;
    private final UserServiceImpl userServiceImpl;

    @Override
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    @Override
    public Page<Drug> getPageDrugs(int page, int limit) {
        return drugRepository.findAll(PageRequest.of(page, limit, Sort.Direction.ASC, "name"));
    }

    @Override
    public Drug addNewDrug(DrugDto drugDto) {
        Classification classification = classificationRepository.findAllById(drugDto.getClassificationId());
        Drug drug = new Drug(drugDto.getName(), drugDto.getInstruction(), classification);
        return drugRepository.save(drug);
    }

    @Override
    public Drug findByIdDrug(Integer idDrug) {
        return drugRepository.findById(idDrug);
    }

    @Override
    public List<Drug> findByNameDrugs(String nameDrug) {
        if (nameDrug != null && !nameDrug.isEmpty()) {
            return drugRepository.findByName(nameDrug);
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addDrugToUser(Integer id, Integer quantity, LocalDate expirationDate) {
        User currentUser = userServiceImpl.getCurrentUser();
        Drug currentDrug = findByIdDrug(id);
        UserDrugQuantity userDrugQuantity;
        UserDrug currentUserDrug = getUserDrug(currentUser.getId(), currentDrug.getId());
        if (currentUserDrug == null) {
            UserDrug userDrug = new UserDrug(currentDrug, currentUser);
            userDrugQuantity = new UserDrugQuantity(userDrug, quantity, expirationDate);
        } else {
            Set<UserDrugQuantity> userDrugQuantityGroup = currentUserDrug.getQuantityList();
            userDrugQuantity = getUserDrugQuantityByDate(userDrugQuantityGroup, expirationDate);
            if (userDrugQuantity == null) {
                userDrugQuantity = new UserDrugQuantity(currentUserDrug, quantity, expirationDate);
            } else {
                Integer modifiedQuantity = userDrugQuantity.getQuantity() + quantity;
                userDrugQuantity.setQuantity(modifiedQuantity);
            }
        }
        userDrugQuantityRepository.save(userDrugQuantity);
    }

    @Override
    public List<UserDrug> getAllDrugsForUser() {
        User currentUser = userServiceImpl.getCurrentUser();
        List<UserDrug> userDrugs = userDrugRepository.findByUserId(currentUser.getId());
        return userDrugs;
    }

    public List<PageDto> getPagesNumbers(Page<Drug> page) {
        List<PageDto> listPageDto = new ArrayList<>();
        int countPage = 0;
        for (int i = 0; i < page.getTotalPages(); i++) {
            PageDto pageDto = new PageDto(++countPage, "?page=" + i);
            listPageDto.add(pageDto);
        }
        return listPageDto;
    }

    private UserDrugQuantity getUserDrugQuantityByDate(Set<UserDrugQuantity> userDrugQuantityGroup, LocalDate expirationDate) {
        for (UserDrugQuantity i : userDrugQuantityGroup) {
            if (expirationDate.equals(i.getExpirationDate())) {
                return i;
            }
        }
        return null;
    }

    private UserDrug getUserDrug(Integer userId, Integer drugId) {
        UserDrug userDrug = userDrugRepository.findByUserIdAndDrugId(userId, drugId);
        return userDrug;
    }

}
