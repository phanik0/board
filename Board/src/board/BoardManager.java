package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoardManager{
	private Scanner scan = new Scanner(System.in);
	private Map<Integer, Board> posts = new HashMap<Integer, Board>();// 게시글 번호/ 게시글
	public static final int PAGE_SIZE = 5; // 한페이지에 보여줄 게시글 수

	private int curPageNum; // 현재 페이지 번
	private int pageCnt; // 전체 페이지 개수
	private int startRow; // 현재 페이지의 게시글 시작번호
	private int endRow; // 현재 페이지의 게시글 마지막번호
	
	/*
	 * 기본적인 기능 로그인 / 로그아웃 / 회원가입 / 회원탈퇴 / 글 조회(전체) / 글 추가 / 글 수정(권한:글쓴이) / 글
	 * 삭제(권한:글쓴이)
	 */
	public Board getBoard(int index) {
		return posts.get(index);
	}
	public int getCurPageNum() {
		return curPageNum;
	}
	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
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
		return scan.nextLine();
	}
	private Post writePost() {
		String head = inputString("제목 : ");
		String body = inputString("내용 : ");
		Post post = new Post(head,body);
		return post;
	}
	
	public void writePost(User user) {
		// TODO Auto-generated method stub
		Post post = writePost();
		user.addMyPost(post);
		Board board = new Board(post);
		posts.put(Board.cnt, board);
	}
	
	public void modifyPost(User user) {
		// TODO Auto-generated method stub
		int index = inputNumber("수정하실 게시글 번호를 입력해주세요")-1;
		if(index <0 || index > user.getMyPostNum()) {
			System.err.println("올바른 범위의 게시글번호를 입력해주세요");
			return;
		}
		Post post = writePost();
		Board board = new Board(post);
		posts.put(index, board);
	}
	
	public void deletePost(User user) {
		// TODO Auto-generated method stub
		int index = inputNumber("삭제하실 게시글 번호를 입력해주세요")-1;
		if(index <0 || index > user.getMyPostNum()) {
			System.err.println("올바른 범위의 게시글번호를 입력해주세요");
			return;
		}
		posts.remove(index);
	}

	

	




	









	
}
