package com.jenn.eventsinkorea.domain.buddy.repository;


import com.jenn.eventsinkorea.domain.buddy.model.Buddy;
import com.jenn.eventsinkorea.web.buddy.form.BuddyFilteringSortingOption;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.jenn.eventsinkorea.domain.buddy.model.QBuddy.buddy;


@Repository(value="buddyDAOImpl")
@RequiredArgsConstructor
public class BuddyDAOImpl implements BuddyDAO {

    private final JPAQueryFactory query;
    private final RepositorySliceHelper sliceHelper;

    @Override
    public Slice<Buddy> filteringBuddy(BuddyFilteringSortingOption option, Pageable pageable) {
        List<Buddy> result = query.selectFrom(buddy)
                .where(learningLangEqual(option.getLearningLang())
                        , nativeLangEqual(option.getNativeLang())
                        , locationEqual(option.getLocation()))
                .orderBy(sorting(option.getSorting()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)//pageable.getPageSize()+1
                .fetch();

        return sliceHelper.toSlice(result,pageable);
    }

    @Override
    public Page<Buddy> filteringAndSortingBuddy(BuddyFilteringSortingOption option) {
        return null;
    }

    private BooleanExpression learningLangEqual(String learningLang) {
        return Objects.isNull(learningLang) || learningLang.isBlank()? null:buddy.learningLang.eq(learningLang);
    }

    private BooleanExpression nativeLangEqual(String nativeLang) {
        return Objects.isNull(nativeLang) || nativeLang.isBlank()? null:buddy.nativeLang.eq(nativeLang);
    }

    private BooleanExpression locationEqual(String location) {
        return Objects.isNull(location) || location.isBlank()? null:buddy.location.eq(location);
    }

    private OrderSpecifier<?> sorting(String sorting){
        if( Objects.isNull(sorting) || sorting.isBlank()){
            return buddy.updatedAt.desc();
            //밑에꺼 임시.
        }else if(sorting.equals("Review")){
            return buddy.buddyReviews.size().desc();
        }else if(sorting.equals("Rating")){
            return buddy.rating.desc();
        }else if(sorting.equals("Latest")){
            return buddy.id.desc();
        }else{
            return buddy.updatedAt.desc();
        }
    }
}
