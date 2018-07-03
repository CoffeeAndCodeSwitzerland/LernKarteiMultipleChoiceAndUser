package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
		themeProperties.setVisible(false);
		addQuestion.setVisible(false);
	}
	
	PrintWriter writer;
	@FXML private TextField signature;
	@FXML private TextField testName;
	@FXML private TextField themeName;
	@FXML private Pane testProperties;
	@FXML private Pane themeProperties;
	@FXML private Pane addQuestion;
	HashMap<String, HashMap> themeArray = new HashMap<String, HashMap>();
	
	
	public void createTest() {
		try {
			writer = new PrintWriter(getStandartPath.getStandartPath()  + testName.getText() + ".txt", "UTF-8");
			writer.println("["+testName.getText()+"]["+signature.getText()+"]");
			
			//Hier sollten noch die restlichen Test-Daten eingefügt werden.
			
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void createTheme() {
		testProperties.setVisible(false);
		themeProperties.setVisible(true);
		addQuestion.setVisible(false);
	}
	
	public void saveTheme() {
		String arrayName = themeName.getText();
		themeArray.put(themeName.getText(), new HashMap<String, ArrayList>());
		System.out.println(themeArray.get(themeName.getText()));
	}
	
	public void editTheme() {
		
	}
	
	public void addQuestions() {
		testProperties.setVisible(false);
		themeProperties.setVisible(false);
		addQuestion.setVisible(true);
	}
}
	
	/*
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
	TextField[] fields = new TextField[4];
    String nameTest = " ";
    String signaturAutor = " ";
    PrintWriter writer;
    boolean testPropertiesWriten = false;
    	
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
			
			
			
		} else {
			createTest.setVisible(true);
			createQuestion.setVisible(false);
			signatur.setText(signaturAutor);
			testName.setText(nameTest);
		}
	}
	
	public void writeTestProperties() {
		try {
			writer = new PrintWriter(getStandartPath.getStandartPath()  + nameTest + ".txt", "UTF-8");
			writer.println("["+nameTest+"]["+signaturAutor+"]");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void nextQuestion(){
		questionNumber++;
		lblFrage.setText("Frage "+questionNumber);
		
		if(testPropertiesWriten == false) {
			writeTestProperties();		
			testPropertiesWriten = true;
		}
				
		writer.println("(");
		writer.println(question.getText()+""
				+ ","+answerOne.getText()+""
				+ ","+answerTwo.getText()+""
				+ ","+answerThree.getText()+";");
		writer.println(")");
		
		for(int i = 0; i < fields.length; i++) {
			fields[i].setText("");//Delete the values.
		}
	}
		
		/*
		for(int i = 0; i < fields.length; i++) {
			if(questions.get((questionNumber)*i+1)==" ") {questions.add(fields[i].getText());}//Read the values.
			fields[i].setText("");//Delete the values.
			//fields[i].setText(questions.get((questionNumber*4)-i+1));//Show the values which are existing
			//Show the values which are existing
			System.out.print(questions.size());
			/*if(questions.size() > questionNumber*6) {
				question.setText(""+questions.get((questionNumber*4)-4));
				answerOne.setText(""+questions.get((questionNumber*4)-3));
				answerTwo.setText(""+questions.get((questionNumber*4)-2));
				answerThree.setText(""+questions.get((questionNumber*4)-1));
			}
		}
	
	public void finishTest() {
		writer.close();
	}*/


