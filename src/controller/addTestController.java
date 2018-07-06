package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.sun.scenario.effect.Effect.AccelType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modul.getStandartPath;

import view.addTestView;

/**
 * This class control the addTestView. This class create the test as a text
 * document.
 * 
 * @author Patrick Bauersfeld
 * @since 05.07.2018
 * @version 0.9
 */
public class addTestController {

	private addTestView myMain;

	public void setMain(addTestView AddTestView) {
		this.myMain = AddTestView;

		testProperties.setVisible(true);
		addQuestion.setVisible(false);

		lblInformation.setVisible(false);

		answerFields[0] = answer1;
		answerFields[1] = answer2;
		answerFields[2] = answer3;
	}

	@FXML
	private Pane testProperties;
	@FXML
	private Pane addQuestion;

	@FXML
	private Button btnSaveQuestion;
	@FXML
	private Button btnMain;

	@FXML
	private TextField testName;
	@FXML
	private TextField signature;
	@FXML
	private TextField question;
	@FXML
	private TextField answer1;
	@FXML
	private TextField answer2;
	@FXML
	private TextField answer3;
	@FXML
	private TextField point;

	@FXML
	private ComboBox boxQuestions;

	@FXML
	private Label lblQuestion;
	@FXML
	private Label lblInformation;

	@FXML
	private CheckBox chapter;

	Alert myInfo = new Alert(AlertType.INFORMATION);
	Alert myWarning = new Alert(AlertType.WARNING);
	Alert myError = new Alert(AlertType.ERROR);

	HashMap<String, String> questions = new HashMap<String, String>();
	HashMap<String, String> idQuestions = new HashMap<String, String>();
	HashMap<String, String> points = new HashMap<String, String>();

	TextField[] answerFields = new TextField[3];

	String[] questionsArray = new String[3];
	String[] answerArray = new String[9];

	int numberOfQuestions = 1;
	int loop = 0;

	String currentQuestion;
	String numberOfCurrentQuestions;

	boolean editingQuestion = false;
	boolean isChapterChecked = false;
	boolean testHaveError = false;
	boolean questionHaveError = false;

	/**
	 * Shows the question and answer fields.
	 */
	public void addQuestion() {

		// Clear the question an answer fields.
		lblQuestion.setText("Frage: " + numberOfQuestions);
		question.setText("");
		for (int i = 0; i < answerFields.length; i++) {
			answerFields[i].setText("");
			;
		}

		testProperties.setVisible(false);
		addQuestion.setVisible(true);
	}

	public void checkQuestion() {

		if (point.getText().equals("")) {
			error("Gib eine Punktzahl ein");
		}

		if (answer1.getText().equals("") || answer2.getText().equals("") || answer3.getText().equals("")) {
			error("Gib eine Antwort ein");
		}

		if (question.getText().equals("")) {
			error("Gib eine Frage ein!");
		}

	}

