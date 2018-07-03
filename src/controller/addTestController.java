package controller;

import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import modul.getStandartPath;*/
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
			
			
			System.out.println(questions.get("question"+numberOfQuestions));
			for(int i = 0; i < 3; i++) {
				System.out.println(questions.get("q"+numberOfQuestions+"answer"+i));
			}
			currentQuestion = "Frage: "+numberOfQuestions+", "+question.getText();
			writeQuestionInBox();	
			numberOfQuestions++;
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
	
	} 
}

/*
PrintWriter writer;
@FXML private TextField signature;
@FXML private TextField testName;
@FXML private Pane testProperties;
@FXML private Pane addQuestion;
@FXML private ComboBox boxTheme;
@FXML private Button btnEditTheme;

//HashMap<String, HashMap> themeArray = new HashMap<String, HashMap>();
String value = "";

HashMap<String, HashMap<String, String>> themeArray = new HashMap<String, HashMap<String,String>>();
HashMap<String, String> questions = new HashMap<String, String>();

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

public void addQuestions() {
	testProperties.setVisible(false);
	addQuestion.setVisible(true);
	
	String name = "question1";
	
	//HashMap<String, String> name = new HashMap<String, String>();
	HashMap<String, String> question2 = new HashMap<String, String>();
	HashMap<String, String> question3 = new HashMap<String, String>();
	
	questions.put("question", "was ist cola?");
	questions.put("answer1", "reis");
	questions.put("answer2", "döner");
	questions.put("answer3", "gewürz");
	
	
	//question1.put("question", "was ist cola?");
	//question1.put("answer1", "reis");
	//question1.put("answer2", "döner");
	//question1.put("answer3", "gewürz");
	
	question3.put("question", "was ist cola?");
	question3.put("answer1", "reis");
	question3.put("answer2", "döner");
	question3.put("answer3", "gewürz");
	
	themeArray.put("question1", questions);
	//themeArray.put("question2", question1);
	themeArray.put("question3", question3);
	
	String value = ((HashMap<String, String>)themeArray.get("question1")).get("answer1").toString();
	System.out.println("Retreived value is : " + value);
	
	String value2 = ((HashMap<String, String>)themeArray.get("question2")).get("answer1").toString();
	System.out.println("Retreived value is : " + value2);
	
	String value3 = ((HashMap<String, String>)themeArray.get("question3")).get("answer1").toString();
	System.out.println("Retreived value is : " + value3);
}


public void writeThemeInBox() {
	try {
		System.out.println("Value: "+value);
			boxTheme.getItems().addAll(value);
            boxTheme.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> observable,
                                            String oldValue, String newValue) {
                            value = newValue;
                        }
                    });
	} catch(Exception e) {
		e.printStackTrace();
	}
}

public void editTheme() {
	testProperties.setVisible(false);
	addQuestion.setVisible(false);
	
	System.out.println("Value:"+value);
}

public void backToTestProperties() {
	testProperties.setVisible(true);
	addQuestion.setVisible(false);
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


