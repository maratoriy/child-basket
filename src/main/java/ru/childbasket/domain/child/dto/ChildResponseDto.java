package ru.childbasket.domain.child.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChildResponseDto {
    private Long childId;

    private String name;

    private Timestamp birthdayDate;
}