package controller;


import java.io.File;

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

/*
 * this class controls the action of the window from the TestView class
 * here you see what happen when you click on the button
 */

public class TestController {
	
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

	@FXML
	ComboBox<String> testlist;
	
	@FXML
	Button btnts;
	
	getStandartPath path = new getStandartPath();
	
	/*
	 * set the layout to choose a test
	 */
	public void setMain(TestView testView) {
		this.tmain = testView;		
		check.setVisible(true);
		question.setVisible(false);
		
		File folder = new File(getStandartPath.getStandartPath()+"\\src\\Tests");
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
		testfrage.setText("Wie viel % Wasser auf Erdoberfläche?");
		ersteAntwort.setText("56%");
		zweiteAntwort.setText("71%");
		driteAntwort.setText("34%");
		
		ToggleGroup group = new ToggleGroup();
		ersteAntwort.setToggleGroup(group);
		zweiteAntwort.setToggleGroup(group);
		driteAntwort.setToggleGroup(group);
		ersteAntwort.setSelected(true);
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
		
		showQuestion();
		System.out.println("next question");
		
	}
	
	/*
	 * show the test, choose one of it
	 */
	public void chooseATest() {
		btnts.setVisible(true);

	}
}
