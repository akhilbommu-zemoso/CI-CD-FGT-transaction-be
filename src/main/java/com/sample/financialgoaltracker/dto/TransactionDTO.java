package com.sample.financialgoaltracker.dto;

import com.sample.financialgoaltracker.entity.User;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDTO {

    private int id;

    private String category;

    private String type;

    private String date;

    private String frequency;

    private String createdAt;

    private String createdBy;

    private String modifiedAt;

    private String modifiedBy;

    private boolean isDeleted;

    private boolean isActive;

    //private User user;
}