	public void saveQuestion() {
		questionHaveError = false;

		checkQuestion();
		if (questionHaveError == false) {

			if (editingQuestion == true) { // Check if the user edit the question or add a new question.

				saveEditedQuestion();// Override the old question.

			} else {

				if (isChapterChecked == true) { // Check if the user wants to ask 3 random questions with the same
												// meaning or one question.

					// Saves the questions in a array
					questionsArray[loop] = question.getText();

					// Saves the answers in a array.
					int count = 0;
					for (int i = loop * 3; i < loop * 3 + 3; i++) {
						answerArray[i] = answerFields[count].getText();
						count++;
					}

					// print the answer and questions array in console
					for (int i = 0; i < answerArray.length; i++) {
						if (i < questionsArray.length) {
							System.out.println(questionsArray[i]);
						}
						System.out.println(answerArray[i]);
					}

					// Check if loop smaller than 2, else you can't add a new question.
					if (loop < 2) {

						// Clear the fields... now the user can add a new questions.
						question.setText("");
						for (int i = 0; i < answerFields.length; i++) {
							answerFields[i].setText("");
						}

						loop++;
						lblQuestion.setText("Frage " + numberOfQuestions + "." + (loop + 1));// Shows the new number of
																								// question.

					} else {

						point.setText("");

						for (int o = 0; o < 3; o++) {

							currentQuestion = "Frage " + numberOfQuestions + "." + (o + 1) + ": " + questionsArray[o];
							System.out.println(currentQuestion);
							writeQuestionInBox();// Write the questions in the combobox so the user can edit them later.

							// Writes the questions in the hasmap.
							questions.put("question" + numberOfQuestions + "." + (o + 1), questionsArray[o]);
							idQuestions.put(currentQuestion, numberOfQuestions + "." + (o + 1));

							// Saves the answers in the hashmap.
							int number = 0;
							for (int q = o * 3; q < o * 3 + 3; q++) {
								questions.put("q" + numberOfQuestions + (".") + (o + 1) + "answer" + number,
										answerArray[q]);
								number++;
							}

							points.put(numberOfQuestions + "." + (o + 1), point.getText());// Saves the points in a
																							// hashmap.

							// Prints questions and answers in the console.
							System.out.println("Frage: " + questions.get("question" + numberOfQuestions));
							for (int i = 0; i < 3; i++) {
								System.out.println(
										"Antwort " + i + ":" + questions.get("q" + numberOfQuestions + "answer" + i));
							}

							System.out.println("New Question saved");
						}
						numberOfQuestions++;
						back();
						chapter();
						chapter.setSelected(false);
					}
				} else {
					/**
					 * Saves 3 times the same question. So can not come a random question in the
					 * test.
					 */
					// for(int xy = 0; xy < 3; xy++) {

					currentQuestion = "Frage " + numberOfQuestions + ": " + question.getText();
					writeQuestionInBox(); // Writes the questions in the combobox so you can edit them.

					/**
					 * Saves the questions.
					 */
					questions.put("question" + numberOfQuestions, question.getText());
					idQuestions.put(currentQuestion, "" + numberOfQuestions);

					/**
					 * Saves the answers.
					 */
					for (int i = 0; i < answerFields.length; i++) {
						questions.put("q" + numberOfQuestions + "answer" + i, answerFields[i].getText());
					}

					// Saves the points
					points.put(numberOfQuestions + "", point.getText());

					/**
					 * Print questions and answers.
					 */
					System.out.println("Frage: " + questions.get("question" + numberOfQuestions));
					for (int i = 0; i < 3; i++) {
						System.out
								.println("Antwort " + i + ":" + questions.get("q" + numberOfQuestions + "answer" + i));
					}

					numberOfQuestions++;
					System.out.println("New Question saved");

					back();
				}
			}
			// }

		}
	}

	/**
	 * Shows another overlay when the checkbox is checked.
	 */
	public void chapter() {
		isChapterChecked = !isChapterChecked;// changes the value of boolean

		if (isChapterChecked == true && editingQuestion == false) {// It changes only the overlay when the user not
																	// editing the question.

			lblQuestion.setText("Frage " + numberOfQuestions + ".1");
			btnSaveQuestion.setText("Weiter");

		} else if (isChapterChecked == false && editingQuestion == false) {

			lblQuestion.setText("Frage " + numberOfQuestions);
			btnSaveQuestion.setText("Speichern");

		}
	}

