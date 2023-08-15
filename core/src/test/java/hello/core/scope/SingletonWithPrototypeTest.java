package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;



public class SingletonWithPrototypeTest {

	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean protoTypeBean1 = ac.getBean(PrototypeBean.class);
		protoTypeBean1.addCount();
		assertThat(protoTypeBean1.getCount()).isEqualTo(1);
		
		PrototypeBean protoTypeBean2 = ac.getBean(PrototypeBean.class);
		protoTypeBean2.addCount();
		assertThat(protoTypeBean2.getCount()).isEqualTo(1);
		
	}
	
	@Test
	void singletonClientUserProtoType() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
		
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);
		
		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);
	}
	
	@Scope("singleton")
	static class ClientBean{
		private final PrototypeBean prototypeBean; //생성시점에 주
		
		@Autowired
		private ObjectProvider<PrototypeBean> prototypeBeanProvider;
		
		@Autowired
		public ClientBean(PrototypeBean prototypeBean) {
			this.prototypeBean = prototypeBean;
		}
		
		public int logic() {
			PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
			prototypeBean.addCount();
			int count = prototypeBean.getCount();
			return count;
		}
	}
	
	@Scope("prototype")
	static class PrototypeBean{
		
		private int count = 0;
		
		public void addCount() {
			count++;
		}
		
		public int getCount() {
			return count;
		}
		
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init" + this);
		}
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
