package com.itosamto.demo.domain;

import com.itosamto.demo.domain.posts.Posts;
import com.itosamto.demo.domain.posts.PostsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BaseTimeEntityTest {

	@Autowired
	PostsRepository postsRepository;

	@Test
	public void baseTimeEntityTest() {
		Posts posts = postsRepository.save(Posts.builder().title("title").content("content").author("author").build());
		assertThat(posts.getCreateDate()).isAfter(LocalDateTime.of(2019,1,1,0,0));
	}

}