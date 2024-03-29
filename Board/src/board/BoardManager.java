package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoardManager{
	private Scanner scan = new Scanner(System.in);
	private Map<Integer, Board> posts = new HashMap<Integer, Board>();// �Խñ� ��ȣ/ �Խñ�
	public static final int PAGE_SIZE = 5; // ���������� ������ �Խñ� ��

	private int curPageNum; // ���� ������ ��
	private int pageCnt; // ��ü ������ ����
	private int startRow; // ���� �������� �Խñ� ���۹�ȣ
	private int endRow; // ���� �������� �Խñ� ��������ȣ
	
	/*
	 * �⺻���� ��� �α��� / �α׾ƿ� / ȸ������ / ȸ��Ż�� / �� ��ȸ(��ü) / �� �߰� / �� ����(����:�۾���) / ��
	 * ����(����:�۾���)
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
		int index = inputNumber("�����Ͻ� �Խñ� ��ȣ�� �Է����ּ���")-1;
		if(index <0 || index > user.getMyPostNum()) {
			System.err.println("�ùٸ� ������ �Խñ۹�ȣ�� �Է����ּ���");
			return;
		}
		Post post = writePost();
		Board board = new Board(post);
		posts.put(index, board);
	}
	
	public void deletePost(User user) {
		// TODO Auto-generated method stub
		int index = inputNumber("�����Ͻ� �Խñ� ��ȣ�� �Է����ּ���")-1;
		if(index <0 || index > user.getMyPostNum()) {
			System.err.println("�ùٸ� ������ �Խñ۹�ȣ�� �Է����ּ���");
			return;
		}
		posts.remove(index);
	}

	

	




	









	
}
