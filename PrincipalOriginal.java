
import java.awt.*;

import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

public class PrincipalOriginal extends JFrame {
    
    Connection conexion;
    
    PrincipalOriginal() {
        conexion = getConexion();
        setSize(1366, 768);
        setTitle("Sistema de gestion de maquinaria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel principal = new JPanel();
        principal.setLayout(null);
        
        principal.setBackground(Color.black);
        principal.setBounds(0, 115, 1366, 768);
        
        ImageIcon background_image = new ImageIcon("C:\\Users\\Adan Sanchez\\Documents\\NetBeansProjects\\Fun_Ing_Soft\\src\\neo3.jpg");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        background_image = new ImageIcon(temp_img);
        JLabel background = new JLabel("", background_image, JLabel.CENTER);
        background.add(principal);
        background.setBounds(0, 0, 1366, 768);
        add(background);

        /**
         * Parte donde se agregan las Pestañas
         */
        JTabbedPane tabla = new JTabbedPane();
        tabla.setBounds(0, 0, 1366, 768);
        principal.add(tabla);
        tabla.addTab("Bienvenido al sistema ", bienvenido());
        tabla.addTab("Maquinaria", Maquinaria());
        tabla.addTab("Obras", Obras());
        tabla.addTab("Clientes", Clientes());
        tabla.addTab("Finanzas", Finanzas());
        
    }
    
    public static void main(String[] args) {
        new PrincipalOriginal();
    }
    
    public JPanel bienvenido() {
        JPanel bienvenido = new JPanel();
        bienvenido.setLayout(null);
        setBounds(0, 0, 1366, 768);
        bienvenido.setBackground(Color.black);
        return bienvenido;
    }
    
    public JPanel Maquinaria() {
        JPanel Maquinas = new JPanel();
        Maquinas.setLayout(null);
        Maquinas.setBackground(Color.black);
        return Maquinas;
        
    }
    
    public JPanel Obras() {
        JPanel Obras = new JPanel();
        Obras.setLayout(null);
        Obras.setBackground(Color.black);
        
        String[] Cabecera = {"NOMBRE DE LA OBRA", "NOMBRE DEL RESPONSABLE", "FECHA DE INICIO", "FECHA DE FINALIZACIÓN", "NÚMERO DEL RESPONSABLE", "INVERSIÓN $", "NOMBRE DEL CLIENTE"};
        String consulta = "SELECT * FROM OBRA";
        DefaultTableModel modelo = new DefaultTableModel(recuperarDatosObra(consulta), Cabecera) {
            //La edicion de la tabla esta desactivada
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        JTable OrasT = new JTable(modelo);
        //La reorganizacion de las colunmas se desactiva
        OrasT.getTableHeader().setReorderingAllowed(false);
        //La redimencion de las colunmas se desactiva
        OrasT.getTableHeader().setResizingAllowed(false);
        //Se Restinge la seleccion de las filas a solo una
        OrasT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sc = new JScrollPane(OrasT);
        sc.setVisible(true);
        sc.setBounds(10, 70, 1336, 410);
        Obras.add(sc);
        
        JTextField busqueda = new JTextField();
        busqueda.setForeground(Color.black);
        busqueda.setBounds(463, 15, 400, 30);
        Obras.add(busqueda);

        //boton que actualiza la tabla de registros
        JButton actualizar = new JButton("ACTUALIZAR");
        actualizar.setBackground(Color.black);
        actualizar.setBorder(new ComponenteBotonRedondo(40));
        actualizar.setForeground(Color.decode("#049cff"));
        actualizar.setBounds(866, 15, 200, 30);
        Obras.add(actualizar);
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //La edicion de la tabla esta desactivada
                DefaultTableModel modeloAux = new DefaultTableModel(recuperarDatosObra(consulta), Cabecera) {
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };
                OrasT.setModel(modeloAux);
            }
        });

