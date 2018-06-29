package view;

import controller.addTestController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * create a new window where you can create a new test.
 */

public class addTestView extends Application{

	private Stage addTestStage = new Stage();
	
	public void start(Stage addTestStage) {
		showAddTest();
	}
	
	public void showAddTest() {
		try {
			FXMLLoader loaderAddTest = new FXMLLoader(addTestView.class.getResource("AddTest.fxml"));
			AnchorPane pane = loaderAddTest.load();
			addTestController controller = loaderAddTest.getController();
			controller.setMain(this);
			
			
			Scene scene = new Scene(pane);
			addTestStage.setScene(scene);
			addTestStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}


