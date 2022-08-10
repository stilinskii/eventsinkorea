package com.jenn.eventsinkorea.web.buddy.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BeABuddyForm {

    @NotNull
    private String nativeLang;
    @NotNull
    private String secondLang;
    @NotNull
    private String location;
    private String intro;
    private MultipartFile image;
}
