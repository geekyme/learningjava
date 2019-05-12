package hello.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hello.models.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Base64;

import static hello.security.SecurityConstants.EXPIRATION_TIME;
import static hello.security.SecurityConstants.HEADER_STRING;
import static hello.security.SecurityConstants.SECRET_KEY;
import static hello.security.SecurityConstants.TOKEN_PREFIX;
import static hello.security.SecurityConstants.LOGIN_URL;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private static final Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthenticationFilter.class);

  private AuthenticationManager authenticationManager;

  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    setFilterProcessesUrl(LOGIN_URL);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {
    logger.info("Attempting authentication");
    try {
      Users creds = new ObjectMapper().readValue(req.getInputStream(), Users.class);

      logger.info("Got user " + creds.toString());
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      logger.error("Error attempting authentication", e);
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {
    logger.info(Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded()));
    String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY).compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}