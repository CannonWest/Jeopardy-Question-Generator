import java.util.ArrayList;

public class Category {
	//FIELDS
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private ArrayList<Boolean> answered = new ArrayList<Boolean>();
	private String category;
	
	//CONSTRUCTORS
	public Category(String category)	{
		this.category = category;
	}
	
	//GET
	public String getCategory() {
		return category;
	}
	public Question getQuestion(int index)	{
		return questionList.get(index);
	}
	public String getQuestionString(int index)	{
		return questionList.get(index).getQuestion();
	}
	public String getAnswerAt(int index)	{
		return questionList.get(index).getAnswer();
	}
	public int getValueAt(int index)	{
		return questionList.get(index).getValue();
	}
	
	//ADD QUESTION
	public void addQuestion(Question question)	{
		questionList.add(question);
		answered.add(false);
	}

	public boolean selectQuestion(int val)	{
		int index = -1;
		for(int i = 0; i < questionList.size(); i++)	{
			if(questionList.get(i).getValue() == val)	{
				index = i;
				break;
			}
		}
		answered.set(index, true);
		return questionList.get(index).select();
		
	}
	
	public boolean answeredAll()	{
		boolean returnS = true;
		for (int i = 0; i < answered.size(); i++)	{
			if(!answered.get(i))
				returnS = false;
		}
		return returnS;
	}
	//TO-STRING
	@SuppressWarnings("static-access")
	@Override
	public String toString()	{
		String output = "";
		output = output.format("%-40s", category);
		for(Question q : questionList)	{
			output = output + q.getValue() + "\t";
		}
		output = output + "\n";
		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}
}
