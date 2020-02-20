//Rachana Kotamraju and Sujit Molleti

package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.Year;
import java.util.Collections;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;


public class Controller implements Initializable {

//TEXTFIELDs
    @FXML
    private TextField SongTextField;
    @FXML
    private TextField AlbumTextField;
    @FXML
    private TextField ArtistTextField;
    @FXML
    private TextField YearTextField;

//BUTTONs
    @FXML
    private Button AddButton;
    @FXML
    private Button DltButton;
    @FXML
    private Button EditButton;

//LABELS
    @FXML
    private Label SongLabel;
    @FXML
    private Label ArtistLabel;
    @FXML
    private Label AlbumLabel;
    @FXML
    private Label YearLabel;


//SONG LIST
    @FXML
    private ListView<SongDetail> SongListView;

    final ObservableList<String> songNamesList = observableArrayList();
    final ObservableList<SongDetail> songsObservableList = observableArrayList();

    @FXML
    private void addButtonClicked(ActionEvent e){

        String song = SongTextField.getText();
        String album = AlbumTextField.getText();
        String artist = ArtistTextField.getText();
        String year = YearTextField.getText();

//CHECKING IF SONG OR ARTIST TEXTFIELDs are EMPTY
        if(song.trim().length() == 0 || artist.trim().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter both a song and an artist name!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        SongDetail temp = new SongDetail(artist, album, year, song);

    //CHECKING FOR DUPLICATES USING SEQUENTIAL SEARCH
        for( SongDetail i : songsObservableList){
            if(i.song.equals(temp.song)){
                if(i.artist.equals(temp.artist)){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry, this song already exists and cannot be added!", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
            }
        }

//USING SEQUENTIAL SEARCH TO INSERT ALPHABETICALLY
        int insertPosition = 0;
        for( SongDetail i : songsObservableList){
            if(temp.song.compareToIgnoreCase(i.song) < 0){
                break;
            }

        //IF SONG NAMES ARE THE SAME, COMPARE ARTIST NAMES
            if(temp.song.compareToIgnoreCase(i.song) == 0){
                if(temp.artist.compareToIgnoreCase(i.artist) < 0){
                    break;
                }
            }
            insertPosition++;
        }
        songsObservableList.add(insertPosition , temp);

        SongTextField.clear();
        ArtistTextField.clear();
        AlbumTextField.clear();
        YearTextField.clear();
    }

    @FXML
    private void deleteButtonClicked(ActionEvent e){
        int selectedItem = SongListView.getSelectionModel().getSelectedIndex();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.CANCEL, ButtonType.YES);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES) {
            songsObservableList.remove(selectedItem);
            SongLabel.setText("Song: ");
            ArtistLabel.setText("Artist: ");
            AlbumLabel.setText("Album: ");
            YearLabel.setText("Year: ");

            //DISABLING EDIT AND DELETE BUTTON BECAUSE ONCE YOU DELETE THERE IS NO SELECTED ITEM
            DltButton.setDisable(true);
            EditButton.setDisable(true);
        }

    }

    @FXML
    private void editButtonClicked(ActionEvent e){
        SongDetail selectedSong = SongListView.getSelectionModel().getSelectedItem();
        SongTextField.setText(selectedSong.song);
        ArtistTextField.setText(selectedSong.artist);
        AlbumTextField.setText(selectedSong.album);
        YearTextField.setText(selectedSong.year);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SongListView.setItems(songsObservableList);
        AddButton.setDisable(true);
        DltButton.setDisable(true);
        EditButton.setDisable(true);

        //ABLING THE ADD BUTTON BECAUSE WE ARE FOCUSING ON THE SONG TEXTFIELD
        SongTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(SongTextField.isFocused()){
                    AddButton.setDisable(false);
                }
            }
        });

        //FOCUSING ON THE LISTVIEW
        SongListView.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(SongListView.isFocused()){

                    //SETTING TEXT
                    SongDetail selectedSong = SongListView.getSelectionModel().getSelectedItem();
                    SongLabel.setText("Song: "+selectedSong.song);
                    ArtistLabel.setText("Artist: "+selectedSong.artist);
                    AlbumLabel.setText("Album: "+selectedSong.album);
                    YearLabel.setText("Year: "+selectedSong.year);

                    //ABLING THE DELETE AND EDIT BUTTONS
                    DltButton.setDisable(false);
                    EditButton.setDisable(false);
                }
            }
        });


    }
}
