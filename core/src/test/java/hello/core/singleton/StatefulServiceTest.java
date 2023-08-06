package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {
	
	@Test
	void singletonServiceTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		
		int aPrice = statefulService1.order("A", 10000);
		int bPrice = statefulService1.order("B", 20000);
		
		//int price = statefulService1.getPrice();
		System.out.println("price=" + aPrice);
		
		//assertThat(statefulService1.getPrice()).isEqualTo(20000);
		
	}
	
	@Configuration
	static class TestConfig {
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
