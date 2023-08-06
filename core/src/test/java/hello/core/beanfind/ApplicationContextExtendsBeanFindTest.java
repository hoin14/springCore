package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class ApplicationContextExtendsBeanFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
	
	@Test
	@DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복오류")
	void findBeanByParentTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class,
					() -> ac.getBean(DiscountPolicy.class));
	}
	
	@Test
	@DisplayName("부모 타입으로 조회시 같은 자식이 둘 이상 있으면 빈 이름 지정")
	void findBeanByParentTypeBeanName() {
		DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("특정 하위 타입으로 조회")
	void findBeanBySubType() {
		RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("부모 타입으로 모두 조회")
	void findAllBeanByParentType() {
		 Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		 for(String key : beansOfType.keySet()) {
			 System.out.println("key=" + key + "value=" + beansOfType.get(key));
		 }
		 System.out.println("beansOfType=" + beansOfType);
		 assertThat(beansOfType.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Object 타입으로 모두 조회")
	void findAllBeanByObjectType() {
		 Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
		 for(String key : beansOfType.keySet()) {
			 System.out.println("key=" + key + "value=" + beansOfType.get(key));
		 }
	}
	
	@Configuration
	static class TestConfig {
		@Bean
		public DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}
		
		@Bean
		public DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
	}
}
