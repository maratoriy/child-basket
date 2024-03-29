package ru.childbasket.domain.child.dto;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChildResponseDto {
    private Long childId;

    private String name;

    private Timestamp birthdayDate;
}