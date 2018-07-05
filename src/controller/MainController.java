package controller;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modul.Database;
import modul.Greetings;
import view.MainView;
import view.StatView;
import view.TestView;
import view.addTestView;

/**
 * Handels the main view
 * 
 * @author GiBr03
 * @version 1.0
 * @since 5.7.2018
 */
public class MainController {

	Greetings greet = new Greetings();
	TestView tv;
	StatView sv;
	addTestView atv;
	Database db = new Database();

	Text greetings = new Text(greet.createGreeting());

	@FXML
	Button btnMultipleChoiceAddQuestions;
	@FXML
	Text txtLabel;

	public void setMain(MainView mainView) {
		txtLabel.setText(greet.createGreeting());
		db.createTables();

		//Makes the text appear and disappear
		FadeTransition fadeTran = new FadeTransition(Duration.millis(2000), txtLabel);
		fadeTran.setFromValue(1.0);
		fadeTran.setToValue(0.0);
		fadeTran.setCycleCount(Timeline.INDEFINITE);
		fadeTran.setAutoReverse(true);
		fadeTran.play();
	}

	/**
	 * Opens the statistics
	 */
	public void OpenStats() {
		sv = new StatView();
		sv.start();
	}

	/**
	 * Opens the test windows
	 */
	public void StartTest() {
		tv = new TestView();
		tv.window();
	}

	/**
	 * Opens the create new test window
	 */
	public void CreatNewTest() {
		atv = new addTestView();
		atv.showAddTest();
	}

}
