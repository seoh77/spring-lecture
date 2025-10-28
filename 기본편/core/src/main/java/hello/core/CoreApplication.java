package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다.
	그리고 이 설정 안에 @ComponentScan이 들어있다.
 */
@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
