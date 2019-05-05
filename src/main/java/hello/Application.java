package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.core.task.TaskExecutor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.lang.reflect.Method;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {
  @Autowired
  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  @Bean("threadPoolTaskExecutor")
  public TaskExecutor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(20);
    executor.setMaxPoolSize(1000);
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setThreadNamePrefix("Async-");
    return executor;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    getTodoReport();
  }

  public void run(String... args) throws Exception {
    logger.info("Started app");
  }

  private static void getTodoReport() {

    Class<HelloController> helloControllerClass = HelloController.class;

    Todo todoAnnotationClass = (Todo) helloControllerClass.getAnnotation(Todo.class);

    if (todoAnnotationClass != null) {
      System.out.println(" Class Name : " + helloControllerClass.getName());
      System.out.println(" Author : " + todoAnnotationClass.author());
      System.out.println(" Priority : " + todoAnnotationClass.priority());
      System.out.println(" Status : " + todoAnnotationClass.status());
      System.out.println(" --------------------------- ");
    }

    for (Method method : helloControllerClass.getMethods()) {
      Todo todoAnnotation = (Todo) method.getAnnotation(Todo.class);
      if (todoAnnotation != null) {
        System.out.println(" Method Name : " + method.getName());
        System.out.println(" Author : " + todoAnnotation.author());
        System.out.println(" Priority : " + todoAnnotation.priority());
        System.out.println(" Status : " + todoAnnotation.status());
        System.out.println(" --------------------------- ");
      }
    }
  }
}