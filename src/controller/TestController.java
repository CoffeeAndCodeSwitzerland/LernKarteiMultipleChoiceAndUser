package controller;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
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

	getStandartPath path = new getStandartPath();

	// question, answer and points
	String frage;
	String antwort1;
	String antwort2;
	String antwort3;
	int punkte;
	int gesamtpunkte;
	int richtigepunkte = 0;

	/*
	 * set the layout to choose a test and get the names of the tests
	 */
	public void setMain(TestView testView) {
		this.tmain = testView;
		check.setVisible(true);
		question.setVisible(false);
		showTrueAnswer.setVisible(false);
		showFalseAnswer.setVisible(false);

		File folder = new File(getStandartPath.getStandartPath());
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				testlist.getItems().add(listOfFiles[i].getName());
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
	public void getQuestion() {
		
		random = rnd.nextInt(2);
		
		for (int i = 0; i < getTest.questionsArrayList.size(); i++) {
			String[] tempArrayString = getTest.questionsArrayList.get(i);
			String tempString = tempArrayString[random];
			System.out.println(tempString);

			String[] parts = tempString.split(",");
			System.out.println("***:"+tempString);
			frage = parts[0];
			antwort1 = parts[1];
			antwort2 = parts[2];
			antwort3 = parts[3];
			String punktestring = parts[4];

			punkte = Integer.parseInt(punktestring);
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

		System.out.println(testName);
		System.out.println(frage);
		System.out.println(antwort1);
		System.out.println(antwort2);
		System.out.println(antwort3);

		getQuestion();

		testfrage.setText(frage);
		ersteAntwort.setText(antwort1);
		zweiteAntwort.setText(antwort2);
		driteAntwort.setText(antwort3);

		ToggleGroup group = new ToggleGroup();
		ersteAntwort.setToggleGroup(group);
		zweiteAntwort.setToggleGroup(group);
		driteAntwort.setToggleGroup(group);
		//ersteAntwort.setSelected(true);
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
		if (answerChecked == true) {
			showQuestion();
			System.out.println("next question");
			nextquestion.setText("Antwort überprüfen");
			answerChecked = false;
		} else {
			checkAnswer();
			nextquestion.setText("Nächste Frage");
			answerChecked = true;
		}
		
	}

	/*
	 * look if the answer is corect or not
	 */
	public void checkAnswer() {
		if (ersteAntwort.isSelected()) {
			if (ersteAntwort.getText() == antwort1) {
				System.out.println(antwort1);
				showTrueAnswer.setVisible(true);
				richtigepunkte += punkte;
			} else {
				showFalseAnswer.setVisible(true);
			}
		} else if (zweiteAntwort.isSelected()) {
			if (zweiteAntwort.getText() == antwort1) {
				System.out.println(antwort1);
				showTrueAnswer.setVisible(true);
				richtigepunkte += punkte;
			} else {
				showFalseAnswer.setVisible(true);
			}
		} else if (driteAntwort.isSelected()) {
			if (driteAntwort.getText() == antwort1) {
				System.out.println(antwort1);
				showTrueAnswer.setVisible(true);
				richtigepunkte += punkte;
			} else {
				showFalseAnswer.setVisible(true);
			}
		}
		gesamtpunkte += punkte;

		System.out.println(gesamtpunkte);
		System.out.println(richtigepunkte);

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
}
