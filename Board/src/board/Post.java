package board;

public class Post extends Board{
	private String head,body;
	private int number;
	public Post(String head, String body) {
		this.head = head;
		this.body = body;
	}
	@Override
	public String toString() {
		return String.format("���� : %s \n ���� : %s",head, body );
	}
}

