package com.diploma.tablet_manager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "drug")
@Data
@NoArgsConstructor
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String instruction;
    @ManyToOne
    private Classification classification;

    public Drug(String name, String instruction, Classification classification) {
        this.name = name;
        this.instruction = instruction;
        this.classification = classification;
    }
}
