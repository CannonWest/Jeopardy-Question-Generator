import java.util.ArrayList;
import java.util.InputMismatchException;

public class Board {
	//FIELDS
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private Round round;
	
	//CONSTRUCTORS
	public Board(Round round)	{
		this.round = round;
	}
	
	//GET
	public Round getRound() {
		return round;
	}
	public String getCategoryListString() {
		String output = "";
		for (Category c : categoryList)	{
			output = output + c.getCategory() + " ";
		}
		return output;
	}

	//ADD CATEGORY
	public void addCategory(Category category)	{
		categoryList.add(category);
	}
	
	public boolean completeBoard()	{
		boolean complete = true;
		for(Category category : categoryList)	{
			if(!category.answeredAll())
				complete = false;
		}
		return complete;
	}
	
	public boolean selectSpace(String str)	{
		String[] arr = str.split(" ");
		if (arr.length != 2)
			throw new InputMismatchException("Please input the first word of the category, followed by the value.");
		
		String[] categories = new String[categoryList.size()];
		for(int i = 0; i < categoryList.size(); i++)	{
			String[] categoryArr = categoryList.get(i).getCategory().split(" ");
			categories[i] = categoryArr[0];
		}
		
		Category selected = null;
		for(int i = 0; i < categories.length; i++)	{
			if (categories[i].equals(arr[0]))	{
					selected = categoryList.get(i);
			}
		}
		
		if (selected == null)
			throw new InputMismatchException("Please input the first word of the category, followed by the value.");
		
		try	{
			return selected.selectQuestion(Integer.parseInt(arr[1]));
		} catch(InputMismatchException i)	{
			throw new InputMismatchException("Please input the first word of the category, followed by the value.");
		} catch(ArrayIndexOutOfBoundsException a)	{
			throw new InputMismatchException("Please input the first word of the category, followed by the value.");
		}
	}
	
	//TO-STRING
	@Override
	public String toString()	{
		String output = "";
		for(Category c : categoryList)	{
			output = output + c.toString() + "\n";
		}
		output = output + "\n";
		return output;
	}
}
