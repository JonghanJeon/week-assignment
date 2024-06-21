package study.likelionbeweekly.week2.javaexsample;

public class SingletonEx {

	public static void main(String[] args) {

		for (int i = 0; i < 5; i++) {

			SingleTon singleTon = SingleTon.getInstance();
			System.out.println(singleTon);
		}
	}
}

class SingleTon {
	private static SingleTon instance;

	private SingleTon() {}

	public static SingleTon getInstance() {
		if (instance == null) {
			instance = new SingleTon();
		}
		return instance;
	}
}
