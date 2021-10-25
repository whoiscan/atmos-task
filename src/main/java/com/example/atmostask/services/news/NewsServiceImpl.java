package com.example.atmostask.services.news;

import com.example.atmostask.entities.News;
import com.example.atmostask.entities.User;
import com.example.atmostask.models.enums.NewsStatus;
import com.example.atmostask.models.req.NewBoardRequest;
import com.example.atmostask.models.res.AdminNewsBoardResponse;
import com.example.atmostask.models.res.NewsBoardResponse;
import com.example.atmostask.repositories.NewsRepository;
import com.example.atmostask.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void saveNewsBoard(User user, NewBoardRequest request) {
        if (user == null)
            return;
        News news = new News();
        news.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        news.setDescription(request.getDescription());
        news.setStatus(NewsStatus.ON_REVIEW);
        news.setUser(user);
        news.setActive(true);
        newsRepository.save(news);
    }

    @Override
    public void editNewsBoard(User user, String text, Long id) {
        if (user == null)
            return;
        if (id == null)
            return;

        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isEmpty())
            return;

        News news = optionalNews.get();
        news.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        news.setDescription(text);
        if (news.isApproved())
            news.setApproved(false);
        news.setStatus(NewsStatus.ON_REVIEW);
        news.setApprovedDate(null);
        newsRepository.save(news);
    }

    @Override
    public void cancelNewsBoard(Long id) {
        if (id == null)
            return;
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isEmpty())
            return;
        News news = optionalNews.get();
        news.setStatus(NewsStatus.REJECTED);
        news.setApproved(false);
        news.setActive(false);
        news.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        newsRepository.save(news);
    }

    @Override
    public void approveNewsBoard(Long id) {
        if (id == null)
            return;
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isEmpty())
            return;
        News news = optionalNews.get();
        news.setStatus(NewsStatus.APPROVED);
        news.setApproved(true);
        news.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        news.setApprovedDate(Timestamp.valueOf(LocalDateTime.now()));
        newsRepository.save(news);
    }

    @Override
    public List<NewsBoardResponse> getAllNewsForUser(Long userId) {
        if (userId == null)
            return Collections.emptyList();
        List<NewsBoardResponse> responseList = new ArrayList<>();
        List<News> newsList = newsRepository.findAllByUserIdOrStatusEqualsOrderByModifiedDateDesc(userId, NewsStatus.APPROVED);
        for (News item : newsList) {
            NewsBoardResponse response = new NewsBoardResponse();
            response.setId(item.getId());
            response.setDescription(item.getDescription());
            response.setApproved(item.isApproved() ? "Да" : "Нет");
            response.setStatus(item.getStatus().toString());
            response.setCreatedDate(DateUtils.dateLongToString(item.getCreatedDate().getTime()));
            response.setEditable(item.getUser().getId().equals(userId));
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public List<AdminNewsBoardResponse> getAllNewsForAdmin() {
        List<AdminNewsBoardResponse> responseList = new ArrayList<>();
        List<News> newsList = newsRepository.findAllByStatusNotLike(NewsStatus.REJECTED);
        for (News item : newsList) {
            AdminNewsBoardResponse response = new AdminNewsBoardResponse();
            response.setId(item.getId());
            response.setDescription(item.getDescription());
            response.setApproved(item.isApproved() ? "Да" : "Нет");
            response.setStatus(item.getStatus().toString());
            response.setCreatedDate(DateUtils.dateLongToString(item.getCreatedDate().getTime()));
            long approvedDate = item.getApprovedDate() == null ? 0 : item.getApprovedDate().getTime();
            response.setApprovedDate(DateUtils.dateLongToString(approvedDate));
            response.setUserName(item.getUser().getUserName());
            response.setEmail(item.getUser().getEmail());
            responseList.add(response);
        }
        return responseList;
    }
}
