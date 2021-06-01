package com.itosamto.demo.service.posts;

import com.itosamto.demo.domain.posts.Posts;
import com.itosamto.demo.domain.posts.PostsRepository;
import com.itosamto.demo.web.dto.PostsListResponseDto;
import com.itosamto.demo.web.dto.PostsResponseDto;
import com.itosamto.demo.web.dto.PostsSaveRequestDto;
import com.itosamto.demo.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

	@Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선 -> 등록,수정,삭제 기능이 전혀 없는 서비스 ㅁ메소드에서 사용하는 것을 추천
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
		);
		postsRepository.delete(posts);
	}



}
