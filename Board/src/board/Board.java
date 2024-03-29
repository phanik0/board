package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private Scanner scan = new Scanner(System.in);
	private Map<Integer,Post>posts;//�Խñ� ��ȣ/ �Խñ�
	private ArrayList<User>users;
	protected static int cnt = 0; // ��ü �Խñۼ�
	private int pageSize; // ���������� ������ �Խñ� ��
	private int curPageNum; // ���� ������ ��
	private int pageCnt; // ��ü ������ ����
	private int startRow; // ���� �������� �Խñ� ���۹�ȣ
	private int endRow; // ���� �������� �Խñ� ��������ȣ
	private int log;
	/*
	 * �⺻���� ���
		�α��� / �α׾ƿ� / ȸ������ /
		 ȸ��Ż�� / �� ��ȸ(��ü) / �� �߰� 
		 / �� ����(����:�۾���) / �� ����(����:�۾���)
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
		System.out.println("[1]�α��� [2]�۾��� [3]�� ���� [4] �� ����");
		else
			System.out.println("[1]ȸ������[2]�α���");
		System.out.println("=============================");
		for(int i = pageSize ; i>=0;i--) {
			Post post = posts.get(i);
			System.out.println(post);
		}
		System.out.println("=============================");
		System.out.println("[1]���������� [2]����������");
		System.out.printf("[%d/%d]",curPageNum,pageCnt);
		System.out.println("=============================");
	}
	public void run() {
		
	}
}
