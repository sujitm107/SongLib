//Rachana Kotamraju and Sujit Molleti

package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;


public class Controller implements Initializable {

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

//SONG LIST
    @FXML
    private ListView<String> SongListView;

    final ObservableList<String> songNamesList = observableArrayList();

    @FXML
    private void addButtonClicked(ActionEvent e){

        String song = SongTextField.getText();
        String album = AlbumTextField.getText();
        String artist = ArtistTextField.getText();
        String year = YearTextField.getText();

        SongDetail temp = new SongDetail(artist, album, year, song);

        songNamesList.add(temp.song);

        System.out.println(temp);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SongListView.setItems(songNamesList);
        AddButton.setDisable(true);

        SongTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(SongTextField.isFocused()){
                    AddButton.setDisable(false);
                }
            }
        });
    }
}
