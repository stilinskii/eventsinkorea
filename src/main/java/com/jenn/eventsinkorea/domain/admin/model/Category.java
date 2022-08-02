package com.jenn.eventsinkorea.domain.admin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "page_id")
    private Integer pageId;

    @Size(min=2, message = "Name must be at least 2 characters long")
    private String name;

    private String slug;

    private int sorting;
}
