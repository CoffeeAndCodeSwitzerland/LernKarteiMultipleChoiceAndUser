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
		
		HBox hb = new HBox();
		hb.getChildren().add(btnWinLoss);
		hb.getChildren().add(btnTimePlayed);
		hb.getChildren().add(btnCloseStats);
		
		hb.getStylesheets().add("Stylesheets/Stats.css");
		hb.getStyleClass().add("hbox");
		
		Stage stats = new Stage();
		stats.setFullScreen(true);
		stats.setTitle("Statistiken");
		BorderPane bp = new BorderPane();
		
		bp.setTop(hb);
		
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
		/*
		
		HBox hb = new HBox();
		hb.getChildren().add(btnWinLoss);
		hb.getChildren().add(btnTimePlayed);
		
		Stage stats = new Stage();
		stats.setFullScreen(true);
		stats.setTitle("Statistiken");
		BorderPane bp = new BorderPane();
		
        ObservableList<PieChart.Data> pieChartDataWinLoss =
                FXCollections.observableArrayList(
                new PieChart.Data("Siege",wins),
                new PieChart.Data("Niederlagen", losses));
		
		PieChart chart = new PieChart(pieChartDataWinLoss);
		chart.setTitle("Sieg/Verlust-Verhältnis");
		chart.setLegendSide(Side.RIGHT);
		chart.setLabelsVisible(false);
		
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		
		xAxis.setLabel("Spielzeit in den letzten 3 Monaten");
		LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();	
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(0, 13));
        series.getData().add(new XYChart.Data(1, 14));
        series.getData().add(new XYChart.Data(2, 15));
		 
        lineChart.getData().add(series);
			
		AnchorPane ap = new AnchorPane();
		
		ap.getChildren().add(chart);
		ap.getChildren().add(lineChart);
		
		AnchorPane.setRightAnchor(lineChart, 8.0);
		AnchorPane.setLeftAnchor(chart, 8.0);
		
		bp.setCenter(ap);
		
		stats.setScene(new Scene(bp, 900, 900));
		
		stats.show();*/
	}
	
}
