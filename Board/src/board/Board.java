package board;


public class Board {
	private Post post;
	protected static int cnt; // 전체 게시글수
	private int postNum;
	public Board() {
		
	}
	public Board(Post post) {
		this.post = post;
		cnt++;
		postNum++;
	}
	public Post getPost() {
		return this.post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
	public int curNum() {
		return this.postNum;
	}
}
