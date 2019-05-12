package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hello.models.Users;
import hello.repositories.UsersRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
public class UsersController {
  private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

  private UsersRepository usersRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UsersController(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.usersRepository = usersRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @PostMapping("/signup")
  public void signUp(@RequestBody Users user) {
    logger.trace("Signing up user {}", user.toString());
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    usersRepository.save(user);
    logger.trace("User saved {}", user.toString());
  }
}