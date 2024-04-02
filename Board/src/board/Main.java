package board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private Map<Integer, Post> posts;// �Խñ� ��ȣ/ �Խñ�
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

	private int curPageNum; // ���� ������ ��
	private int pageCnt; // ��ü ������ ����
	private int startRow; // ���� �������� �Խñ� ���۹�ȣ
	private int endRow; // ���� �������� �Խñ� ��������ȣ
	public static final int PAGE_SIZE = 5;

	private int inputNumber(String message) {
		System.out.println(message);
		int number = 0;
		try {
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("���ڸ� �Է����ּ���");
		}
		return number;
	}

	private String inputString(String message) {
		System.out.println(message);
		return scan.next();
	}

	private void setBoard() {
		log = -1;
		posts = new HashMap<>();
		boards = new BoardManager();
		users = new UserManager();
		curPageNum = 0;
	}

	private void setPage() {
		startRow = Board.cnt-curPageNum * BoardManager.PAGE_SIZE;
		endRow = startRow - BoardManager.PAGE_SIZE + 1;
		endRow = endRow <= Board.cnt ? 0 : endRow;
		
		pageCnt = Board.cnt / BoardManager.PAGE_SIZE;
		pageCnt = Board.cnt % BoardManager.PAGE_SIZE > 0 ? ++pageCnt : pageCnt;
		System.out.println(startRow);
		System.out.println(endRow);
	}

	private void movePage(int sel) {
		setPage();
		if (sel != 1|| sel!=2) {
			System.err.println("�ùٸ� ��ȣ�� �Է����ּ���");
			return;
		}
		if(sel == 1 && curPageNum >1) {
			curPageNum --;
		}else if(sel ==2 && curPageNum < Board.cnt)
			curPageNum ++;
		
	}

	private void runBoard() {
		int sel = inputNumber("�޴��� �������ּ���");
		if (sel < 0 || sel > BoardManager.PAGE_SIZE) {
			System.err.println("�ùٸ� ��ȣ�� �Է����ּ���");
		}
		if (sel == MENU) {
			isMenu = true;
			if (log == -1)
				runLogOutBoard();
			else
				runLogInBoard();
		} else if(sel == 1){
			movePage(sel);
		}else if(sel == 2) {
			movePage(sel);
		}
	}

	private void runLogInBoard() {
		printLogInBoard();
		int sel = inputNumber("�޴��� �������ּ���");
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
	private void printLogInBoard() {
		System.out.println("[1]�۾���");
		System.out.println("[2]�� ����");
		System.out.println("[3]�� ����");
		System.out.println("[4]�α׾ƿ�");
		System.out.println("[5]ȸ�� Ż��");
	}
	private void logOut() {
		log = -1;
		System.out.println("�α׾ƿ��Ǿ����ϴ�");
		isMenu = false;
	}

	private void signOut() {
		String pw = inputString("��й�ȣ�� �Է����ּ���");

		if (!users.checkUserInfo(loggedInUser.getId(), pw)) {
			System.err.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			return;
		}
		users.removeUser(log);
		isMenu = false;

	}

	private void runLogOutBoard() {
		printLogOutBoard();
		int sel = inputNumber("�޴��� �������ּ���");
		switch (sel) {
		case SIGN_IN:
			signIn();
			break;
		case SIGN_UP:
			signUp();
			break;
		}
	}
	private void printLogOutBoard() {
		System.out.println("[1]�α���");
		System.out.println("[2]ȸ�� ����");
	}
	private void signIn() {
		String id = inputString("ID�� �Է����ּ���");
		String pw = inputString("��й�ȣ�� �Է����ּ���");
		if (!users.checkUserInfo(id, pw)) {
			System.err.println("ȸ�������� ��ġ���� �ʽ��ϴ�");
			return;
		}
		loggedInUser = users.findUser(id);
		log = users.findUserIndex(id);
		System.out.println("�α��� �Ǿ����ϴ�");
		isMenu = false;
	}

	private void signUp() {
		String id = inputString("ID�� �Է����ּ���");
		if(users.getSize()>0) {
		User temp = users.findUser(id);
		if (temp != null) {
			System.err.println("�̹������ϴ� ���̵��Դϴ�");
			return;
		}
		}
		String pw = inputString("��й�ȣ�� �Է����ּ���");
		User user = new User(id, pw);
		users.addUser(user);
		isMenu = false;

	}

	private void printBoard() {
		setPage();
//		for(int i = 1; i <startRow; i++) {
//			Board board = boards.getBoard(i);
//			Post post = board.getPost();
//			System.out.println(post);
//		}
		List<Integer> keySet = new ArrayList(boards.posts.keySet());
		Collections.sort(keySet);
		System.out.println(keySet);
		System.out.println("=========Blind Time==========");
		if (isMenu) {
			if (log != -1)
				System.out.println("[1]�۾��� [2]�� ���� [3] �� ���� [4]�α׾ƿ�[5] ȸ��Ż��");
			else
				System.out.println("[1]ȸ������[2]�α���");
		} else {
			System.out.println("[0]�޴�");
		}
		for (int i = startRow-1; i>=endRow ; i--) {
			int number = keySet.get(i);
			Board board =  BoardManager.posts.get(number);
			String title = board.getPostHead();
			String out = String.format("%s : %s", i, title);
			System.out.println(out);
		}
		System.out.println("=============================");
//		if (startRow > PAGE_SIZE)
//			for (int i = startRow; i >= startRow - PAGE_SIZE; i--) {
//				Board board = boards.getBoard(i);
//				Post post = board.getPost();
//				System.out.println(post);
//			}
//		else if(startRow>0){
//			for (int i = startRow; i >= 0; i--) {
//				Board board = boards.getBoard(i);
//				if(board == null)
//					continue;
//				Post post = board.getPost();
//				System.out.println(post);
//
//			}

//		}
		System.out.println("=============================");
		System.out.println("[1]���������� [2]����������");
		System.out.printf("[%d/%d]", curPageNum, pageCnt);
		System.out.println("=============================");

	}

	public void run() {
		setBoard();
		while (true) {
			printBoard();
			runBoard();
		}

	}
	/*
	 * �⺻���� ��� �α��� / �α׾ƿ� / ȸ������ / ȸ��Ż�� / �� ��ȸ(��ü) / �� �߰� / �� ����(����:�۾���) / ��
	 * ����(����:�۾���)
	 */

}
