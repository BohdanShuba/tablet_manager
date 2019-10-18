package com.diploma.tablet_manager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_drug_quantity")
@Data
@NoArgsConstructor
public class UserDrugQuantity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "drug_user_id")
    private UserDrug userDrug;
    private Integer quantity;
    private LocalDate expirationDate;

    public UserDrugQuantity(UserDrug userDrug, Integer quantity, LocalDate expirationDate) {
        this.userDrug = userDrug;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

}
