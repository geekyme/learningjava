package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Todo(priority = Todo.Priority.LOW, author = "Caleb")
public class HelloController {
  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @Todo(priority = Todo.Priority.HIGH)
  public void notYetStartedMethod() {
    // No Code Written yet
  }
}