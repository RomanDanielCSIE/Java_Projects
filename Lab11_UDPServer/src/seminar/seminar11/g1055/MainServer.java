package seminar.seminar11.g1055;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainServer {

    private DatagramSocket socket;
    private boolean serverActiv = true;

    private void raspuns(DatagramPacket din, String mesaj) throws Exception {
        try (ByteArrayOutputStream bout = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bout)) {
            out.writeObject(mesaj);
            byte[] b = bout.toByteArray();
            DatagramPacket dout = new DatagramPacket(b, b.length, din.getAddress(), din.getPort());
            socket.send(dout);
        }
    }

    private void comunicare(DatagramPacket din, Connection c) {
        try (ByteArrayInputStream bin = new ByteArrayInputStream(din.getData());
             ObjectInputStream in = new ObjectInputStream(bin)) {
            String mesaj = in.readObject().toString();
            if (mesaj.equalsIgnoreCase("stop")) {
                System.out.println("Oprire server.");
                serverActiv = false;
                raspuns(din, "Server oprit.");
            } else {
                if (mesaj.equalsIgnoreCase("comanda")) {

                    //Command handling
                    String comandaSql = in.readObject().toString();
                    try (Statement s = c.createStatement()) {
                        s.executeUpdate(comandaSql);
                        raspuns(din, "Comanda procesata.");
                    } catch (Exception ex) {
                        System.err.println(ex);
                        raspuns(din, ex.toString());
                    }
                } else {

                    //Interogation handling
                    String interogare = in.readObject().toString();
                    try (Statement s = c.createStatement();
                         ResultSet r = s.executeQuery(interogare)) {
                        ResultSetMetaData rm = r.getMetaData();
                        int m = rm.getColumnCount();
                        StringBuilder linie = new StringBuilder();
                        List<String> linii = new ArrayList<>();
                        while (r.next()) {
                            for (int i = 1; i < m; i++) {
                                linie.append(r.getObject(i)).append(",");
                            }
                            linie.append(r.getObject(m));
                            linii.add(linie.toString());
                        }
                        try (ByteArrayOutputStream bout = new ByteArrayOutputStream();
                             ObjectOutputStream out = new ObjectOutputStream(bout)) {
                            out.writeObject(linii.size());
                            for (String l:linii){
                                out.writeObject(l);
                            }
                            byte[] b = bout.toByteArray();
                            DatagramPacket dout = new DatagramPacket(b,b.length,din.getAddress(),din.getPort());
                            socket.send(dout);
                        }
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void start() {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:seminar11_1055.db")) {
            socket = new DatagramSocket(2000);
            socket.setSoTimeout(10000);
            while (serverActiv) {
                DatagramPacket din = new DatagramPacket(new byte[10000], 10000);
                try {
                    socket.receive(din);
                    Thread firPrelucrare = new Thread(() -> comunicare(din, c));
                    firPrelucrare.start();
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            try {
                socket.close();
            } catch (Exception ex) {
            }
        }
    }

    public static void main(String[] args) {
        MainServer app = new MainServer();
        app.start();
    }

}
