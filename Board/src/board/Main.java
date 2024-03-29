package board;

import java.util.ArrayList;
import java.util.HashMap;
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
		int sel = inputNumber("�޴��� �������ּ���");
		if (sel < 0 || sel > BoardManager.PAGE_SIZE) {
			System.err.println("�ùٸ� ��ȣ�� �Է����ּ���");
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
		User temp = users.findUser(id);
		if (temp != null) {
			System.err.println("�̹������ϴ� ���̵��Դϴ�");
			return;
		}
		String pw = inputString("��й�ȣ�� �Է����ּ���");
		User user = new User(id, pw);
		users.addUser(user);
		isMenu = false;

	}

	private void printBoard() {
		System.out.println("=========Blind Time==========");
		if (isMenu) {
			if (log != -1)
				System.out.println("[1]�۾��� [2]�� ���� [3] �� ���� [4]�α׾ƿ�[5] ȸ��Ż��");
			else
				System.out.println("[1]ȸ������[2]�α���");
		} else
			System.out.println("[0]�޴�");
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
		System.out.println("[1]���������� [2]����������");
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
	 * �⺻���� ��� �α��� / �α׾ƿ� / ȸ������ / ȸ��Ż�� / �� ��ȸ(��ü) / �� �߰� / �� ����(����:�۾���) / ��
	 * ����(����:�۾���)
	 */

}
