package com.example.atmostask.services.news;


import com.example.atmostask.entities.User;
import com.example.atmostask.models.req.NewBoardRequest;
import com.example.atmostask.models.res.AdminNewsBoardResponse;
import com.example.atmostask.models.res.NewsBoardResponse;

import java.util.List;

public interface NewsService {
    void saveNewsBoard(User user, NewBoardRequest request);

    void editNewsBoard(User user, String text, Long id);

    void cancelNewsBoard(Long id);

    void approveNewsBoard(Long id);

    List<NewsBoardResponse> getAllNewsForUser(Long userId);

    List<AdminNewsBoardResponse> getAllNewsForAdmin();
}
