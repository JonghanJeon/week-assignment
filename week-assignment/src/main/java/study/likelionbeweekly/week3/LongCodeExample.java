package study.likelionbeweekly.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LongCodeExample {

    // 유저 저장소의 역할
    private static final Map<String, String> users = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // main 메서드 실행 시 키보드 입력 받는 객체(클라이언트 요청)
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        do {
            /**
             * 회원 가입 처리 로직
             * /joinPage -> 회원 가입 요청
             * /loginPage -> 로그인 요청
             * main 메서드 실행 후 숫자 1 과 2 중 선택
             */
            System.out.println("1. /joinPage\t"
                             + "2. /loginPage\t"
                             + "3. 종료");

            int number = Integer.parseInt(bufferedReader.readLine());
            // 아이디를 입력하면 저장될 변수
            String id;
            // 비밀번호를 입력하면 저장될 변수
            String password;
            // 요청 저장될 변수
            String requestMapping;
            switch (number) {
                case 1: // 1번 선택 시 회원 가입 페이지 이동
                    System.out.print("가입할 아이디 입력: ");
                    id = bufferedReader.readLine(); // 아이디 입력
                    System.out.print("가입할 비밀번호 입력: ");
                    password = bufferedReader.readLine();   // 비밀번호 입력
                    requestMapping = "/join";
                    logic(id, password, requestMapping);
                    break;
                case 2: // 2번 선택 시 로그인 페이지 이동
                    System.out.print("로그인 아이디 입력: ");
                    id = bufferedReader.readLine(); // 아이디 입력
                    System.out.print("로그인 비밀번호 입력: ");
                    password = bufferedReader.readLine();   // 비밀번호 입력
                    requestMapping = "/login";
                    logic(id, password, requestMapping);
                    break;
                case 3: // 종료
                    return;
            }
        } while (true);
    }

    private static void logic(String id, String password, String requestMapping) {
        switch (requestMapping) {
            case "/join":   // 회원 가입 요청
                if (users.containsKey(id)) {
                    System.out.println("중복 아이디 가입");
                    return;
                }
                users.put(id, password);
                System.out.println("회원 가입 완료");
                break;

            case "/login":  // 로그인 요청
                // 가입한 유저인지 확인
                // 가입한 유저가 없으면 종료
                if (!users.containsKey(id)) {
                    System.out.println("가입되지 않은 아이디");
                    return;
                }

                // 입력한 id 를 기준으로 password 조회
                String findUserPassword = users.get(id);
                // 비밀번호 불일치 시 종료
                if (!password.equals(findUserPassword)) {
                    System.out.println("비밀번호 불일치");
                    return;
                }
                // 비밀번호 일치 시 종료
                System.out.println("로그인 성공");
                System.out.println("당신의 아이디는 " + id + " 입니다.");
                break;
        }
    }
}
