package seminar.seminar10.g1055;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML private TextField txTitlu;
    @FXML private TextArea txOut;

    @FXML private TitledPane tPane;

    private TableView<Carte> tabel;

    @FXML private void cerere(){
        try(Socket socket = new Socket("localhost",2013)){
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                String titlu = txTitlu.getText().trim();
                out.writeObject(titlu);
                List<Carte> lista = (List<Carte>) in.readObject();
                lista.forEach(carte -> txOut.appendText(carte+"\n"));
                tabel.getItems().clear();
                lista.forEach(carte -> tabel.getItems().add(carte));
                tPane.setContent(tabel);
            }

        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabel = new TableView<>();
        TableColumn<Carte,String> coloanaCota = new TableColumn<>("Cota");
        coloanaCota.setCellValueFactory(new PropertyValueFactory<>("cota"));
        tabel.getColumns().add(coloanaCota);
        TableColumn<Carte,String> coloanaTitlu = new TableColumn<>("Titlu");
        coloanaTitlu.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        tabel.getColumns().add(coloanaTitlu);
        TableColumn<Carte,String> coloanaAn = new TableColumn<>("Anul");
        coloanaAn.setCellValueFactory(new PropertyValueFactory<>("anul"));
        tabel.getColumns().add(coloanaAn);
        TableColumn<Carte,String> coloanaValoare = new TableColumn<>("Pret");
        coloanaValoare.setCellValueFactory(new PropertyValueFactory<>("valoareInventar"));
        tabel.getColumns().add(coloanaValoare);
    }
}
