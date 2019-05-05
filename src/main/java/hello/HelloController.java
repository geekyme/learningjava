package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hello.models.Pets;
import hello.models.Users;
import hello.repositories.PetsRepository;
import hello.service.GithubLookupService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import java.util.ArrayList;
import java.util.List;

@RestController
@Todo(priority = Todo.Priority.LOW, author = "Caleb")
public class HelloController {
  private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  private PetsRepository repository;

  @Autowired
  private GithubLookupService gitHubLookupService;

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping("/pets")
  public List<Pets> getAllPets() {
    return repository.findAll();
  }

  @RequestMapping("/users")
  public List<Users> getAllUsers() {
    long start = System.currentTimeMillis();

    List<Users> users = new ArrayList<>();

    try {

      CompletableFuture<Users> u1 = gitHubLookupService.findUser("geekyme");
      CompletableFuture<Users> u2 = gitHubLookupService.findUser("spkjess");
      CompletableFuture<Users> u3 = gitHubLookupService.findUser("zetoke");
      CompletableFuture.allOf(u1, u2, u3).join();

      logger.info("Elapsed time: " + (System.currentTimeMillis() - start));

      users.add(u1.get());
      users.add(u2.get());
      users.add(u3.get());

      return users;
    } catch (ExecutionException e) {
      System.out.println(e);
    } catch (InterruptedException e) {
      System.out.println(e);
    }

    return users;
  }

  @Todo(priority = Todo.Priority.HIGH)
  public void notYetStartedMethod() {
    // No Code Written yet
  }
}