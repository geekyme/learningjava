package hello.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

import hello.models.Users;

@Service
public class GithubLookupService {
  private static final Logger logger = LoggerFactory.getLogger(GithubLookupService.class);

  @Async("threadPoolTaskExecutor")
  public CompletableFuture<Users> findUser(String user) throws InterruptedException {
    logger.info("Looking up " + user);
    // RestTemplate restTemplate = new RestTemplate();
    // String url = String.format("https://api.github.com/users/%s", user);
    // Users results = restTemplate.getForObject(url, Users.class);

    Users results = new Users();

    results.setName(user);
    // Artificial delay of 1s for demonstration purposes
    Thread.sleep(1000L);
    return CompletableFuture.completedFuture(results);
  }
}
