package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.*;
import com.diploma.tablet_manager.dto.*;
import com.diploma.tablet_manager.mapper.Mapper;
import com.diploma.tablet_manager.repos.ClassificationRepository;
import com.diploma.tablet_manager.repos.DrugRepository;
import com.diploma.tablet_manager.repos.UserDrugQuantityRepository;
import com.diploma.tablet_manager.repos.UserDrugRepository;
import com.diploma.tablet_manager.service.DrugService;
import com.diploma.tablet_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final ClassificationRepository classificationRepository;
    private final UserDrugQuantityRepository userDrugQuantityRepository;
    private final UserDrugRepository userDrugRepository;
    private final UserService userService;
    private final Mapper<ClassificationDto, Classification> classificationMapper;
    private final Mapper<DrugDto, Drug> drugMapper;
    private final Mapper<DrugQuantityDto, UserDrugQuantity> userDrugQuantityMapper;
    private final Mapper<UserDrugWithQuantityDto, UserDrug> userDrugMapper;

    @Override
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    @Override
    public Page<Drug> getPageDrugs(int page, int limit) {
        return drugRepository.findAll(PageRequest.of(page, limit, Sort.Direction.ASC, "name"));
    }

    @Override
    public Page<Drug> getPageDrugsClassification(int id, int page, int limit) {
        return drugRepository.findAllByClassificationId(id, PageRequest.of(page, limit, Sort.Direction.ASC, "name"));
    }

    @Override
    public DrugDto addNewDrug(DrugDto drugDto) {
        Classification classification = classificationRepository.findAllById(drugDto.getClassificationId());
        Drug drug = new Drug(drugDto.getName(), drugDto.getInstruction(), classification);
        return drugMapper.toDto(drugRepository.save(drug));
    }

    @Override
    public Drug findByIdDrug(Integer idDrug) {
        return drugRepository.findById(idDrug);
    }

    @Override
    public List<ClassificationDto> getAllClassifications() {
        List<ClassificationDto> classificationsDto = new LinkedList<>();
        List<Classification> classifications = classificationRepository.findAll();

        for (Classification classification : classifications) {
            classificationsDto.add(classificationMapper.toDto(classification));
        }
        return classificationsDto;
    }

    @Override
    public List<DrugDto> findByNameDrugs(String nameDrug) {
        if (nonNull(nameDrug) && !nameDrug.isEmpty()) {

            List<Drug> drugs = drugRepository.findByName(nameDrug);
            List<DrugDto> drugDtoGroup = new LinkedList<>();
            for (Drug drug : drugs) {
                drugDtoGroup.add(drugMapper.toDto(drug));
            }
            return drugDtoGroup;
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public DrugQuantityDto addDrugToUser(Integer id, Integer quantity, LocalDate expirationDate) {
        User currentUser = userService.getCurrentUser();
        Drug currentDrug = findByIdDrug(id);
        UserDrugQuantity userDrugQuantity;
        UserDrug currentUserDrug = getUserDrug(currentUser.getId(), currentDrug.getId());
        if (isNull(currentUserDrug)) {
            UserDrug userDrug = new UserDrug(currentDrug, currentUser);
            userDrugQuantity = new UserDrugQuantity(userDrug, quantity, expirationDate);
        } else {
            Set<UserDrugQuantity> userDrugQuantityGroup = currentUserDrug.getQuantityList();
            userDrugQuantity = getUserDrugQuantityByDate(userDrugQuantityGroup, expirationDate);
            if (isNull(userDrugQuantity)) {
                userDrugQuantity = new UserDrugQuantity(currentUserDrug, quantity, expirationDate);
            } else {
                Integer modifiedQuantity = userDrugQuantity.getQuantity() + quantity;
                userDrugQuantity.setQuantity(modifiedQuantity);
            }
        }
        return userDrugQuantityMapper.toDto(userDrugQuantityRepository.save(userDrugQuantity));
    }

    @Override
    public List<UserDrugWithQuantityDto> getAllDrugsForUser() {
        User currentUser = userService.getCurrentUser();
        List<UserDrug> userDrugs = userDrugRepository.findByUserId(currentUser.getId());
        List<UserDrugWithQuantityDto> userDrugWithQuantityDtoGroup = new LinkedList<>();
        for (UserDrug userDrug : userDrugs) {
            userDrugWithQuantityDtoGroup.add(userDrugMapper.toDto(userDrug));
        }
        return userDrugWithQuantityDtoGroup;
    }

    @Override
    public UserDrugQuantity getUserDrugQuantityById(Integer id) {
        UserDrugQuantity userDrugQuantity = userDrugQuantityRepository.findById(id);
        return userDrugQuantity;
    }

    @Override
    public DrugQuantityDto changeQuantity(Integer userDrugQuantityId, Integer newDrugCount) {
        UserDrugQuantity userDrugQuantity = getUserDrugQuantityById(userDrugQuantityId);
        userDrugQuantity.setQuantity(userDrugQuantity.getQuantity() - newDrugCount);
        return userDrugQuantityMapper.toDto(userDrugQuantityRepository.save(userDrugQuantity));
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
