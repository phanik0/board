package board;

import java.util.Map;

public class Board {
	private Map<>post;
	int cnt = 0; // ��ü �Խñۼ�
	int pageSize = 5; // ���������� ������ �Խñ� ��
	int curPageNum = 1; // ���� ������ ��
	int pageCnt = 0; // ��ü ������ ����
	int startRow = 0; // ���� �������� �Խñ� ���۹�ȣ
	int endRow = 0; // ���� �������� �Խñ� ��������ȣ
	private void printBoard() {
		System.out.println("========Blind Time==========");
		for(int i = 0 ; i<pageSize;i++) {
			String title = user.getTitle();
			System.out.println();
		}
	}
	public void run() {
		
	}
}