	public void writeQuestionInBox() {
		try {
			System.out.println("currentQuestion: " + currentQuestion);
			boxQuestions.getItems().addAll(currentQuestion);
			boxQuestions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					currentQuestion = newValue;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void back() {
		testProperties.setVisible(true);
		addQuestion.setVisible(false);
		lblInformation.setVisible(false);
		loop = 0;
	}

	public void editQuestion() {
		if (numberOfQuestions > 1) {
			editingQuestion = true;

			numberOfCurrentQuestions = idQuestions.get(currentQuestion);
			System.out.println("currentQuestion: Nr." + numberOfCurrentQuestions + " " + currentQuestion);

			testProperties.setVisible(false);
			addQuestion.setVisible(true);

			lblQuestion.setText("Frage: " + numberOfCurrentQuestions);

			/**
			 * Shows the values...
			 */
			question.setText(questions.get("question" + numberOfCurrentQuestions));
			for (int i = 0; i < answerFields.length; i++) {
				answerFields[i].setText(questions.get("q" + numberOfCurrentQuestions + "answer" + i));
			}
			point.setText(points.get(numberOfCurrentQuestions));
		}
	}

	/**
	 * Override the old value
	 */
	public void saveEditedQuestion() {

		// override question.
		questions.replace("question" + numberOfCurrentQuestions, question.getText());
		System.out.println(questions.get("question" + numberOfCurrentQuestions));

		/**
		 * override the answers.
		 */
		for (int i = 0; i < answerFields.length; i++) {
			questions.replace("q" + numberOfCurrentQuestions + "answer" + i, answerFields[i].getText());
		}

		// Saves the points
		points.replace(numberOfCurrentQuestions, point.getText());

		editingQuestion = false;
		back();
	}

	public void checkTest() {

		if (testName.getText().equals("")) {
			error("Der Test muss einen Namen haben!");
		}

		if (numberOfQuestions == 1) {
			error("Der Test muss mindestens eine Frage haben!");
		}

	}

	private void info(String info) {
		myInfo.setTitle("Information");
		myInfo.setHeaderText(info);
		myInfo.show();
	}

	private void warning(String warning) {
		myWarning.setTitle("Achtung");
		myWarning.setHeaderText(warning);
		myWarning.show();
	}

	private void error(String error) {
		testHaveError = true;
		questionHaveError = true;
		myError.setTitle("Fehler");
		myError.setHeaderText(error);
		myError.show();
	}

	public void createTest() {

		testHaveError = false;

		checkTest();// Check if the test have some empty fields.

		if (testHaveError == false) {
			PrintWriter writer;

			try {

				writer = new PrintWriter(getStandartPath.getStandartPath() + testName.getText() + ".txt", "UTF-8");// get
																													// the
																													// path
																													// and
																													// saves
																													// the
																													// document
																													// there.
				System.out.println(getStandartPath.getStandartPath());

				writer.println("[" + testName.getText() + "][" + signature.getText() + "]");// Write the test-name and
																							// the signature of author
																							// in the document.

				/**
				 * Writes the value in the right format in the document so that the class
				 * TestView can read it.
				 */
				for (int x = 0; x <= numberOfQuestions - 2; x++) {
					int pointNumber = 1;
					writer.println("(");

					if (questions.get("question" + (1 + x)) == null) {
						/**
						 * Print the questions which are differently.
						 */
						for (int i = 1 + x; i <= x + 3; i++) {
							writer.println(questions.get("question" + (x + 1) + "." + pointNumber) + "" + ","
									+ questions.get("q" + (x + 1) + "." + pointNumber + "answer0") + "" + ","
									+ questions.get("q" + (x + 1) + "." + pointNumber + "answer1") + "" + ","
									+ questions.get("q" + (x + 1) + "." + pointNumber + "answer2") + "" + ","
									+ points.get((x + 1) + "." + pointNumber) + ";");
							pointNumber++;
						}
					} else {
						for (int i = 1; i <= 3; i++) {
							/**
							 * Print the questions which are the same.
							 */
							writer.println(questions.get("question" + (1 + x)) + "" + ","
									+ questions.get("q" + (1 + x) + "answer0") + "" + ","
									+ questions.get("q" + (1 + x) + "answer1") + "" + ","
									+ questions.get("q" + (1 + x) + "answer2") + "" + "," + points.get((1 + x) + "")
									+ ";");
						}
					}
					writer.println(")");
				}

				/*
				 * Test-example [Testname][Signature] ( question1.1,answer1 (right
				 * answer),answer2,answer3;question1.2,answer1,answer2,answer3;question1.3,
				 * answer1,answer2,answer3 ) ( question2.1,answer1 (right
				 * answer),answer2,answer3;question2.2,answer1,answer2,answer3;question2.3,
				 * answer1,answer2,answer3 )
				 */

				writer.close();// Close the writer and create the document.

				System.out.println("Test-Name: " + testName.getText());
				System.out.println("Signature: " + signature.getText());
				System.out.println("New test created");

				lblInformation.setText("Test wurde erfolgreich erstellt...");
				lblInformation.setVisible(true);

				info("Der Test wurde erfolgreich erstellt!");

			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void mainMenu() {
		Stage stage = (Stage) btnMain.getScene().getWindow();
		stage.close();
	}
}