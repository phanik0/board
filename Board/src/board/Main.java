package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

abstract public class Main {
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
	}

	private void runBoard() {
		int sel = inputNumber("�޴��� �������ּ���");
		if (sel == MENU) {
			isMenu = true;
			if (log != -1)
				runLogInBoard();
			else
				runLogOutBoard();
		}
	}

	private void runLogInBoard() {

		int sel = inputNumber("�޴��� �������ּ���");
		switch (sel) {
		case WRITE:
			writePost(loggedInUser);
			break;
		case MODIFY:
			modifyPost(loggedInUser);
			break;
		case DELETE:
			deletePost(loggedInUser);
			break;
		case LOG_OUT:
			logOut();
			break;
		case SIGN_OUT:
			signOut();
			break;

		}
	}

	abstract public void writePost(User user);

	abstract public void modifyPost(User user);

	abstract public void deletePost(User user);

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
		users.remove(log);
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
		User user = new User(id,pw);
		users.add(user);
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
		for (int i = BoardManager.PAGE_SIZE; i >= 0; i--) {
			Post post = posts.get(i);
			System.out.println(post);
		}
		System.out.println("=============================");
		System.out.println("[1]���������� [2]����������");
		System.out.printf("[%d/%d]", boards.getCurPageNum(), boards.getPageCnt());
		System.out.println("=============================");

	}
	public void run() {
		
	}
	/*
	 * �⺻���� ��� �α��� / �α׾ƿ� / ȸ������ / ȸ��Ż�� / �� ��ȸ(��ü) / �� �߰� / �� ����(����:�۾���) / ��
	 * ����(����:�۾���)
	 */

}
