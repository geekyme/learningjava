package hello.service;

import hello.models.Users;
import hello.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class MongoUserDetailsService implements UserDetailsService {
  @Autowired
  private UsersRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = repository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return new User(user.getUsername(), user.getPassword(), emptyList());
  }
}