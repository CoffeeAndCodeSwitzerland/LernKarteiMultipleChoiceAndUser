package controller;

import java.io.File;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.TestView;
import modul.getStandartPath;
import modul.GetTest;
import modul.WritePlayerData;
import modul.ReadPlayerData;

/*
 * This class controls the action of the layout_chosecheck class.
 * @since 02-07-18
 * @version 1.0
 * @author Mischii 
 */

public class TestController {

	private TestView tmain;

	public static String testName = "startwert";

	boolean answerChecked = false;

	GetTest getTest = new GetTest();
	Random rnd = new Random();
	int random = 0;

	@FXML
	Pane check;

	@FXML
	Pane showTrueAnswer;

	@FXML
	Pane showFalseAnswer;

	@FXML
	StackPane question;

	@FXML
	Label testfrage;

	@FXML
	RadioButton ersteAntwort;

	@FXML
	RadioButton zweiteAntwort;

	@FXML
	RadioButton driteAntwort;

	@FXML
	Button btnhm;

	@FXML
	ComboBox<String> testlist;

	@FXML
	Button btnts;

	@FXML
	Button nextquestion;

	@FXML
	Button finishedTest;

	@FXML
	Label score;

	@FXML
	Pane showScore;

	getStandartPath path = new getStandartPath();
	ReadPlayerData readplData = new ReadPlayerData();
	WritePlayerData writeplData = new WritePlayerData();
	
	// question, answer and points
	String frage;
	String antwort1;
	String antwort2;
	String antwort3;
	int punkte;
	int gesamtpunkte;
	int richtigepunkte = 0;

	Integer questionCounter = 0;
	Integer arraySize;
	Boolean firstTime = true;
	Boolean a1 = false;
	Boolean a2 = false;
	Boolean a3 = false;
	Random rand = new Random();

	String fileName;
	String txt;

