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
		return String.format("제목 : %s \n 내용 : %s",head, body );
	}
}

