public class Show {
	//FIELDS
	private Board[] boardList = new Board[3];
	private int showNum;
	private String airDate;
	
	//CONSTRUCTORS
	public Show(int showNum, String airDate)	{
		super();
		this.showNum = showNum;
		this.airDate = airDate;
	}
	public Show()	{
		showNum = 0;
		airDate = "";
	}
	
	//GET
	public Board getBoard(int i)	{
		return boardList[i];
	}
	public void addBoard(Board board, int i)	{
		boardList[i] = board;
	}
	
	@Override
	public String toString() {
		String output = "";
		for(Board b : boardList)	{
			output = output + b.toString() + "\n";
		}
		output = output + "\n\n";
		return output;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Show other = (Show) obj;
		if (airDate == null) {
			if (other.airDate != null)
				return false;
		} else if (!airDate.equals(other.airDate))
			return false;
		if (showNum != other.showNum)
			return false;
		return true;
	}

}
