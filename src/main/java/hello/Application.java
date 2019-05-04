package hello;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.lang.reflect.Method;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    getTodoReport();
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      System.out.println("Let's inspect the beans provided by Spring Boot:");

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        System.out.println(beanName);
      }

    };
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