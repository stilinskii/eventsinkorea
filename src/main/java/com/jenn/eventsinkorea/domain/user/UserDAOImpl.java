package com.jenn.eventsinkorea.domain.user;

import com.jenn.eventsinkorea.domain.user.model.User;
import com.jenn.eventsinkorea.web.admin.form.UserSearchForm;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.jenn.eventsinkorea.domain.user.QUser.user;

@Repository(value="userDAOImpl")
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final JPAQueryFactory query;


   @Override
    public List<User> findUsersBySearch(UserSearchForm searchForm) {
        return query.selectFrom(user)
                .where(joinedDateBetween(searchForm)
                        ,nationality(searchForm.getNationality())
                        ,optionContains(searchForm.getOption(),searchForm.getKeyword()))
                .fetch();
    }

    @Override
    public Page<User> findUsersBySearchPage(UserSearchForm searchForm, Pageable pageable) {
           List<User> content = query.selectFrom(user)
                .where(joinedDateBetween(searchForm)
                        ,nationality(searchForm.getNationality())
                        ,optionContains(searchForm.getOption(),searchForm.getKeyword())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

           Long count = query.select(user.count())
                .from(user)
                   .where(joinedDateBetween(searchForm)
                           ,nationality(searchForm.getNationality())
                           ,optionContains(searchForm.getOption(),searchForm.getKeyword())
                   )
                   .fetchOne();

           return new PageImpl<>(content, pageable, count);
    }

    public BooleanExpression joinedDateBetween(UserSearchForm searchForm){
        //1 week , 1 month , 6 month , Enter Date.
        Calendar date = Calendar.getInstance();
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        Integer weekAgo = Integer.parseInt(format.format(week.getTime()));

        String joinedDateOption = searchForm.getJoinedDate();
        if(Objects.isNull(joinedDateOption)|| joinedDateOption.isBlank() || joinedDateOption.equals("Joined Date")){
            return null;
        }else{
            if(joinedDateOption.equals("1 week")){
                date.add(Calendar.DATE , -7);
                return user.joinedDate.goe(date.getTime());
            }else if(joinedDateOption.equals("1 month")){
                date.add(Calendar.MONTH , -1);
                return user.joinedDate.goe(date.getTime());
            }else if(joinedDateOption.equals("6 month")){
                date.add(Calendar.MONTH , -6);
                return user.joinedDate.goe(date.getTime());
            }else{
                return user.joinedDate.goe(searchForm.getFrom()).and(user.joinedDate.loe(searchForm.getTo()));
                //시간까지 같이 측정돼서 원하는대로 안나옴. TODO
            }
        }
    }



    public BooleanExpression nationality(String nationality){
        return Objects.isNull(nationality) ||nationality.isBlank() || nationality.equals("Nationality")? null : user.nationality.eq(nationality);
    }

    public BooleanExpression optionContains(String option, String keyword){
        if((Objects.isNull(keyword) || keyword.isBlank()) || (Objects.isNull(option) || option.isBlank())){
            return null;
        }else{
            if(option.equals("All")){
                return usernameContains(keyword).or(userIdContains(keyword)).or(emailContains(keyword));
            } else if (option.equals("Id")) {
                return userIdContains(keyword);
            }else if(option.equals("Username")){
                return usernameContains(keyword);
            } else {
                //email
                return emailContains(keyword);
            }
        }
    }

    public BooleanExpression usernameContains(String keyword){
        return Objects.isNull(keyword) || keyword.isBlank()? null : user.username.contains(keyword);
    }

    public BooleanExpression userIdContains(String keyword){
        return Objects.isNull(keyword) || keyword.isBlank() ? null : user.username.contains(keyword);
    }

    public BooleanExpression emailContains(String keyword){
        return Objects.isNull(keyword) || keyword.isBlank() ? null : user.email.contains(keyword);
    }
}
