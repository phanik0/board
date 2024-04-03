package board;

public class Board {
	private Post post;
	protected static int cnt; // 전체 게시글수
	protected int postNum;

	public Board(Post post) {
		this.post = post;
		this.postNum = Post.postNum;
	}

	public Board(Post post, int postNum) {
		this.post = post;
		this.postNum = postNum;

	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public int getPostNum() {
		return this.postNum;
	}

	public String getPostHead() {
		Post post = this.getPost();
		return post.getHead();
	}

	@Override
	public String toString() {
		Post post = this.getPost();

		return String.format("" + post);
	}
}
