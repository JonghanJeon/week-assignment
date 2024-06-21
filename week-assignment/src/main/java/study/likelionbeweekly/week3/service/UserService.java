package study.likelionbeweekly.week3.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import study.likelionbeweekly.week3.domain.User;

public interface UserService {

    void saveUser(String id, String password);

    User login(String id, String password, Model model);
}
