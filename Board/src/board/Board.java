package board;


public class Board {
	private Post post;
	protected static int cnt; // ��ü �Խñۼ�
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
	
	public String getPostHead() {
		Post post = this.getPost();
		return post.getHead();
	}
	@Override
	public String toString() {
		Post post = this.getPost();
		
		return String.format(""+post);
	}
}
