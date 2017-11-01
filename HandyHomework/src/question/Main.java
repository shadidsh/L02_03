package question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assessment.Assessment;

public class Main {
	public static void main(String[] args) {
		String question = "", answer = "", questionName = "";
		int points = 0;
		Assessment assessment = new Assessment();
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
			TextQuestion TQ = new TextQuestion(question, answer, questionName, points);
			System.out.println("This is the question: " +TQ.getQuestion());
			System.out.println("This is the answer: " +TQ.getAnswer());
			assessment.AddQuestion(TQ);
			System.out.println(assessment.getAssessment());
		}

	}
}