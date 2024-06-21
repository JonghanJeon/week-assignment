package study.likelionbeweekly.week2.diexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Parent {
	// @Autowired
	private Child child;

	// @Autowired
	// public void setChild(Child child) {
	// 	this.child = child;
	// }

	@Autowired
	public Parent(Child child) {
		this.child = child;
	}

	public void doSomething() {
		System.out.println("I am Parent!");
		child.doSomething();
	}
}
