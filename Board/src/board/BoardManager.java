package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoardManager {
	private Scanner scan = new Scanner(System.in);
	protected static Map<Integer, Board> posts = new HashMap<Integer, Board>();// �Խñ� ��ȣ/ �Խñ�
	public static final int PAGE_SIZE = 5; // ���������� ������ �Խñ� ��

	
	/*
	 * �⺻���� ��� �α��� / �α׾ƿ� / ȸ������ / ȸ��Ż�� / �� ��ȸ(��ü) / �� �߰� / �� ����(����:�۾���) / ��
	 * ����(����:�۾���)
	 */


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
		return scan.nextLine();
	}

	private Post writePost() {
		String head = inputString("���� : ");

		String body = inputString("���� : ");
		Post post = new Post(head, body);
		return post;
	}

	public void writePost(User user) {

		Board.cnt++;
		Post.postNum++;
		Post post = writePost();
		user.addMyPost(post);
		Board board = new Board(post);
		posts.put(Post.postNum, board);
		System.out.println(posts);
	}

	public void modifyPost(User user) {

		int index = inputNumber("�����Ͻ� �Խñ� ��ȣ�� �Է����ּ���");
		if (!user.checkUserPost(index)) {
			System.err.println("�ùٸ�  �Խñ۹�ȣ�� �Է����ּ���");
			return;
		}
		Post post = writePost();
		user.modifyMyPost(index, post);
		Board board = new Board(post, index);
		posts.put(index, board);
	}

	public void deletePost(User user) {

		int index = inputNumber("�����Ͻ� �Խñ� ��ȣ�� �Է����ּ���");
		if (!user.checkUserPost(index)) {
			System.err.println("�ùٸ� �Խñ۹�ȣ�� �Է����ּ���");
			return;
		}
		user.removeMyPost(index);
		posts.remove(index);
		Board.cnt--;
	}

}
