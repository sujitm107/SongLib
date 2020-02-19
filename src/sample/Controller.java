package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.Year;

import static javafx.collections.FXCollections.observableArrayList;


public class Controller{

    @FXML
    private TextField SongTextField;

    @FXML
    private TextField AlbumTextField;

    @FXML
    private TextField ArtistTextField;

    @FXML
    private TextField YearTextField;

    @FXML
    private Button AddButton;

    @FXML
    private ListView<String> listView;

    final ObservableList<String> songNamesList = observableArrayList();

    @FXML
    private void addButtonClicked(ActionEvent e){

        String song = SongTextField.getText();
        String album = AlbumTextField.getText();
        String artist = ArtistTextField.getText();
        String year = YearTextField.getText();

        SongDetail temp = new SongDetail(artist, album, year, song);

        System.out.println(temp);
    }
}
