package com.example.atmostask.repositories;

import com.example.atmostask.entities.News;
import com.example.atmostask.models.enums.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByUserIdOrStatusEqualsOrderByModifiedDateDesc(Long userId, NewsStatus status);

    List<News> findAllByStatusNotLike(NewsStatus status);
}
