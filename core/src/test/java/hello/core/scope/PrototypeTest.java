package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

	@Test
	void protoTypeBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		
		PrototypeBean pro1 = ac.getBean(PrototypeBean.class);
		PrototypeBean pro2 = ac.getBean(PrototypeBean.class);
		System.out.println("find prototypeBean1:" + pro1);
		System.out.println("find prototypeBean2:" + pro2);
		assertThat(pro1).isNotSameAs(pro2);
	}
	
	@Scope("prototype")
	static class PrototypeBean{
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init");
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destory");
		}
	}
	
}
