
import java.awt.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditarObra extends JFrame {

    Connection conexion;
    JLabel imagen;

    EditarObra(Connection conexion, int id_Obra) {
        this.conexion = conexion;

        setSize(1386, 768);
        setTitle("Editar obras");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //se recuperan los datos de la obra seleccionada
        String[] datosRecuperados = recuperarDatosObra("SELECT * FROM OBRA WHERE CLAVE_OBRA = " + id_Obra);

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

        //campon donde se edita el nombre del responsable
        CampoDato NombreResponsabletxtEditar = new CampoDato(datosRecuperados[2]);
        NombreResponsabletxtEditar.setForeground(Color.black);
        NombreResponsabletxtEditar.setBounds(105, 40, 200, 30);
        NombreResponsabletxtEditar.setBorder(null);
        //se restringe solo a texto
        NombreResponsabletxtEditar.setTipo('T');
        //se establece la longitud que tendra el campo
        NombreResponsabletxtEditar.setLongitud(30);
        DatosObras.add(NombreResponsabletxtEditar);

        JLabel ApellidoResponsablePaternoEditar = new JLabel("Apellido paterno:");
        ApellidoResponsablePaternoEditar.setForeground(Color.white);
        Font fuenteResponsablesP = new Font("Arial", Font.BOLD, 20);
        ApellidoResponsablePaternoEditar.setFont(fuenteResponsablesP);
        ApellidoResponsablePaternoEditar.setBounds(315, 40, 300, 20);
        DatosObras.add(ApellidoResponsablePaternoEditar);

        //campo donde se edita el apellido paterno del responsable
        CampoDato ApellidoResponsablePaternotxtEditar = new CampoDato(datosRecuperados[3]);
        ApellidoResponsablePaternotxtEditar.setForeground(Color.black);
        ApellidoResponsablePaternotxtEditar.setBounds(480, 40, 200, 30);
        ApellidoResponsablePaternotxtEditar.setBorder(null);
        //se restringe solo a texto
        ApellidoResponsablePaternotxtEditar.setTipo('T');
        //se establece la longitud del campo
        ApellidoResponsablePaternotxtEditar.setLongitud(30);
        DatosObras.add(ApellidoResponsablePaternotxtEditar);

        JLabel ApellidoResponsableMaternoEditar = new JLabel("Apellido materno:");
        ApellidoResponsableMaternoEditar.setForeground(Color.white);
        Font fuenteResponsablesMaterno = new Font("Arial", Font.BOLD, 20);
        ApellidoResponsableMaternoEditar.setFont(fuenteResponsables);
        ApellidoResponsableMaternoEditar.setBounds(690, 40, 300, 20);
        DatosObras.add(ApellidoResponsableMaternoEditar);

        //campo donde se edita el apellido paterno del responsable
        CampoDato ApellidoResponsableMaternotxtEditar = new CampoDato(datosRecuperados[4]);
        ApellidoResponsableMaternotxtEditar.setForeground(Color.black);
        ApellidoResponsableMaternotxtEditar.setBounds(860, 40, 200, 30);
        ApellidoResponsableMaternotxtEditar.setBorder(null);
        //se restringe solo a texto
        ApellidoResponsableMaternotxtEditar.setTipo('T');
        //se establece la longitud del campo
        ApellidoResponsableMaternotxtEditar.setLongitud(30);
        DatosObras.add(ApellidoResponsableMaternotxtEditar);

        JLabel MontoEditar = new JLabel("Monto de la obra: $");
        MontoEditar.setForeground(Color.white);
        Font fuenteMonto = new Font("Arial", Font.BOLD, 20);
        MontoEditar.setFont(fuenteMonto);
        MontoEditar.setBounds(1065, 40, 300, 20);
        DatosObras.add(MontoEditar);

        //capo donde se edita el costo de la obra
        CampoDato MontotxtEditar = new CampoDato(datosRecuperados[13]);
        MontotxtEditar.setForeground(Color.black);
        MontotxtEditar.setBounds(1250, 40, 70, 30);
        MontotxtEditar.setBorder(null);
        //se restrtinge a numeros con la posibiulidad de que sean decimales
        MontotxtEditar.setTipo('D');
        //se establece la longitud del campo
        MontotxtEditar.setLongitud(20);
        DatosObras.add(MontotxtEditar);

        JLabel TelefonoEditar = new JLabel("Télefono:");
        TelefonoEditar.setForeground(Color.white);
        Font fuenteTelefono = new Font("Arial", Font.BOLD, 20);
        TelefonoEditar.setFont(fuenteTelefono);
        TelefonoEditar.setBounds(0, 80, 300, 20);
        DatosObras.add(TelefonoEditar);

        //campo donde se edita el telefono del responsable
        CampoDato TelefonotxtEditar = new CampoDato(datosRecuperados[14]);
        TelefonotxtEditar.setForeground(Color.black);
        TelefonotxtEditar.setBounds(107, 80, 200, 30);
        TelefonotxtEditar.setBorder(null);
        //se restringe solo a numeros sin la posibilidad de ser decimales
        TelefonotxtEditar.setTipo('E');
        //se establece la longitud del campo
        TelefonotxtEditar.setLongitud(10);
        DatosObras.add(TelefonotxtEditar);

        JLabel CorreoEditar = new JLabel("E-mail:");
        CorreoEditar.setForeground(Color.white);
        Font fuenteCorreo = new Font("Arial", Font.BOLD, 20);
        CorreoEditar.setFont(fuenteCorreo);
        CorreoEditar.setBounds(410, 80, 300, 20);
        DatosObras.add(CorreoEditar);

        //campo donde se edita el correo del responsable
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

        //se rellena el comboBox con los clientes ya registrados
        java.util.List<Object> allClientes = recuperarDatos("SELECT * FROM CLIENTE", "NOMBRE_CLIENTE");
        JComboBox clienteCEditado = new JComboBox(allClientes.toArray());
        for (int i = 0; i < allClientes.size(); i++) {
            //se recorre la lista de clientes hasta seleccionar al que corresponde
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

        //campo donde se edita la calle donde se ubica la obra
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

        //campo donde se edita el numero de la calle donde se ubica la obra
        CampoDato NumtxtEditar = new CampoDato(datosRecuperados[6]);
        NumtxtEditar.setForeground(Color.black);
        NumtxtEditar.setBounds(480, 150, 50, 30);
        NumtxtEditar.setBorder(null);
        //se restringe solo a numeros sin la posibilidad de ser decimales
        NumtxtEditar.setTipo('E');
        //re establece la longitud del campo
        NumtxtEditar.setLongitud(10);
        DatosObras.add(NumtxtEditar);

        JLabel coloniaEditar = new JLabel("Colonia:");
        coloniaEditar.setForeground(Color.white);
        Font fontColonia = new Font("Arial", Font.BOLD, 20);
        coloniaEditar.setFont(fontColonia);
        coloniaEditar.setBounds(780, 150, 300, 20);
        DatosObras.add(coloniaEditar);

        //campo donde se edita la colonia donde esta ubicada la obra
        CampoDato ColtxtEditar = new CampoDato(datosRecuperados[7]);
        ColtxtEditar.setForeground(Color.black);
        ColtxtEditar.setBounds(858, 150, 200, 30);
        ColtxtEditar.setBorder(null);
        //se restringe solo a texto
        ColtxtEditar.setTipo('T');
        //se establece la longitud del campo
        ColtxtEditar.setLongitud(30);
        DatosObras.add(ColtxtEditar);

        JLabel MunicipioEditar = new JLabel("Municipio:");
        MunicipioEditar.setForeground(Color.white);
        Font fontMunicipio = new Font("Arial", Font.BOLD, 20);
        MunicipioEditar.setFont(fontMunicipio);
        MunicipioEditar.setBounds(1080, 150, 300, 20);
        DatosObras.add(MunicipioEditar);

        //campo donde se edita el municipio donde se ubuca la obra
        CampoDato MunicipiotxtEditar = new CampoDato(datosRecuperados[8]);
        MunicipiotxtEditar.setForeground(Color.black);
        MunicipiotxtEditar.setBounds(1180, 150, 170, 30);
        MunicipiotxtEditar.setBorder(null);
        //se restringe solo a texto
        MunicipiotxtEditar.setTipo('T');
        //se establece la longitud del campo
        MunicipiotxtEditar.setLongitud(30);
        DatosObras.add(MunicipiotxtEditar);

        JLabel EstadoEditar = new JLabel("Estado:");
        EstadoEditar.setForeground(Color.white);
        Font fontEstado = new Font("Arial", Font.BOLD, 20);
        EstadoEditar.setFont(fontEstado);
        EstadoEditar.setBounds(0, 190, 300, 20);
        DatosObras.add(EstadoEditar);

        //campo donde se edita el estado donde se ubica la obra
        CampoDato EstadotxtEditar = new CampoDato(datosRecuperados[10]);
        EstadotxtEditar.setForeground(Color.black);
        EstadotxtEditar.setBounds(105, 190, 200, 30);
        EstadotxtEditar.setBorder(null);
        //se restringe solo a texto
        EstadotxtEditar.setTipo('T');
        //se establece la longitud del campo
        EstadotxtEditar.setLongitud(30);
        DatosObras.add(EstadotxtEditar);

        JLabel cp = new JLabel("CP:");
        cp.setForeground(Color.white);
        Font fontcp = new Font("Arial", Font.BOLD, 20);
        cp.setFont(fontEstado);
        cp.setBounds(443, 190, 300, 20);
        DatosObras.add(cp);

        //campo donde se edita el CP donde se ubica la obra
        CampoDato cptxt = new CampoDato(datosRecuperados[9]);
        cptxt.setForeground(Color.black);
        cptxt.setBounds(480, 190, 50, 30);
        cptxt.setBorder(null);
        //se restringe solo a numeros sin la posibilidad a que sean decimales
        cptxt.setTipo('E');
        //se establece la longitud del campo
        cptxt.setLongitud(10);
        DatosObras.add(cptxt);

        ///Agregado
        JLabel NombreObraEditar = new JLabel("Nombre de la obra:");
        NombreObraEditar.setForeground(Color.white);
        Font fontNObra = new Font("Arial", Font.BOLD, 20);
        NombreObraEditar.setFont(fontNObra);
        NombreObraEditar.setBounds(675, 190, 300, 20);
        DatosObras.add(NombreObraEditar);

        //campo donde se edita el nombre de la obra
        CampoDato NombreObraEditartxt = new CampoDato(datosRecuperados[0]);
        NombreObraEditartxt.setForeground(Color.black);
        NombreObraEditartxt.setBounds(858, 190, 200, 30);
        NombreObraEditartxt.setBorder(null);
        //se restringe solo a texto
        NombreObraEditartxt.setTipo('T');
        //se establece la longitud del campo
        NombreObraEditartxt.setLongitud(50);
        DatosObras.add(NombreObraEditartxt);

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
        //se desactiva el boton por lo que el spinner inica en 0
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

        JLabel TipoMaquinariaEditar = new JLabel("Tipo de maquinaria:");
        TipoMaquinariaEditar.setForeground(Color.white);
        Font fuenteMaquina = new Font("Arial", Font.BOLD, 20);
        TipoMaquinariaEditar.setFont(fuenteMaquina);
        TipoMaquinariaEditar.setBounds(0, 220, 300, 150);
        DatosObras.add(TipoMaquinariaEditar);

        //se agregan los tipos de maquinaria al comboBox
        java.util.List<Object> allTipos = recuperarDatos("SELECT TIPO_MAQ FROM Maquinaria GROUP BY TIPO_MAQ", "TIPO_MAQ");
        JComboBox TipoMCEditar = new JComboBox(allTipos.toArray());
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

        //se agregan los modelos de la maquina inicial
        String consultaModelos = "SELECT MODELO_MAQ FROM Maquinaria WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem() + "' GROUP BY MODELO_MAQ";
        java.util.List<Object> allModelos = recuperarDatos(consultaModelos, "MODELO_MAQ");
        JComboBox MaquinariaCEditar = new JComboBox(allModelos.toArray());
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

        //se limita el spinner con la cantidad de maquinaria disponible del tipo y modelo indicado
        String consultaCantidad = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                + " WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaCEditar.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                + "GROUP BY TIPO_MAQ,MODELO_MAQ";
        Object cantidadFinal = recuperarDato(consultaCantidad, "Disponibles");
        //si la maquina seleccionada y el modelo seleccionado no tienen maquinas disponibles se le accina a la disponibilidad 0
        int disponibles = Integer.parseInt(cantidadFinal == null ? "0" : (String) cantidadFinal);
        JSpinner CantidadSpinerEditar = new JSpinner(new SpinnerNumberModel(0, 0, disponibles, 1));
        CantidadSpinerEditar.setForeground(Color.black);
        CantidadSpinerEditar.setBounds(1299, 280, 50, 30);
        CantidadSpinerEditar.setBorder(null);
        DatosObras.add(CantidadSpinerEditar);

        //cada vez que cambie de un tipo de maquinaria se actualizara el modelo automaticamente y el spinner delimitante de ellos
        TipoMCEditar.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                String consultaModelosNueva = "SELECT MODELO_MAQ FROM Maquinaria WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem() + "' GROUP BY MODELO_MAQ";
                //se optienen la lista de modelos de la maquina se leccionada
                java.util.List<Object> auxiliar = recuperarDatos(consultaModelosNueva, "MODELO_MAQ");
                //se le asigna los modelos correspondientes de la nueva maquina seleccionada
                MaquinariaCEditar.setModel(new DefaultComboBoxModel(auxiliar.toArray()));
                String consultaCantidadNueva = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                        + " WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaCEditar.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                        + "GROUP BY TIPO_MAQ,MODELO_MAQ";
                Object cantidadfinalNueva = recuperarDato(consultaCantidadNueva, "Disponibles");
                //si la maquina seleccionada y el modelo seleccionado no tienen maquinas disponibles se le accina a la disponibilidad 0
                int disponibles = Integer.parseInt(cantidadfinalNueva == null ? "0" : (String) cantidadfinalNueva);
                CantidadSpinerEditar.setModel(new SpinnerNumberModel(0, 0, disponibles, 1));
                //se desactiva el boton agregar maquina debido a que si la disponibilidad es 0 no se puede agregar una maquina
                agregarMaquinaria.setEnabled(false);
                String consultaImagenNueva = "SELECT * FROM Maquinaria"
                        + " WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem()
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
        MaquinariaCEditar.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                String consultaCantidadNueva = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                        + " WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaCEditar.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                        + "GROUP BY TIPO_MAQ,MODELO_MAQ";
                Object cantidadFinalNueva = recuperarDato(consultaCantidadNueva, "Disponibles");
                //si la maquina seleccionada y el modelo seleccionado no tienen maquinas disponibles se le accina a la disponibilidad 0
                int disonibles = Integer.parseInt(cantidadFinalNueva == null ? "0" : (String) cantidadFinalNueva);
                CantidadSpinerEditar.setModel(new SpinnerNumberModel(0, 0, disonibles, 1));
                //se desactiva el boton agregar maquina debido a que si la disponibilidad es 0 no se puede agregar una maquina
                agregarMaquinaria.setEnabled(false);
            }
        });

        String consultaImagen = "SELECT * FROM Maquinaria" + " WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem()
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
                String tipo = (String) TipoMCEditar.getSelectedItem();
                String modelo = (String) MaquinariaCEditar.getSelectedItem();
                int cantidad = (int) CantidadSpinerEditar.getValue();
                lista.addElement(String.format("%-20s/     %-20s/     %d", tipo, modelo, cantidad));

                //el spinner se actualiza a los nuevos valores desponibles
                SpinnerNumberModel aux = (SpinnerNumberModel) CantidadSpinerEditar.getModel();
                int menos = (int) aux.getMaximum() - (int) CantidadSpinerEditar.getValue();
                CantidadSpinerEditar.setModel(new SpinnerNumberModel(0, 0, menos, 1));
                if ((int) CantidadSpinerEditar.getValue() == 0) {
                    agregarMaquinaria.setEnabled(false);
                }
            }
        });

        editarMaquinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //recupera la los datos de la mquinaria que se decea editar
                String modi[] = list.getSelectedValue().replaceAll(" ", "").split("/");
                for (int i = 0; i < allTipos.size(); i++) {
                    if (allTipos.get(i).equals(modi[0])) {
                        //se establece el tipo de maquina a editar
                        TipoMCEditar.setSelectedIndex(i);
                    }
                }
                //se establecen los modelos de las maquina se leccionada
                String consultaModelosNueva = "SELECT MODELO_MAQ FROM Maquinaria WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem() + "' GROUP BY MODELO_MAQ";
                java.util.List<Object> auxiliar = recuperarDatos(consultaModelosNueva, "MODELO_MAQ");
                MaquinariaCEditar.setModel(new DefaultComboBoxModel(auxiliar.toArray()));
                for (int i = 0; i < auxiliar.size(); i++) {
                    if (auxiliar.get(i).equals(modi[1])) {
                        //se establece el modelo de la maquina a editar
                        MaquinariaCEditar.setSelectedIndex(i);
                    }
                }
                //se recupera el numero de maquinas disponibles para ese modelo
                String consultaCantidadNueva = "SELECT TIPO_MAQ,MODELO_MAQ,COUNT(MODELO_MAQ)as Disponibles FROM Maquinaria"
                        + " WHERE TIPO_MAQ = '" + TipoMCEditar.getSelectedItem() + "' AND MODELO_MAQ = " + MaquinariaCEditar.getSelectedItem() + " AND ESTADO_MAQ = 'DISPONIBLE' "
                        + "GROUP BY TIPO_MAQ,MODELO_MAQ";
                Object cantidadFinalNueva = recuperarDato(consultaCantidadNueva, "Disponibles");
                int disonibles = Integer.parseInt(cantidadFinalNueva == null ? "0" : (String) cantidadFinalNueva);
                //se establece el spinner al numero de maquinas que se tenia
                CantidadSpinerEditar.setModel(new SpinnerNumberModel(Integer.parseInt(modi[2]), 0, disonibles, 1));
            }
        });

        CantidadSpinerEditar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                //el boton agregar se desactiva si el spinner esta en 0 para no poder agregar maquinas que no estan disponibles
                if ((int) CantidadSpinerEditar.getValue() == 0) {
                    agregarMaquinaria.setEnabled(false);
                } else {
                    agregarMaquinaria.setEnabled(true);
                }
            }
        });

        JLabel FechaInicioEditar = new JLabel("Fecha de inicio de la obra:");
        FechaInicioEditar.setForeground(Color.white);
        Font fuenteFechaI = new Font("Arial", Font.BOLD, 20);
        FechaInicioEditar.setFont(fuenteFechaI);
        FechaInicioEditar.setBounds(0, 230, 300, 300);
        DatosObras.add(FechaInicioEditar);

        //campo donde se edita la fecha de inicio de la obra
        JCalendar FechaIEditar = new JCalendar(Date.valueOf(datosRecuperados[11]));
        FechaIEditar.setForeground(Color.black);
        FechaIEditar.setBorder(null);
        FechaIEditar.setBounds(0, 400, 390, 200);
        DatosObras.add(FechaIEditar);

        JLabel FechaFinalEditar = new JLabel("Fecha final de la obra:");
        FechaFinalEditar.setForeground(Color.white);
        Font fuenteFechaF = new Font("Arial", Font.BOLD, 20);
        FechaFinalEditar.setFont(fuenteFechaF);
        FechaFinalEditar.setBounds(400, 230, 300, 300);
        DatosObras.add(FechaFinalEditar);

        //campo donde se establece la fecha final de la obra
        JCalendar FechaFEditar = new JCalendar(Date.valueOf(datosRecuperados[12]));
        FechaFEditar.setForeground(Color.black);
        FechaFEditar.setBorder(null);
        FechaFEditar.setBounds(402, 400, 390, 200);
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
                //se establece el formato de la fecha que acepta la base de datos
                SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
                String consultaCliente = "SELECT * FROM CLIENTE WHERE NOMBRE_CLIENTE = '" + clienteCEditado.getSelectedItem() + "'";
                //se recupera el id del cliente
                int idCliente = Integer.parseInt(recuperarDato(consultaCliente, "IDCLIENTE"));
                String auxNumeroEdit = NumtxtEditar.getText().equals("S/N") ? "\"S/N\"" : NumtxtEditar.getText();
                //consulta que actualiza los datos
                String EditarObra = "UPDATE OBRA SET "
                        + "NOMBRE_OBRA='" + NombreObraEditartxt.getText() + "',NOMBRE_CLIENTE='" + clienteCEditado.getSelectedItem()
                        + "',NOMBRE_RESPONSABLE='" + NombreResponsabletxtEditar.getText() + "',AP_PAT='" + ApellidoResponsablePaternotxtEditar.getText()
                        + "',AP_MAT='" + ApellidoResponsableMaternotxtEditar.getText() + "',CALLE_OBRA='" + CalletxtEditar.getText()
                        + "',NUMERO_CALLE=" + auxNumeroEdit + ",COLONIA='" + ColtxtEditar.getText() + "',MUNICIPIO='" + MunicipiotxtEditar.getText()
                        + "',CP=" + cptxt.getText() + ",ESTADO='" + EstadotxtEditar.getText() + "',FECHA_INICIO='" + ff.format(FechaIEditar.getDate())
                        + "',FECHA_FIN='" + ff.format(FechaFEditar.getDate()) + "',INVERSION=" + MontotxtEditar.getText() + ",TELEFONO_RESP=" + TelefonotxtEditar.getText()
                        + ",CORREO_RESP='" + CorreotxtEditar.getText() + "',IDCLIENTE=" + idCliente
                        + " where CLAVE_OBRA=" + id_Obra;
                try {
                    Statement stmt = (Statement) conexion.createStatement();
                    stmt.executeUpdate(EditarObra);
                } catch (SQLException ex) {
                    System.err.println("Error al insertar " + ex);
                }
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

    //metodo para recuperar todos los datos de la obra
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
