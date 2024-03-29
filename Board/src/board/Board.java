package board;

import java.util.Map;

public class Board {
	private Map<>post;
	int cnt = 0; // 전체 게시글수
	int pageSize = 5; // 한페이지에 보여줄 게시글 수
	int curPageNum = 1; // 현재 페이지 번
	int pageCnt = 0; // 전체 페이지 개수
	int startRow = 0; // 현재 페이지의 게시글 시작번호
	int endRow = 0; // 현재 페이지의 게시글 마지막번호
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
