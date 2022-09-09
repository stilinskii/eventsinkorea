package com.jenn.eventsinkorea.web.admin.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserSearchForm {

    private String keyword;
    private String nationality;
    private String option;//All / id / username / email
    private String joinedDate;//all / 1week / 1month / 6month
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date from;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date to;

}
