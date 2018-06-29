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

public class MainController {
	
	Greetings greet = new Greetings();
	TestView tv;
	StatView sv;
	Database db = new Database();
	
	private MainView tmain;	
	
	Text greetings = new Text(greet.createGreeting());
	
	@FXML
	Button btnMultipleChoiceAddQuestions;
	@FXML
	Text txtLabel;
	
	
	public void setMain(MainView mainView) {
		this.tmain = mainView;
		txtLabel.setText(greet.createGreeting());
		db.createTables();
		 
		FadeTransition fadeTran = new FadeTransition(Duration.millis(2000), txtLabel);
		fadeTran.setFromValue(1.0);
		fadeTran.setToValue(0.0);
		fadeTran.setCycleCount(Timeline.INDEFINITE);
		fadeTran.setAutoReverse(true);
		fadeTran.play();
	}
	
	private void textEffect() {
		for(int i = 0; i > -1; i ++) {
			double opacity = 1;
			boolean plusOrMinus = false;
			if(plusOrMinus == false) {
				for(double x = 1; x > 0; x-=0.01) {
					opacity -= 0.01;
					txtLabel.setOpacity(opacity);
					System.out.println("Minus");
					System.out.println(plusOrMinus);
					System.out.println(opacity);
				}
				plusOrMinus = true;
			}
			else if(plusOrMinus == true) {
				for(double x = 0.01; x > 0; x+=0.01) {
					opacity += 0.01;
					txtLabel.setOpacity(opacity);
					System.out.print("Plus");
				}
				plusOrMinus = false;
			}
		}
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
