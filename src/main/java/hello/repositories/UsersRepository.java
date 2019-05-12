package hello.repositories;

import hello.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
  Users findByUsername(String username);
}