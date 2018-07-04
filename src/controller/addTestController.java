package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	@FXML private Button btnAddQuestion;
	@FXML private Button btnSaveQuestion;
	@FXML private Button btnCreateTest;
	@FXML private Button btnEditQuestion;
	@FXML private TextField testName;
	@FXML private TextField signature;
	@FXML private TextField question;
	@FXML private TextField answer1;
	@FXML private TextField answer2;
	@FXML private TextField answer3;
	@FXML private ComboBox boxQuestions;
	@FXML private Label lblQuestion;
	@FXML private CheckBox multipleChoice;
	HashMap<String, String> questions = new HashMap<String, String>();
	HashMap<String, Integer> idQuestions = new HashMap<String, Integer>();
	TextField[] fields = new TextField[3];
	String[] questionsArray = new String[3];
	String[] answerArray = new String[9];
	int numberOfQuestions = 1;
	String currentQuestion;
	int numberOfCurrentQuestions;
	boolean editingQuestion = false;
	int loop = 0;
	boolean isMultipleChoiceChecked = false;
	
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
		
		if(editingQuestion == true) { //Check if the user edit the question or add a new question
			saveEditedQuestion();
		} else {
			
			if(isMultipleChoiceChecked == true) { //Check if the user wants to ask 3 random questions which have the same meaning
				
				//Saves the questions in a array
				questionsArray[loop]=question.getText(); 
				
				/**
				 * Saves the answers in a array.
				 */
				int count = 0;
				for(int i = loop*3; i < loop*3+3; i++) {
					answerArray[i] = fields[count].getText();
					count++;
				}
				
				/**
				 * print the answer and questions array.
				 */
				for(int i = 0; i < answerArray.length; i++) {
					if(i < questionsArray.length) {
						System.out.println(questionsArray[i]);	
					}	
					System.out.println(answerArray[i]);
				}
				
				/**
				 * Check if loop smaller than 2, else you can't add a new question.
				 */
				if(loop < 2) {
					
					/**
					 * Clear the fields.. now you can add new questions.
					 */
					question.setText("");
					for(int i = 0; i < fields.length; i++) {
						fields[i].setText("");
					}
					
					loop++;
					lblQuestion.setText("Frage "+numberOfQuestions+"."+(loop+1));//Shows the new number of question.
					
					/**
					 * Saves the questions in the questions hashmap.
					 */
				} else {
					
					/**
					 * loop which replays 3 times. (Saves all three questions and answers)
					 */
					for(int o = 0; o < 3; o++) {
						
						currentQuestion = "Frage "+numberOfQuestions+": "+questionsArray[o];
						System.out.println(currentQuestion);
						writeQuestionInBox();//Writes the questions in the combobox so you can edit them.
						
						questions.put("question"+numberOfQuestions, questionsArray[o]);
						idQuestions.put(currentQuestion, numberOfQuestions);
						
						/**
						 * Saves the answers.
						 */
						int number = 0;
						for(int q = o*3; q < o*3+3; q++) {
							questions.put("q"+numberOfQuestions+"answer"+number, answerArray[q]);
							number++;
						}
						//04.07.2018 13:36 number = 0;
						
						/**
						 * Print questions and answers.
						 */
						System.out.println("Frage: "+questions.get("question"+numberOfQuestions));
						for(int i = 0; i < 3; i++) {
							System.out.println("Antwort "+i+":"+questions.get("q"+numberOfQuestions+"answer"+i));
						}
						
						numberOfQuestions++;
						System.out.println("New Question saved");
					}
					loop = 0;
					back();
					multipleChoice();
					multipleChoice.setSelected(false);
				}
				
				/**
				 * Saves 3 times the same question. So can not come a random question in the test.
				 */
			} else {
				/**
				 * Makes a loop which repeat 3 times
				 */
			 for(int xy = 0; xy < 3; xy++) {
				 
					currentQuestion = "Frage "+numberOfQuestions+": "+question.getText();
					writeQuestionInBox();	//Writes the questions in the combobox so you can edit them.
				 
				 	/**
				 	 * Saves the questions.
				 	 */
					questions.put("question"+numberOfQuestions, question.getText());
					idQuestions.put(currentQuestion, numberOfQuestions);
					
					/**
					 * Saves the answers.
					 */
					for(int i = 0; i < fields.length; i++) {
						questions.put("q"+numberOfQuestions+"answer"+i, fields[i].getText());
					}
					
					/**
					 * Print questions and answers.
					 */
					System.out.println("Frage: "+questions.get("question"+numberOfQuestions));
					for(int i = 0; i < 3; i++) {
						System.out.println("Antwort "+i+":"+questions.get("q"+numberOfQuestions+"answer"+i));
					}
					
					numberOfQuestions++;
					System.out.println("New Question saved");
					
					back();
			 }
			}
		}
	}
	
	/**
	 * Shows another overlay when the checkbox is checked.
	 */
	public void multipleChoice() {
		isMultipleChoiceChecked = !isMultipleChoiceChecked;//changes the value of boolean
		
		if(isMultipleChoiceChecked == true && editingQuestion == false) {//It changes only the overlay when the user not editing the question.
			
			lblQuestion.setText("Frage "+numberOfQuestions+".1");
			btnSaveQuestion.setText("Weiter");
			
		} else if (isMultipleChoiceChecked == false && editingQuestion == false){
			
			lblQuestion.setText("Frage "+numberOfQuestions);
			btnSaveQuestion.setText("Speichern");
			
		}
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
		loop = 0;
	}
	
	public void editQuestion() {
		
		editingQuestion = true;
		
		numberOfCurrentQuestions = idQuestions.get(currentQuestion);
		System.out.println("currentQuestion: Nr."+numberOfCurrentQuestions+" "+currentQuestion);
		
		testProperties.setVisible(false);
		addQuestion.setVisible(true);
		
		lblQuestion.setText("Frage: "+numberOfCurrentQuestions);
		
		/**
		 * Shows the values...
		 */
		question.setText(questions.get("question"+numberOfCurrentQuestions));
		for(int i = 0; i < fields.length; i++) {
			fields[i].setText(questions.get("q"+numberOfCurrentQuestions+"answer"+i));
		}
	}
	
	/**
	 * Override the old value 
	 */
	public void saveEditedQuestion() {
		
		//override question.
		questions.replace("question"+numberOfCurrentQuestions, question.getText());
		System.out.println(questions.get("question"+numberOfCurrentQuestions));
		
		/**
		 * override the answers.
		 */
		for(int i = 0; i < fields.length; i++) {
			questions.replace("q"+numberOfCurrentQuestions+"answer"+i, fields[i].getText());
		}
		
		editingQuestion = false;
		back();
	}
	
	public void createTest() {
		
		PrintWriter writer;
		
		try {
			
			writer = new PrintWriter(getStandartPath.getStandartPath()+testName.getText()+".txt", "UTF-8");//get the path and saves the document there.
			System.out.println(getStandartPath.getStandartPath());
			
			writer.println("["+testName.getText()+"]["+signature.getText()+"]");//Write the test-name and the signature of author in the document.
			
			/**
			 * Writes the value in the right format in the document so that the class TestView can read it.
			 */
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
			
				/* Test-example
				[Testname][Signature]
				(
				question1.1,answer1 (right answer),answer2,answer3;question1.2,answer1,answer2,answer3;question1.3,answer1,answer2,answer3
				)
				(
				question2.1,answer1 (right answer),answer2,answer3;question2.2,answer1,answer2,answer3;question2.3,answer1,answer2,answer3
				)
				*/
			
			/*04.07.2018 14:03 for(int i = 0; i < fields.length; i++) {
				fields[i].setText("");//Delete the values.
			}*/
			
			writer.close();//Close the writere and create the document.
			//you can find the defaultpath by creating the test in the console as output or: C:\Users\%username%\git\LernKarteiMultipleChoiceAndUser\src\Tests\
			//It is possible that the path have changed or the git repository is not on the sam place on your device...
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}

