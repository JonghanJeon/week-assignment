package study.likelionbeweekly.week2.beanexample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context
			= new AnnotationConfigApplicationContext(Config.class);

		Person person1 = context.getBean(Person.class);
		Person person2 = context.getBean(Person.class);

		System.out.println("person1 = " + person1);
		System.out.println("person2 = " + person2);

		person1.doSomething();
		person2.doSomething();
	}
}
