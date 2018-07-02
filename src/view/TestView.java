package view;

import controller.TestController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * start a new window where you can select a test 
 * and answer the questions of the selected test
 * @since 02-07-18
 * @version 1.0
 * @author Mischii
 */

public class TestView extends Application{

	private Stage testStage = new Stage();
	
	public void start(Stage primaryStage) {}
	
	/**
	 * Loads the fxml for the layout.
	 * Set and starts the layout_chosecheck. 
	 * @param Stage primaryStage
	 */
	
	public void window() {
		try {
			FXMLLoader loader = new FXMLLoader(TestView.class.getResource("layout_chosecheck.fxml"));
			AnchorPane pane = loader.load();
			TestController controller = loader.getController();
			controller.setMain(this);
			
			
			Scene scene = new Scene(pane);
			testStage.setScene(scene);
			testStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
