package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

	@Test
	void singletonBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletoneBean.class);
		
		SingletoneBean singletoneBean1 = ac.getBean(SingletoneBean.class);
		SingletoneBean singletoneBean2 = ac.getBean(SingletoneBean.class);
		System.out.println("singletonBean1:" + singletoneBean1);
		System.out.println("singletonBean2:" + singletoneBean2);
		assertThat(singletoneBean1).isSameAs(singletoneBean2);
	}
	
	@Scope("singleton")
	static class SingletoneBean{
		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}
		@PreDestroy
		public void destory() {
			System.out.println("SingletonBean.destroy");			
		}
	}
}
