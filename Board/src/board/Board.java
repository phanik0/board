package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private Scanner scan = new Scanner(System.in);
	private Map<Integer,Post>posts;//게시글 번호/ 게시글
	private ArrayList<User>users;
	protected static int cnt = 0; // 전체 게시글수
	private int pageSize; // 한페이지에 보여줄 게시글 수
	private int curPageNum; // 현재 페이지 번
	private int pageCnt; // 전체 페이지 개수
	private int startRow; // 현재 페이지의 게시글 시작번호
	private int endRow; // 현재 페이지의 게시글 마지막번호
	private int log;
	/*
	 * 기본적인 기능
		로그인 / 로그아웃 / 회원가입 /
		 회원탈퇴 / 글 조회(전체) / 글 추가 
		 / 글 수정(권한:글쓴이) / 글 삭제(권한:글쓴이)
	 */

	private int inputNumber(String message) {
		System.out.println(message);
		int number = 0;
		try {
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자만 입력해주세요");
		}
		return number;
	}
	private String inputString(String message) {
		System.out.println(message);
		return scan.next();
	}
	private void setBoard() {
		pageSize = 5;
		posts = new HashMap<>();
		users = new ArrayList<>();
	}
	private void printBoard() {
		System.out.println("=========Blind Time==========");
		if(log != -1)
		System.out.println("[1]로그인 [2]글쓰기 [3]글 수정 [4] 글 삭제");
		else
			System.out.println("[1]회원가입[2]로그인");
		System.out.println("=============================");
		for(int i = pageSize ; i>=0;i--) {
			Post post = posts.get(i);
			System.out.println(post);
		}
		System.out.println("=============================");
		System.out.println("[1]이전페이지 [2]다음페이지");
		System.out.printf("[%d/%d]",curPageNum,pageCnt);
		System.out.println("=============================");
	}
	public void run() {
		
	}
}
