package board;

import java.util.HashMap;
import java.util.Map;

public class User {
	private String id,pw;
	private Map<Integer, Post> myPost;// ���̵� / �Խñ�
	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
		myPost = new HashMap<>();
	}
	
}
