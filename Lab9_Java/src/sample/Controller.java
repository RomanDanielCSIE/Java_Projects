package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private ComboBox<String> cTabele;
    @FXML private TextField tComanda;
    @FXML private TextArea tOut;
    @FXML private TextArea tMemorator;
    @FXML private TitledPane pTabel;

    @FXML private void clickAfisare(){
        interogare("select * from "+cTabele.getSelectionModel().getSelectedItem());
    }

    @FXML private void clickInterogare(){
        interogare(tComanda.getText().trim());
    }

    private void interogare(String comanda){
        try(Statement s = Main.c.createStatement()){
            try(ResultSet r = s.executeQuery(comanda)){
                ResultSetMetaData rm = r.getMetaData();
                int m = rm.getColumnCount();

                //TableView instantiation
                TableView<String[]> tabel = new TableView<>();

                //Table configuration
                for (int i=0;i<m;i++){

                //Column instantiation
                    TableColumn<String[],String> tc = new TableColumn<>(rm.getColumnName(i+1));

                    //Column renderer
                    final int j = i;
                    tc.setCellValueFactory( linie -> new SimpleStringProperty(linie.getValue()[j]));
                    tabel.getColumns().add(tc);
                }

                //Table population
                while (r.next()){
                    String[] linie = new String[m];
                    for (int i=0;i<m;i++){
                        linie[i] = r.getObject(i+1).toString();
                    }
                    tabel.getItems().add(linie);
                }
                pTabel.setContent(tabel);
                tComanda.clear();
                tMemorator.appendText(comanda+"\n");
            }
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }
    }

    @FXML private void clickComanda(){
        try(Statement s = Main.c.createStatement()){
            String comanda = tComanda.getText().trim();
            int k = s.executeUpdate(comanda);
            if (k != Statement.EXECUTE_FAILED){
                tMemorator.appendText(comanda+"\n");
                tComanda.clear();
                tOut.appendText("Comanda executata. Inregistrari afectate:"+k+"\n");
            }


            if (comanda.startsWith("create table")||comanda.startsWith("create view")){
                String numeTabelaViziune = comanda.split(" ")[2];
                cTabele.getItems().add(numeTabelaViziune);
                cTabele.getSelectionModel().select(numeTabelaViziune);
            }
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseMetaData metaData = Main.c.getMetaData();
            try(ResultSet r = metaData.getTables(null,null,null,new String[]{"TABLE","VIEW"})){
                while (r.next()){
                    cTabele.getItems().add(r.getString(3));
                }
                cTabele.getSelectionModel().select(0);
            }
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }
    }
}
