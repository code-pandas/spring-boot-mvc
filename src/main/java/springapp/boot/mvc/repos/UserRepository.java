package springapp.boot.mvc.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import springapp.boot.mvc.model.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    @Override
    List<User> findAll();
}
