package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modul.Greetings;
import view.MainView;
import view.StatView;
import view.TestView;

public class MainController {
	
	Greetings greet = new Greetings();
	TestView tv;
	StatView sv;
	
	private MainView tmain;	
	
	Text greetings = new Text(greet.createGreeting());
	
	@FXML
	Button btnMultipleChoiceAddQuestions;
	
	public void setMain(MainView mainView) {
		this.tmain = mainView;	
	}
	
	
	public void OpenStats() {
		sv = new StatView();
		sv.start();
	}
	
	public void StartTest() {
		tv = new TestView();
		tv.window();
	}

	public void CreatNewTest() {
		Stage stage = (Stage) btnMultipleChoiceAddQuestions.getScene().getWindow();
	    stage.close();
	}

}
