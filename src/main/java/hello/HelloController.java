package hello;

import hello.models.Pets;
import hello.repositories.PetsRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@Todo(priority = Todo.Priority.LOW, author = "Caleb")
public class HelloController {
  @Autowired
  private PetsRepository repository;

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping("/pets")
  public List<Pets> getAllPets() {
    return repository.findAll();
  }

  @Todo(priority = Todo.Priority.HIGH)
  public void notYetStartedMethod() {
    // No Code Written yet
  }
}