
import java.awt.*;

import javax.swing.*;

import java.awt.Image;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NuevoAgregarObra extends JFrame {

    Connection conexion;
    JLabel imagen;

    NuevoAgregarObra(Connection conexion) {
        this.conexion = conexion;

        setSize(1385, 768);
        setTitle("Agregar obras");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        //panel donde se guardaran todos componentes
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

        //campo donde se escibre el nombre del resposable
        CampoDato NombreResponsabletxt = new CampoDato();
        NombreResponsabletxt.setForeground(Color.black);
        NombreResponsabletxt.setBounds(105, 40, 200, 30);
        NombreResponsabletxt.setBorder(null);
        //se restringe solo a texto
        NombreResponsabletxt.setTipo('T');
        //se limita la cantidad de letras que puede escribir
        NombreResponsabletxt.setLongitud(30);
        DatosObras.add(NombreResponsabletxt);

        JLabel ApellidoResponsablePaterno = new JLabel("Apellido paterno:");
        ApellidoResponsablePaterno.setForeground(Color.white);
        Font fuenteResponsablesP = new Font("Arial", Font.BOLD, 20);
        ApellidoResponsablePaterno.setFont(fuenteResponsablesP);
        ApellidoResponsablePaterno.setBounds(315, 40, 300, 20);
        DatosObras.add(ApellidoResponsablePaterno);

        //campo donde se agrega el apellido paterno del responsable
        CampoDato ApellidoResponsablePaternotxt = new CampoDato();
        ApellidoResponsablePaternotxt.setForeground(Color.black);
        ApellidoResponsablePaternotxt.setBounds(480, 40, 200, 30);
        ApellidoResponsablePaternotxt.setBorder(null);
        //se restringe solo a texto
        ApellidoResponsablePaternotxt.setTipo('T');
        //se limita la cantidad de letras que puede escribir
        ApellidoResponsablePaternotxt.setLongitud(30);
        DatosObras.add(ApellidoResponsablePaternotxt);

        JLabel ApellidoResponsableMaterno = new JLabel("Apellido materno:");
        ApellidoResponsableMaterno.setForeground(Color.white);
        Font fuenteResponsablesMaterno = new Font("Arial", Font.BOLD, 20);
        ApellidoResponsableMaterno.setFont(fuenteResponsables);
        ApellidoResponsableMaterno.setBounds(690, 40, 300, 20);
        DatosObras.add(ApellidoResponsableMaterno);

        //campo donde se escribe el apellido materno del responsable
        CampoDato ApellidoResponsableMaternotxt = new CampoDato();
        ApellidoResponsableMaternotxt.setForeground(Color.black);
        ApellidoResponsableMaternotxt.setBounds(860, 40, 200, 30);
        ApellidoResponsableMaternotxt.setBorder(null);
        //se restringe solo a texto
        ApellidoResponsableMaternotxt.setTipo('T');
        //se limita la cantidad de letras que puede escribir
        ApellidoResponsableMaternotxt.setLongitud(30);
        DatosObras.add(ApellidoResponsableMaternotxt);

        JLabel Monto = new JLabel("Monto de la obra: $");
        Monto.setForeground(Color.white);
        Font fuenteMonto = new Font("Arial", Font.BOLD, 20);
        Monto.setFont(fuenteMonto);
        Monto.setBounds(1065, 40, 300, 20);
        DatosObras.add(Monto);

        //campo donde se escribe la cantidad que costara la obra
        CampoDato Montotxt = new CampoDato();
        Montotxt.setForeground(Color.black);
        Montotxt.setBounds(1250, 40, 70, 30);
        Montotxt.setBorder(null);
        //se restringe solo a numeros con la posibilidad de que sean decimales
        Montotxt.setTipo('D');
        //se limita la cantidad de letras que puede escribir
        Montotxt.setLongitud(20);
        DatosObras.add(Montotxt);

        JLabel Telefono = new JLabel("Télefono:");
        Telefono.setForeground(Color.white);
        Font fuenteTelefono = new Font("Arial", Font.BOLD, 20);
        Telefono.setFont(fuenteTelefono);
        Telefono.setBounds(0, 80, 300, 20);
        DatosObras.add(Telefono);

        //capo donde se escribe el telefono del responsable
        CampoDato Telefonotxt = new CampoDato();
        Telefonotxt.setForeground(Color.black);
        Telefonotxt.setBounds(107, 80, 200, 30);
        Telefonotxt.setBorder(null);
        //se restringe solo a numeros sin poder ser decimales
        Telefonotxt.setTipo('E');
        //se limita la cantidad de letras que puede escribir
        Telefonotxt.setLongitud(15);
        DatosObras.add(Telefonotxt);

        JLabel Correo = new JLabel("E-mail:");
        Correo.setForeground(Color.white);
        Font fuenteCorreo = new Font("Arial", Font.BOLD, 20);
        Correo.setFont(fuenteCorreo);
        Correo.setBounds(410, 80, 300, 20);
        DatosObras.add(Correo);

        //campo donde se escribe el correo del responsable
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

        //campo donde se agrega la called de la obra
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

        //campo donde se agrega el numero de la calle de la obra
        CampoDato Numtxt = new CampoDato();
        Numtxt.setForeground(Color.black);
        Numtxt.setBounds(480, 150, 50, 30);
        Numtxt.setBorder(null);
        //se restringe solo a numeros sin la posibilidad de que sean decimales
        Numtxt.setTipo('E');
        //se limita la cantidad de letras que puede escribir
        Numtxt.setLongitud(11);
        DatosObras.add(Numtxt);

        JLabel colonia = new JLabel("Colonia:");
        colonia.setForeground(Color.white);
        Font fontColonia = new Font("Arial", Font.BOLD, 20);
        colonia.setFont(fontColonia);
        colonia.setBounds(780, 150, 300, 20);
        DatosObras.add(colonia);

        //capo donde se agrega la colonia donde ests obicada la obra
        CampoDato Coltxt = new CampoDato();
        Coltxt.setForeground(Color.black);
        Coltxt.setBounds(858, 150, 200, 30);
        Coltxt.setBorder(null);
        //se restringe solo a texto el campo
        Coltxt.setTipo('T');
        //se limita la cantidad de letras que puede escribir        
        Coltxt.setLongitud(30);
        DatosObras.add(Coltxt);

        JLabel Municipio = new JLabel("Municipio:");
        Municipio.setForeground(Color.white);
        Font fontMunicipio = new Font("Arial", Font.BOLD, 20);
        Municipio.setFont(fontMunicipio);
        Municipio.setBounds(1080, 150, 300, 20);
        DatosObras.add(Municipio);

        //campo donde se escribe el municipio donde se ubica la obra
        CampoDato Municipiotxt = new CampoDato();
        Municipiotxt.setForeground(Color.black);
        Municipiotxt.setBounds(1180, 150, 170, 30);
        Municipiotxt.setBorder(null);
        //se restringe solo a texto el campo
        Municipiotxt.setTipo('T');
        //se limita la cantidad de letras que puede escribir
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
        //se restringe el solo a texto el campo
        Estadotxt.setTipo('T');
        //se limita la cantidad de letras que puede escribir
        Estadotxt.setLongitud(30);
        DatosObras.add(Estadotxt);

        JLabel cp = new JLabel("CP:");
        cp.setForeground(Color.white);
        Font fontcp = new Font("Arial", Font.BOLD, 20);
        cp.setFont(fontEstado);
        cp.setBounds(443, 190, 300, 20);
        DatosObras.add(cp);

        //campo donde se escribe el CP donde se ubuca la obra
        CampoDato cptxt = new CampoDato();
        cptxt.setForeground(Color.black);
        cptxt.setBounds(480, 190, 50, 30);
        cptxt.setBorder(null);
        //se resting a numeros sin la posibidad de ser decimales
        cptxt.setTipo('E');
        //se limita la cantidad de letras que puede escribir
        cptxt.setLongitud(30);
        DatosObras.add(cptxt);

        ///Agregado
        JLabel NombreObra = new JLabel("Nombre de la obra:");
        NombreObra.setForeground(Color.white);
        Font fontNObra = new Font("Arial", Font.BOLD, 20);
        NombreObra.setFont(fontNObra);
        NombreObra.setBounds(675, 190, 300, 20);
        DatosObras.add(NombreObra);

        //campo donde se escribe el nombre de la obra
        CampoDato NombreObratxt = new CampoDato();
        NombreObratxt.setForeground(Color.black);
        NombreObratxt.setBounds(858, 190, 200, 30);
        NombreObratxt.setBorder(null);
        //se restringe solo a texto el campo
        NombreObratxt.setTipo('T');
        //se limita la cantidad de letras que puede escribir
        NombreObratxt.setLongitud(50);
        DatosObras.add(NombreObratxt);

        JLabel anuncioMaquinaria = new JLabel("Tipos y cantidades de máquinaria para la obra:");
        anuncioMaquinaria.setForeground(Color.decode("#049cff"));
        Font fuenteMaquinaria = new Font("Arial Black", Font.BOLD, 20);
        anuncioMaquinaria.setFont(fuenteMaquinaria);
        anuncioMaquinaria.setBounds(400, 100, 700, 300);
        DatosObras.add(anuncioMaquinaria);

        //boton para agregar la maquinaria a la lista
        JButton agregarMaquinaria = new JButton("Agregar");
        agregarMaquinaria.setBackground(Color.black);
        agregarMaquinaria.setBounds(900, 320, 200, 30);
        Font fontAgregar = new Font("Arial", Font.BOLD, 20);
        agregarMaquinaria.setFont(fontAgregar);
        agregarMaquinaria.setBorder(new ComponenteBotonRedondo(50));
        agregarMaquinaria.setForeground(Color.decode("#049cff"));
        //se desactiva el boton agregar maquina debido a que si la disponibilidad es 0 no se puede agregar una maquina
        agregarMaquinaria.setEnabled(false);
        DatosObras.add(agregarMaquinaria);

        //boton para editar  la maquinaria  de la lista
        JButton editarMaquinaria = new JButton("Editar");
        editarMaquinaria.setBackground(Color.black);
        editarMaquinaria.setBounds(1150, 320, 200, 30);
        Font fontEditar = new Font("Arial", Font.BOLD, 20);
        editarMaquinaria.setFont(fontEditar);
        editarMaquinaria.setBorder(new ComponenteBotonRedondo(50));
        editarMaquinaria.setForeground(Color.decode("#049cff"));
        DatosObras.add(editarMaquinaria);

        //boton para eliminar  la maquinaria  de la lista
        JButton eliminarMaquinaria = new JButton("Eliminar");
        eliminarMaquinaria.setBackground(Color.black);
        eliminarMaquinaria.setBounds(1020, 360, 200, 30);
        Font fontEliminar = new Font("Arial", Font.BOLD, 20);
        eliminarMaquinaria.setFont(fontEliminar);
        eliminarMaquinaria.setBorder(new ComponenteBotonRedondo(50));
        eliminarMaquinaria.setForeground(Color.decode("#049cff"));
        DatosObras.add(eliminarMaquinaria);

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
        //si la maquina seleccionada y el modelo seleccionado no tienen maquinas disponibles se le accina a la disponibilidad 0
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
                //se optienen la lista de modelos de la maquina se leccionada
                List<Object> auxiliar = recuperarDatos(consultaModelosNueva, "MODELO_MAQ");
                //se le asigna los modelos correspondientes de la nueva maquina seleccionada
                MaquinariaC.setModel(new DefaultComboBoxModel(auxiliar.toArray()));
                String consultaCantidadNueva = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                        + " WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaC.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                        + "GROUP BY TIPO_MAQ,MODELO_MAQ";
                Object cantidadfinalNueva = recuperarDato(consultaCantidadNueva, "Disponibles");
                //si la maquina seleccionada y el modelo seleccionado no tienen maquinas disponibles se le accina a la disponibilidad 0
                int disponibles = Integer.parseInt(cantidadfinalNueva == null ? "0" : (String) cantidadfinalNueva);
                CantidadSpiner.setModel(new SpinnerNumberModel(0, 0, disponibles, 1));
                //se desactiva el boton agregar maquina debido a que si la disponibilidad es 0 no se puede agregar una maquina
                agregarMaquinaria.setEnabled(false);
                String consultaImagenNueva = "SELECT * FROM Maquinaria" + " WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem()
                        + "'GROUP BY TIPO_MAQ";
                //Para que la imagen se redimensione
                ImageIcon foto = getImagen(consultaImagenNueva, "IMAGEN_MAQ");
                if (foto != null) {
                    Image foton = foto.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_DEFAULT);
                    foto = new ImageIcon(foton);
                    //se establece la imagen del tipo de maquina seleccionada
                    imagen.setIcon(foto);
                } else {
                    imagen.removeAll();
                    imagen.setText("No Existe una Imagen el la base de Datos");
                }
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
                //si la maquina seleccionada y el modelo seleccionado no tienen maquinas disponibles se le accina a la disponibilidad 0
                int disonibles = Integer.parseInt(cantidadFinalNueva == null ? "0" : (String) cantidadFinalNueva);
                CantidadSpiner.setModel(new SpinnerNumberModel(0, 0, disonibles, 1));
                //se desactiva el boton agregar maquina debido a que si la disponibilidad es 0 no se puede agregar una maquina
                agregarMaquinaria.setEnabled(false);
            }
        });

        String consultaImagen = "SELECT * FROM Maquinaria" + " WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem()
                + "'GROUP BY TIPO_MAQ";
        //se establece la imagen al tipo de maquna seleecionada
        imagen = new JLabel(getImagen(consultaImagen, "IMAGEN_MAQ"));
        imagen.setBounds(850, 400, 200, 200);
        DatosObras.add(imagen);

        //Para que la imagen se redimensione
        ImageIcon foto = getImagen(consultaImagen, "IMAGEN_MAQ");
        if (foto != null) {
            Image foton = foto.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_DEFAULT);
            foto = new ImageIcon(foton);
            imagen.setIcon(foto);
        } else {
            imagen.setText("No Existe una Imagen el la base de Datos");
        }

        //lista de las maquinas que se ocuparan en la construccion
        DefaultListModel<String> lista = new DefaultListModel();
        JList<String> list = new JList<>(lista);
        list.setBounds(1100, 400, 250, 200);
        DatosObras.add(list);

        //se agrega a una lista las maquinas requeridas 
        agregarMaquinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //el registro selecciomnado cuando se edita se eliminara
                if (!list.isSelectionEmpty()) {
                    int actualizar = list.getSelectedIndex();
                    lista.remove(actualizar);
                }
                //se recupera el tipo de maquina y el modelo seleccionado
                String tipo = (String) TipoMC.getSelectedItem();
                String modelo = (String) MaquinariaC.getSelectedItem();
                int cantidad = (int) CantidadSpiner.getValue();
                lista.addElement(String.format("%-20s/     %-20s/     %d", tipo, modelo, cantidad));

                //el spinner se actualiza a los nuevos valores desponibles
                SpinnerNumberModel aux = (SpinnerNumberModel) CantidadSpiner.getModel();
                int menos = (int) aux.getMaximum() - (int) CantidadSpiner.getValue();
                CantidadSpiner.setModel(new SpinnerNumberModel(0, 0, menos, 1));
                if ((int) CantidadSpiner.getValue() == 0) {
                    agregarMaquinaria.setEnabled(false);
                }
            }
        });

        editarMaquinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //recupera la los datos de la mquinaria que se decea editar
                    String modi[] = list.getSelectedValue().replaceAll(" ", "").split("/");
                    for (int i = 0; i < allTipos.size(); i++) {
                        if (allTipos.get(i).equals(modi[0])) {
                            //se establece el tipo de maquina a editar
                            TipoMC.setSelectedIndex(i);
                        }
                    }
                    //se establecen los modelos de las maquina se leccionada
                    String consultaModelosNueva = "SELECT MODELO_MAQ FROM Maquinaria WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem() + "' GROUP BY MODELO_MAQ";
                    List<Object> auxiliar = recuperarDatos(consultaModelosNueva, "MODELO_MAQ");
                    MaquinariaC.setModel(new DefaultComboBoxModel(auxiliar.toArray()));
                    for (int i = 0; i < auxiliar.size(); i++) {
                        if (auxiliar.get(i).equals(modi[1])) {
                            //se establece el modelo de la maquina a editar
                            MaquinariaC.setSelectedIndex(i);
                        }
                    }
                    //se recupera el numero de maquinas disponibles para ese modelo
                    String consultaCantidadNueva = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                            + " WHERE TIPO_MAQ = '" + TipoMC.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaC.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                            + "GROUP BY TIPO_MAQ,MODELO_MAQ";
                    Object cantidadFinalNueva = recuperarDato(consultaCantidadNueva, "Disponibles");
                    int disonibles = Integer.parseInt(cantidadFinalNueva == null ? "0" : (String) cantidadFinalNueva);
                    //se establece el spinner al numero de maquinas que se tenia
                    CantidadSpiner.setModel(new SpinnerNumberModel(Integer.parseInt(modi[2]), 0, disonibles, 1));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Seleccione una maquina a editar de la lista");
                }
            }
        });

        //elimina el registro selecciondado
        eliminarMaquinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    lista.remove(list.getSelectedIndex());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Seleccione un registro de la lista");
                }
            }
        });

        CantidadSpiner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                //el boton agregar se desactiva si el spinner esta en 0 para no poder agregar maquinas que no estan disponibles
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

        //calendario donde se escoje la fecha de inicio de la obra
        JCalendar FechaI = new JCalendar();
        FechaI.setForeground(Color.black);
        FechaI.setBorder(null);
        FechaI.setBounds(0, 400, 390, 200);
        DatosObras.add(FechaI);

        JLabel FechaFinal = new JLabel("Fecha final de la obra:");
        FechaFinal.setForeground(Color.white);
        Font fuenteFechaF = new Font("Arial", Font.BOLD, 20);
        FechaFinal.setFont(fuenteFechaF);
        FechaFinal.setBounds(400, 230, 300, 300);
        DatosObras.add(FechaFinal);

        //calendario donde se escojen la fecha final de la obra
        JCalendar FechaF = new JCalendar();
        FechaF.setForeground(Color.black);
        FechaF.setBorder(null);
        FechaF.setBounds(402, 400, 390, 200);
        DatosObras.add(FechaF);

        //boton que recupera los datos de todos los campos y los guarda en la base de datos
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
                    //consulta a la tabla OBRA --->
                    psd = conexion.prepareStatement("INSERT INTO OBRA (NOMBRE_OBRA,NOMBRE_CLIENTE,NOMBRE_RESPONSABLE,AP_PAT,AP_MAT,"
                            + "CALLE_OBRA,NUMERO_CALLE,COLONIA,MUNICIPIO,CP,ESTADO,FECHA_INICIO,"
                            + "FECHA_FIN,INVERSION,TELEFONO_RESP,CORREO_RESP,IDCLIENTE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    //se recuperan los datos para introducirlo a la base de datos
                    psd.setString(1, NombreObratxt.getText());//nombre obra
                    psd.setString(2, (String) clienteC.getSelectedItem());//nombre empresa
                    psd.setString(3, NombreResponsabletxt.getText());//nombre responsable
                    psd.setString(4, ApellidoResponsablePaternotxt.getText());//apellido paterno responsable
                    psd.setString(5, ApellidoResponsableMaternotxt.getText());//apellido materno responsable
                    psd.setString(6, Calletxt.getText());//calle de la obra
                    psd.setString(7, Numtxt.getText().isEmpty() ? "S/N" : Numtxt.getText());//numero de la calle donde esta la obra
                    psd.setString(8, Coltxt.getText());//colonia donde esta la obra
                    psd.setString(9, Municipiotxt.getText());//municipio donde esta la obra
                    psd.setString(10, cptxt.getText());//cp donde se ubica la obra
                    psd.setString(11, Estadotxt.getText());//estado donde se ubica la obra
                    psd.setDate(12, Date.valueOf(ff.format(FechaI.getDate())));//fecha inicio de la obra
                    psd.setDate(13, Date.valueOf(ff.format(FechaF.getDate())));//fecha final de la obra
                    psd.setDouble(14, Double.parseDouble(Montotxt.getText()));//monto o invercion de la obra                    
                    psd.setString(15, Telefonotxt.getText());//telefono del responsable 
                    psd.setString(16, Correotxt.getText());//correo del responsable       
                    String consulta = "SELECT * FROM CLIENTE WHERE NOMBRE_CLIENTE = '" + clienteC.getSelectedItem() + "'";
                    int id = Integer.parseInt(recuperarDato(consulta, "IDCLIENTE"));
                    psd.setInt(17, id);//id del cliente

                    int res = psd.executeUpdate();
                    if (res < 0) {
                        JOptionPane.showMessageDialog(null, "No se pudo añadir el registro");
                    }

                    String consultaObra = "SELECT * FROM OBRA WHERE NOMBRE_OBRA = '" + NombreObratxt.getText()
                            + "' AND NOMBRE_CLIENTE='" + clienteC.getSelectedItem() + "'";
                    int id_Obra = Integer.parseInt(recuperarDato(consultaObra, "CLAVE_OBRA"));
                    System.out.println(id_Obra);
                    for (int i = 0; i < lista.size(); i++) {
                        list.setSelectedIndex(i);
                        String filaLista[] = list.getSelectedValue().replaceAll(" ", "").split("/");
                        for (int j = 0; j < filaLista.length; j++) {
                            System.out.println(filaLista[j]);
                        }
                        int repeticiones = Integer.parseInt(filaLista[2]);
                        int repeticion = 1;
                        while (repeticion <= repeticiones) {
                            String recuperarIdMaquina = "SELECT * FROM Maquinaria WHERE TIPO_MAQ = '" + filaLista[0]
                                    + "' AND MODELO_MAQ = " + filaLista[1] + " AND ESTADO_MAQ = 'DISPONIBLE' ";
                            System.out.println(recuperarIdMaquina);
                            int idMaquina = Integer.parseInt(recuperarDato(recuperarIdMaquina, "CLAVE_MAQ"));                            
                            PreparedStatement psd2 = conexion.prepareStatement("INSERT INTO OBRA_MAQ_INSUMO (CLAVE_MAQ,CLAVE_OBRA) VALUES(?,?)");
                            psd2.setString(1, String.valueOf(idMaquina));
                            psd2.setString(2, String.valueOf(id_Obra));                            
                            int res2 = psd2.executeUpdate();
                            if (res2 < 0) {
                                JOptionPane.showMessageDialog(null, "No se pudo añadir el registro");
                            }
                            String actualizarEstado = "UPDATE Maquinaria SET ESTADO_MAQ= 'EN USO' where CLAVE_MAQ=" + idMaquina;
                            System.out.println(actualizarEstado);
                            try {
                                Statement stmt = (Statement) conexion.createStatement();
                                stmt.executeUpdate(actualizarEstado);
                            } catch (SQLException ex) {
                                System.err.println("Error al insertar " + ex);
                            }
                            repeticion++;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NuevoAgregarObra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //boton de acceso rapido por si un cliente no se habia registrado y los datos de la obra ya se habian rellenado
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

        //boton para recargar la lsiat de clientes
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

    //METODO QUE DEVUELVE UN IMAGE ICON
    public ImageIcon getImagen(String consulta, String columna) {
        ImageIcon ic = null;
        InputStream is = null;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            if (rs.next()) {
                try {
                    is = rs.getBinaryStream(columna);
                } catch (Exception ex) {
                }
                BufferedImage bi = ImageIO.read(is);
                ic = new ImageIcon(bi);
            }
        } catch (Exception e) {
        }
        return ic;
    }
}
