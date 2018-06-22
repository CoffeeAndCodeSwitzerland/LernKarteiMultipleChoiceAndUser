package application;
	
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author MiHa04
 *
 */
public class Main extends javafx.application.Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("layout_chosecheck.fxml"));
			AnchorPane pane = loader.load();
			Controller controller = loader.getController();
			controller.setMain(this);
			
			
			Scene scene = new Scene(pane);
		    primaryStage.setScene(scene);
			primaryStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