        //boton para agregar una nueva obra
        JButton Agregar = new JButton("Agregar");
        Agregar.setBackground(Color.black);
        Agregar.setBorder(new ComponenteBotonRedondo(40));
        Agregar.setForeground(Color.decode("#049cff"));
        Agregar.setBounds(400, 498, 150, 50);
        Obras.add(Agregar);
        //abre una nueva venta para agregar una nueva obra
        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new NuevoAgregarObra(conexion);
            }
        });

        //boton para borrar el registro seleccionado
        JButton Editar = new JButton("Editar");
        Editar.setBackground(Color.black);
        Editar.setBorder(new ComponenteBotonRedondo(40));
        Editar.setForeground(Color.decode("#049cff"));
        Editar.setBounds(600, 498, 150, 50);
        Obras.add(Editar);
        //abre una nueva ventana para editar la obra seleccionada 
        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //recupera la fila seleccionada
                    int fila = OrasT.getSelectedRow();
                    //recupera el nombre de la obra
                    String nombreObra = (String) OrasT.getValueAt(fila, 0);
                    //recupera el nomre del cliente
                    String nombreCliente = (String) OrasT.getValueAt(fila, 6);
                    String consultaObra = "SELECT * FROM OBRA WHERE NOMBRE_OBRA = '" + nombreObra + "' AND NOMBRE_CLIENTE='" + nombreCliente + "'";
                    int id_Obra = Integer.parseInt(recuperarDato(consultaObra, "CLAVE_OBRA"));
                    //abre la ventana para agregar una nueva obra
                    new EditarObra(conexion, id_Obra);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Seleccione un registro");
                }
            }
        });
        
        JButton Eliminar = new JButton("Eliminar");
        Eliminar.setBackground(Color.black);
        Eliminar.setBorder(new ComponenteBotonRedondo(40));
        Eliminar.setForeground(Color.decode("#049cff"));
        Eliminar.setBounds(800, 498, 150, 50);
        Obras.add(Eliminar);
        //se elimana la obera seleccionada
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //recupera la fila seleccionada
                    int fila = OrasT.getSelectedRow();
                    //recupera el nombre de la obra
                    String nombreObra = (String) OrasT.getValueAt(fila, 0);
                    //recupera el nombre del cliente
                    String nombreCliente = (String) OrasT.getValueAt(fila, 6);
                    String consultaObra = "SELECT * FROM OBRA WHERE NOMBRE_OBRA = '" + nombreObra + "' AND NOMBRE_CLIENTE='" + nombreCliente + "'";
                    int id_Obra = Integer.parseInt(recuperarDato(consultaObra, "CLAVE_OBRA"));
                    String eliminar = "DELETE FROM OBRA WHERE CLAVE_OBRA = " + id_Obra;
                    try {
                        Statement stmt = (Statement) conexion.createStatement();
                        stmt.executeUpdate(eliminar);
                    } catch (Exception ex) {
                        System.err.println("Error al eliminar " + ex);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Seleccione un registro");
                }
            }
        });
        return Obras;
    }
    
    public JPanel Clientes() {
        JPanel Clientes = new JPanel();
        Clientes.setLayout(null);
        Clientes.setBackground(Color.black);
        return Clientes;
    }
    
    public JPanel Finanzas() {
        JPanel Finanzas = new JPanel();
        Finanzas.setBackground(Color.black);
        JLabel FinanzasMensaje = new JLabel("Gráficas de finanzas mesuales");
        FinanzasMensaje.setForeground(Color.white);
        Finanzas.add(FinanzasMensaje);
        return Finanzas;
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
            String aux = String.valueOf(rs.getObject(columna));
            dato = aux;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos de la base de datos\n" + e.toString());
        }
        return dato;
    }
    
    //recupera todos los registros de las obras de la base de datos
    public Object[][] recuperarDatosObra(String consulta) {
        Object[][] datos = new Object[getTotalFilas(consulta)][8];
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);            
            int i = 0;
            try {
                while (rs.next()) {
                    datos[i][0] = rs.getString(2);//nombre de la obra
                    datos[i][1] = rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6);//nombre del responsable
                    datos[i][2] = String.valueOf(rs.getDate(13));//fecha inicio
                    datos[i][3] = String.valueOf(rs.getDate(15));//fecha final
                    datos[i][4] = rs.getString(17);//numero del responsable
                    datos[i][5] = String.valueOf(rs.getDouble(16));//inversion
                    datos[i][6] = rs.getString(3);//nombre del cliente
                    i++;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No hay registros");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos de la base de datos\n" + e.toString());
        }
        return datos;
    }
    
    public int getTotalFilas(String consulta) {
        int count = 0;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()) {
                count += 1;
            }
        } catch (Exception e) {
            System.err.println("Error al listar " + e);
        }
        return count;
    }
}
