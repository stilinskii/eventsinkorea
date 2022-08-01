package com.jenn.eventsinkorea.web.admin.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserSearchForm {

    private String keyword;
    private String nationality;
    private String option;//All / id / username / email
    private String joinedDate;//all / 1week / 1month / 6month
    private String from;
    private String to;

}
