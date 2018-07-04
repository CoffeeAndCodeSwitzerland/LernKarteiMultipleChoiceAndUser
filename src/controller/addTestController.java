package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import modul.getStandartPath;

import view.addTestView;

public class addTestController {
	
	private addTestView myMain;
	
	public void setMain(addTestView AddTestView) {
		this.myMain = AddTestView;
		
		testProperties.setVisible(true);
		addQuestion.setVisible(false);
		
		fields[0] = answer1;
		fields[1] = answer2;
		fields[2] = answer3;
	}
	
	@FXML private Pane testProperties;
	@FXML private Pane addQuestion;
	@FXML private Pane editQuestion;
	@FXML private Button btnAddQuestion;
	@FXML private Button btnSaveQuestion;
	@FXML private Button btnCreateTest;
	@FXML private TextField testName;
	@FXML private TextField signature;
	@FXML private TextField question;
	@FXML private TextField answer1;
	@FXML private TextField answer2;
	@FXML private TextField answer3;
	@FXML private ComboBox boxQuestions;
	@FXML private Label lblQuestion;
	HashMap<String, String> questions = new HashMap<String, String>();
	HashMap<String, Integer> idQuestions = new HashMap<String, Integer>();
	TextField[] fields = new TextField[3];
	int numberOfQuestions = 1;
	String currentQuestion;
	int numberOfCurrentQuestions;
	boolean editing = false;
	
	public void addQuestion() {		
		
		//Set the textfields and labels to default
		lblQuestion.setText("Frage: "+numberOfQuestions);
		question.setText("");
		for(int i = 0; i < fields.length; i++) {
			fields[i].setText("");;
		}
		
		testProperties.setVisible(false);
		addQuestion.setVisible(true);
	}
	
	public void saveQuestion() {
		if(editing == true) {
			saveEditedQuestion();
		} else {
			questions.put("question"+numberOfQuestions, question.getText());
			idQuestions.put("Frage: "+numberOfQuestions+", "+question.getText(), numberOfQuestions);
			for(int i = 0; i < fields.length; i++) {
				questions.put("q"+numberOfQuestions+"answer"+i, fields[i].getText());
			}
			
			
			System.out.println("Frage: "+questions.get("question"+numberOfQuestions));
			for(int i = 0; i < 3; i++) {
				System.out.println("Antwort "+i+":"+questions.get("q"+numberOfQuestions+"answer"+i));
			}
			currentQuestion = "Frage: "+numberOfQuestions+", "+question.getText();
			writeQuestionInBox();	
			numberOfQuestions++;
			System.out.println("New Question saved");
		}
		testProperties.setVisible(true);
		addQuestion.setVisible(false);
	}
	
	public void writeQuestionInBox() {
		try {
			System.out.println("currentQuestion: "+currentQuestion);
			boxQuestions.getItems().addAll(currentQuestion);
			boxQuestions.getSelectionModel().selectedItemProperty()
	                    .addListener(new ChangeListener<String>() {
	                        public void changed(ObservableValue<? extends String> observable,
	                                            String oldValue, String newValue) {
	                        	currentQuestion = newValue;
	                        }
	                    });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void back() {
		testProperties.setVisible(true);
		addQuestion.setVisible(false);
	}
	
	public void editQuestion() {
		editing = true;
		numberOfCurrentQuestions = idQuestions.get(currentQuestion);
		System.out.println("currentQuestion: "+currentQuestion);
		
		testProperties.setVisible(false);
		addQuestion.setVisible(true);
		
		lblQuestion.setText("Frage: "+numberOfCurrentQuestions);
		question.setText(questions.get("question"+numberOfCurrentQuestions));
		for(int i = 0; i < fields.length; i++) {
			fields[i].setText(questions.get("q"+numberOfCurrentQuestions+"answer"+i));
		}
	}
	
	public void saveEditedQuestion() {
		questions.replace("question"+numberOfCurrentQuestions, question.getText());
		for(int i = 0; i < fields.length; i++) {
			questions.replace("q"+numberOfCurrentQuestions+"answer"+i, fields[i].getText());
		}
		editing = false;
	}
	
	public void createTest() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(getStandartPath.getStandartPath()+testName.getText()+".txt", "UTF-8");
			writer.println("["+testName.getText()+"]["+signature.getText()+"]");
			System.out.println(getStandartPath.getStandartPath());
			
			for(int x = 0; x <= numberOfQuestions-2; x += 3) {
				writer.println("(");
				for(int i = 1+x; i <= x+3; i++) {
					writer.println(questions.get("question"+i)+""
							+ ","+questions.get("q"+i+"answer0")+""
							+ ","+questions.get("q"+i+"answer1")+""
							+ ","+questions.get("q"+i+"answer2")+",1;");
				}	
				writer.println(")");	
			}
			
			for(int i = 0; i < fields.length; i++) {
				fields[i].setText("");//Delete the values.
			}
			
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}

