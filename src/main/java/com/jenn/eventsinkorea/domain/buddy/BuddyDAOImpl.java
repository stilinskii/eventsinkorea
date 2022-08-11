package com.jenn.eventsinkorea.domain.buddy;


import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringOption;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.jenn.eventsinkorea.domain.buddy.model.QBuddy.buddy;


@Repository(value="buddyDAOImpl")
@RequiredArgsConstructor
public class BuddyDAOImpl implements BuddyDAO{

    private final JPAQueryFactory query;

    @Override
    public List<Buddy> filteringBuddy(BuddyFilteringOption option) {
        return query.selectFrom(buddy)
                .where(learningLangEqual(option.getLearningLang())
                        ,nativeLangEqual(option.getNativeLang())
                        ,locationEqual(option.getLocation()))
                .fetch();
    }

    private BooleanExpression learningLangEqual(String learningLang) {
        return Objects.isNull(learningLang) || learningLang.equals("language")? null:buddy.learningLang.eq(learningLang);
    }

    private BooleanExpression nativeLangEqual(String nativeLang) {
        return Objects.isNull(nativeLang) || nativeLang.equals("language")? null:buddy.nativeLang.eq(nativeLang);
    }

    private BooleanExpression locationEqual(String location) {
        return Objects.isNull(location) || location.equals("location")? null:buddy.location.eq(location);
    }
}
