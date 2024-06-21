package study.likelionbeweekly.week3.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import study.likelionbeweekly.week3.domain.User;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private static final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        final String id = user.getId();
        users.put(id, user);
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }
}
