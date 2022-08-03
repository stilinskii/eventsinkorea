package com.jenn.eventsinkorea.domain.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2, message = "Name must be at least 2 characters long")
    private String name;

    private String slug;

    private int sorting;

    @NotNull
    @Column(name = "page_id")
    private Long pageId;


//없애야하나..?
//    @ManyToOne
//    @JoinColumn(name = "page_id",referencedColumnName="id")
//    private Page page;
}
