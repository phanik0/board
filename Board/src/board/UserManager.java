package board;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User>users;
	public User getUser(int index) {
		return users.get(index).clone();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	public User findUser(String id) {
		User user = null;
		for (int i = 0; i < users.size(); i++) {
			User target = users.get(i);
			if (target.getId().equals(id))
				user = target;
		}
		return user;
	}
	public int findUserIndex(String id) {
		int index = -1;
		for (int i = 0; i < users.size(); i++) {
			User target = users.get(i);
			if (target.getId().equals(id))
				index = i;
		}
		return index;
	}
	public boolean checkUserInfo(String id, String pw) {
		User user = findUser(id);
		if (user == null) {
			System.err.println("존재하지않는아이디입니다");
			return false;
		}
		if (user.getPw().equals(pw)) {
			return true;
		}
		return false;
	}
	public void removeUser(int index) {
		users.remove(index);
	}
}
