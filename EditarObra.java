
import java.awt.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

public class EditarObra extends JFrame {
    Connection conexion;

    EditarObra(int id_Obra) {
        conexion=getConexion();

        setSize(1386, 768);
        setTitle("Editar obras");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        String[] datosRecuperados = recuperarDatosObra("SELECT * FROM OBRA WHERE CLAVE_OBRA = " + id_Obra );
            
        JPanel DatosObras = new JPanel();
        DatosObras.setLayout(null);
        DatosObras.setSize(1366, 768);
        DatosObras.setBackground(Color.black);

        JLabel nombreResponsableEditar = new JLabel("Datos del responsable de la obra:");
        nombreResponsableEditar.setForeground(Color.decode("#049cff"));
        Font fuenteResponsable = new Font("Arial Black", Font.BOLD, 20);
        nombreResponsableEditar.setFont(fuenteResponsable);
        nombreResponsableEditar.setBounds(500, 0, 500, 40);
        DatosObras.add(nombreResponsableEditar);

        JLabel nombresResponsableEditar = new JLabel("Nombre(s):");
        nombresResponsableEditar.setForeground(Color.white);
        Font fuenteResponsables = new Font("Arial", Font.BOLD, 20);
        nombresResponsableEditar.setFont(fuenteResponsables);
        nombresResponsableEditar.setBounds(0, 40, 300, 20);
        DatosObras.add(nombresResponsableEditar);

        CampoDato NombreResponsabletxtEditar = new CampoDato(datosRecuperados[2]);
        NombreResponsabletxtEditar.setForeground(Color.black);
        NombreResponsabletxtEditar.setBounds(105, 40, 200, 30);
        NombreResponsabletxtEditar.setBorder(null);
        NombreResponsabletxtEditar.setTipo('T');
        NombreResponsabletxtEditar.setLongitud(20);
        DatosObras.add(NombreResponsabletxtEditar);

        JLabel ApellidoResponsablePaternoEditar = new JLabel("Apellido paterno:");
        ApellidoResponsablePaternoEditar.setForeground(Color.white);
        Font fuenteResponsablesP = new Font("Arial", Font.BOLD, 20);
        ApellidoResponsablePaternoEditar.setFont(fuenteResponsablesP);
        ApellidoResponsablePaternoEditar.setBounds(315, 40, 300, 20);
        DatosObras.add(ApellidoResponsablePaternoEditar);

        CampoDato ApellidoResponsablePaternotxtEditar = new CampoDato(datosRecuperados[3]);
        ApellidoResponsablePaternotxtEditar.setForeground(Color.black);
        ApellidoResponsablePaternotxtEditar.setBounds(480, 40, 200, 30);
        ApellidoResponsablePaternotxtEditar.setBorder(null);
        ApellidoResponsablePaternotxtEditar.setTipo('T');
        ApellidoResponsablePaternotxtEditar.setLongitud(20);
        DatosObras.add(ApellidoResponsablePaternotxtEditar);

        JLabel ApellidoResponsableMaternoEditar = new JLabel("Apellido materno:");
        ApellidoResponsableMaternoEditar.setForeground(Color.white);
        Font fuenteResponsablesMaterno = new Font("Arial", Font.BOLD, 20);
        ApellidoResponsableMaternoEditar.setFont(fuenteResponsables);
        ApellidoResponsableMaternoEditar.setBounds(690, 40, 300, 20);
        DatosObras.add(ApellidoResponsableMaternoEditar);

        CampoDato ApellidoResponsableMaternotxtEditar = new CampoDato(datosRecuperados[4]);
        ApellidoResponsableMaternotxtEditar.setForeground(Color.black);
        ApellidoResponsableMaternotxtEditar.setBounds(860, 40, 200, 30);
        ApellidoResponsableMaternotxtEditar.setBorder(null);
        ApellidoResponsableMaternotxtEditar.setTipo('T');
        ApellidoResponsableMaternotxtEditar.setLongitud(20);
        DatosObras.add(ApellidoResponsableMaternotxtEditar);

        JLabel MontoEditar = new JLabel("Monto de la obra: $");
        MontoEditar.setForeground(Color.white);
        Font fuenteMonto = new Font("Arial", Font.BOLD, 20);
        MontoEditar.setFont(fuenteMonto);
        MontoEditar.setBounds(1065, 40, 300, 20);
        DatosObras.add(MontoEditar);

        CampoDato MontotxtEditar = new CampoDato(datosRecuperados[13]);
        MontotxtEditar.setForeground(Color.black);
        MontotxtEditar.setBounds(1250, 40, 70, 30);
        MontotxtEditar.setBorder(null);
        MontotxtEditar.setTipo('D');
        MontotxtEditar.setLongitud(20);
        DatosObras.add(MontotxtEditar);

        JLabel TelefonoEditar = new JLabel("Télefono:");
        TelefonoEditar.setForeground(Color.white);
        Font fuenteTelefono = new Font("Arial", Font.BOLD, 20);
        TelefonoEditar.setFont(fuenteTelefono);
        TelefonoEditar.setBounds(0, 80, 300, 20);
        DatosObras.add(TelefonoEditar);

        CampoDato TelefonotxtEditar = new CampoDato(datosRecuperados[14]);
        TelefonotxtEditar.setForeground(Color.black);
        TelefonotxtEditar.setBounds(107, 80, 200, 30);
        TelefonotxtEditar.setBorder(null);
        TelefonotxtEditar.setTipo('E');
        TelefonotxtEditar.setLongitud(15);
        DatosObras.add(TelefonotxtEditar);

        JLabel CorreoEditar = new JLabel("E-mail:");
        CorreoEditar.setForeground(Color.white);
        Font fuenteCorreo = new Font("Arial", Font.BOLD, 20);
        CorreoEditar.setFont(fuenteCorreo);
        CorreoEditar.setBounds(410, 80, 300, 20);
        DatosObras.add(CorreoEditar);

        JTextField CorreotxtEditar = new JTextField(datosRecuperados[15]);
        CorreotxtEditar.setForeground(Color.black);
        CorreotxtEditar.setBounds(480, 80, 200, 30);
        CorreotxtEditar.setBorder(null);
        DatosObras.add(CorreotxtEditar);

        JLabel cliente = new JLabel("Cliente:");
        cliente.setForeground(Color.white);
        Font fuenteCliente = new Font("Arial", Font.BOLD, 20);
        cliente.setFont(fuenteCliente);
        cliente.setBounds(785, 80, 300, 20);
        DatosObras.add(cliente);

        java.util.List<Object> allClientes = recuperarDatos("SELECT * FROM CLIENTE", "NOMBRE_CLIENTE");
        JComboBox clienteCEditado = new JComboBox(allClientes.toArray());
        for (int i = 0; i < allClientes.size(); i++) {
            if (allClientes.get(i).equals((String) datosRecuperados[1])) {
                clienteCEditado.setSelectedIndex(i);
            }
        }
        clienteCEditado.setForeground(Color.black);
        clienteCEditado.setBorder(null);
        clienteCEditado.setBounds(860, 80, 200, 30);
        DatosObras.add(clienteCEditado);

        JLabel DomicilioEditar = new JLabel("Ubicación de la obra:");
        DomicilioEditar.setForeground(Color.decode("#049cff"));
        Font fuenteDomicilio = new Font("Arial Black", Font.BOLD, 20);
        DomicilioEditar.setFont(fuenteDomicilio);
        DomicilioEditar.setBounds(550, 35, 600, 200);
        DatosObras.add(DomicilioEditar);

        JLabel CalleEditar = new JLabel("Calle:");
        CalleEditar.setForeground(Color.white);
        Font fontCalle = new Font("Arial", Font.BOLD, 20);
        CalleEditar.setFont(fontCalle);
        CalleEditar.setBounds(0, 150, 300, 20);
        DatosObras.add(CalleEditar);

        JTextField CalletxtEditar = new JTextField(datosRecuperados[5]);
        CalletxtEditar.setForeground(Color.black);
        CalletxtEditar.setBounds(105, 150, 200, 30);
        CalletxtEditar.setBorder(null);
        DatosObras.add(CalletxtEditar);

        JLabel NumeroEditar = new JLabel("Número:");
        NumeroEditar.setForeground(Color.white);
        Font fontNumero = new Font("Arial", Font.BOLD, 20);
        NumeroEditar.setFont(fontNumero);
        NumeroEditar.setBounds(397, 150, 300, 20);
        DatosObras.add(NumeroEditar);

        CampoDato NumtxtEditar = new CampoDato(datosRecuperados[6]);
        NumtxtEditar.setForeground(Color.black);
        NumtxtEditar.setBounds(480, 150, 50, 30);
        NumtxtEditar.setBorder(null);
        NumtxtEditar.setTipo('E');
        NumtxtEditar.setLongitud(10);
        DatosObras.add(NumtxtEditar);

        JLabel coloniaEditar = new JLabel("Colonia:");
        coloniaEditar.setForeground(Color.white);
        Font fontColonia = new Font("Arial", Font.BOLD, 20);
        coloniaEditar.setFont(fontColonia);
        coloniaEditar.setBounds(780, 150, 300, 20);
        DatosObras.add(coloniaEditar);

        CampoDato ColtxtEditar = new CampoDato(datosRecuperados[7]);
        ColtxtEditar.setForeground(Color.black);
        ColtxtEditar.setBounds(858, 150, 200, 30);
        ColtxtEditar.setBorder(null);
        ColtxtEditar.setTipo('T');
        ColtxtEditar.setLongitud(20);
        DatosObras.add(ColtxtEditar);

        JLabel MunicipioEditar = new JLabel("Municipio:");
        MunicipioEditar.setForeground(Color.white);
        Font fontMunicipio = new Font("Arial", Font.BOLD, 20);
        MunicipioEditar.setFont(fontMunicipio);
        MunicipioEditar.setBounds(1080, 150, 300, 20);
        DatosObras.add(MunicipioEditar);

        CampoDato MunicipiotxtEditar = new CampoDato(datosRecuperados[8]);
        MunicipiotxtEditar.setForeground(Color.black);
        MunicipiotxtEditar.setBounds(1180, 150, 170, 30);
        MunicipiotxtEditar.setBorder(null);
        MunicipiotxtEditar.setTipo('T');
        MunicipiotxtEditar.setLongitud(30);
        DatosObras.add(MunicipiotxtEditar);

        JLabel EstadoEditar = new JLabel("Estado:");
        EstadoEditar.setForeground(Color.white);
        Font fontEstado = new Font("Arial", Font.BOLD, 20);
        EstadoEditar.setFont(fontEstado);
        EstadoEditar.setBounds(0, 190, 300, 20);
        DatosObras.add(EstadoEditar);

        CampoDato EstadotxtEditar = new CampoDato(datosRecuperados[10]);
        EstadotxtEditar.setForeground(Color.black);
        EstadotxtEditar.setBounds(105, 190, 200, 30);
        EstadotxtEditar.setBorder(null);
        EstadotxtEditar.setTipo('T');
        EstadotxtEditar.setLongitud(30);
        DatosObras.add(EstadotxtEditar);
        
                JLabel cp = new JLabel("CP:");
        cp.setForeground(Color.white);
        Font fontcp = new Font("Arial", Font.BOLD, 20);
        cp.setFont(fontEstado);
        cp.setBounds(443, 190, 300, 20);
        DatosObras.add(cp);

        CampoDato cptxt = new CampoDato(datosRecuperados[9]);
        cptxt.setForeground(Color.black);
        cptxt.setBounds(480, 190, 50, 30);
        cptxt.setBorder(null);
        cptxt.setTipo('E');
        cptxt.setLongitud(30);
        DatosObras.add(cptxt);

        ///Agregado
        JLabel NombreObraEditar = new JLabel("Nombre de la obra:");
        NombreObraEditar.setForeground(Color.white);
        Font fontNObra = new Font("Arial", Font.BOLD, 20);
        NombreObraEditar.setFont(fontNObra);
        NombreObraEditar.setBounds(675, 190, 300, 20);
        DatosObras.add(NombreObraEditar);

        CampoDato NombreObraEditartxt = new CampoDato(datosRecuperados[0]);
        NombreObraEditartxt.setForeground(Color.black);
        NombreObraEditartxt.setBounds(858, 190, 200, 30);
        NombreObraEditartxt.setBorder(null);
        NombreObraEditartxt.setTipo('T');
        NombreObraEditartxt.setLongitud(50);
        DatosObras.add(NombreObraEditartxt);

        JLabel anuncioMaquinaria = new JLabel("Tipos y cantidades de máquinaria para la obra:");
        anuncioMaquinaria.setForeground(Color.decode("#049cff"));
        Font fuenteMaquinaria = new Font("Arial Black", Font.BOLD, 20);
        anuncioMaquinaria.setFont(fuenteMaquinaria);
        anuncioMaquinaria.setBounds(400, 100, 700, 300);
        DatosObras.add(anuncioMaquinaria);

        JLabel TipoMaquinariaEditar = new JLabel("Tipo de maquinaria:");
        TipoMaquinariaEditar.setForeground(Color.white);
        Font fuenteMaquina = new Font("Arial", Font.BOLD, 20);
        TipoMaquinariaEditar.setFont(fuenteMaquina);
        TipoMaquinariaEditar.setBounds(0, 220, 300, 150);
        DatosObras.add(TipoMaquinariaEditar);

        ////El cliente dijo que hasta el momento tiene 5 tractores,2 volteos,6 retroexcabadoras, 1 montacargas
        JComboBox TipoMCEditar = new JComboBox();
        TipoMCEditar.setForeground(Color.black);
        TipoMCEditar.setBorder(null);
        TipoMCEditar.setBounds(190, 280, 200, 30);
        DatosObras.add(TipoMCEditar);

        JLabel ModeloMaquinariaEditar = new JLabel("Modelo de la maquinaria:");
        ModeloMaquinariaEditar.setForeground(Color.white);
        Font fuenteModeloM = new Font("Arial", Font.BOLD, 20);
        ModeloMaquinariaEditar.setFont(fuenteModeloM);
        ModeloMaquinariaEditar.setBounds(455, 220, 300, 150);
        DatosObras.add(ModeloMaquinariaEditar);

        JComboBox MaquinariaCEditar = new JComboBox();
        MaquinariaCEditar.setForeground(Color.black);
        MaquinariaCEditar.setBounds(692, 280, 200, 30);
        MaquinariaCEditar.setBorder(null);
        DatosObras.add(MaquinariaCEditar);

        JLabel CantidadMaquinasEditar = new JLabel("Cantidad de máquinas para obra:");
        CantidadMaquinasEditar.setForeground(Color.white);
        Font fuenteCantidadMaquinas = new Font("Arial", Font.BOLD, 20);
        CantidadMaquinasEditar.setFont(fuenteCantidadMaquinas);
        CantidadMaquinasEditar.setBounds(980, 220, 500, 150);
        DatosObras.add(CantidadMaquinasEditar);

        JSpinner CantidadSpinerEditar = new JSpinner();
        CantidadSpinerEditar.setForeground(Color.black);
        CantidadSpinerEditar.setBounds(1299, 280, 50, 30);
        CantidadSpinerEditar.setBorder(null);
        DatosObras.add(CantidadSpinerEditar);

        JPanel imagen = new JPanel();
        imagen.setBackground(Color.decode("#049cff"));
        imagen.setBounds(950, 400, 400, 200);
        DatosObras.add(imagen);

        JLabel FechaInicioEditar = new JLabel("Fecha de inicio de la obra:");
        FechaInicioEditar.setForeground(Color.white);
        Font fuenteFechaI = new Font("Arial", Font.BOLD, 20);
        FechaInicioEditar.setFont(fuenteFechaI);
        FechaInicioEditar.setBounds(0, 230, 300, 300);
        DatosObras.add(FechaInicioEditar);

        JCalendar FechaIEditar = new JCalendar(Date.valueOf(datosRecuperados[11]));
        FechaIEditar.setForeground(Color.black);
        FechaIEditar.setBorder(null);
        FechaIEditar.setBounds(0, 400, 390, 200);
        DatosObras.add(FechaIEditar);

        JLabel FechaFinalEditar = new JLabel("Fecha final de la obra:");
        FechaFinalEditar.setForeground(Color.white);
        Font fuenteFechaF = new Font("Arial", Font.BOLD, 20);
        FechaFinalEditar.setFont(fuenteFechaF);
        FechaFinalEditar.setBounds(478, 230, 300, 300);
        DatosObras.add(FechaFinalEditar);

        JCalendar FechaFEditar = new JCalendar(Date.valueOf(datosRecuperados[12]));
        FechaFEditar.setForeground(Color.black);
        FechaFEditar.setBorder(null);
        FechaFEditar.setBounds(480, 400, 390, 200);
        DatosObras.add(FechaFEditar);

        JButton AgregarInformaciónEditar = new JButton("Guardar información");
        AgregarInformaciónEditar.setBackground(Color.black);
        AgregarInformaciónEditar.setBounds(550, 640, 300, 50);
        Font fontBoton = new Font("Arial", Font.BOLD, 20);
        AgregarInformaciónEditar.setFont(fontBoton);
        AgregarInformaciónEditar.setBorder(new ComponenteBotonRedondo(50));
        AgregarInformaciónEditar.setForeground(Color.decode("#049cff"));
        DatosObras.add(AgregarInformaciónEditar);
        AgregarInformaciónEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
                String consultaCliente = "SELECT * FROM CLIENTE WHERE NOMBRE_CLIENTE = '" + clienteCEditado.getSelectedItem() + "'";
                int idCliente = Integer.parseInt(recuperarDato(consultaCliente, "IDCLIENTE"));
                //String consultaObra = "SELECT * FROM OBRA WHERE NOMBRE_OBRA = " + id_Obra;
                //int Obra = Integer.parseInt(recuperarDato(consultaObra, "CLAVEOB"));
                String auxNumeroEdit=NumtxtEditar.getText().equals("S/N")?"\"S/N\"":NumtxtEditar.getText();
                String EditarObra = "UPDATE OBRA SET "
                        + "NOMBRE_OBRA='" + NombreObraEditartxt.getText() + "',NOMBRE_CLIENTE='" + clienteCEditado.getSelectedItem()
                        + "',NOMBRE_RESPONSABLE='" + NombreResponsabletxtEditar.getText() + "',AP_PAT='" + ApellidoResponsablePaternotxtEditar.getText()
                        + "',AP_MAT='" + ApellidoResponsableMaternotxtEditar.getText() + "',CALLE_OBRA='" + CalletxtEditar.getText()
                        + "',NUMERO_CALLE=" + auxNumeroEdit + ",COLONIA='" + ColtxtEditar.getText()+ "',MUNICIPIO='" + MunicipiotxtEditar.getText() 
                        + "',CP=" + cptxt.getText() + ",ESTADO='" + EstadotxtEditar.getText() + "',FECHA_INICIO='" + ff.format(FechaIEditar.getDate())
                        + "',FECHA_FIN='" + ff.format(FechaFEditar.getDate()) + "',INVERSION=" + MontotxtEditar.getText() + ",TELEFONO_RESP=" + TelefonotxtEditar.getText()
                        + ",CORREO_RESP='" + CorreotxtEditar.getText() + "',IDCLIENTE=" + idCliente
                        + " where CLAVE_OBRA=" + id_Obra;
                System.out.println(EditarObra);
                try {
                    Statement stmt = (Statement) conexion.createStatement();
                    stmt.executeUpdate(EditarObra);
                } catch (SQLException ex) {
                    System.err.println("Error al insertar " + ex);
                }
            }
        });

        ImageIcon recarga = new ImageIcon("C:\\Users\\Adan Sanchez\\Documents\\NetBeansProjects\\Fun_Ing_Soft\\src\\neo5.png");
        Image img = recarga.getImage();
        Image temp_img = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        recarga = new ImageIcon(temp_img);
        JButton recargar = new JButton(recarga);
        recargar.setBounds(1065, 82, 25, 25);
        DatosObras.add(recargar);
        recargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                java.util.List<Object> auxiliar = recuperarDatos("SELECT * FROM CLIENTE", 2);
                JComboBox aux = new JComboBox(auxiliar.toArray());
                clienteCEditado.removeAllItems();
                while (!auxiliar.isEmpty()) {
                    String au = (String) auxiliar.remove(0);
                    clienteCEditado.addItem(au);
                }
            }
        });

        JLabel background = new JLabel();

        background.add(DatosObras);
        add(background);
        setVisible(true);

    }

    //Aqui hacemos la conexión a la BDD
    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://ns64.hostgator.mx:3306/dirtycod_constructora?autoReconnect=true&useSSL=false", "dirtycod_dirty", "dirtycode");
            System.out.println("Se concecto Correctamente ");

        } catch (Exception e) {
            System.err.println("Hubo un error en la instalacion " + e);
        }
        return con;
    }

    public String recuperarDato(String consulta, String columna) {
        String dato = null;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                String aux = String.valueOf(rs.getObject(columna));
                dato = aux;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos de la base de datos\n" + e.toString());
        }
        return dato;
    }

    public java.util.List<Object> recuperarDatos(String consulta, String columna) {
        java.util.List<Object> datos = new ArrayList<Object>();
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            int i = 0;
            while (rs.next()) {
                String dat = rs.getString(columna);
                i++;
                datos.add(dat);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos de la base de datos\n" + e.toString());
        }
        return datos;
    }

    public String[] recuperarDatosObra(String consulta) {
        String[] datos = new String[16];
        try {            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            rs.next();
            datos[0] = rs.getString(2);//nombre de la obra
            datos[1] = rs.getString(3);//nombre de la empresa
            datos[2] = rs.getString(4);//nombre del responsable
            datos[3] = rs.getString(5);//apellido paterno del responsable
            datos[4] = rs.getString(6);//apellido materno del responsable
            datos[5] = rs.getString(7);//calle donde esta la obra
            datos[6] = rs.getString(8);//numero de la  calle de la obra
            datos[7] = rs.getString(9);////colonia donde esta la obra
            datos[8] = rs.getString(10);//municipio donde esta la obra
            datos[9] = rs.getString(11);//cp de la ubicaion de la obra
            datos[10] = rs.getString(12);//estado donde se ubica la obra
            datos[11] = String.valueOf(rs.getDate(13));//fecha inicio
            datos[12] = String.valueOf(rs.getDate(15));//fecha final
            datos[13] = String.valueOf(rs.getDouble(16));//inversion
            datos[14] = rs.getString(17);//numero del responsable
            datos[15] = rs.getString(18);//correo del responsable
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos de la base de datos\n" + e.toString());
        }
        for (int i = 0; i < datos.length; i++) {
            System.out.println(datos[i].toString());
        }
        return datos;
    }

    /*public static void main(String[]args){
		
		new EditarObra();
	}*/
}
