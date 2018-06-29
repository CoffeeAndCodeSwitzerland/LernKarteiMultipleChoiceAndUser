package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import view.TestView;
import view.addTestView;

public class addTestController {
	
	private addTestView myMain;
	
	//Views
	@FXML private TextField signatur;
	@FXML private TextField testName;
	@FXML private Button btnGoToQuestions;
	@FXML private Pane createTest;
	@FXML private Pane createQuestion;
	@FXML private Button btnGoBack;
	@FXML private TextField question;
	@FXML private TextField answerOne;
	@FXML private TextField answerTwo;
	@FXML private TextField answerThree;
	@FXML private Button btnNextQuestion;
	@FXML private Label lblFrage;
	int questionNumber = 1;
	ArrayList<String> questions = new ArrayList<>();
	TextField[] fields = new TextField[4];
    String nameTest = " ";
    String signaturAutor = " ";
    	
	public void setMain(addTestView AddTestView) {
		this.myMain = AddTestView;
		createTest.setVisible(true);
		createQuestion.setVisible(false);
	    fields[0] = question;
	    fields[1] = answerOne;
	    fields[2] = answerTwo;
	    fields[3] = answerThree;
	}
	
	public void readTestProperties() {
		signaturAutor = signatur.getText();
		nameTest = testName.getText();
		System.out.print("Test-Name: "+nameTest);
		System.out.print("Signatur:"+signaturAutor);
		createTest.setVisible(false);
		createQuestion.setVisible(true);
		lblFrage.setText("Frage "+questionNumber);
	}
	
	public void goBack() {
		if (questionNumber > 1) {
			questionNumber--;
			lblFrage.setText("Frage "+questionNumber);
			
			//Show the values
			question.setText(""+questions.get((questionNumber*4)-4));
			answerOne.setText(""+questions.get((questionNumber*4)-3));
			answerTwo.setText(""+questions.get((questionNumber*4)-2));
			answerThree.setText(""+questions.get((questionNumber*4)-1));
		} else {
			createTest.setVisible(true);
			createQuestion.setVisible(false);
			signatur.setText(signaturAutor);
			testName.setText(nameTest);
		}
	}
	
	public void nextQuestion() {
		questionNumber++;
		lblFrage.setText("Frage "+questionNumber);
		
		for(int i = 0; i < fields.length; i++) {
			if(questions.get((questionNumber)*i+1)==" ") {questions.add(fields[i].getText());}//Read the values.
			fields[i].setText("");//Delete the values.
			//fields[i].setText(questions.get((questionNumber*4)-i+1));//Show the values which are existing
			//Show the values which are existing
			System.out.print(questions.size());
			if(questions.size() > questionNumber*6) {
				question.setText(""+questions.get((questionNumber*4)-4));
				answerOne.setText(""+questions.get((questionNumber*4)-3));
				answerTwo.setText(""+questions.get((questionNumber*4)-2));
				answerThree.setText(""+questions.get((questionNumber*4)-1));
			}
		}
	}
}

