package com.example.examen_javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StatistiqueController implements Initializable {

    @FXML
    private AnchorPane pageStatistique;
    @FXML
    private BorderPane bord;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Product ");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantite sold ");

        BarChart barChart = new BarChart(xAxis,yAxis);
        XYChart.Series data = new XYChart.Series<>();
        data.setName("Product sold");
        data.getData().add(new XYChart.Data("Product A",3000));
        data.getData().add(new XYChart.Data("Product B",1000));
        data.getData().add(new XYChart.Data("Product C",500));

        barChart.setLegendVisible(false);
        barChart.getData().add(data);
        bord.setCenter(barChart);

    }


}
