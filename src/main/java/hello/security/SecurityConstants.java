
package hello.security;

import hello.Todo;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

public class SecurityConstants {
  @Todo(priority = Todo.Priority.HIGH, message = "load from properties file")
  private static String keyString = "Ci3HWzUl8Zc0qXKLOu8Za82lcf5rojPqg2Y/bTgPII8=";

  public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(keyString.getBytes());
  public static final long EXPIRATION_TIME = 864_000_000; // 10 days
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/users/signup";
  public static final String LOGIN_URL = "/users/login";
}