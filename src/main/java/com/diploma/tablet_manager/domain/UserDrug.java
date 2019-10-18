package com.diploma.tablet_manager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_drug")
@Data
@EqualsAndHashCode(exclude = "quantityList")
@ToString(exclude = "quantityList")
@NoArgsConstructor
public class UserDrug {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Drug drug;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "userDrug", fetch = FetchType.EAGER)
    private Set<UserDrugQuantity> quantityList;

    public UserDrug(Drug drug, User user) {
        this.drug = drug;
        this.user = user;
    }

}
