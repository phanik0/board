package board;

public class Post {
	private String head,body;
	protected static int postNum;
	public Post(String head, String body) {
		this.head = head;
		this.body = body;
	}
	public String getHead() {
		return this.head;
	}
	@Override
	public String toString() {
		return String.format("���� : %s \n ���� : %s",head, body );
	}
}

