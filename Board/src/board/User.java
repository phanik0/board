package board;

import java.util.HashMap;
import java.util.Map;

public class User {
	private String id,pw;
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
		return new User(this.id,this.pw);
	}
	public int getMyPostNum() {
		return this.myPostNum;
	}
	public void addMyPost(Post post) {
		myPost.put(++myPostNum, post);
	}
	public void modifyMyPost(int index , Post post) {
		myPost.put(index, post);
	}
}
