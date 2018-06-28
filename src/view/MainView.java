package view;

import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * 
 * Start the application
 * @since 27-06-18
 * @version 1.0
 * @author GiBr03s
 * 
 */
public class MainView {
	/**
	 * Loads the fxml for the layout.
	 * Set and starts the main-layout/main-menu. 
	 * @param Stage primaryStage
	 */
	public void startMyApplication(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(MainView.class.getResource("MainLayout.fxml"));
			AnchorPane pane = loader.load();
			MainController controller = loader.getController();
			controller.setMain(this);

			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
