package com.company.bamin;

public class Third {

    public static void main(String[] args) {
	// write your code here
        System.out.println(solution("I love you"));
    }

    private static String solution(String word) {
        String result = "";

        String changeWords = "ABCDEFGHIJKLM" +
                             "NOPQRSTUVWXYZ";

        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            if (aChar == ' ') {
                result += " ";
                continue;
            }

            if (Character.isLowerCase(aChar)) {
                char temp = Character.toUpperCase(aChar);
                int index = changeWords.indexOf(temp);
                index = 25 - index;

                result += changeWords.substring(index, index+1).toLowerCase();
            } else {
                int index = changeWords.indexOf(aChar);
                index = 25 - index;

                result += changeWords.substring(index, index+1);
            }
        }

        return result;
    }
}
