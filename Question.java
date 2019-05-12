import java.util.Scanner;

public class Question {
	//FIELDS
	private int value;
	private String question;
	private String answer;
	private String category;
	private Show show;
	private Round round;
	private Scanner userInp = new Scanner(System.in);
	
	//CONSTRUCTORS
	public Question(int value, String question, String answer, String category, Show show, Round round) {
		super();
		this.value = value;
		this.question = question;
		this.answer = answer;
		this.category = category;
		this.show = show;
		this.round = round;
	}
	
	//GETTERS
	public int getValue() {
		return value;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public String getCategory() {
		return category;
	}

	public Show getShow() {
		return show;
	}

	public Round getRound() {
		return round;
	}
	
	public boolean select()	{
		System.out.println("\n" + this.getCategory() + ":");
		System.out.println(this.getQuestion());
		System.out.println("Type anything to reveal the answer");
		userInp.next();
		System.out.println(this.getAnswer());
		System.out.println("Did you get it right? Type 'Y' for yes");
		String ans = userInp.next();
		if (ans.toLowerCase() == "y")
			return true;
		return false;
	}
	
	//TO-STRING
	@Override
	public String toString() {
		return question + "\t" + answer + "\t" + value;
	}
}
