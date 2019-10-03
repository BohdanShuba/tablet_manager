package com.diploma.tablet_manager.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "drug_classification")
@Data

public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Character atcCode;
    private String codeDescription;


}
