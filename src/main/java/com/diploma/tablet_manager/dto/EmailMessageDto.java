package com.diploma.tablet_manager.dto;

import lombok.Data;

@Data
public class EmailMessageDto {
    private String recipient;
    private String subject;
    private String text;
}
