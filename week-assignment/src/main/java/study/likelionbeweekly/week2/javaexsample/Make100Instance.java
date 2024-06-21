package study.likelionbeweekly.week2.javaexsample;

public class Make100Instance {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			//객체
			Person person;

			//인스턴스화
			person = new Person();

			//출력
			System.out.println(person);
		}
	}
}

class Person{
}
