package com.jenn.eventsinkorea.web.buddy.form;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuddyFilteringSortingOption {
    String learningLang;
    String nativeLang;
    String location;
    String sorting;
}
