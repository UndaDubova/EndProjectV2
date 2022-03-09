package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class chartsController implements Initializable {

    @FXML
    PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Food", 60),
                        new PieChart.Data("Clothes", 25),
                        new PieChart.Data("Utilities", 15));

        pieChart.setData(pieChartData);

    }
}
