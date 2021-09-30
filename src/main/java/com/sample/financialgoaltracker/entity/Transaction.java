package com.sample.financialgoaltracker.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private String date;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private String modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_active")
    private boolean isActive;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;


}
