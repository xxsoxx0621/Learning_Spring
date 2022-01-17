package kh.spring.advisor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvisor {
	
	@Before
	public void beforeLog() {
		System.out.println("AOP Before 동작합니다. ");
	}
	
}
