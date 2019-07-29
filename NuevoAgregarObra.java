
import java.awt.*;

import javax.swing.*;

import java.awt.Image;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NuevoAgregarObra extends JFrame {

    Connection conexion;

    NuevoAgregarObra() {
        conexion = getConexion();

        setSize(1385, 768);
        setTitle("Agregar obras");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        JPanel DatosObras = new JPanel();
        DatosObras.setLayout(null);
        DatosObras.setSize(1366, 768);
        DatosObras.setBackground(Color.black);

        JLabel nombreResponsable = new JLabel("Datos del responsable de la obra:");
        nombreResponsable.setForeground(Color.decode("#049cff"));
        Font fuenteResponsable = new Font("Arial Black", Font.BOLD, 20);
        nombreResponsable.setFont(fuenteResponsable);
        nombreResponsable.setBounds(500, 0, 500, 40);
        DatosObras.add(nombreResponsable);

        JLabel nombresResponsable = new JLabel("Nombre(s):");
        nombresResponsable.setForeground(Color.white);
        Font fuenteResponsables = new Font("Arial", Font.BOLD, 20);
        nombresResponsable.setFont(fuenteResponsables);
        nombresResponsable.setBounds(0, 40, 300, 20);
        DatosObras.add(nombresResponsable);

        CampoDato NombreResponsabletxt = new CampoDato();
        NombreResponsabletxt.setForeground(Color.black);
        NombreResponsabletxt.setBounds(105, 40, 200, 30);
        NombreResponsabletxt.setBorder(null);
        NombreResponsabletxt.setTipo('T');
        NombreResponsabletxt.setLongitud(20);
        DatosObras.add(NombreResponsabletxt);

        JLabel ApellidoResponsablePaterno = new JLabel("Apellido paterno:");
        ApellidoResponsablePaterno.setForeground(Color.white);
        Font fuenteResponsablesP = new Font("Arial", Font.BOLD, 20);
        ApellidoResponsablePaterno.setFont(fuenteResponsablesP);
        ApellidoResponsablePaterno.setBounds(315, 40, 300, 20);
        DatosObras.add(ApellidoResponsablePaterno);

        CampoDato ApellidoResponsablePaternotxt = new CampoDato();
        ApellidoResponsablePaternotxt.setForeground(Color.black);
        ApellidoResponsablePaternotxt.setBounds(480, 40, 200, 30);
        ApellidoResponsablePaternotxt.setBorder(null);
        ApellidoResponsablePaternotxt.setTipo('T');
        ApellidoResponsablePaternotxt.setLongitud(20);
        DatosObras.add(ApellidoResponsablePaternotxt);

        JLabel ApellidoResponsableMaterno = new JLabel("Apellido materno:");
        ApellidoResponsableMaterno.setForeground(Color.white);
        Font fuenteResponsablesMaterno = new Font("Arial", Font.BOLD, 20);
        ApellidoResponsableMaterno.setFont(fuenteResponsables);
        ApellidoResponsableMaterno.setBounds(690, 40, 300, 20);
        DatosObras.add(ApellidoResponsableMaterno);

        CampoDato ApellidoResponsableMaternotxt = new CampoDato();
        ApellidoResponsableMaternotxt.setForeground(Color.black);
        ApellidoResponsableMaternotxt.setBounds(860, 40, 200, 30);
        ApellidoResponsableMaternotxt.setBorder(null);
        ApellidoResponsableMaternotxt.setTipo('T');
        ApellidoResponsableMaternotxt.setLongitud(20);
        DatosObras.add(ApellidoResponsableMaternotxt);

        JLabel Monto = new JLabel("Monto de la obra: $");
        Monto.setForeground(Color.white);
        Font fuenteMonto = new Font("Arial", Font.BOLD, 20);
        Monto.setFont(fuenteMonto);
        Monto.setBounds(1065, 40, 300, 20);
        DatosObras.add(Monto);

        CampoDato Montotxt = new CampoDato();
        Montotxt.setForeground(Color.black);
        Montotxt.setBounds(1250, 40, 70, 30);
        Montotxt.setBorder(null);
        Montotxt.setTipo('D');
        Montotxt.setLongitud(20);
        DatosObras.add(Montotxt);

        JLabel Telefono = new JLabel("Télefono:");
        Telefono.setForeground(Color.white);
        Font fuenteTelefono = new Font("Arial", Font.BOLD, 20);
        Telefono.setFont(fuenteTelefono);
        Telefono.setBounds(0, 80, 300, 20);
        DatosObras.add(Telefono);

        CampoDato Telefonotxt = new CampoDato();
        Telefonotxt.setForeground(Color.black);
        Telefonotxt.setBounds(107, 80, 200, 30);
        Telefonotxt.setBorder(null);
        Telefonotxt.setTipo('E');
        Telefonotxt.setLongitud(15);
        DatosObras.add(Telefonotxt);

        JLabel Correo = new JLabel("E-mail:");
        Correo.setForeground(Color.white);
        Font fuenteCorreo = new Font("Arial", Font.BOLD, 20);
        Correo.setFont(fuenteCorreo);
        Correo.setBounds(410, 80, 300, 20);
        DatosObras.add(Correo);

        JTextField Correotxt = new JTextField();
        Correotxt.setForeground(Color.black);
        Correotxt.setBounds(480, 80, 200, 30);
        Correotxt.setBorder(null);
        DatosObras.add(Correotxt);

        JLabel cliente = new JLabel("Cliente:");
        cliente.setForeground(Color.white);
        Font fuenteCliente = new Font("Arial", Font.BOLD, 20);
        cliente.setFont(fuenteCliente);
        cliente.setBounds(785, 80, 300, 20);
        DatosObras.add(cliente);

        //se rellena el comboBox con los clientes ya registrados
        List<Object> allClientes = recuperarDatos("SELECT * FROM CLIENTE", "NOMBRE_CLIENTE");
        System.out.println(allClientes);
        JComboBox clienteC = new JComboBox(allClientes.toArray());
        clienteC.setForeground(Color.black);
        clienteC.setBorder(null);
        clienteC.setBounds(860, 80, 200, 30);
        DatosObras.add(clienteC);

        JLabel DomicilioEditar = new JLabel("Ubicación de la obra:");
        DomicilioEditar.setForeground(Color.decode("#049cff"));
        Font fuenteDomicilio = new Font("Arial Black", Font.BOLD, 20);
        DomicilioEditar.setFont(fuenteDomicilio);
        DomicilioEditar.setBounds(550, 35, 600, 200);
        DatosObras.add(DomicilioEditar);

        JLabel Calle = new JLabel("Calle:");
        Calle.setForeground(Color.white);
        Font fontCalle = new Font("Arial", Font.BOLD, 20);
        Calle.setFont(fontCalle);
        Calle.setBounds(0, 150, 300, 20);
        DatosObras.add(Calle);

        JTextField Calletxt = new JTextField();
        Calletxt.setForeground(Color.black);
        Calletxt.setBounds(105, 150, 200, 30);
        Calletxt.setBorder(null);
        DatosObras.add(Calletxt);

        JLabel Numero = new JLabel("Número:");
        Numero.setForeground(Color.white);
        Font fontNumero = new Font("Arial", Font.BOLD, 20);
        Numero.setFont(fontNumero);
        Numero.setBounds(397, 150, 300, 20);
        DatosObras.add(Numero);

        CampoDato Numtxt = new CampoDato();
        Numtxt.setForeground(Color.black);
        Numtxt.setBounds(480, 150, 50, 30);
        Numtxt.setBorder(null);
        Numtxt.setTipo('E');
        Numtxt.setLongitud(10);
        DatosObras.add(Numtxt);

        JLabel colonia = new JLabel("Colonia:");
        colonia.setForeground(Color.white);
        Font fontColonia = new Font("Arial", Font.BOLD, 20);
        colonia.setFont(fontColonia);
        colonia.setBounds(780, 150, 300, 20);
        DatosObras.add(colonia);

        CampoDato Coltxt = new CampoDato();
        Coltxt.setForeground(Color.black);
        Coltxt.setBounds(858, 150, 200, 30);
        Coltxt.setBorder(null);
        Coltxt.setTipo('T');
        Coltxt.setLongitud(30);
        DatosObras.add(Coltxt);

        JLabel Municipio = new JLabel("Municipio:");
        Municipio.setForeground(Color.white);
        Font fontMunicipio = new Font("Arial", Font.BOLD, 20);
        Municipio.setFont(fontMunicipio);
        Municipio.setBounds(1080, 150, 300, 20);
        DatosObras.add(Municipio);

        CampoDato Municipiotxt = new CampoDato();
        Municipiotxt.setForeground(Color.black);
        Municipiotxt.setBounds(1180, 150, 170, 30);
        Municipiotxt.setBorder(null);
        Municipiotxt.setTipo('T');
        Municipiotxt.setLongitud(30);
        DatosObras.add(Municipiotxt);

        JLabel Estado = new JLabel("Estado:");
        Estado.setForeground(Color.white);
        Font fontEstado = new Font("Arial", Font.BOLD, 20);
        Estado.setFont(fontEstado);
        Estado.setBounds(0, 190, 300, 20);
        DatosObras.add(Estado);

        CampoDato Estadotxt = new CampoDato();
        Estadotxt.setForeground(Color.black);
        Estadotxt.setBounds(105, 190, 200, 30);
        Estadotxt.setBorder(null);
        Estadotxt.setTipo('T');
        Estadotxt.setLongitud(30);
        DatosObras.add(Estadotxt);
        
        JLabel cp = new JLabel("CP:");
        cp.setForeground(Color.white);
        Font fontcp = new Font("Arial", Font.BOLD, 20);
        cp.setFont(fontEstado);
        cp.setBounds(443, 190, 300, 20);
        DatosObras.add(cp);

        CampoDato cptxt = new CampoDato();
        cptxt.setForeground(Color.black);
        cptxt.setBounds(480, 190, 50, 30);
        cptxt.setBorder(null);
        cptxt.setTipo('E');
        cptxt.setLongitud(30);
        DatosObras.add(cptxt);

        ///Agregado
        JLabel NombreObra = new JLabel("Nombre de la obra:");
        NombreObra.setForeground(Color.white);
        Font fontNObra = new Font("Arial", Font.BOLD, 20);
        NombreObra.setFont(fontNObra);
        NombreObra.setBounds(675, 190, 300, 20);
        DatosObras.add(NombreObra);

        CampoDato NombreObratxt = new CampoDato();
        NombreObratxt.setForeground(Color.black);
        NombreObratxt.setBounds(858, 190, 200, 30);
        NombreObratxt.setBorder(null);
        NombreObratxt.setTipo('T');
        NombreObratxt.setLongitud(50);
        DatosObras.add(NombreObratxt);

        JLabel anuncioMaquinaria = new JLabel("Tipos y cantidades de máquinaria para la obra:");
        anuncioMaquinaria.setForeground(Color.decode("#049cff"));
        Font fuenteMaquinaria = new Font("Arial Black", Font.BOLD, 20);
        anuncioMaquinaria.setFont(fuenteMaquinaria);
        anuncioMaquinaria.setBounds(400, 100, 700, 300);
        DatosObras.add(anuncioMaquinaria);

        //boton para agregar la maquinaria 
        JButton agregarMaquinaria = new JButton("Agregar");
        agregarMaquinaria.setBackground(Color.black);
        agregarMaquinaria.setBounds(900, 330, 200, 30);
        Font fontAgregar = new Font("Arial", Font.BOLD, 20);
        agregarMaquinaria.setFont(fontAgregar);
        agregarMaquinaria.setBorder(new ComponenteBotonRedondo(50));
        agregarMaquinaria.setForeground(Color.decode("#049cff"));
        agregarMaquinaria.setEnabled(false);
        DatosObras.add(agregarMaquinaria);

        //boton para editar  la maquinaria 
        JButton editarMaquinaria = new JButton("Editar");
        editarMaquinaria.setBackground(Color.black);
        editarMaquinaria.setBounds(1150, 330, 200, 30);
        Font fontEditar = new Font("Arial", Font.BOLD, 20);
        editarMaquinaria.setFont(fontAgregar);
        editarMaquinaria.setBorder(new ComponenteBotonRedondo(50));
        editarMaquinaria.setForeground(Color.decode("#049cff"));
        DatosObras.add(editarMaquinaria);

        JLabel TipoMaquinaria = new JLabel("Tipo de maquinaria:");
        TipoMaquinaria.setForeground(Color.white);
        Font fuenteMaquina = new Font("Arial", Font.BOLD, 20);
        TipoMaquinaria.setFont(fuenteMaquina);
        TipoMaquinaria.setBounds(0, 220, 300, 150);
        DatosObras.add(TipoMaquinaria);

        //se agregan los tipos de maquinaria al comboBox
        List<Object> allTipos = recuperarDatos("SELECT TIPO_MAQ FROM Maquinaria GROUP BY TIPO_MAQ", "TIPO_MAQ");
        JComboBox TipoMC = new JComboBox(allTipos.toArray());
        TipoMC.setForeground(Color.black);
        TipoMC.setBorder(null);
        TipoMC.setBounds(190, 280, 200, 30);
        DatosObras.add(TipoMC);

        JLabel ModeloMaquinaria = new JLabel("Modelo de la maquinaria:");
        ModeloMaquinaria.setForeground(Color.white);
        Font fuenteModeloM = new Font("Arial", Font.BOLD, 20);
        ModeloMaquinaria.setFont(fuenteModeloM);
        ModeloMaquinaria.setBounds(455, 220, 300, 150);
        DatosObras.add(ModeloMaquinaria);

        //se agregan los modelos de la maquina inicial
        String consultaModelos = "SELECT MODELO_MAQ FROM Maquinaria WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem() + "' GROUP BY MODELO_MAQ";
        List<Object> allModelos = recuperarDatos(consultaModelos, "MODELO_MAQ");
        JComboBox MaquinariaC = new JComboBox(allModelos.toArray());
        MaquinariaC.setForeground(Color.black);
        MaquinariaC.setBounds(692, 280, 200, 30);
        MaquinariaC.setBorder(null);
        DatosObras.add(MaquinariaC);

        JLabel CantidadMaquinas = new JLabel("Cantidad de máquinas para obra:");
        CantidadMaquinas.setForeground(Color.white);
        Font fuenteCantidadMaquinas = new Font("Arial", Font.BOLD, 20);
        CantidadMaquinas.setFont(fuenteCantidadMaquinas);
        CantidadMaquinas.setBounds(980, 220, 500, 150);
        DatosObras.add(CantidadMaquinas);

        //se limita el spinner con la cantidad de maquinaria disponible del tipo y modelo indicado
        String consultaCantidad = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                + " WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaC.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                + "GROUP BY TIPO_MAQ,MODELO_MAQ";
        Object cantidadFinal = recuperarDato(consultaCantidad, "Disponibles");
        int disponibles = Integer.parseInt(cantidadFinal == null ? "0" : (String) cantidadFinal);
        JSpinner CantidadSpiner = new JSpinner(new SpinnerNumberModel(0, 0, disponibles, 1));
        CantidadSpiner.setForeground(Color.black);
        CantidadSpiner.setBounds(1299, 280, 50, 30);
        CantidadSpiner.setBorder(null);
        DatosObras.add(CantidadSpiner);

        //cada vez que cambie de un tipo de maquinaria se actualizara el modelo automaticamente y el spinner delimitante de ellos
        TipoMC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                String consultaModelosNueva = "SELECT MODELO_MAQ FROM Maquinaria WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem() + "' GROUP BY MODELO_MAQ";
                List<Object> auxiliar = recuperarDatos(consultaModelosNueva, "MODELO_MAQ");
                MaquinariaC.setModel(new DefaultComboBoxModel(auxiliar.toArray()));
                String consultaCantidadNueva = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                        + " WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaC.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                        + "GROUP BY TIPO_MAQ,MODELO_MAQ";
                Object cantidadfinalNueva = recuperarDato(consultaCantidadNueva, "Disponibles");
                int disponibles = Integer.parseInt(cantidadfinalNueva == null ? "0" : (String) cantidadfinalNueva);
                CantidadSpiner.setModel(new SpinnerNumberModel(0, 0, disponibles, 1));
                agregarMaquinaria.setEnabled(false);
            }
        });

        //se actualiza el spinner delimitante del modelo indicado
        MaquinariaC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                String consultaCantidadNueva = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                        + " WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaC.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                        + "GROUP BY TIPO_MAQ,MODELO_MAQ";
                Object cantidadFinalNueva = recuperarDato(consultaCantidadNueva, "Disponibles");
                int disonibles = Integer.parseInt(cantidadFinalNueva == null ? "0" : (String) cantidadFinalNueva);
                CantidadSpiner.setModel(new SpinnerNumberModel(0, 0, disonibles, 1));
                agregarMaquinaria.setEnabled(false);
            }
        });

        JPanel imagen = new JPanel();
        imagen.setBackground(Color.decode("#049cff"));
        //imagen.setBounds(950, 400, 400, 200);
        imagen.setBounds(850, 400, 200, 200);
        DatosObras.add(imagen);

        DefaultListModel<String> lista = new DefaultListModel();
        JList<String> list = new JList<>(lista);
        list.setBounds(1100, 400, 250, 200);
        DatosObras.add(list);

        //se agrega a una lista las maquinas requeridas 
        agregarMaquinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String tipo = (String) TipoMC.getSelectedItem();
                String modelo = (String) MaquinariaC.getSelectedItem();
                int cantidad = (int) CantidadSpiner.getValue();
                lista.addElement(String.format("%s    /     %s    /     %d", tipo, modelo, cantidad));

                SpinnerNumberModel aux = (SpinnerNumberModel) CantidadSpiner.getModel();
                int menos = (int) aux.getMaximum() - (int) CantidadSpiner.getValue();
                CantidadSpiner.setModel(new SpinnerNumberModel(0, 0, menos, 1));
                if ((int) CantidadSpiner.getValue() == 0) {
                    agregarMaquinaria.setEnabled(false);
                }
            }
        });

        CantidadSpiner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                if ((int) CantidadSpiner.getValue() == 0) {
                    agregarMaquinaria.setEnabled(false);
                } else {
                    agregarMaquinaria.setEnabled(true);
                }
            }
        });

        JLabel FechaInicio = new JLabel("Fecha de inicio de la obra:");
        FechaInicio.setForeground(Color.white);
        Font fuenteFechaI = new Font("Arial", Font.BOLD, 20);
        FechaInicio.setFont(fuenteFechaI);
        FechaInicio.setBounds(0, 230, 300, 300);
        DatosObras.add(FechaInicio);

        JCalendar FechaI = new JCalendar();
        FechaI.setForeground(Color.black);
        FechaI.setBorder(null);
        FechaI.setBounds(0, 400, 390, 200);
        DatosObras.add(FechaI);

        JLabel FechaFinal = new JLabel("Fecha final de la obra:");
        FechaFinal.setForeground(Color.white);
        Font fuenteFechaF = new Font("Arial", Font.BOLD, 20);
        FechaFinal.setFont(fuenteFechaF);
        //FechaFinal.setBounds(478, 230, 300, 300);
        FechaFinal.setBounds(400, 230, 300, 300);
        DatosObras.add(FechaFinal);

        JCalendar FechaF = new JCalendar();
        FechaF.setForeground(Color.black);
        FechaF.setBorder(null);
        //  FechaF.setBounds(480, 400, 390, 200);
        FechaF.setBounds(402, 400, 390, 200);
        DatosObras.add(FechaF);

        JButton AgregarInformaciónEditar = new JButton("Guardar información");
        AgregarInformaciónEditar.setBackground(Color.black);
        AgregarInformaciónEditar.setBounds(380, 640, 300, 50);
        Font fontBoton = new Font("Arial", Font.BOLD, 20);
        AgregarInformaciónEditar.setFont(fontBoton);
        AgregarInformaciónEditar.setBorder(new ComponenteBotonRedondo(50));
        AgregarInformaciónEditar.setForeground(Color.decode("#049cff"));
        DatosObras.add(AgregarInformaciónEditar);
        AgregarInformaciónEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SimpleDateFormat ff = new SimpleDateFormat("YYYY-MM-dd");
                try {
                    PreparedStatement psd; // variable para la BDD
                    // primer consulta a la tabla OBRA --->
                    psd = conexion.prepareStatement("INSERT INTO OBRA (NOMBRE_OBRA,NOMBRE_CLIENTE,NOMBRE_RESPONSABLE,AP_PAT,AP_MAT,"
                            + "CALLE_OBRA,NUMERO_CALLE,COLONIA,MUNICIPIO,CP,ESTADO,FECHA_INICIO,"
                            + "FECHA_FIN,INVERSION,TELEFONO_RESP,CORREO_RESP,IDCLIENTE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    psd.setString(1, NombreObratxt.getText());//nombre obra
                    psd.setString(2, (String)clienteC.getSelectedItem());//nombre empresa
                    psd.setString(3, NombreResponsabletxt.getText());//nombre responsable
                    psd.setString(4, ApellidoResponsablePaternotxt.getText());//apellido paterno responsable
                    psd.setString(5, ApellidoResponsableMaternotxt.getText());//apellido materno responsable
                    psd.setString(6, Calletxt.getText());//calle de la obra
                    psd.setString(7, Numtxt.getText().isEmpty()?"S/N":Numtxt.getText());//numero de la calle donde esta la obra
                    psd.setString(8, Coltxt.getText());//colonia donde esta la obra
                    psd.setString(9, Municipiotxt.getText());//municipio donde esta la obra
                    psd.setString(10, cptxt.getText());//cp donde se ubica la obra
                    psd.setString(11, Estadotxt.getText());//estado donde se ubica la obra
                    psd.setDate(12, Date.valueOf(ff.format(FechaI.getDate())));//fecha inicio de la obra
                    //psd.setInt(7, 12);
                    psd.setDate(13, Date.valueOf(ff.format(FechaF.getDate())));//fecha final de la obra
                    psd.setDouble(14, Double.parseDouble(Montotxt.getText()));//monto o invercion de la obra                    
                    psd.setString(15, Telefonotxt.getText());//telefono del responsable 
                    psd.setString(16, Correotxt.getText());//correo del responsable       
                    String consulta = "SELECT * FROM CLIENTE WHERE NOMBRE_CLIENTE = '" + clienteC.getSelectedItem() + "'";
                    int id = Integer.parseInt(recuperarDato(consulta, "IDCLIENTE"));
                    psd.setInt(17, id);//id del cliente                                       
                    //psd.setString(a, consulta);                                 

                    //PreparedStatement segundaConexiuon=cn.prepareStatement("INSERT INTO MAQUINARIA (ESTADO_MAQ) VALUES ('EN USO')");
                    //String modicar[] = list.getSelectedValue().replaceAll(" ", "").split("/");
                    int res = psd.executeUpdate();
                    if (res < 0) {
                        JOptionPane.showMessageDialog(null, "No se pudo añadir el registro");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NuevoAgregarObra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton AgregarCliente = new JButton("Agregar Cliente ");
        AgregarCliente.setBackground(Color.black);
        AgregarCliente.setBounds(720, 640, 300, 50);
        Font fontBotonCliente = new Font("Arial", Font.BOLD, 20);
        AgregarCliente.setFont(fontBotonCliente);
        AgregarCliente.setBorder(new ComponenteBotonRedondo(50));
        AgregarCliente.setForeground(Color.decode("#049cff"));
        DatosObras.add(AgregarCliente);
        AgregarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //new AgregarCliente();
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
                List<Object> auxiliar = recuperarDatos("SELECT * FROM CLIENTE", "NOMBRE_CLIENTE");
                clienteC.setModel(new DefaultComboBoxModel(auxiliar.toArray()));
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

    //recupera un dato en espesifico de la base de datos
    public String recuperarDato(String consulta, String columna) {
        String dato = null;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            rs.next();
            try {
                dato = String.valueOf(rs.getObject(columna));
            } catch (Exception ex) {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos de la base de datos\n" + e.toString());
        }
        return dato;
    }

    //recupera una lista de datos de la bsae de datos de solo una columna
    public List<Object> recuperarDatos(String consulta, String columna) {
        List<Object> datos = new ArrayList<Object>();
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

    public static void main(String[] args) {

        new NuevoAgregarObra();
    }

}