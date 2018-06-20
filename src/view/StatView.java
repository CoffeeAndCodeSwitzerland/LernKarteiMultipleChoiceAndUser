package view;

import modul.GetStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StatView {
	GetStats gs = new GetStats();
	
	public void start() {
		
		Integer wins = gs.getLosses();
		
		Stage stats = new Stage();
		stats.setFullScreen(true);
		stats.setTitle("Statistiken");
		BorderPane bp = new BorderPane();
		
        ObservableList<PieChart.Data> pieChartDataWinLoss =
                FXCollections.observableArrayList(
                new PieChart.Data("Siege",wins),
                new PieChart.Data("Niederlagen", 12));
		
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
		
		stats.show();
	}
	
}
