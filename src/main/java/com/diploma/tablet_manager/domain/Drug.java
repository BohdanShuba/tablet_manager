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
    @Column(name = "id")
    private Integer id;
    private String name;
    private String instruction;
    private Integer id_purpose;

    public Drug(String name, String instruction, int id_purpose) {
        this.name = name;
        this.instruction = instruction;
        this.id_purpose = id_purpose;
    }
}
