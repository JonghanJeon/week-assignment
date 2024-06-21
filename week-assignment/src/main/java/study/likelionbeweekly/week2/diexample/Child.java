package study.likelionbeweekly.week2.diexample;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class Child {
	public void doSomething() {
		System.out.println("I am child!");
	}
}
