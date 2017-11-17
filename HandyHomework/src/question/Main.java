package question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		String question = "", answer = "", questionName = "";
		int points = 0;
		//Assessment assessment = new Assessment();
		InputStreamReader inRead = new InputStreamReader(System.in);
	    BufferedReader bRead = new BufferedReader(inRead);
	    System.out.println("Type exit to terminate program");
		while(true) {
		    try {
		    	System.out.println("Provide a question: ");
		    	question = bRead.readLine();
		    	if(question.equals("exit")) {
		    		System.exit(0);
		    	}
		    	System.out.println("Provide an answer : ");
		    	answer = bRead.readLine();
		    	if(answer.equals("exit")) {
		    		System.exit(0);
		    	}
		    	System.out.println("Provide a name for the question: ");
		    	questionName = bRead.readLine();
		    	if(question.equals("exit")) {
		    		System.exit(0);
		    	}
		    	System.out.println(" ");
		    	points = Integer.valueOf(bRead.readLine());
		    	if(answer.equals("exit")) {
		    		System.exit(0);
		    	}
		    } catch (IOException e) {
		        System.out.println("Reading input error");
		    }
		    
		}

	}
}