	/*
	 * set the layout to choose a test and get the names of the tests
	 */
	public void setMain(TestView testView) {
		this.tmain = testView;
		check.setVisible(true);
		question.setVisible(false);
		showTrueAnswer.setVisible(false);
		showFalseAnswer.setVisible(false);
		showScore.setVisible(false);

		File folder = new File(getStandartPath.getStandartPath());
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String tempStringName = "";
				tempStringName = listOfFiles[i].getName();
				fileName = tempStringName.substring(0, tempStringName.indexOf("."));
				System.out.println("File " + fileName);
				testlist.getItems().add(fileName);
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		btnts.setVisible(false);

		// get the name of the choose test, testName is the actual test
		testlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				testName = newValue;
			}
		});

	}

	/*
	 * close this window and goes back to the MainView
	 */
	public void showMainmenue() {
		Stage stage = (Stage) btnhm.getScene().getWindow();
		stage.close();
	}

	/*
	 * get the questions out of the file and split the string
	 */
	public String[] getQuestionsArray() {
		if (firstTime == true) {
			arraySize = getTest.questionsArrayList.size();
		}
		String[] tempArrayString = null;
		for (int i = 0; i < getTest.questionsArrayList.size(); i++) {
			tempArrayString = getTest.questionsArrayList.get(questionCounter);
		}
		return tempArrayString;
	}

	public void splitQuestions() {
		String tempString = getQuestionsArray()[rand.nextInt(3)];

		String[] parts = tempString.split(",");
		frage = parts[0];
		antwort1 = parts[1];
		antwort2 = parts[2];
		antwort3 = parts[3];
		String punktestring = parts[4];
		try {
			punkte = Integer.parseInt(punktestring);
		} catch (Exception e) {
			System.out.println(">>>Error: " + e);
		}
	}

	/**
	 * Arranges the questions randomly
	 */
	public void arrangeQuestions() {
		String[] tempString = { antwort1, antwort2, antwort3 };
		String[] answersString = new String[3];
		Integer[] answersPosition = new Integer[3];
		for (int i = 0; i < 3; i++) {
			System.out.println("Int i: " + i);
			Integer random;// Is the postition of the answer will be generated randomly
			random = rand.nextInt(3);
			while (random == answersPosition[0] || random == answersPosition[1] || random == answersPosition[2]) {// Checks
																													// if
																													// the
																													// place
																													// is
																													// already
																													// occupied
				random = rand.nextInt(3);
			}
			answersPosition[i] = random;
			answersString[i] = tempString[answersPosition[i]];
			/*
			 * Uncomment to better understand the concept System.out.println("Positions: " +
			 * Arrays.toString(answersPosition)); System.out.println("Answers: " +
			 * Arrays.toString(answersString));
			 */
			ersteAntwort.setText(answersString[0]);
			zweiteAntwort.setText(answersString[1]);
			driteAntwort.setText(answersString[2]);
		}
	}

	/*
	 * set the layout to answer a question
	 */
	public void showQuestion() {
		showTrueAnswer.setVisible(false);
		showFalseAnswer.setVisible(false);

		GetTest.startClass(testName);
		check.setVisible(false);
		question.setVisible(true);
		getQuestionsArray();
		splitQuestions();
		arrangeQuestions();

		testfrage.setText(frage);

		ToggleGroup group = new ToggleGroup();
		ersteAntwort.setToggleGroup(group);
		zweiteAntwort.setToggleGroup(group);
		driteAntwort.setToggleGroup(group);

		ersteAntwort.setSelected(false);
		zweiteAntwort.setSelected(false);
		driteAntwort.setSelected(false);

	}

	/*
	 * goes back to choose a other test
	 */
	public void showCheck() {
		question.setVisible(false);
		check.setVisible(true);
	}

	/*
	 * show the test, choose one of it
	 */
	public void chooseATest() {
		btnts.setVisible(true);

	}

	/*
	 * show the next question
	 */
	public void setNextQuestion() {
		if (ersteAntwort.isSelected() | zweiteAntwort.isSelected() | driteAntwort.isSelected()) {
			if (answerChecked == true) {
				if (questionCounter < arraySize - 1) {
					System.out.println(
							"QCounter: " + questionCounter + "ArrayList: " + getTest.questionsArrayList.size());
					questionCounter += 1;
					showQuestion();
					nextquestion.setText("Antwort überprüfen");
					answerChecked = false;
					getTest.questionsArrayList.clear();
				} else {
					check.setVisible(false);
					question.setVisible(false);
					showTrueAnswer.setVisible(false);
					showFalseAnswer.setVisible(false);
					showScore.setVisible(true);
					score.setText("Sie haben " + richtigepunkte + " von " + gesamtpunkte + " Punkten erreicht!");
					writeplData.writePlayerData("pointstotal",Integer.toString(Integer.parseInt(readplData.getPlayerInfo("pointstotal")) + gesamtpunkte));
					writeplData.writePlayerData("pointsreceived",Integer.toString(Integer.parseInt(readplData.getPlayerInfo("pointsreceived")) + richtigepunkte));
					writeplData.writePlayerData("testsplayed",Integer.toString(Integer.parseInt(readplData.getPlayerInfo("testsplayed")) + 1));
				}
			} else {
				checkAnswer();
				nextquestion.setText("Nächste Frage");
				answerChecked = true;
			}
		} else {// TODO: make that this apears
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Fehler");
			alert.setHeaderText(null);
			alert.setContentText("Bitte wählen Sie eine Antwort aus");
		}

	}

	/*
	 * look if the answer is correct or not
	 */
	public void checkAnswer() {
		if (ersteAntwort.isSelected()) {
			if (ersteAntwort.getText() == antwort1) {
				showTrueAnswer.setVisible(true);
				richtigepunkte += punkte;
			} else {
				showFalseAnswer.setVisible(true);
			}
		} else if (zweiteAntwort.isSelected()) {
			if (zweiteAntwort.getText() == antwort1) {
				showTrueAnswer.setVisible(true);
				richtigepunkte += punkte;
			} else {
				showFalseAnswer.setVisible(true);
			}
		} else if (driteAntwort.isSelected()) {
			if (driteAntwort.getText() == antwort1) {
				showTrueAnswer.setVisible(true);
				richtigepunkte += punkte;
			} else {
				showFalseAnswer.setVisible(true);
			}
		}
		gesamtpunkte += punkte;

	}

	/*
	 * bring you back to the main menu when the test is finished
	 */
	public void finishedTest() {
		Stage stage = (Stage) btnhm.getScene().getWindow();
		stage.close();
	}

	// Only getter and setter from here on
	/**
	 * @return the testName
	 */
	public static String getTestName() {
		return testName;
	}

	/**
	 * @param testName
	 *            the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * 
	 */
	public void getQuestionsArraySize() {
		arraySize = getTest.questionsArrayList.size();
	}
}
