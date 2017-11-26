package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import answer.TextAnswer;
import question.MultQuestion;
import question.Question;
import question.TextQuestion;

public class DbQuestions extends DbConnection implements QuestionDAO {


	@Override
	public List<Question> allQuestions(int aid) {
		Connection conn = getConnection();
		try {
    		String query = "Select qid, name, question, is_mult, points, is_latex from "
    				+ constants.Constants.DataConstants.QUESTIONS + " where "
    						+ " aid = ?";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, aid);
    		ResultSet Rs = stat.executeQuery();
    		
    		ArrayList<Question> questions = new ArrayList<Question>() ;
    		TextAnswer ta;
    		List<TextAnswer> multAns;
    		
    		while (Rs.next()) {
    			int qid = Rs.getInt(1);
    			String name = Rs.getString(2);
    			String question = Rs.getString(3);
    			boolean isMult = Rs.getBoolean(4);
    			int points = Rs.getInt(5);
    			boolean isLat = Rs.getBoolean(6);
    			
    			if (isMult) {
    				MultQuestion mq = new MultQuestion(qid, name, question, points);
    				multAns = this.multAnswerQuestion(qid);
    				mq.addAnswers(multAns);
    				questions.add(mq);
    				
    			} else {        			
    				TextQuestion tq = new TextQuestion(qid, name, question, points, isLat);
        			ta = this.singleAnswerQuestion(qid);
        			tq.setAnswer(ta);
        			questions.add(tq);
    			}

    		}    		
    		conn.close();
    		return questions;
    		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}		
		return null;
	}
	
	@Override
	public List<TextQuestion> TextQuestions(int aid) {
		Connection conn = getConnection();
		try {
    		String query = "Select qid, name, question, points from "
    				+ constants.Constants.DataConstants.QUESTIONS + " where "
    						+ " aid = ? and is_mult = false";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, aid);
    		ResultSet Rs = stat.executeQuery();
    		
    		ArrayList<TextQuestion> questions = new ArrayList<TextQuestion>() ;
    		TextAnswer ta;
    		
    		while (Rs.next()) {
    			int qid = Rs.getInt(1);
    			String name = Rs.getString(2);
    			String question = Rs.getString(3);
    			int points = Rs.getInt(4);

    			TextQuestion tq = new TextQuestion(qid, name, question, points);
    			ta = this.singleAnswerQuestion(qid);
    			tq.setAnswer(ta);
    			questions.add(tq);
    		}    		
    		conn.close();
    		return questions;
    		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}		
		return null;
	}

	@Override
	public List<MultQuestion> multChoiceQuestions(int aid) {
		Connection conn = getConnection();
		try {
    		String query = "Select qid, is_mult, name, question, points from "
    				+ constants.Constants.DataConstants.QUESTIONS + " where "
    						+ " aid = ? and is_mult = true";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, aid);
    		ResultSet Rs = stat.executeQuery();
    		
    		ArrayList<MultQuestion> questions = new ArrayList<MultQuestion>() ;
    		TextAnswer ta;
    		while (Rs.next()) {
    			int qid = Rs.getInt(1);
    			boolean isMult = Rs.getBoolean(2);
    			String name = Rs.getString(3);
    			String question = Rs.getString(4);
    			int points = Rs.getInt(5);
    			
    			MultQuestion mc = new MultQuestion(qid,  name, question, points);
    			List<TextAnswer> tAns =  multAnswerQuestion(qid);
    			mc.addAnswers(tAns);
    			questions.add(mc);    		
    		}
    		
    		conn.close();
    		return questions;
    		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}
		
		return null;
	}
	
	@Override
	public List<TextQuestion> LatexQuestions(int aid) {
		Connection conn = getConnection();
		try {
    		String query = "Select qid, name, question, points from "
    				+ constants.Constants.DataConstants.QUESTIONS + " where "
    						+ " aid = ? and is_mult = false and is_latex = true";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, aid);
    		ResultSet Rs = stat.executeQuery();
    		
    		ArrayList<TextQuestion> questions = new ArrayList<TextQuestion>() ;
    		TextAnswer ta;
    		
    		while (Rs.next()) {
    			int qid = Rs.getInt(1);
    			String name = Rs.getString(2);
    			String question = Rs.getString(3);
    			int points = Rs.getInt(4);

    			TextQuestion tq = new TextQuestion(qid, name, question, points);
    			ta = this.singleAnswerQuestion(qid);
    			tq.setAnswer(ta);
    			questions.add(tq);
    		}    		
    		conn.close();
    		return questions;
    		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}		
		return null;
	}
	
	@Override
	public int insertQuestions(int aid, String name, String question, int points, boolean isMult, boolean isLat) {
		Connection conn = getConnection();
		int res = -1;
		try{
    		String insert = "INSERT INTO " + constants.Constants.DataConstants.QUESTIONS 
    				+ "(name, aid, question, points, is_mult, is_latex) " +
    				" VALUES(?,?,?,?,?,?) RETURNING qid";
			PreparedStatement stat = conn.prepareStatement(insert);
			stat.setString(1, name);
			stat.setInt(2, aid);
			stat.setString(3, question);
			stat.setInt(4, points);
			stat.setBoolean(5, isMult);
			stat.setBoolean(6, isLat);

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
	public List<TextAnswer> multAnswerQuestion(int questID) {
    	Connection conn = getConnection();
    	ArrayList<TextAnswer> at = new ArrayList<TextAnswer>();
    	try{
    		String query = "SELECT is_correct, answer FROM "
    	+ constants.Constants.DataConstants.ANSWERS + " where qid = ?;";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, questID);
    		ResultSet Rs = stat.executeQuery();
    		System.out.println(stat);
    		while (Rs.next()) {    			
    			Boolean isCorrect = Rs.getBoolean(1);
    			String answer = Rs.getString(2);    			
    			TextAnswer ans = new TextAnswer(questID, answer, isCorrect);
    			at.add(ans);
    		}
    		conn.close();
    		return at;
    		
    	}catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
		return null;
	}
	
	@Override
	public TextAnswer singleAnswerQuestion(int questID) {
    	Connection conn = getConnection();
    	try{
    		String query = "SELECT is_correct, answer FROM "
    	+ constants.Constants.DataConstants.ANSWERS + " where qid = ?;";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, questID);
    		ResultSet Rs = stat.executeQuery();    		
    		System.out.println(stat);
    		if (Rs.next()) {
    			Boolean isCorrect = Rs.getBoolean(1);
    			String answer = Rs.getString(2);    			
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

	@Override
	public void removeQuestionsForAssessments(int aid) {
		List<TextQuestion> questions = this.TextQuestions(aid);
		for (TextQuestion tq: questions) {
			this.removeQuestion(tq.getQid());
		}
	}

	@Override
	public void removeUserAnswers(int aid) {
		Connection conn = getConnection();
		try {
    		String deleteAns = "DELETE FROM " 
		+ constants.Constants.DataConstants.USERANSWERS + " WHERE aid = ?";
    		PreparedStatement stat = conn.prepareStatement(deleteAns);
    		stat.setInt(1, aid);
    		stat.executeUpdate();
    		
    		conn.close();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());  
		}	
	}

	@Override
	public boolean hasMultChoice(int aid) {
    	Connection conn = getConnection();
    	
    	try { 
    		
    		String query = "select exists(select 1 from " + constants.Constants.DataConstants.QUESTIONS 
    				+  " where aid = ? and is_mult = true)";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, aid);
    		
    		ResultSet Rs = stat.executeQuery(); 
    		Rs.next();
    		boolean res = Rs.getBoolean(1);
    		conn.close();
    		return res;
    		
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
    	return false;   
	}

	@Override
	public boolean hasTextQuestions(int aid) {
    	Connection conn = getConnection();
    	try { 
    		
    		String query = "select exists(select 1 from " + constants.Constants.DataConstants.QUESTIONS 
    				+  " where aid = ? and is_mult = false and is_latex = false)";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, aid);
    		
    		ResultSet Rs = stat.executeQuery(); 
    		Rs.next();
    		boolean res = Rs.getBoolean(1);
    		return res;
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
    	return false;   
	}

	@Override
	public boolean hasLatex(int aid) {
    	Connection conn = getConnection();
    	try { 
    		
    		String query = "select exists(select 1 from " + constants.Constants.DataConstants.QUESTIONS 
    				+  " where aid = ? and is_mult = false and is_latex = true)";
    		PreparedStatement stat = conn.prepareStatement(query);
    		stat.setInt(1, aid);
    		
    		ResultSet Rs = stat.executeQuery(); 
    		Rs.next();
    		boolean res = Rs.getBoolean(1);
    		conn.close();
    		return res;
    	} catch(Exception ex) {
    		System.out.print(ex.getMessage());    		
    	}
    	return false; 
	}

}
