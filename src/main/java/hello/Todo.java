package hello;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Todo {
  public enum Priority {
    LOW, MEDIUM, HIGH
  }

  public enum Status {
    STARTED, NOT_STARTED
  }

  String author() default "Shawn";

  Priority priority() default Priority.LOW;

  Status status() default Status.NOT_STARTED;

  String message() default "";
}
