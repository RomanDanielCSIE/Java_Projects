package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Controller {

    @FXML private TextArea tout;
    @FXML private TextField txComanda;
    @FXML private TextArea tIstoric;

    @FXML private void interogare(){
        try(DatagramSocket socket = new DatagramSocket();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout)){
            out.writeObject("interogare");
            out.writeObject(txComanda.getText().trim());
            byte[] b = bout.toByteArray();
            DatagramPacket dout = new DatagramPacket(b,b.length, InetAddress.getByName("localhost"),2000);
            socket.send(dout);
            DatagramPacket din = new DatagramPacket(new byte[100000],100000);
            socket.receive(din);
            try(ByteArrayInputStream bin = new ByteArrayInputStream(din.getData());
                ObjectInputStream in = new ObjectInputStream(bin)){
                int n = (Integer) in.readObject();
                for (int i=0;i<n;i++){
                    tout.appendText(in.readObject()+"\n");
                }
                tIstoric.appendText(txComanda.getText().trim()+"\n");
                txComanda.clear();
            }
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }
    }

    @FXML private void comanda(){
        try(DatagramSocket socket = new DatagramSocket();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout)){
            out.writeObject("comanda");
            out.writeObject(txComanda.getText().trim());
            byte[] b = bout.toByteArray();
            DatagramPacket dout = new DatagramPacket(b,b.length, InetAddress.getByName("localhost"),2000);
            socket.send(dout);
            DatagramPacket din = new DatagramPacket(new byte[1000],1000);
            socket.receive(din);
            try(ByteArrayInputStream bin = new ByteArrayInputStream(din.getData());
                ObjectInputStream in = new ObjectInputStream(bin)){
                tout.appendText(in.readObject()+"\n");
                tIstoric.appendText(txComanda.getText().trim()+"\n");
                txComanda.clear();
            }
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }
    }

    @FXML private void stop(){
        try(DatagramSocket socket = new DatagramSocket();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout)){
            out.writeObject("stop");
            byte[] b = bout.toByteArray();
            DatagramPacket dout = new DatagramPacket(b,b.length, InetAddress.getByName("localhost"),2000);
            socket.send(dout);
            DatagramPacket din = new DatagramPacket(new byte[1000],1000);
            socket.receive(din);
            try(ByteArrayInputStream bin = new ByteArrayInputStream(din.getData());
                ObjectInputStream in = new ObjectInputStream(bin)){
                tout.appendText(in.readObject()+"\n");
            }
        }
        catch (Exception ex){
            Alert err = new Alert(Alert.AlertType.ERROR,ex.toString());
            err.showAndWait();
        }
    }

}
