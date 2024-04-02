package board;

import java.util.HashMap;
import java.util.Map;

public class User {
	private String id, pw;
	private int myPostNum;
	private Map<Integer, Post> myPost;// 아이디 / 게시글

	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
		myPost = new HashMap<>();
	}

	public String getId() {
		return this.id;
	}

	public String getPw() {
		return this.pw;
	}

	public User clone() {
		return new User(this.id, this.pw);
	}

	public int getMyPostNum() {
		return this.myPostNum;
	}

	public int getMyPostSize() {
		return myPost.size();
	}

	public boolean checkUserPost(int index) {
		if (myPost.get(index) == null) {
			return false;
		}
		return true;
	}

	public Post getMyPost(int index) {
		return myPost.get(index);
	}

	public void addMyPost(Post post) {
		myPost.put(Post.postNum, post);
	}

	public void modifyMyPost(int index, Post post) {
		myPost.put(index, post);
	}

	public void removeMyPost(int index) {
		myPost.remove(index);
	}
}
