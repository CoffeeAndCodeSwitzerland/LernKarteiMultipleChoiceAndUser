package view;

import controller.addTestController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * create a new window where you can create a new Test.
 */

public class addTestView extends Application{

	private Stage addTestStage = new Stage();
	
	public void start(Stage primaryStage) {
		showAddTest();
	}
	
	public void showAddTest() {
		try {
			FXMLLoader loader = new FXMLLoader(TestView.class.getResource("addTest.fxml"));
			AnchorPane pane = loader.load();
			addTestController controller = loader.getController();
			controller.setMain(this);
			
			
			Scene scene = new Scene(pane);
			addTestStage.setScene(scene);
			addTestStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch (args);
	}
}
