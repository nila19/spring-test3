package com.springtest.spring.api;

import javax.annotation.PostConstruct;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springtest.spring.api.model.Response;
import com.springtest.spring.config.props.HelloProps;

@RestController
@RequestMapping(value = "/hello")
@RequiredArgsConstructor
public class HelloController {
  private static final String URL = "http://k8s-workshop-name-service";

  private final HelloProps helloProps;
  private final Environment environment;
  private final RestTemplate restTemplate;

  @Value("${app.url.nameService}")
  private String nameServiceUrl;

  @Value("${app.lives}")
  private int lives;

  @Value("${app.enemies.cheat.level}")
  private String cheatLevel;

  @PostConstruct
  public void init() {
    String region = this.environment.getProperty("REGION_CD");
    String dialect = this.environment.getProperty("dialect");
    System.out.println(">>>> Env variables: region = " + region + " => dialect = " + dialect);
    System.out.println(">>>> Prop: cheatLevel = " + this.cheatLevel + " => lives = " + this.lives);
  }

  @Hidden
  @GetMapping(value = "/hello")
  public Response hello() {
    String region = this.environment.getProperty("REGION_CD");
    String dialect = this.environment.getProperty("dialect");
    System.out.println("Env variables: region = " + region + " => dialect = " + dialect);
    String msg = this.helloProps.getMessage() + " : " + this.helloProps.getFromId()
                 + " => " + region + " + " + dialect + " : " + this.lives + " ==> "
                 + this.cheatLevel + " Something new..";
    return new Response(0, msg);
  }

  @GetMapping(value = "/hello2")
  public Response hello2() {
    String fromApi = this.getResultFromApi();
    System.out.println("fromApi = " + fromApi);
    String msg = this.helloProps.getMessage() + " : " + this.nameServiceUrl
                 + " => " + fromApi;
    return new Response(0, msg);
  }

  private String getResultFromApi() {
    ResponseEntity<String> entity =
        this.restTemplate.getForEntity(this.nameServiceUrl, String.class);
    String host = entity.getHeaders().get("k8s-host").get(0);
    return entity.getBody() + " FROM => " + host;
  }
}
