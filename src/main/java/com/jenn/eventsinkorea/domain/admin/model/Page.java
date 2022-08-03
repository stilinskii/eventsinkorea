package com.jenn.eventsinkorea.domain.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name="pages")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, message = "Title must be at least 2 characters long.")
    private String title;
    private String slug;
    @Size(min=5, message = "Content must be at least 5 characters long.")
    private String content;
    private int sorting;

//    @OneToMany(mappedBy = "page", fetch = FetchType.LAZY)
//    private List<Category> categories = new ArrayList<>();

}
