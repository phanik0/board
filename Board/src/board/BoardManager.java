package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoardManager {
	private Scanner scan = new Scanner(System.in);
	protected static Map<Integer, Board> posts = new HashMap<Integer, Board>();// 게시글 번호/ 게시글
	public static final int PAGE_SIZE = 5; // 한페이지에 보여줄 게시글 수

	
	/*
	 * 기본적인 기능 로그인 / 로그아웃 / 회원가입 / 회원탈퇴 / 글 조회(전체) / 글 추가 / 글 수정(권한:글쓴이) / 글
	 * 삭제(권한:글쓴이)
	 */


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

		int index = inputNumber("수정하실 게시글 번호를 입력해주세요");
		if (!user.checkUserPost(index)) {
			System.err.println("올바른  게시글번호를 입력해주세요");
			return;
		}
		Post post = writePost();
		user.modifyMyPost(index, post);
		Board board = new Board(post, index);
		posts.put(index, board);
	}

	public void deletePost(User user) {

		int index = inputNumber("삭제하실 게시글 번호를 입력해주세요");
		if (!user.checkUserPost(index)) {
			System.err.println("올바른 게시글번호를 입력해주세요");
			return;
		}
		user.removeMyPost(index);
		posts.remove(index);
		Board.cnt--;
	}

}
