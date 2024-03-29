package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private Map<Integer, Post> posts;// 게시글 번호/ 게시글
	private UserManager users;
	private BoardManager boards;
	private Scanner scan = new Scanner(System.in);
	private final int SIGN_IN = 1;
	private final int SIGN_UP = 2;

	private final int MENU = 0;

	private final int WRITE = 1;
	private final int MODIFY = 2;
	private final int DELETE = 3;
	private final int LOG_OUT = 4;
	private final int SIGN_OUT = 5;
	private boolean isMenu;
	private User loggedInUser;
	private int log;

	private int curPageNum; // 현재 페이지 번
	private int pageCnt; // 전체 페이지 개수
	private int startRow; // 현재 페이지의 게시글 시작번호
	private int endRow; // 현재 페이지의 게시글 마지막번호
	public static final int PAGE_SIZE = 5;

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
		posts = new HashMap<>();
		boards = new BoardManager();
		users = new UserManager();

	}

	private void setPage() {
		startRow = Board.cnt;
		endRow = startRow + BoardManager.PAGE_SIZE - 1;
		endRow = endRow >= Board.cnt ? Board.cnt - 1 : endRow;

		pageCnt = Board.cnt / BoardManager.PAGE_SIZE;
		pageCnt = Board.cnt % BoardManager.PAGE_SIZE > 0 ? ++pageCnt : pageCnt;

	}

	private void movePage() {
		
	}

	private void runBoard() {
		int sel = inputNumber("메뉴를 선택해주세요");
		if (sel < 0 || sel > BoardManager.PAGE_SIZE) {
			System.err.println("올바른 번호를 입력해주세요");
		}
		if (sel == MENU) {
			isMenu = true;
			if (log != -1)
				runLogInBoard();
			else
				runLogOutBoard();
		} else {

		}
	}

	private void runLogInBoard() {

		int sel = inputNumber("메뉴를 선택해주세요");
		switch (sel) {
		case WRITE:
			boards.writePost(loggedInUser);
			break;
		case MODIFY:
			boards.modifyPost(loggedInUser);
			break;
		case DELETE:
			boards.deletePost(loggedInUser);
			break;
		case LOG_OUT:
			logOut();
			break;
		case SIGN_OUT:
			signOut();
			break;

		}
	}

	private void logOut() {
		log = -1;
		System.out.println("로그아웃되었습니다");
		isMenu = false;
	}

	private void signOut() {
		String pw = inputString("비밀번호를 입력해주세요");

		if (!users.checkUserInfo(loggedInUser.getId(), pw)) {
			System.err.println("비밀번호가 일치하지 않습니다");
			return;
		}
		users.removeUser(log);
		isMenu = false;

	}

	private void runLogOutBoard() {
		int sel = inputNumber("메뉴를 선택해주세요");
		switch (sel) {
		case SIGN_IN:
			signIn();
			break;
		case SIGN_UP:
			signUp();
			break;
		}
	}

	private void signIn() {
		String id = inputString("ID를 입력해주세요");
		String pw = inputString("비밀번호를 입력해주세요");
		if (!users.checkUserInfo(id, pw)) {
			System.err.println("회원정보가 일치하지 않습니다");
			return;
		}
		loggedInUser = users.findUser(id);
		log = users.findUserIndex(id);
		System.out.println("로그인 되었습니다");
		isMenu = false;
	}

	private void signUp() {
		String id = inputString("ID를 입력해주세요");
		User temp = users.findUser(id);
		if (temp != null) {
			System.err.println("이미존재하는 아이디입니다");
			return;
		}
		String pw = inputString("비밀번호를 입력해주세요");
		User user = new User(id, pw);
		users.addUser(user);
		isMenu = false;

	}

	private void printBoard() {
		System.out.println("=========Blind Time==========");
		if (isMenu) {
			if (log != -1)
				System.out.println("[1]글쓰기 [2]글 수정 [3] 글 삭제 [4]로그아웃[5] 회원탈퇴");
			else
				System.out.println("[1]회원가입[2]로그인");
		} else
			System.out.println("[0]메뉴");
		System.out.println("=============================");
		if (startRow > PAGE_SIZE)
			for (int i = startRow; i >= 0; i--) {
				Post post = posts.get(i);
				System.out.println(post);
			}
		else {
			for (int i = startRow; i >= startRow - PAGE_SIZE; i--) {
				Post post = posts.get(i);
				System.out.println(post);
			}

		}
		System.out.println("=============================");
		System.out.println("[1]이전페이지 [2]다음페이지");
		System.out.printf("[%d/%d]", boards.getCurPageNum(), pageCnt);
		System.out.println("=============================");

	}

	public void run() {
		setBoard();
		while (true) {
			movePage();
			printBoard();
			runBoard();
		}

	}
	/*
	 * 기본적인 기능 로그인 / 로그아웃 / 회원가입 / 회원탈퇴 / 글 조회(전체) / 글 추가 / 글 수정(권한:글쓴이) / 글
	 * 삭제(권한:글쓴이)
	 */

}
