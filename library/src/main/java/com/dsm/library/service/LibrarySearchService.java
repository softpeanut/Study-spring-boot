package com.dsm.library.service;

import com.dsm.library.domain.Library;
import com.dsm.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibrarySearchService {
    private final LibraryRepository libraryRepository;

    public List<Library> test() {
        return libraryRepository.findAll();
    }
}

// 이것이 자바다 -> 자바 기초 공부 1, 2권
// 이펙티브 자바 Effective Java 3/E -> 어려울 수 있음
// Clean Code -> 무조건 어려움

// MySQL 공부, 데이터 모델링, 인덱스, 조인 등 -> 이걸 알아야 JPA를 공부할 수 있다.
// 서버 배포 꼭 해보기

// 스프링 부트 어쩌구 AWS 어쩌구