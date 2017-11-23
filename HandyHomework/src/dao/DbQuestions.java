package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import answer.TextAnswer;
import question.TextQuestion;

public class DbQuestions extends DbConnection implements QuestionDAO {

	@Override
	public List<TextQuestion> questions_for_assessments(int aid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertQuestions(int aid, String name, String question, int points) {
		Connection conn = getConnection();
		int res = -1;
		try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.QUESTIONS 
    				+ "(name, aid, question, points, is_mult) " +
    				" VALUES(?,?,?,?,?) RETURNING qid";
			PreparedStatement stat = conn.prepareStatement(insert);
			stat.setString(1, name);
			stat.setInt(2, aid);
			stat.setString(3, question);
			stat.setInt(4, points);
			stat.setBoolean(5, false);

			ResultSet Rs = stat.executeQuery();
			Rs.next();
			res =  Rs.getInt(1); 
    		
			conn.close();		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}
		return res;
	}
	/*
	@Override
	public List<TextAnswer> ansForQuestion(int questID) {
    	Connection conn = getConnection();
    	ArrayList<TextAnswer>  at =  new ArrayList<TextAnswer>();
    	try{
    		String query = "SELECT * FROM "	+ constants.Constants.DataConstants.ANSWERS + " where qid = ?;";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, questID);
    		ResultSet Rs = stat.executeQuery();    		
    		
    		while (Rs.next()) {
    			// Create and return a list of answer objects
    			
    			Boolean isCorrect = Rs.getBoolean(3);
    			String answer = Rs.getString(4);    			
    			TextAnswer ans = new TextAnswer(questID, answer, isCorrect);
    			at.add(ans);
    			System.out.println(answer);    
    		}
    		
    		conn.close();
    	}catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
		return at;
	}
	*/
	
	@Override
	public TextAnswer singleAnswerQuestion(int questID) {
    	Connection conn = getConnection();
    	try{
    		String query = "SELECT * FROM "	+ constants.Constants.DataConstants.ANSWERS + " where qid = ?;";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, questID);
    		ResultSet Rs = stat.executeQuery();    		
    		
    		if (Rs.next()) {
    			// Create and return a list of answer objects
    			
    			Boolean isCorrect = Rs.getBoolean(3);
    			String answer = Rs.getString(4);    			
    			TextAnswer ans = new TextAnswer(questID, answer, isCorrect);
    			return ans;
    		}
    		conn.close();
    	}catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
		return null;
	}
	

	@Override
	public int insertAnswers(int forQuest, boolean isCorrect, String answer) {
		Connection conn = getConnection();
		int res = -1;
		try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.ANSWERS 
    				+ "(qid, is_correct, answer) " +
    				" VALUES(?,?,?) RETURNING qid";
			PreparedStatement stat = conn.prepareStatement(insert);
		
			stat.setInt(1, forQuest);
			stat.setBoolean(2, isCorrect);
			stat.setString(3, answer);

			ResultSet Rs = stat.executeQuery();
			Rs.next();
			res =  Rs.getInt(1); 
    		
			conn.close();		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}
		return res;
	}

	@Override
	public void removeQuestion(int qid) {
		Connection conn = getConnection();
		//int res = -1;
		try {
    		String deleteAns = "DELETE FROM " + constants.Constants.DataConstants.ANSWERS + " WHERE qid = ?";
    		PreparedStatement stat = conn.prepareStatement(deleteAns);
    		stat.setInt(1, qid);
    		stat.executeUpdate();
    		
    		String deleteQuest = "DELETE FROM " + constants.Constants.DataConstants.QUESTIONS + " WHERE qid = ?";
    		PreparedStatement stat2 = conn.prepareStatement(deleteQuest);
    		stat2.setInt(1, qid);
    		stat2.executeUpdate();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}
	}

}
