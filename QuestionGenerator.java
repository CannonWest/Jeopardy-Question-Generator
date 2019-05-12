import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuestionGenerator {
	private static int score = 0;
	
	public static void main(String args[])	{
		Scanner qs = null;
		try {
			File qsFile = new File("JEOPARDY_CSVpipetake2.csv");
			qs = new Scanner(qsFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Question> questions = populateList(qs);
		
		while(play(questions))	{}
	}
	
	private static boolean play(ArrayList<Question> questions)	{
		Scanner userInp = new Scanner(System.in);
		System.out.println("Welcome to jeopardy! To select a question from the board,\ntype the first word of the category followed by the value.\nFor example, if you wanted to select the $200 question from the category \"World History\", you would type \"World 200\"\n");
		boolean quit = false;
		while(!quit)	{
			System.out.println("Ready to play? Type 'Y' for yes");
			String resp = userInp.next().toUpperCase();
			if(resp.equals("Y"))	{
				quit = true;
				System.out.println();
			}
		}
		
		quit = false;
		int board = 0;
		Board currBoard = null;
		while(!quit)	{
			Show thisShow = generateShow(questions);
			currBoard = thisShow.getBoard(board);
			
			System.out.println(currBoard);
			while(!currBoard.completeBoard())	{
				System.out.println("Please input the first word of the category, followed by the value.");
				String q = userInp.next();
				String num = userInp.next();
				String select = q + " " + num;
				try	{
					if(currBoard.selectSpace(select))	
						score += Integer.parseInt(num);
				} catch(InputMismatchException e)	{
					System.out.println(e.getMessage());
				}
			}
			
			board++;
			if(board >= 3)
				quit = true;
		}
		
		System.out.println("Your score was " + score + "Play again? Type 'Y' for yes or 'N' for no");
		userInp.close();
		return false;
	}
	
	private static ArrayList<Question> populateList(Scanner qs)	{
		ArrayList<Question> questions = new ArrayList<Question>();
		qs.nextLine();
		while (qs.hasNextLine())	{
			String line = qs.nextLine();
			String[] elems = line.split("\\|");
			int showNum = Integer.parseInt(elems[0]);
			Show show = new Show(showNum, elems[1]);
			
			char r = Character.toLowerCase(elems[2].charAt(0));
			Round round = null;
			switch(r)	{
				case 'j':
					round = Round.JEOPARDY;
					break;
				case 'd':
					round = Round.DOUBLE;
					break;
				case 't':
					round = Round.FINAL;
					break;
				case 'f':
					round = Round.FINAL;
					break;
				default:
					throw new InputMismatchException();
			}
			int val = 0;
			if (round != Round.FINAL)	{
				String valString = elems[4].replaceAll("\\$", "");
				valString = valString.replaceAll("\\,", "");
				val = Integer.parseInt(valString.trim());
			}
			
			try	{
				if(elems[5].contains("http://")  || elems[6].contains("http://"))
					throw new ArrayIndexOutOfBoundsException();
				questions.add(new Question(val, elems[5], elems[6], elems[3], show, round));
			} catch(ArrayIndexOutOfBoundsException e)	{//Skip any incomplete questions from the data file
			}
		}
		return questions;
	}

	private static Show generateShow(ArrayList<Question> questions)	{
		Question searchQ = questions.get((int)(Math.random()*questions.size()));
		Show show = searchQ.getShow();
		
		ArrayList<Question> thisShowQs = new ArrayList<Question>();
		for(Question q : questions)	{
			if(q.getShow().equals(show))
				thisShowQs.add(q);
		}
		
		ArrayList<Category> catList = new ArrayList<Category>();
		for(Question q : thisShowQs)	{
			Category thisCat = new Category(q.getCategory());
			if(!catList.contains(thisCat))	{
				catList.add(thisCat);
			}
		}
		
		for(Category c : catList)	{
			for(Question q : thisShowQs)	{
				Category thisCat = new Category(q.getCategory());
				if(thisCat.equals(c))	{
					c.addQuestion(q);
				}
			}
		}
		
		Board jeop = new Board(Round.JEOPARDY);
		Board doub = new Board(Round.DOUBLE);
		Board fin = new Board(Round.FINAL);
		for(int i = 0; i < catList.size(); i++)	{
			switch(catList.get(i).getQuestion(0).getRound())	{
				case JEOPARDY:
					jeop.addCategory(catList.get(i));
					break;
				case DOUBLE:
					doub.addCategory(catList.get(i));
					break;
				case FINAL:
					fin.addCategory(catList.get(i));
					break;
			}
		}
		
		show.addBoard(jeop, 0);
		show.addBoard(doub, 1);
		show.addBoard(fin, 2);
		
		return show;
	}
	
}
