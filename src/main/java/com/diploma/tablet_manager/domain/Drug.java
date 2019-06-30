package com.diploma.tablet_manager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drug")
@Data
@NoArgsConstructor
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String name;
    private String instruction;
    @ManyToOne
    private Classification drugClassification;
    @OneToMany(mappedBy = "drug")
    private List<UserDrug> userDrugList;

    public Drug(String name, String instruction, Classification classification) {
        this.name = name;
        this.instruction = instruction;
        this.drugClassification = classification;
    }
}
