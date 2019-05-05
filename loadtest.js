import http from "k6/http";

export let options = {
  vus: 10,
  duration: "30s"
};

export default function() {
  http.get("http://localhost:8080/users");
}
