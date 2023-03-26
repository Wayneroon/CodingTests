package com.company.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KakaoBank {
	private static final String OPEN_FILE_PATH = "comments.csv";
	private static final String SAVE_FILE_NAME = "result.log";
	private static final String SPACE = " ";

	public static void main(String[] args) throws IOException {
		Map<String, Long> map = getSchoolMap();
		saveFile(map);
	}

	/**
	 * 학교명/숫자 Map 반환
	 * @return map
	 */
	public static Map getSchoolMap() {
		Map<String, Long> map = new HashMap<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(OPEN_FILE_PATH))) {
			String line;
			String reg = ".*(학교).*";
			while ((line = br.readLine()) != null) {
				if (Pattern.matches(reg, line)) {
					putMatchingName(line, map, false);
				}
			}
		} catch (IOException e) {e.printStackTrace();}
		return map;
	}

	/**
	 * 매칭되는 학교 숫자 put
	 * @param line
	 * @param map
	 * @param reverseYn
	 */
	private static void putMatchingName(String line, Map<String, Long> map, boolean reverseYn) {
		String reverseWord;
		if (reverseYn) {
			reverseWord = line + SPACE;
		} else {
			reverseWord = reverse(line) + SPACE;
		}
		String[] split = reverseWord.split("교학");
		if (split.length > 2) {
			for (int i = split.length - 1; i >= 0; i--) {
				putMatchingName("교학" + split[i], map, true);
			}
			return;
		}

		String reverseReg = "(?<=교학)(.*?)(?=[^ㄱ-ㅎ가-힣])";
		Pattern pattern = Pattern.compile(reverseReg);
		Matcher matcher = pattern.matcher(reverseWord);
		if (matcher.find()) {
			String name = matcher.group();
			if ((name.startsWith("등초") && name.length() != 2) || (name.startsWith("중") && name.length() != 1) || (name.startsWith("등고") && name.length() != 2) || (name.startsWith("대") && name.length() != 1)) {
				name = reverse(name) + "학교";
				map.put(name, map.getOrDefault(name, 0l) + 1l);
			}
		}
	}

	/**
	 * 문자열 뒤집기
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * 파일 저장
	 * @param map
	 * @throws IOException
	 */
	private static void saveFile(Map<String, Long> map) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(SAVE_FILE_NAME, false))) {
			map.forEach((key, value) -> {
				try {
					bw.write(key + "\t" + value);
					bw.newLine();
				} catch (IOException e) {e.printStackTrace();}
			});
		}
	}
}
