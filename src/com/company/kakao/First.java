package com.company.kakao;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/155652?language=java
 */
public class First {

	public static void main(String[] args) {
		// write your code here
		System.out.println(solution("aukks", "wbqd", 5));
	}

	public static String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();

        for (char letter : s.toCharArray()) {
            char temp = letter;
            int idx = 0;
            while (idx < index) {
                temp = temp == 'z' ? 'a' : (char) (temp + 1);
                if (!skip.contains(String.valueOf(temp))) {
                    idx += 1;
                }
            }
            answer.append(temp);
        }

        return answer.toString();
	}
}
