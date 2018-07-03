package controller;

import javafx.stage.Stage;
import view.MainView;

public class MultipleChoiceAndUser extends javafx.application.Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		new MainView().startMyApplication(primaryStage);
	}
}