package study.likelionbeweekly.week3.repository;

import study.likelionbeweekly.week3.domain.User;

public interface UserRepository {

    void save(User user);

    User findById(String id);
}
