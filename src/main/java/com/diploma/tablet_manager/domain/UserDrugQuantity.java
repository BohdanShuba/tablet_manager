package com.diploma.tablet_manager.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_drug_quantity")
@Data
public class UserDrugQuantity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="drug_user_id")
    private UserDrug userDrug;
    private Integer quantity;
    private LocalDate expirationDate;
}
