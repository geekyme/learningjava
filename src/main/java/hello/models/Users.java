package hello.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {
  @Id
  public ObjectId _id;

  public String username;

  public String password;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public Users(@JsonProperty("username") String username, @JsonProperty("password") String password) {
    this.username = username;
    this.password = password;
  }

  public String get_id() {
    return _id.toHexString();
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User [username=" + username + ", password=" + password + "]";
  }
}