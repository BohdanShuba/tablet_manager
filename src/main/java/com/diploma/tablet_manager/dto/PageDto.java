package com.diploma.tablet_manager.dto;

import lombok.Data;

@Data
public class PageDto {
    private int number;
    private String url;

    public PageDto(int number, String url) {
        this.number = number;
        this.url = url;
    }
}
