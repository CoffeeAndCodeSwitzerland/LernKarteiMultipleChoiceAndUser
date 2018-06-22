package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * create a new window where you can select a test and answer the questions of the selected test
 */

public class TestView extends Application{

	private Stage testStage = new Stage();
	
	public void start(Stage primaryStage) {}
	
	public void window() {
		try {
			FXMLLoader loader = new FXMLLoader(TestView.class.getResource("layout_chosecheck.fxml"));
			AnchorPane pane = loader.load();
			Controller controller = loader.getController();
			controller.setMain(this);
			
			
			Scene scene = new Scene(pane);
			testStage.setScene(scene);
			testStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch (args);
	}
}
