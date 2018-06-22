package view;


import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainView extends Application{

	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(MainView.class.getResource("MainLayout.fxml"));
			AnchorPane pane = loader.load();
			MainController controller = loader.getController();
			controller.setMain(this);
			
			
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
		    
			} catch(Exception e) {
			e.printStackTrace();
			}
		}

public static void main(String[] args) {
	launch (args);
}
}
