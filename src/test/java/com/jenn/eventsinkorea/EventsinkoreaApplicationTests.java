package com.jenn.eventsinkorea;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.Hello;
import entity.QHello;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
class EventsinkoreaApplicationTests {

	@Autowired
	JPAQueryFactory query;

	@Test
	void contextLoads() {


		Hello hello = new Hello();
		QHello qHello = new QHello("h");

		Hello result = query
				.selectFrom(qHello)
				.fetchOne();

		assertThat(result).isEqualTo(hello);
		assertThat(result.getId()).isEqualTo(hello.getId());
	}

}
