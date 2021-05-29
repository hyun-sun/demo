package com.itosamto.demo.service.posts;

import com.itosamto.demo.domain.posts.Posts;
import com.itosamto.demo.domain.posts.PostsRepository;
import com.itosamto.demo.web.dto.PostsResponseDto;
import com.itosamto.demo.web.dto.PostsSaveRequestDto;
import com.itosamto.demo.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostsService {
	private final PostsRepository postsRepository;

	public PostsService(PostsRepository postsRepository) {
		this.postsRepository = postsRepository;
	}

	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
		);
		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
		);
		return new PostsResponseDto(entity);
	}

}
