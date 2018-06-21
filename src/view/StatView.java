package view;

import modul.GetStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StatView {
	GetStats gs = new GetStats();
	
	public void start() {
		
		Integer wins = gs.getLosses();
		Integer losses = gs.getLosses();
		
		//Menu buttons
		Button btnTimePlayed = new Button();
		Button btnWinLoss = new Button();
		Button btnCloseStats = new Button();
		
		btnWinLoss.setText("Gewinn/Verlust-Statistik");
		btnTimePlayed.setText("Spielzeit");
		btnCloseStats.setText("Statistiken schliessen");
		//Menu box
		HBox hb = new HBox();
		hb.getChildren().addAll(btnWinLoss,btnTimePlayed,btnCloseStats);
		hb.setStyle("-fx-background-color: #ff69b4;    -fx-padding: 15;\r\n" + 
					"    -fx-spacing: 10;");
		
		BorderPane bp = new BorderPane();
		bp.setTop(hb);
		//Stage and stats
		Stage stats = new Stage();
		stats.setFullScreen(true);
		stats.setTitle("Statistiken");
		stats.setScene(new Scene(bp, 900, 900));
		stats.show();
		
		btnCloseStats.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stats.close();
			}			
		});
		
		btnWinLoss.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}			
		});
	}
	
}
