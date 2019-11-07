package com.diploma.tablet_manager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_drug_quantity", uniqueConstraints =
    @UniqueConstraint(name = "user_drug_quantity_unique_constraint", columnNames = {"expirationDate","user_drug_id"}))
@Data
@NoArgsConstructor
public class UserDrugQuantity {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_drug_id")
    private UserDrug userDrug;
    private Integer quantity;
    private LocalDate expirationDate;

    public UserDrugQuantity(UserDrug userDrug, Integer quantity, LocalDate expirationDate) {
        this.userDrug = userDrug;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

}
