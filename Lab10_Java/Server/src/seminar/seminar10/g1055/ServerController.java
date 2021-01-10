package seminar.seminar10.g1055;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerController {

    private Connection c;
    private List<Carte> lista = new ArrayList<>();
    private ServerSocket serverSocket;
    private boolean serverActiv = false;

    @FXML private TextArea txOut;

    private void tratareCerere(Socket socket){
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){
            String titlu = in.readObject().toString();
            List<Carte> listaRaspuns = lista.stream().filter(carte -> carte.getTitlu().contains(titlu))
                    .collect(Collectors.toList());
            out.writeObject(listaRaspuns);
        }
        catch (Exception ex){
            Platform.runLater(()->{
                Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
                err.showAndWait();
            });
        }
    }

    private void ascultare(){
        while (serverActiv){
            try{
                Socket socket = serverSocket.accept();
                Thread firCerere = new Thread(()-> tratareCerere(socket));
                firCerere.start();
            }
            catch (Exception ex){
            }
        }
    }

    @FXML private void start(){
        if (serverActiv){
            txOut.appendText("Server activ!!!\n");
            return;
        }
        try{
            serverSocket = new ServerSocket(2013);
            serverSocket.setSoTimeout(10000);
            txOut.appendText("Server Startat.\n");
            serverActiv = true;
            Thread firAscultare = new Thread(()->ascultare());

            firAscultare.setDaemon(true); //Cod pus în afara prezentarii video. Firul se termina dupa terminarea firelor non-daemon

            firAscultare.start();
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }
    }

    @FXML private void stop(){
        if (!serverActiv){
            txOut.appendText("Server inactiv!!!\n");
            return;
        }
        try{
            serverSocket.close();
            serverActiv=false;
            txOut.appendText("Stop Server!\n");
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }

    }

    public void setC(Connection c) {
        this.c = c;
        try(Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select * from depozit")){
            while (r.next()){
                Carte carte = new Carte();
                carte.setCota(r.getString(1));
                carte.setTitlu(r.getString(2));
                carte.setAnul(r.getInt(3));
                carte.setValoareInventar(r.getDouble(4));
                lista.add(carte);
            }

        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }
    }
}
