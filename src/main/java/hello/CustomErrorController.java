package hello;

import hello.models.ErrorJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {
  private static final String PATH = "/error";

  @Value("${debug}")
  private boolean debug;

  @Autowired
  private ErrorAttributes errorAttributes;

  @RequestMapping(value = PATH)
  ErrorJson error(HttpServletRequest request, HttpServletResponse response) {
    return new ErrorJson(response.getStatus(), getErrorAttributes(request, debug));
  }

  @Override
  public String getErrorPath() {
    return PATH;
  }

  private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
    WebRequest webRequest = new ServletWebRequest(request);
    return errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
  }
}