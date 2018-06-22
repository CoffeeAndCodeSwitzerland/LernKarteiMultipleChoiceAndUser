package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.TestView;

/*
 * this class controls the action of the window from the TestView class
 * here you see what happen when you click on the button
 */

public class Controller {
	
	private TestView tmain;	
	
	@FXML
	Pane check;
	
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
	
	/*
	 * set the layout to choose a test
	 */
	public void setMain(TestView testView) {
		this.tmain = testView;		
		check.setVisible(true);
		question.setVisible(false);
		
	}
	
	/*
	 * close this window and goes back to the MainView
	 */
	public void showMainmenue() {
		Stage stage = (Stage) btnhm.getScene().getWindow();
	    stage.close();
	}
	
	/*
	 * set the layout to answer a question
	 */
	public void showQuestion() {
		check.setVisible(false);
		question.setVisible(true);
		testfrage.setText("Wie viel % !Wasser auf Erdoberfläche?");
		ersteAntwort.setText("47%");
		zweiteAntwort.setText("29%");
		driteAntwort.setText("14%");
	}	
	
	/*
	 * goes back to choose a other test
	 */
	public void showCheck() {
		question.setVisible(false);
		check.setVisible(true);
		
	}
	
	/*
	 * show the next question
	 */
	public void setNextQuestion() {
		testfrage.setText("Wie viel % Wasser auf Erdoberfläche?");
		ersteAntwort.setText("53%");
		zweiteAntwort.setText("71%");
		driteAntwort.setText("86%");
		
	}
}
