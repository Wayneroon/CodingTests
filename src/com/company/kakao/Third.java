package com.company.kakao;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/150368
 */
public class Third {

	public static void main(String[] args) {
		// write your code here
		System.out.println(solution(4,  5, new int[] {1,0,3,1,2}, new int[] {0,3,0,4,0}));
	}

	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		for(int i = n-1 ; i >-1 ; i--){
			// 해당 위치에 0, 0 일 때까지
			while (deliveries[i] > 0 || pickups[i] > 0){
				deliveries[i] -= cap;
				pickups[i] -= cap;
				answer += 2 *(i+1);

			}

			if(i==0) break; // i= 0 일시 아래에서 배열 에러발생.

			// 초과분에 대해서 i-1에 이월하여 해결.
			if(deliveries[i]<0){
				deliveries[i-1] += deliveries[i];
			}

			if(pickups[i]<0){
				pickups[i-1] += pickups[i]; ///
			}
		}


		return answer;
	}
}
