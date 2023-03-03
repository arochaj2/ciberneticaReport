/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciberneticareport;

import configuracion.Settings;
import reporte.DataReporte;
import reporte.DataSubReporte;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.Aseguradoras;
import models.CrrReclamantes;
import models.CrrReclamo;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import services.AseguradorasJpaController;
import services.CrrReclamantesJpaController;
import services.CrrReclamoJpaController;


/**
 *
 * @author José Arocha
 */



public class CiberneticaForm extends javax.swing.JFrame {
Color color;

private final ImageIcon imageicon;
private TrayIcon trayicon;
private SystemTray systemtray;



   public static java.util.List<Aseguradoras> aseguradorasData = new ArrayList<>();
   public static java.util.List<CrrReclamo> crrReclamosData = new ArrayList<>();
   public static java.util.List<CrrReclamantes> crrReclamantesData = new ArrayList<>();
   

    /**
     * Creates new form CiberneticaForm
     */
    public CiberneticaForm() {
        

        
        imageicon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/iconfinder_pdf_3745 (1).png")));

        UIManager.put("ComboBox.disabledForeground", Color.BLUE);

        initComponents();



        this.setIconImage(imageicon.getImage());
        instanciarTray();
     

        loadConfi();
 
        
         jCheckBox1.setVisible(false);
        
          jPanel2.setVisible(false);
          jProgressBarMixto1.setVisible(false);
          jLabelProgress1.setVisible(false);
          
          
          jPanelProgresMixto1.setVisible(false);


      
        
     
        
color = jList1.getSelectionBackground();
        
    jButton22.setText("\u02C4");
    jButtonDOWN.setText("\u02C5");
    jButtonUP.setText("\u02C4");
    jButtonDO.setText("\u02C5");
      

   ((DefaultListModel<String>) jList2.getModel()).addElement("* Número de Parte");
   ((DefaultListModel<String>) jList2.getModel()).addElement("* Número de Placa Policia");
   ((DefaultListModel<String>) jList2.getModel()).addElement("* Número de Resolución");
   ((DefaultListModel<String>) jList2.getModel()).addElement("* Número de Denuncia");
   ((DefaultListModel<String>) jList2.getModel()).addElement("* Número de Oficio");
   
   
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Nombre de Asegurados");
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Nombre de Terceros");
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Nombre de Conductores");
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Apellido de Asegurados");
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Apellido de Terceros");
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Apellido de Conductores");
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Cedula Corrida de Asegurados");
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Cedula Corrida de Terceros");
   ((DefaultListModel<String>) jList3.getModel()).addElement("* Cedula Corrida de Conductores");


   ((DefaultListModel<String>) jList7.getModel()).addElement("* Número de Placa");
   ((DefaultListModel<String>) jList7.getModel()).addElement("* Número de chasis");
   ((DefaultListModel<String>) jList7.getModel()).addElement("* Número de Motor");
   ((DefaultListModel<String>) jList7.getModel()).addElement("* VIN");





        
        
    }
    
   public void cargarDatos(){
   
       try {
           jCheckBox1.setVisible(true);
           
          if(jRadioButton4.isSelected()){
       
                     String dir = jTextFieldServi1.getText();
          
          String user = jTextFieldPuerto1.getText();
          String password= jTextFieldBaseDatos1.getText();
          
            System.out.println(user);
            System.out.println(password);
          
            
            Map<String,String> direcConex = new HashMap();
            
            direcConex.put("javax.persistence.jdbc.url", "jdbc:sqlite:"+dir);
             direcConex.put("javax.persistence.jdbc.user", user);
              direcConex.put("javax.persistence.jdbc.driver", "org.sqlite.JDBC");
               direcConex.put("javax.persistence.jdbc.password", password);
            

Map properties = new HashMap();
//properties.put("javax.persistence.database-product-name", "Oracle");
//properties.put("javax.persistence.database-major-version", 12);
//properties.put("javax.persistence.database-minor-version", 1);
//properties.put("javax.persistence.schema-generation.scripts.action", "drop-and-create");
//properties.put("javax.persistence.schema-generation.scripts.drop-target", "jpa21-generate-schema-no-connection-drop.jdbc");
//properties.put("javax.persistence.schema-generation.scripts.create-target", "jpa21-generate-schema-no-connection-create.jdbc");
  
//            properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
//            properties.put("javax.persistence.schema-generation.create-source", "script");
//           // properties.put("javax.persistence.schema-generation.drop-source", "script");
//            properties.put("javax.persistence.schema-generation.create-script-source", "META-INF/create-script.sql");
//            // properties.put("javax.persistence.schema-generation.drop-script-source", "META-INF/drop-script.sql");
//
//
//
//Persistence.generateSchema("default", properties);

String pass=  String.valueOf(jPasswordFiel2.getPassword());

            System.out.println(pass);

//            direcConex.put("javax.persistence.schema-generation.database.action", "drop-and-create");
////            direcConex.put("javax.persistence.schema-generation.create-source", "script");
////            direcConex.put("javax.persistence.schema-generation.drop-source", "script");
////            direcConex.put("javax.persistence.schema-generation.create-script-source", "META-INF/create-script.sql");
////            direcConex.put("javax.persistence.schema-generation.drop-script-source", "META-INF/drop-script.sql");
//            direcConex.put("javax.persistence.jdbc.url", "jdbc:mysql://"+jTextFieldServi.getText()+":"+jTextFieldPuerto.getText()+"/"+jTextFieldBaseDatos.getText()+"?zeroDateTimeBehavior=CONVERT_TO_NULL");
//            direcConex.put("javax.persistence.jdbc.user", jTextFieldUsuario.getText());
//            direcConex.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
//            direcConex.put("javax.persistence.jdbc.password", pass);
//            direcConex.put("hibernate.show_sql", "true");           
//            direcConex.put("javax.persistence.sql-load-script-source", "META-INF/import.sql");
            
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CiberneticaReportINTERFAZPU", direcConex);
            
            EntityManager em = emf.createEntityManager();

            AseguradorasJpaController aseguradoraService = new AseguradorasJpaController(emf);
            CrrReclamoJpaController reclamoService = new CrrReclamoJpaController(emf);
            CrrReclamantesJpaController reclamanteService = new CrrReclamantesJpaController(emf);

            aseguradorasData = aseguradoraService.findAseguradorasEntities();

            crrReclamosData = reclamoService.findCrrReclamoEntities();
            
            crrReclamantesData = reclamanteService.findCrrReclamantesEntities();
            


            em.close();
            emf.close();
       
       
       }else{
       
                            String dir = jTextFieldServi1.getText();
          
          String user = jTextFieldPuerto1.getText();
          String password= jTextFieldBaseDatos1.getText();
          
            System.out.println(user);
            System.out.println(password);
          
            
            Map<String,String> direcConex = new HashMap();
            
            

Map properties = new HashMap();


String pass=  String.valueOf(jPasswordFiel2.getPassword());

            System.out.println(pass);

            direcConex.put("javax.persistence.schema-generation.database.action", "drop-and-create");
//            direcConex.put("javax.persistence.schema-generation.create-source", "script");
//            direcConex.put("javax.persistence.schema-generation.drop-source", "script");
//            direcConex.put("javax.persistence.schema-generation.create-script-source", "META-INF/create-script.sql");
//            direcConex.put("javax.persistence.schema-generation.drop-script-source", "META-INF/drop-script.sql");
            direcConex.put("javax.persistence.jdbc.url", "jdbc:mysql://"+jTextFieldServi.getText()+":"+jTextFieldPuerto.getText()+"/"+jTextFieldBaseDatos.getText()+"?zeroDateTimeBehavior=CONVERT_TO_NULL");
            direcConex.put("javax.persistence.jdbc.user", jTextFieldUsuario.getText());
            direcConex.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
            direcConex.put("javax.persistence.jdbc.password", pass);
            direcConex.put("hibernate.show_sql", "true");           
            direcConex.put("javax.persistence.sql-load-script-source", "META-INF/import.sql");
            
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CiberneticaReportINTERFAZPU", direcConex);
            
            EntityManager em = emf.createEntityManager();

            AseguradorasJpaController aseguradoraService = new AseguradorasJpaController(emf);
            CrrReclamoJpaController reclamoService = new CrrReclamoJpaController(emf);
            CrrReclamantesJpaController reclamanteService = new CrrReclamantesJpaController(emf);

            aseguradorasData = aseguradoraService.findAseguradorasEntities();

            crrReclamosData = reclamoService.findCrrReclamoEntities();
            
            crrReclamantesData = reclamanteService.findCrrReclamantesEntities();
            


            em.close();
            emf.close();
       
       
       
       
       } 
           
           
       } catch (Exception e) {
           
            jCheckBox1.setText("FALLIDA");
            jCheckBox1.setSelected(false);

            System.out.println(e);
       }
       
       
       
       
       
   
   } 
 
    
 
    
    
    
                private void swapElements(int pos1, int pos2) {
        
        try {
         
  
   String tmp=((DefaultListModel<String>) jList2.getModel()).getElementAt(pos1);
            
    ((DefaultListModel<String>) jList2.getModel()).set(pos1, ((DefaultListModel<String>) jList2.getModel()).get(pos2));
    ((DefaultListModel<String>) jList2.getModel()).set(pos2,tmp);        
            
               
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        
        

    
}
    
    
    
    private void swapElementsM(int pos1, int pos2) {
        
        try {
         
  
   String tmp=((DefaultListModel<String>) jList3.getModel()).getElementAt(pos1);
            
    ((DefaultListModel<String>) jList3.getModel()).set(pos1, ((DefaultListModel<String>) jList3.getModel()).get(pos2));
    ((DefaultListModel<String>) jList3.getModel()).set(pos2,tmp);        
            
               
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        
        

    
}
    
  
        private void swapElementsA(int pos1, int pos2) {
        
        try {
         
  
   String tmp=((DefaultListModel<String>) jList5.getModel()).getElementAt(pos1);
            
    ((DefaultListModel<String>) jList5.getModel()).set(pos1, ((DefaultListModel<String>) jList5.getModel()).get(pos2));
    ((DefaultListModel<String>) jList5.getModel()).set(pos2,tmp);        
            
               
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        
        

    
}
    
          private void swapElementsV(int pos1, int pos2) {
        
        try {
         
  
   String tmp=((DefaultListModel<String>) jList7.getModel()).getElementAt(pos1);
            
    ((DefaultListModel<String>) jList7.getModel()).set(pos1, ((DefaultListModel<String>) jList7.getModel()).get(pos2));
    ((DefaultListModel<String>) jList7.getModel()).set(pos2,tmp);        
            
               
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        
        

    
}  
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        menuItem1 = new java.awt.MenuItem();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldSerCorreo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldPuertoCorreo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldUsuarioCorreo = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPasswordFiel1 = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldCC = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCO = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldAsunto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaPara = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldDirectorio = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaMensaje = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<>();
        jButtonADD1 = new javax.swing.JButton();
        jButtonREMOV1 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList<>();
        jButtonUP1 = new javax.swing.JButton("\u02C4");
        jButtonDO1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton22 = new javax.swing.JButton("\u02C4");
        jButtonDOWN = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jButtonADD = new javax.swing.JButton();
        jButtonREMOV = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jButtonUP = new javax.swing.JButton("\u02C4");
        jButtonDO = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jList7 = new javax.swing.JList<>();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        jList8 = new javax.swing.JList<>();
        jButton25 = new javax.swing.JButton("\u02C4");
        jButtonDOWN1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jList9 = new javax.swing.JList<>();
        jButtonADD2 = new javax.swing.JButton();
        jButtonREMOV2 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jList10 = new javax.swing.JList<>();
        jButtonUP2 = new javax.swing.JButton("\u02C4");
        jButtonDO2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jDateChooserNewStmFech = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooserNewStmFech1 = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jComboBoxDia = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jComboBoxHora = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldServi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPuerto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldBaseDatos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordFiel2 = new javax.swing.JPasswordField();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldServi1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldPuerto1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldBaseDatos1 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanelProgresMixto1 = new javax.swing.JPanel();
        jProgressBarMixto1 = new javax.swing.JProgressBar();
        jLabelProgress1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        popupMenu1.setLabel("popupMenu2");

        menuItem1.setLabel("Primer Plano");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        popupMenu1.add(menuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INFORME CIBERNETICA");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton2.setText("INPUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("CONECTAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("OUTPUT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.CardLayout());

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel15.setText("SERVIDOR:");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel16.setText("PUERTO:");

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel27.setText("USUARIO:");

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel28.setText("CLAVE:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel9.setText("PARA:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel10.setText("CC:");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel12.setText("ASUNTO:");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel13.setText("MENSAJE:");

        jTextAreaPara.setColumns(20);
        jTextAreaPara.setRows(5);
        jScrollPane2.setViewportView(jTextAreaPara);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel11.setText("CO:");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel14.setText("DIRECTORIO:");

        jButton8.setText("añadir");
        jButton8.setToolTipText("Selleccione el directorio donde se crearan los Informes");
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextAreaMensaje.setColumns(20);
        jTextAreaMensaje.setRows(5);
        jScrollPane3.setViewportView(jTextAreaMensaje);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(11, 11, 11))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jTextFieldDirectorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addComponent(jPasswordFiel1)
                    .addComponent(jTextFieldUsuarioCorreo)
                    .addComponent(jTextFieldPuertoCorreo)
                    .addComponent(jTextFieldAsunto)
                    .addComponent(jTextFieldCO)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jTextFieldCC)
                    .addComponent(jTextFieldSerCorreo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldSerCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextFieldPuertoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextFieldUsuarioCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jPasswordFiel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDirectorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("EMAIL / ASEGURADORAS", jPanel13);

        jList5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList5.setModel(new DefaultListModel ());
        jList5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList5FocusGained(evt);
            }
        });
        jScrollPane11.setViewportView(jList5);

        jButtonADD1.setText("Agregar >>");
        jButtonADD1.setEnabled(false);
        jButtonADD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonADD1ActionPerformed(evt);
            }
        });

        jButtonREMOV1.setText("<< Quitar");
        jButtonREMOV1.setEnabled(false);
        jButtonREMOV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonREMOV1ActionPerformed(evt);
            }
        });

        jList6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList6.setModel(new DefaultListModel ());
        jList6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList6FocusGained(evt);
            }
        });
        jScrollPane12.setViewportView(jList6);

        jButtonUP1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonUP1.setText("˄");
        jButtonUP1.setEnabled(false);
        jButtonUP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUP1ActionPerformed(evt);
            }
        });

        jButtonDO1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonDO1.setText("˅");
        jButtonDO1.setEnabled(false);
        jButtonDO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDO1ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel21.setText("Parametros:");

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel22.setText("Reporte a Generar:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonREMOV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonADD1))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonUP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDO1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jButtonUP1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDO1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(jScrollPane11)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jButtonADD1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonREMOV1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ASEGURADORAS", jPanel1);

        jList2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList2.setModel(new DefaultListModel ());
        jList2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList2FocusGained(evt);
            }
        });
        jScrollPane8.setViewportView(jList2);

        jButton20.setText("Agregar >>");
        jButton20.setEnabled(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("<< Quitar");
        jButton21.setEnabled(false);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jList1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList1.setModel(new DefaultListModel ());
        jList1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList1FocusGained(evt);
            }
        });
        jScrollPane7.setViewportView(jList1);

        jButton22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton22.setText("˄");
        jButton22.setEnabled(false);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButtonDOWN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonDOWN.setText("˅");
        jButtonDOWN.setEnabled(false);
        jButtonDOWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDOWNActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel17.setText("Parametros:");

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel18.setText("Reporte a Generar:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton20))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDOWN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                    .addComponent(jScrollPane8)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jButton20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton21)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDOWN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );

        jTabbedPane1.addTab("CRR_RECLAMOS", jPanel4);

        jList3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList3.setModel(new DefaultListModel ());
        jList3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList3FocusGained(evt);
            }
        });
        jScrollPane9.setViewportView(jList3);

        jButtonADD.setText("Agregar >>");
        jButtonADD.setEnabled(false);
        jButtonADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonADDActionPerformed(evt);
            }
        });

        jButtonREMOV.setText("<< Quitar");
        jButtonREMOV.setEnabled(false);
        jButtonREMOV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonREMOVActionPerformed(evt);
            }
        });

        jList4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList4.setModel(new DefaultListModel ());
        jList4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList4FocusGained(evt);
            }
        });
        jScrollPane10.setViewportView(jList4);

        jButtonUP.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonUP.setText("˄");
        jButtonUP.setEnabled(false);
        jButtonUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUPActionPerformed(evt);
            }
        });

        jButtonDO.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonDO.setText("˅");
        jButtonDO.setEnabled(false);
        jButtonDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDOActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel19.setText("Parametros:");

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel20.setText("Reporte a Generar:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonREMOV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonADD))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonUP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                    .addComponent(jScrollPane9)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jButtonADD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonREMOV)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jButtonUP, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDO, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );

        jTabbedPane1.addTab("CRR_RECLAMANTES", jPanel5);

        jList7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList7.setModel(new DefaultListModel ());
        jList7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList7FocusGained(evt);
            }
        });
        jScrollPane13.setViewportView(jList7);

        jButton23.setText("Agregar >>");
        jButton23.setEnabled(false);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setText("<< Quitar");
        jButton24.setEnabled(false);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jList8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList8.setModel(new DefaultListModel ());
        jList8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList8FocusGained(evt);
            }
        });
        jScrollPane14.setViewportView(jList8);

        jButton25.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton25.setText("˄");
        jButton25.setEnabled(false);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButtonDOWN1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonDOWN1.setText("˅");
        jButtonDOWN1.setEnabled(false);
        jButtonDOWN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDOWN1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel23.setText("Parametros:");

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel24.setText("Reporte a Generar:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton23))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonDOWN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDOWN1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14)
                            .addComponent(jScrollPane13)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton24)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("VEHICULOS", jPanel7);

        jList9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList9.setModel(new DefaultListModel ());
        jList9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList9FocusGained(evt);
            }
        });
        jScrollPane15.setViewportView(jList9);

        jButtonADD2.setText("Agregar >>");
        jButtonADD2.setEnabled(false);
        jButtonADD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonADD2ActionPerformed(evt);
            }
        });

        jButtonREMOV2.setText("<< Quitar");
        jButtonREMOV2.setEnabled(false);
        jButtonREMOV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonREMOV2ActionPerformed(evt);
            }
        });

        jList10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jList10.setModel(new DefaultListModel ());
        jList10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList10FocusGained(evt);
            }
        });
        jScrollPane16.setViewportView(jList10);

        jButtonUP2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonUP2.setText("˄");
        jButtonUP2.setEnabled(false);
        jButtonUP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUP2ActionPerformed(evt);
            }
        });

        jButtonDO2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonDO2.setText("˅");
        jButtonDO2.setEnabled(false);
        jButtonDO2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDO2ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel25.setText("Parametros:");

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel26.setText("Enviar a:");

        jButton5.setText("añadir >>");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("<<remover");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonREMOV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonADD2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonUP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonDO2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel26))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jButtonUP2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDO2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(jScrollPane15)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jButtonADD2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonREMOV2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 803, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DATO FALTANTE", jPanel11);

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("DATOS FALTANTES");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("DATOS SUMINISTRADOS");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jDateChooserNewStmFech.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jDateChooserNewStmFech.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setText("DESDE:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel2.setText("HASTA:");

        jDateChooserNewStmFech1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jDateChooserNewStmFech1.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel29.setText("DÍA:");

        jComboBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        jComboBoxDia.setOpaque(false);

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel30.setText("HORA DEL ENVIO:");

        jComboBoxHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));

        jCheckBox2.setText("PROGRAMAR ENVIO");
        jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel31.setText("PERIODO (MESES):");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setText("SERVIDOR:");

        jTextFieldServi.setText("jTextField1");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setText("PUERTO:");

        jTextFieldPuerto.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setText("BASE DE DATOS:");

        jTextFieldBaseDatos.setText("jTextField1");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel6.setText("USUARIO:");

        jTextFieldUsuario.setText("jTextField1");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel7.setText("CLAVE:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordFiel2)
                    .addComponent(jTextFieldBaseDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
                    .addComponent(jTextFieldServi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldPuerto)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldServi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldBaseDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jPasswordFiel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("SERVIDOR", jPanel10);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel8.setText("DIRECCIÓN BD");

        jTextFieldServi1.setText("jTextField1");

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel32.setText("USUARIO:");

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel33.setText("CLAVE:");

        jButton10.setText("añadir");
        jButton10.setToolTipText("Selleccione el directorio donde se crearan los Informes");
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldBaseDatos1)
                    .addComponent(jTextFieldPuerto1)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jTextFieldServi1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldServi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextFieldPuerto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextFieldBaseDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("SQLITE", jPanel14);

        jRadioButton3.setText("MySQL");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setSelected(true);
        jRadioButton4.setText("SQLite");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserNewStmFech, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserNewStmFech1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxHora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxDia, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox2))
                        .addGap(48, 48, 48))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserNewStmFech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jDateChooserNewStmFech1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox2)
                            .addComponent(jLabel30)))
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.add(jPanel9, "card4");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Process.. Consola"));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("GENERAR REPORTE PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 656, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel3.add(jPanel8, "card3");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanelProgresMixto1.setPreferredSize(new java.awt.Dimension(207, 25));

        jProgressBarMixto1.setForeground(new java.awt.Color(0, 0, 255));
        jProgressBarMixto1.setMaximum(50);

        jLabelProgress1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabelProgress1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelProgress1.setText("jLabel2");

        javax.swing.GroupLayout jPanelProgresMixto1Layout = new javax.swing.GroupLayout(jPanelProgresMixto1);
        jPanelProgresMixto1.setLayout(jPanelProgresMixto1Layout);
        jPanelProgresMixto1Layout.setHorizontalGroup(
            jPanelProgresMixto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProgresMixto1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelProgress1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarMixto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelProgresMixto1Layout.setVerticalGroup(
            jPanelProgresMixto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelProgress1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jProgressBarMixto1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelProgresMixto1, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelProgresMixto1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jCheckBox1.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("OK CONEXIÓN");
        jCheckBox1.setEnabled(false);

        jButton7.setText("ENVIAR EMAIL");
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText("2do PLANO");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, 0)
                        .addComponent(jButton4)
                        .addGap(0, 0, 0)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList2FocusGained

        jButton20.setEnabled(false);

        jButton21.setEnabled(true);
        jButton22.setEnabled(true);
        jButtonDOWN.setEnabled(true);

        jList1.setSelectionBackground(Color.lightGray);
        jList2.setSelectionBackground(color);

    }//GEN-LAST:event_jList2FocusGained

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed

      java.util.List parametros=  jList1.getSelectedValuesList();
      
      int index = jList1.getSelectedIndex();
      
      for(int i=0; i<parametros.size();i++){
      
   ((DefaultListModel<String>) jList2.getModel()).addElement(parametros.get(i).toString());
   ((DefaultListModel<String>) jList1.getModel()).removeElement(parametros.get(i).toString());

      }
      
           index=index-1;
      
      if(index<0){index=0;}
      
      jList1.setSelectedIndex(index);

    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed

      java.util.List parametros = jList2.getSelectedValuesList();
      
      int index =jList2.getSelectedIndex();
      
      
      for(int i=0; i<parametros.size();i++){
      
   ((DefaultListModel<String>) jList1.getModel()).addElement(parametros.get(i).toString());
   ((DefaultListModel<String>) jList2.getModel()).removeElement(parametros.get(i).toString());

      }
      
      index=index-1;
      
      if(index<0){index=0;}
      
      jList2.setSelectedIndex(index);

    }//GEN-LAST:event_jButton21ActionPerformed

    private void jList1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList1FocusGained

        jButton20.setEnabled(true);
        jButton21.setEnabled(false);

        jButton22.setEnabled(false);
        jButtonDOWN.setEnabled(false);

        jList1.setSelectionBackground(color);
        jList2.setSelectionBackground(Color.lightGray);

    }//GEN-LAST:event_jList1FocusGained

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed

        try {

            int indexOfSelected = jList2.getSelectedIndex();

            swapElements(indexOfSelected, indexOfSelected - 1);

            indexOfSelected = indexOfSelected - 1;

            jList2.setSelectedIndex(indexOfSelected );
            jList2.updateUI();

        } catch (IndexOutOfBoundsException e) {
        }

    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButtonDOWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDOWNActionPerformed

        try {

            int indexOfSelected = jList2.getSelectedIndex();

            swapElements(indexOfSelected, indexOfSelected + 1);

            indexOfSelected = indexOfSelected + 1;

            jList2.setSelectedIndex(indexOfSelected );
            jList2.updateUI();

        } catch (IndexOutOfBoundsException e) {
        }

    }//GEN-LAST:event_jButtonDOWNActionPerformed

    private void jList3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList3FocusGained
       
              jButtonADD.setEnabled(false);  
        
      jButtonREMOV.setEnabled(true);
      jButtonUP.setEnabled(true);
      jButtonDO.setEnabled(true);
      
      
      jList4.setSelectionBackground(Color.lightGray);
       jList3.setSelectionBackground(color);
        
        
        
        
    }//GEN-LAST:event_jList3FocusGained

    private void jButtonADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonADDActionPerformed
       
              java.util.List parametros=  jList4.getSelectedValuesList();
      
      int index = jList4.getSelectedIndex();
      
      for(int i=0; i<parametros.size();i++){
      
   ((DefaultListModel<String>) jList3.getModel()).addElement(parametros.get(i).toString());
   ((DefaultListModel<String>) jList4.getModel()).removeElement(parametros.get(i).toString());

      }
      
           index=index-1;
      
      if(index<0){index=0;}
      
      jList4.setSelectedIndex(index);
        
        
        
    }//GEN-LAST:event_jButtonADDActionPerformed

    private void jButtonREMOVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonREMOVActionPerformed

              java.util.List parametros = jList3.getSelectedValuesList();
      
      int index =jList3.getSelectedIndex();
      
      
      for(int i=0; i<parametros.size();i++){
      
   ((DefaultListModel<String>) jList4.getModel()).addElement(parametros.get(i).toString());
   ((DefaultListModel<String>) jList3.getModel()).removeElement(parametros.get(i).toString());

      }
      
      index=index-1;
      
      if(index<0){index=0;}
      
      jList3.setSelectedIndex(index);
        
        
        
    }//GEN-LAST:event_jButtonREMOVActionPerformed

    private void jList4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList4FocusGained

        jButtonADD.setEnabled(true);
        
        jButtonREMOV.setEnabled(false);
        jButtonUP.setEnabled(false);
        jButtonDO.setEnabled(false);
        
        jList4.setSelectionBackground(color);
        jList3.setSelectionBackground(Color.lightGray);
        
        
        
    }//GEN-LAST:event_jList4FocusGained

    private void jButtonUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUPActionPerformed
       
        try {
            
  int indexOfSelected = jList3.getSelectedIndex();
   
  swapElementsM(indexOfSelected, indexOfSelected - 1);
  
  indexOfSelected = indexOfSelected - 1;
  
    jList3.setSelectedIndex(indexOfSelected );
    jList3.updateUI();           
            
            
        } catch (IndexOutOfBoundsException e) {
        }
   


        
    }//GEN-LAST:event_jButtonUPActionPerformed

    private void jButtonDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDOActionPerformed
      
                try {
            
  int indexOfSelected = jList3.getSelectedIndex();
   
  swapElementsM(indexOfSelected, indexOfSelected + 1);
  
  indexOfSelected = indexOfSelected + 1;
  
    jList3.setSelectedIndex(indexOfSelected );
    jList3.updateUI();           
            
            
        } catch (IndexOutOfBoundsException e) {
        }
        
        
        
    }//GEN-LAST:event_jButtonDOActionPerformed

    private void jList5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList5FocusGained
        
        jButtonUP1.setEnabled(true);

        jButtonADD1.setEnabled(false);
        jButtonREMOV1.setEnabled(true);
        jButtonDO1.setEnabled(true);

        jList6.setSelectionBackground(Color.lightGray);
        jList5.setSelectionBackground(color);
  
        
    }//GEN-LAST:event_jList5FocusGained

    private void jButtonADD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonADD1ActionPerformed
      
        
      java.util.List parametros=  jList6.getSelectedValuesList();
      
      int index = jList6.getSelectedIndex();
      
      for(int i=0; i<parametros.size();i++){
      
   ((DefaultListModel<String>) jList5.getModel()).addElement(parametros.get(i).toString());
   ((DefaultListModel<String>) jList6.getModel()).removeElement(parametros.get(i).toString());

      }
      
           index=index-1;
      
      if(index<0){index=0;}
      
      jList6.setSelectedIndex(index);
        
//        if(jList5.getModel().getSize()>=8){
//        
//            jButtonADD1.setEnabled(false);
//        
//        }
        
        
        
    }//GEN-LAST:event_jButtonADD1ActionPerformed

    private void jButtonREMOV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonREMOV1ActionPerformed
       
        
              java.util.List parametros = jList5.getSelectedValuesList();
      
      int index =jList5.getSelectedIndex();
      
      
      for(int i=0; i<parametros.size();i++){
      
   ((DefaultListModel<String>) jList6.getModel()).addElement(parametros.get(i).toString());
   ((DefaultListModel<String>) jList5.getModel()).removeElement(parametros.get(i).toString());

      }
      
      index=index-1;
      
      if(index<0){index=0;}
      
      jList5.setSelectedIndex(index);
        
      
//              if(jList5.getModel().getSize()<=7){
//        
//            jButtonADD1.setEnabled(true);
//        
//        }
      
        
        
    }//GEN-LAST:event_jButtonREMOV1ActionPerformed

    private void jList6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList6FocusGained
       
        jButtonADD1.setEnabled(true);
        jButtonREMOV1.setEnabled(false);

        jButtonUP1.setEnabled(false);
        jButtonDO1.setEnabled(false);

        jList6.setSelectionBackground(color);
        jList5.setSelectionBackground(Color.lightGray);
        
//                if(jList5.getModel().getSize()>=8){
//        
//            jButtonADD1.setEnabled(false);
//        
//        }
        
        
    }//GEN-LAST:event_jList6FocusGained

    private void jButtonUP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUP1ActionPerformed
       
        
                try {

            int indexOfSelected = jList5.getSelectedIndex();

            swapElementsA(indexOfSelected, indexOfSelected - 1);

            indexOfSelected = indexOfSelected - 1;

            jList5.setSelectedIndex(indexOfSelected );
            jList5.updateUI();

        } catch (IndexOutOfBoundsException e) {
        }

        
        
        
    }//GEN-LAST:event_jButtonUP1ActionPerformed

    private void jButtonDO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDO1ActionPerformed
        
                try {

            int indexOfSelected = jList5.getSelectedIndex();

            swapElementsA(indexOfSelected, indexOfSelected + 1);

            indexOfSelected = indexOfSelected + 1;

            jList5.setSelectedIndex(indexOfSelected );
            jList5.updateUI();

        } catch (IndexOutOfBoundsException e) {
        }
        
        
    }//GEN-LAST:event_jButtonDO1ActionPerformed

    private void jList7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList7FocusGained
       
        jButton23.setEnabled(false);

        jButton24.setEnabled(true);
        jButton25.setEnabled(true);
        jButtonDOWN1.setEnabled(true);

        jList8.setSelectionBackground(Color.lightGray);
        jList7.setSelectionBackground(color);
        
        
        
    }//GEN-LAST:event_jList7FocusGained

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
     
      java.util.List parametros=  jList8.getSelectedValuesList();
      
      int index = jList8.getSelectedIndex();
      
      for(int i=0; i<parametros.size();i++){
      
   ((DefaultListModel<String>) jList7.getModel()).addElement(parametros.get(i).toString());
   ((DefaultListModel<String>) jList8.getModel()).removeElement(parametros.get(i).toString());

      }
      
           index=index-1;
      
      if(index<0){index=0;}
      
      jList8.setSelectedIndex(index);
        
        
        
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
     
              java.util.List parametros = jList7.getSelectedValuesList();
      
      int index =jList7.getSelectedIndex();
      
      
      for(int i=0; i<parametros.size();i++){
      
   ((DefaultListModel<String>) jList8.getModel()).addElement(parametros.get(i).toString());
   ((DefaultListModel<String>) jList7.getModel()).removeElement(parametros.get(i).toString());

      }
      
      index=index-1;
      
      if(index<0){index=0;}
      
      jList7.setSelectedIndex(index);
        
        
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jList8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList8FocusGained
        
        
        jButton23.setEnabled(true);
        jButton24.setEnabled(false);

        jButton25.setEnabled(false);
        jButtonDOWN1.setEnabled(false);

        jList8.setSelectionBackground(color);
        jList7.setSelectionBackground(Color.lightGray);
        
        
        
    }//GEN-LAST:event_jList8FocusGained

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
       
        
               try {

            int indexOfSelected = jList7.getSelectedIndex();

            swapElementsV(indexOfSelected, indexOfSelected - 1);

            indexOfSelected = indexOfSelected - 1;

            jList7.setSelectedIndex(indexOfSelected );
            jList7.updateUI();

        } catch (IndexOutOfBoundsException e) {
        }

        
        
        
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButtonDOWN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDOWN1ActionPerformed
      
                try {

            int indexOfSelected = jList7.getSelectedIndex();

            swapElementsV(indexOfSelected, indexOfSelected + 1);

            indexOfSelected = indexOfSelected + 1;

            jList7.setSelectedIndex(indexOfSelected );
            jList7.updateUI();

        } catch (IndexOutOfBoundsException e) {
        }
        
        
        
        
    }//GEN-LAST:event_jButtonDOWN1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        
           Thread Tarea=new Thread(new HiloGeneraMultiReport()); 
           Tarea.start();
           Tarea = null;
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
     
        if(jRadioButton2.isSelected()){jRadioButton2.setSelected(false);}
        
        
        
        
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
      
        if(jRadioButton1.isSelected()){jRadioButton1.setSelected(false);}
        
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  
//        jButton3.setBackground(Color.lightGray);
//        jButton2.setBackground(Color.white);
        
        jPanel9.setVisible(true);
        jPanel8.setVisible(false);
  
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
//        jButton2.setBackground(Color.lightGray);
//        jButton3.setBackground(Color.white);
        
        jPanel9.setVisible(false);
        jPanel8.setVisible(true);
     
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jList9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList9FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jList9FocusGained

    private void jButtonADD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonADD2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonADD2ActionPerformed

    private void jButtonREMOV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonREMOV2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonREMOV2ActionPerformed

    private void jList10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList10FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jList10FocusGained

    private void jButtonUP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUP2ActionPerformed

    private void jButtonDO2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDO2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDO2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
         
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    

            saveConfi();
        
    }//GEN-LAST:event_formWindowClosing

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      
        
                     JTextField jpf = new JTextField();
                    jpf.selectAll();
        
        
                            int Aceptar;
                    
                    
                    JLabel titulo = new JLabel ("Ingrese Email de Aseguradora:");
                    JLabel titulo2 = new JLabel ("Email");
                    
                    Aceptar=JOptionPane.showConfirmDialog(null, new Object[]{titulo,null, jpf},
                            "Ingrese Email:", JOptionPane.OK_CANCEL_OPTION);
                    
                    
                    if(Aceptar==0){
                    
                   String email = jpf.getText();
                    
                   ((DefaultListModel<String>) jList10.getModel()).addElement(email);
                   
                    
                    
                    }
        
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     
      java.util.List parametros = jList10.getSelectedValuesList();
      
        

        String email =parametros.get(0).toString();
        
        int p = JOptionPane.showConfirmDialog(null, "¿ Confirma que desea remover de la lista el email:\n "
           +email+" ?","Remover email de Aseguradora", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        
        
        
     if(p==0){
         
           
        ((DefaultListModel<String>) jList10.getModel()).removeElement(parametros.get(0).toString()); 
     
     }
        
             
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    
       
        try {
            
            
         cargarDatos();

                          
       Iterator<Aseguradoras> it = aseguradorasData.iterator();
       int counter = 0;
            
       while(it.hasNext()){
       
         Aseguradoras aseg= it.next();
         
 if(counter<8){
 ((DefaultListModel<String>) jList5.getModel()).addElement(aseg.getNombrecorto());
 } 
 
 else{((DefaultListModel<String>) jList6.getModel()).addElement(aseg.getNombrecorto());}
 
 counter++;
       
       }

                                
            
        } catch (Exception e) {
            
            jCheckBox1.setText("FALLIDA");
            jCheckBox1.setSelected(false);

            System.out.println(e);
        }
  
        
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 
        
     File archivoPDF = new File(jTextFieldDirectorio.getText()+"\\InformeCalidadA.pdf");   
     EnviarEmail(archivoPDF);
    


        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
     
        


JFileChooser chooser = new JFileChooser(jTextFieldDirectorio.getText());
chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
chooser.setDialogTitle("[ Seleccione el Directorio ]");



int returnVal = chooser.showOpenDialog(chooser);

if(returnVal == JFileChooser.APPROVE_OPTION) {
    
  jTextFieldDirectorio.setText(chooser.getSelectedFile().getAbsolutePath());  
    
//   System.out.println("You chose to open this directory: " +
//        chooser.getSelectedFile().getAbsolutePath());
}
        
        
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    
        
        try {
            
            if(SystemTray.isSupported()){
            
            systemtray.add(trayicon);
            this.setVisible(false);
            
            }
            
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
        
        
        
    }//GEN-LAST:event_jButton9ActionPerformed

           private void instanciarTray(){
    
    trayicon = new TrayIcon(imageicon.getImage(),"tooltip del icon",popupMenu1);
    trayicon.setImageAutoSize(true);
    systemtray= SystemTray.getSystemTray();
    
    
    }
    
    
    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
     
   




  
            
        if(jCheckBox2.isSelected()){
        
jComboBoxDia.setEnabled(false);
jComboBoxHora.setEnabled(false);
            
          

//GeneraReport programar = new GeneraReport(this);

//Thread pro = new Thread(programar);
//pro.run();

//programar.execute();
            

        
        }else{
        

 
jComboBoxDia.setEnabled(true);
jComboBoxHora.setEnabled(true);

            
        }
        
        
        
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
        
JFileChooser chooser = new JFileChooser(jTextFieldDirectorio.getText());
chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
chooser.setDialogTitle("[ Seleccione el Directorio ]");



int returnVal = chooser.showOpenDialog(chooser);

if(returnVal == JFileChooser.APPROVE_OPTION) {
    
  jTextFieldServi1.setText(chooser.getSelectedFile().getAbsolutePath());  
    

}
        
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
     
        jRadioButton3.setSelected(true);
        jRadioButton4.setSelected(false);
        
        
        
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(true);
        
        
        
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
       systemtray.remove(trayicon);
       this.setVisible(true);
    }//GEN-LAST:event_menuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CiberneticaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CiberneticaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CiberneticaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CiberneticaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CiberneticaForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton10;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton20;
    public javax.swing.JButton jButton21;
    public javax.swing.JButton jButton22;
    public javax.swing.JButton jButton23;
    public javax.swing.JButton jButton24;
    public javax.swing.JButton jButton25;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    public javax.swing.JButton jButton6;
    public javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    public javax.swing.JButton jButton9;
    public javax.swing.JButton jButtonADD;
    public javax.swing.JButton jButtonADD1;
    public javax.swing.JButton jButtonADD2;
    public javax.swing.JButton jButtonDO;
    public javax.swing.JButton jButtonDO1;
    public javax.swing.JButton jButtonDO2;
    public javax.swing.JButton jButtonDOWN;
    public javax.swing.JButton jButtonDOWN1;
    public javax.swing.JButton jButtonREMOV;
    public javax.swing.JButton jButtonREMOV1;
    public javax.swing.JButton jButtonREMOV2;
    public javax.swing.JButton jButtonUP;
    public javax.swing.JButton jButtonUP1;
    public javax.swing.JButton jButtonUP2;
    public javax.swing.JCheckBox jCheckBox1;
    public javax.swing.JCheckBox jCheckBox2;
    public javax.swing.JComboBox<String> jComboBox3;
    public javax.swing.JComboBox<String> jComboBoxDia;
    public javax.swing.JComboBox<String> jComboBoxHora;
    public com.toedter.calendar.JDateChooser jDateChooserNewStmFech;
    public com.toedter.calendar.JDateChooser jDateChooserNewStmFech1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    public javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    public javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelProgress1;
    public javax.swing.JList<String> jList1;
    public javax.swing.JList<String> jList10;
    public javax.swing.JList<String> jList2;
    public javax.swing.JList<String> jList3;
    public javax.swing.JList<String> jList4;
    public javax.swing.JList<String> jList5;
    public javax.swing.JList<String> jList6;
    public javax.swing.JList<String> jList7;
    public javax.swing.JList<String> jList8;
    public javax.swing.JList<String> jList9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    public javax.swing.JPanel jPanel11;
    public javax.swing.JPanel jPanel12;
    public javax.swing.JPanel jPanel13;
    public javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanel9;
    public javax.swing.JPanel jPanelProgresMixto1;
    public javax.swing.JPasswordField jPasswordFiel1;
    public javax.swing.JPasswordField jPasswordFiel2;
    public javax.swing.JProgressBar jProgressBarMixto1;
    public javax.swing.JRadioButton jRadioButton1;
    public javax.swing.JRadioButton jRadioButton2;
    public javax.swing.JRadioButton jRadioButton3;
    public javax.swing.JRadioButton jRadioButton4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane10;
    public javax.swing.JScrollPane jScrollPane11;
    public javax.swing.JScrollPane jScrollPane12;
    public javax.swing.JScrollPane jScrollPane13;
    public javax.swing.JScrollPane jScrollPane14;
    public javax.swing.JScrollPane jScrollPane15;
    public javax.swing.JScrollPane jScrollPane16;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JScrollPane jScrollPane8;
    public javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTabbedPane jTabbedPane2;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextArea jTextAreaMensaje;
    public javax.swing.JTextArea jTextAreaPara;
    public javax.swing.JTextField jTextFieldAsunto;
    public javax.swing.JTextField jTextFieldBaseDatos;
    public javax.swing.JTextField jTextFieldBaseDatos1;
    public javax.swing.JTextField jTextFieldCC;
    public javax.swing.JTextField jTextFieldCO;
    public javax.swing.JTextField jTextFieldDirectorio;
    public javax.swing.JTextField jTextFieldPuerto;
    public javax.swing.JTextField jTextFieldPuerto1;
    public javax.swing.JTextField jTextFieldPuertoCorreo;
    public javax.swing.JTextField jTextFieldSerCorreo;
    public javax.swing.JTextField jTextFieldServi;
    public javax.swing.JTextField jTextFieldServi1;
    public javax.swing.JTextField jTextFieldUsuario;
    public javax.swing.JTextField jTextFieldUsuarioCorreo;
    public java.awt.MenuItem menuItem1;
    public java.awt.PopupMenu popupMenu1;
    // End of variables declaration//GEN-END:variables


         private class HiloGeneraMultiReport implements Runnable{

         

             
         
        @Override
        public void run() {

           jProgressBarMixto1.setIndeterminate(true);
           jProgressBarMixto1.setStringPainted(false);
           jLabelProgress1.setText("Generando Reporte:");
           jPanel2.setVisible(true);
           jPanelProgresMixto1.setVisible(true);
           jProgressBarMixto1.setVisible(true);
           jLabelProgress1.setVisible(true);
          
   JFileChooser file=new JFileChooser();
   String FileName= "InformeCalidadA";     
           
        if(jRadioButton2.isSelected()) {FileName= "InformeCalidadB";}
   
   
        file.setDialogTitle("[ Exportar a PDF ]");
        FileFilter filter = new FileNameExtensionFilter("PDF file", "pdf", "pdf");
        file.setFileFilter(filter);
        File archivo = new File(FileName+".pdf");
        file.setSelectedFile(archivo); 
          
        int result =file.showSaveDialog(null);
        
    if(result == JFileChooser.APPROVE_OPTION){
        
        File ArchivoPDF =file.getSelectedFile(); 
        
           jProgressBarMixto1.setIndeterminate(true);
           jProgressBarMixto1.setStringPainted(false);
           jLabelProgress1.setText("Generando Reporte:");
           jPanelProgresMixto1.setVisible(true);
           jProgressBarMixto1.setVisible(true);
           jLabelProgress1.setVisible(true);
           
           
            try {
                
                
                
                ArrayList listaData = new ArrayList();
                
                DataReporte Data= new DataReporte(1);
                

                
//conectar cc=new conectar();
//Connection cn= cc.conexion("");  
        

    String Text=jTextArea1.getText();
    Text=Text+"Iniciando Conexión... Ok\n";
    jTextArea1.setText(Text);
    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());


     

           for(int i=0;i < jList5.getModel().getSize();i++){
               
               
String can1="0";
String can2="0"; 
String can3="0"; 
String can4="0"; 
String can5="0"; 
String can6="0"; 
String can7="0"; 
String can8="0"; 
String can9="0"; 
String can10="0"; 
String can11="0"; 
String can12="0"; 
String can13="0"; 
String can14="0"; 
String can15="0"; 
String can16="0"; 
String can17="0"; 
String can18="0"; 


String Por1="0.0";
String Por2="0.0";
String Por3="0.0";
String Por4="0.0";
String Por5="0.0";
String Por6="0.0";
String Por7="0.0";
String Por8="0.0";
String Por9="0.0";
String Por10="0.0";
String Por11="0.0";
String Por12="0.0";
String Por13="0.0";
String Por14="0.0";
String Por15="0.0";
String Por16="0.0";
String Por17="0.0";
String Por18="0.0";

String reclamosT="0";
String reclamantesT="0";
String vehiculosT="0";

String reclamosP="0.0";
String reclamantesP="0.0";
String vehiculosP="0.0";           
               

              BigDecimal TotalCeldasAmarillas = BigDecimal.ZERO;

               BigDecimal TCreclamos = BigDecimal.ZERO;
               BigDecimal TCreclamantes = BigDecimal.ZERO;

               BigDecimal TotalDatosNoSum = BigDecimal.ZERO;

               String aseg = jList5.getModel().getElementAt(i).toString();
           

            
            
            Long counter= crrReclamosData.stream().filter(recl->recl.getCodaseguradora().getNombrecorto().equals(aseg)).count();
            
           BigDecimal rcT= new BigDecimal(counter);
        
            reclamosT=rcT.toPlainString();
            reclamosP="100.0";

            
            counter= crrReclamantesData.stream().filter(recl->recl.getCodaseguradora().getNombrecorto().equals(aseg)).count();
            
            
               System.out.println(aseg +" "+counter);
        
          //  BigDecimal rcT = rs2.getBigDecimal("count");
            
            rcT= new BigDecimal(counter);
        
            reclamantesT=rcT.toPlainString();
            reclamantesP="100.0";
            

        
            counter= crrReclamantesData.stream().filter(recl->recl.getCodaseguradora().getNombrecorto().equals(aseg)).count();
            
//        BigDecimal rcT = rs1.getBigDecimal("count");
            rcT= new BigDecimal(counter);
        
            reclamantesT=rcT.toPlainString();
            reclamantesP="100.0";
            



        
            
             counter= crrReclamantesData.stream().filter(recl->recl.getCodaseguradora().getNombrecorto().equals(aseg) 
                     && ! recl.getNummotor().equals("")&&  recl.getNummotor()!=null).count();
            
             rcT= new BigDecimal(counter);
          //  BigDecimal rcT = rs5.getBigDecimal("count");
        
            vehiculosT=rcT.toPlainString();
            vehiculosP="100.0";
            

           
           
                     for(int k=0;k < jList2.getModel().getSize();k++){
           
                         
                  TCreclamos=TCreclamos.add(BigDecimal.ONE);
                         
           String parametro= jList2.getModel().getElementAt(k).toString();
           
           
                         Date dateI = jDateChooserNewStmFech.getDate();
                         Date dateF = jDateChooserNewStmFech1.getDate();

                         SimpleDateFormat sdf = new SimpleDateFormat("MMM d yyyy HH:mma", Locale.US);
           
         

        String reg="";
        Long counter1 = null;
        
           if(parametro.contentEquals("* Número de Parte")){reg="numparte";
           
              java.util.List<CrrReclamo> lista=  crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                        .getNombrecorto().equals(aseg))
                        .filter(recl -> {

                            Date fecha = null;
                            try {
                                fecha = sdf.parse(recl.getFecharegistro());
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }

                            return (fecha.before(dateF) || (fecha.before(dateI)))
                                    || (fecha.after(dateF) && fecha.before(dateI));
                        })
                        .filter(recl ->                                             
                         (recl.getNumparte().trim().equals("")
                        || recl.getNumparte().trim().equals(".") || recl.getNumparte().trim().equals("0")
                        || recl.getNumparte().trim().equals(".."))).collect(Collectors.toList());
              
              
              int tot=0;
              
              for(CrrReclamo reclamo:lista){
              
   Text = jTextArea1.getText();
   
    Text=Text+aseg+": "+reclamo.getFecharegistro()+" "+reg+" "+tot+"\n";
    jTextArea1.setText(Text);
    
    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum()); 
    
    tot++;
              
              }
              
              counter1=Long.valueOf(tot);
           
           
           }
           if(parametro.contentEquals("* Número de Placa Policia")){reg="numeroplacapolicia";
           
               java.util.List<CrrReclamo> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .filter(recl -> (recl.getNumeroplacapolicia().trim().equals("")
                       || recl.getNumeroplacapolicia().trim().equals(".") || recl.getNumeroplacapolicia().trim().equals("0")
                       || recl.getNumeroplacapolicia().trim().equals(".."))).collect(Collectors.toList());

               int tot = 0;
               for (CrrReclamo reclamo : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamo.getFecharegistro() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }

               counter1 = Long.valueOf(tot);
           
           }
           if(parametro.contentEquals("* Número de Resolución")){reg="numresolucion";
           
           
               java.util.List<CrrReclamo> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .filter(recl -> (recl.getNumresolucion().trim().equals("")
                       || recl.getNumresolucion().trim().equals(".") || recl.getNumresolucion().trim().equals("0")
                       || recl.getNumresolucion().trim().equals(".."))).collect(Collectors.toList());

               int tot = 0;
               for (CrrReclamo reclamo : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamo.getFecharegistro() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }

               counter1 = Long.valueOf(tot);
           
           }
           if(parametro.contentEquals("* Número de Denuncia")){reg="numdenuncia";
           
           
              java.util.List<CrrReclamo> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .filter(recl -> (recl.getNumdenuncia().trim().equals("")
                       || recl.getNumdenuncia().trim().equals(".") || recl.getNumdenuncia().trim().equals("0")
                       || recl.getNumdenuncia().trim().equals(".."))).collect(Collectors.toList());

               int tot = 0;
               for (CrrReclamo reclamo : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamo.getFecharegistro() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }

               counter1 = Long.valueOf(tot);
           
           
           
           }
           if(parametro.contentEquals("* Número de Oficio")){reg="numoficio";
           
           
           
               java.util.List<CrrReclamo> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .filter(recl -> (recl.getNumoficio().trim().equals("")
                       || recl.getNumoficio().trim().equals(".") || recl.getNumoficio().trim().equals("0")
                       || recl.getNumoficio().trim().equals(".."))).collect(Collectors.toList());

               int tot = 0;
               for (CrrReclamo reclamo : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamo.getFecharegistro() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }

               counter1 = Long.valueOf(tot);
           

           
           }
           
           
            try {
            
     
          

        
    
  if(jRadioButton2.isSelected()){
  
  counter1 = Integer.parseInt(reclamosT)-counter1;
  }      
        
    if(k==0){
        
   can1=Long.toString(counter1);
  
   if(counter1>0){
   BigDecimal totb= new BigDecimal(counter1);
   BigDecimal totrb= new BigDecimal(reclamosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por1=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
   
   }

    
    }
    if(k==1){can2=Long.toString(counter1);
    if(counter1>0){
    
   BigDecimal totb= new BigDecimal(counter1);
   BigDecimal totrb= new BigDecimal(reclamosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por2=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
   
    }
    

    } 
    if(k==2){can3=Long.toString(counter1);
    
    if(counter1>0){
    
   BigDecimal totb= new BigDecimal(counter1);
   BigDecimal totrb= new BigDecimal(reclamosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   
   Por3=porcentaje.toPlainString();
    
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
    }

    
    
    } 
    
    if(k==3){can4=Long.toString(counter1);
    
    if(counter1>0){
   BigDecimal totb= new BigDecimal(counter1);
   BigDecimal totrb= new BigDecimal(reclamosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por4=porcentaje.toPlainString();
    
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
    }

    
    }
    
    if(k==4){can5=Long.toString(counter1);
    
    if(counter1>0){
   
   BigDecimal totb= new BigDecimal(counter1);
   BigDecimal totrb= new BigDecimal(reclamosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por5=porcentaje.toPlainString();   
    
TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);

    }

    } 
        

    
           
         TotalDatosNoSum=TotalDatosNoSum.add(new BigDecimal(counter1)); 
        
        
        }
        

        
        catch(Exception e){
        
    System.out.println(e);
    
    Text=jTextArea1.getText();
    Text=Text+e+"\n";
    jTextArea1.setText(Text);
    
    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
            
            
        }          
            
            

            
          
 
           
           }
                     

           
                     
                     
                                 for(int k=0;k < jList3.getModel().getSize();k++){
           
                                     String parametro = jList3.getModel().getElementAt(k).toString();

                                     TCreclamantes = TCreclamantes.add(BigDecimal.ONE);

                                     Date dateI = jDateChooserNewStmFech.getDate();
                                     Date dateF = jDateChooserNewStmFech1.getDate();

                                     SimpleDateFormat sdf = new SimpleDateFormat("MMM d yyyy HH:mma", Locale.US);
           
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Nombre de Asegurados");
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Nombre de Terceros");
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Nombre de Conductores");
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Apellido de Asegurados");
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Apellido de Terceros");
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Primer Apellido de Conductores");
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Cedula Corrida de Asegurados");
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Cedula Corrida de Terceros");
//   ((DefaultListModel<String>) jList3.getModel()).addElement("* Cedula Corrida de Conductores");
  



String reg="";
          
int tot=0;

        if(parametro.contentEquals("* Primer Nombre de Asegurados")){reg="primernombre";
           
                 
           
                 
            java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                    .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })               
                    .flatMap(recl->recl.getCrrReclamantesList().stream())                 
                                           .filter(rectes -> (rectes.getPrimernombre().trim().equals("")
                       || rectes.getPrimernombre().trim().equals(".") || rectes.getPrimernombre().trim().equals("0")
                       || rectes.getPrimernombre().trim().equals("..")))
                    .collect(Collectors.toList());



                tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }

               
           
           
           
           }
        
        
           if(parametro.contentEquals("* Primer Nombre de Terceros")){reg="";}
           if(parametro.contentEquals("* Primer Nombre de Conductores")){reg="primernombreconductor";
           
           
                       java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                    .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })               
                    .flatMap(recl->recl.getCrrReclamantesList().stream())                 
                                           .filter(rectes -> (rectes.getPrimernombreconductor().trim().equals("")
                       || rectes.getPrimernombreconductor().trim().equals(".") || rectes.getPrimernombreconductor().trim().equals("0")
                       || rectes.getPrimernombreconductor().trim().equals("..")))
                    .collect(Collectors.toList());



                tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }
           
           
           
           
           
           
           }
           if(parametro.contentEquals("* Primer Apellido de Asegurados")){reg="primerapellido";
           
           
               java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .flatMap(recl -> recl.getCrrReclamantesList().stream())
                       .filter(rectes -> (rectes.getPrimerapellido().trim().equals("")
                       || rectes.getPrimerapellido().trim().equals(".") || rectes.getPrimerapellido().trim().equals("0")
                       || rectes.getPrimerapellido().trim().equals("..")))
                       .collect(Collectors.toList());

               tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }
           
           
           
           
           
           
           }
           if(parametro.contentEquals("* Primer Apellido de Terceros")){reg="";}
           if(parametro.contentEquals("* Primer Apellido de Conductores")){reg="primerapellidoconductor";
           
                       java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .flatMap(recl -> recl.getCrrReclamantesList().stream())
                       .filter(rectes -> (rectes.getPrimerapellidoconductor().trim().equals("")
                       || rectes.getPrimerapellidoconductor().trim().equals(".") || rectes.getPrimerapellidoconductor().trim().equals("0")
                       || rectes.getPrimerapellidoconductor().trim().equals("..")))
                       .collect(Collectors.toList());

               tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }   
           
           
           
           
           
           
           }
           if(parametro.contentEquals("* Cedula Corrida de Asegurados")){reg="cedularuccorrida";
           
           
                                  java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .flatMap(recl -> recl.getCrrReclamantesList().stream())
                       .filter(rectes -> (rectes.getCedularuccorrida().trim().equals("")
                       || rectes.getCedularuccorrida().trim().equals(".") || rectes.getCedularuccorrida().trim().equals("0")
                       || rectes.getCedularuccorrida().trim().equals("..")))
                       .collect(Collectors.toList());

               tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }   
           
           
           
           
           }
           if(parametro.contentEquals("* Cedula Corrida de Terceros")){reg="";}
           if(parametro.contentEquals("* Cedula Corrida de Conductores")){reg="cedulacorridaconductor";
           
               java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .flatMap(recl -> recl.getCrrReclamantesList().stream())
                       .filter(rectes -> (rectes.getCedulacorridaconductor().trim().equals("")
                       || rectes.getCedulacorridaconductor().trim().equals(".") || rectes.getCedulacorridaconductor().trim().equals("0")
                       || rectes.getCedulacorridaconductor().trim().equals("..")))
                       .collect(Collectors.toList());

               tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }
           
           
           
           
           }
           
           
            try {
            
                
      

                
            
//            Statement st4 = cn.createStatement();
            
//                        ResultSet rs4 = st4.executeQuery("SELECT CRR_RECLAMO.fecharegistro, CRR_RECLAMO.numeroreclamo FROM CRR_RECLAMO,Aseguradoras, CRR_RECLAMANTES \n" +
//"WHERE Aseguradoras.Nombre_corto='"+aseg+"' AND Aseguradoras.Aseguradora_id=CRR_RECLAMO.codaseguradora AND (trim(CRR_RECLAMO."+reg+")='' OR trim(CRR_RECLAMO."+reg+")='0')\n" +
//"AND CRR_RECLAMO.numeroreclamo=CRR_RECLAMANTES.numeroreclamo GROUP BY CRR_RECLAMO.numeroreclamo");
            
            
//ResultSet rs4 = st4.executeQuery("SELECT CRR_RECLAMO.fecharegistro, CRR_RECLAMO.numeroreclamo FROM CRR_RECLAMO,Aseguradoras, CRR_RECLAMANTES "
//        + "WHERE Aseguradoras.Nombre_corto='"+aseg+"' AND Aseguradoras.Aseguradora_id=CRR_RECLAMO.codaseguradora "
//        + "AND (trim(CRR_RECLAMANTES."+reg+")='' OR trim(CRR_RECLAMANTES."+reg+")='0'OR trim(CRR_RECLAMANTES."+reg+")='0'"
//                + "OR trim(CRR_RECLAMANTES."+reg+")='.'OR trim(CRR_RECLAMANTES."+reg+")='..') "
//        + "AND CRR_RECLAMO.numeroreclamo=CRR_RECLAMANTES.numeroreclamo GROUP BY CRR_RECLAMO.numeroreclamo");
        
  
       


//int tot = 0;
            
//        while (rs4.next()){
//            
//          
//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//String strFecha = rs4.getString("fecharegistro");
//Date fecha = null;
//try {
//
//fecha = sdf.parse(strFecha);
//
//} catch (ParseException ex) {
//
//ex.printStackTrace();
//
//}
//                       
//            String Date=rs.getString("fecharegistro");       
//            System.out.println(aseg+": "+fecha);
//            System.out.println(aseg+": "+strFecha);
//
//
//
// 
//        
//
//if((fecha.before(dateF)||(fecha.before(dateI)))||
//        (fecha.after(dateF) && fecha.before(dateI))){
//    
//    tot++; 
//    System.out.println(aseg+": "+fecha+" "+reg+" "+tot);
//
//    
//     Text = jTextArea1.getText();
//   
//    Text=Text+aseg+": "+fecha+" "+reg+" "+tot+"\n";
//    jTextArea1.setText(Text);
//    
//    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
//    
//    
//    
//}
//
//
//        
//        }
        
    
        
  if(jRadioButton2.isSelected()){
  
  tot = Integer.parseInt(reclamantesT)-tot;
  }         
        
        
    if(k==0){
        
   can6=Integer.toString(tot);
  
   if(tot>0){
   BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por6=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
   }

    
    }
    if(k==1){can7=Integer.toString(tot);
    if(tot>0){
    
   BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por7=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
    }
    

    } 
    if(k==2){can8=Integer.toString(tot);
    
    if(tot>0){
    
   BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   
   Por8=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
    
    }

    
    
    } 
    
    if(k==3){can9=Integer.toString(tot);
    
    if(tot>0){
       BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por9=porcentaje.toPlainString();
    
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
    }

    
    }
    
    if(k==4){can10=Integer.toString(tot);
    
    if(tot>0){
    BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por10=porcentaje.toPlainString();   
    
TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);

    }

    } 
        
    if(k==5){can11=Integer.toString(tot);
    
    if(tot>0){
    BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por11=porcentaje.toPlainString();   
    
TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);

    }

    } 
    
        if(k==6){can12=Integer.toString(tot);
    
    if(tot>0){
    BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por12=porcentaje.toPlainString();   
    
TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);

    }

    } 
        
            if(k==7){can13=Integer.toString(tot);
    
    if(tot>0){
    BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por13=porcentaje.toPlainString();   
    
TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);

    }

    } 
            
                if(k==8){can14=Integer.toString(tot);
    
    if(tot>0){
    BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(reclamantesT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por14=porcentaje.toPlainString();   
    
TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);

    }

    } 
    
    
        
//        st4.close();
//        rs4.close();
        
        TotalDatosNoSum=TotalDatosNoSum.add(new BigDecimal(tot)); 
        
        
        }
        

        
        catch(Exception e){
        
            System.out.println(e);
            
    Text=jTextArea1.getText();
    Text=Text+e+"\n";
    jTextArea1.setText(Text);
    
    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
            
            
        }          
            
            

            
           
 
           
           }
                    
                     
                     
 for(int k=0;k < jList7.getModel().getSize();k++){
           
     String parametro = jList7.getModel().getElementAt(k).toString();

     TCreclamantes = TCreclamantes.add(BigDecimal.ONE);

     Date dateI = jDateChooserNewStmFech.getDate();
     Date dateF = jDateChooserNewStmFech1.getDate();

     SimpleDateFormat sdf = new SimpleDateFormat("MMM d yyyy HH:mma", Locale.US);
           
           
//   ((DefaultListModel<String>) jList7.getModel()).addElement("* Número de Placa");
//   ((DefaultListModel<String>) jList7.getModel()).addElement("* Número de chasis");
//   ((DefaultListModel<String>) jList7.getModel()).addElement("* Número de Motor");
//   ((DefaultListModel<String>) jList7.getModel()).addElement("* VIN");
         

String reg="";
int tot=0;

           if(parametro.contentEquals("* Número de Placa")){reg="numplaca";
           
               java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .flatMap(recl -> recl.getCrrReclamantesList().stream())
                       .filter(rectes -> (rectes.getNumplaca().trim().equals("")
                       || rectes.getNumplaca().trim().equals(".") || rectes.getNumplaca().trim().equals("0")
                       || rectes.getNumplaca().trim().equals("..")))
                       .collect(Collectors.toList());

               tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }           
 
           
           }
           if(parametro.contentEquals("* Número de chasis")){reg="numchasis";
           
           
                          java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .flatMap(recl -> recl.getCrrReclamantesList().stream())
                       .filter(rectes -> (rectes.getNumchasis().trim().equals("")
                       || rectes.getNumchasis().trim().equals(".") || rectes.getNumchasis().trim().equals("0")
                       || rectes.getNumchasis().trim().equals("..")))
                       .collect(Collectors.toList());

               tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }  
           
           
           
           
           }
           if(parametro.contentEquals("* Número de Motor")){reg="nummotor";
           
               java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .flatMap(recl -> recl.getCrrReclamantesList().stream())
                       .filter(rectes -> (rectes.getNummotor().trim().equals("")
                       || rectes.getNummotor().trim().equals(".") || rectes.getNummotor().trim().equals("0")
                       || rectes.getNummotor().trim().equals("..")))
                       .collect(Collectors.toList());

               tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }
           
           
           
           
           
           }
           if(parametro.contentEquals("* VIN")){reg="vin";
           
               java.util.List<CrrReclamantes> lista = crrReclamosData.stream().filter(recl -> recl.getCodaseguradora()
                       .getNombrecorto().equals(aseg))
                       .filter(recl -> {

                           Date fecha = null;
                           try {
                               fecha = sdf.parse(recl.getFecharegistro());
                           } catch (ParseException ex) {
                               ex.printStackTrace();
                           }

                           return (fecha.before(dateF) || (fecha.before(dateI)))
                                   || (fecha.after(dateF) && fecha.before(dateI));
                       })
                       .flatMap(recl -> recl.getCrrReclamantesList().stream())
                       .filter(rectes -> (rectes.getVin().trim().equals("")
                       || rectes.getVin().trim().equals(".") || rectes.getVin().trim().equals("0")
                       || rectes.getVin().trim().equals("..")))
                       .collect(Collectors.toList());

               tot = 0;
               for (CrrReclamantes reclamante : lista) {

                   Text = jTextArea1.getText();

                   Text = Text + aseg + ": " + reclamante.getFechaRaclamo() + " " + reg + " " + tot + "\n";
                   jTextArea1.setText(Text);

                   jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());

                   tot++;

               }
           
           
           
           
           
           
           
           }

           
           
            try {
            
                
      

                
            
//            Statement st4 = cn.createStatement();
            
//                        ResultSet rs4 = st4.executeQuery("SELECT CRR_RECLAMO.fecharegistro, CRR_RECLAMO.numeroreclamo FROM CRR_RECLAMO,Aseguradoras, CRR_RECLAMANTES \n" +
//"WHERE Aseguradoras.Nombre_corto='"+aseg+"' AND Aseguradoras.Aseguradora_id=CRR_RECLAMO.codaseguradora AND (trim(CRR_RECLAMO."+reg+")='' OR trim(CRR_RECLAMO."+reg+")='0')\n" +
//"AND CRR_RECLAMO.numeroreclamo=CRR_RECLAMANTES.numeroreclamo GROUP BY CRR_RECLAMO.numeroreclamo");
            
            
//ResultSet rs4 = st4.executeQuery("SELECT CRR_RECLAMO.fecharegistro, CRR_RECLAMO.numeroreclamo FROM CRR_RECLAMO,Aseguradoras, CRR_RECLAMANTES "
//        + "WHERE Aseguradoras.Nombre_corto='"+aseg+"' AND Aseguradoras.Aseguradora_id=CRR_RECLAMO.codaseguradora "
//        + "AND (trim(CRR_RECLAMANTES."+reg+")='' OR trim(CRR_RECLAMANTES."+reg+")='0') "
//        + "AND CRR_RECLAMO.numeroreclamo=CRR_RECLAMANTES.numeroreclamo GROUP BY CRR_RECLAMO.numeroreclamo");
        
 
       


//int tot = 0;
//            
//        while (rs4.next()){
//            
//          
////SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//String strFecha = rs4.getString("fecharegistro");
//Date fecha = null;
//try {
//
//fecha = sdf.parse(strFecha);
//
//} catch (ParseException ex) {
//
//ex.printStackTrace();
//
//}
//                       
////            String Date=rs.getString("fecharegistro");       
////            System.out.println(aseg+": "+fecha);
////            System.out.println(aseg+": "+strFecha);
//
//
//
// 
//        
//
//if((fecha.before(dateF)||(fecha.before(dateI)))||
//        (fecha.after(dateF) && fecha.before(dateI))){
//    
//    tot++; 
//    System.out.println(aseg+": "+fecha+" "+reg+" "+tot);
//    
//    Text = jTextArea1.getText();
//   
//    Text=Text+aseg+": "+fecha+" "+reg+" "+tot+"\n";
//    jTextArea1.setText(Text);
//    
//    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
//    
//    
//    
//
//}
//
//
//        
//        }
    
        
        
          if(jRadioButton2.isSelected()){
  
  tot = Integer.parseInt(vehiculosT)-tot;
  }
        
        
      
    if(k==0){
        
   can15=Integer.toString(tot);
  
   if(tot>0){
   BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(vehiculosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por15=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
   }

    
    }
    if(k==1){can16=Integer.toString(tot);
    if(tot>0){
    
   BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(vehiculosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por16=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
   
    }
    

    } 
    if(k==2){can17=Integer.toString(tot);
    
    if(tot>0){
    
   BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(vehiculosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   
   Por17=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
   
    
    }

    
    
    } 
    
    if(k==3){can18=Integer.toString(tot);
    
    if(tot>0){
   
   BigDecimal totb= new BigDecimal(tot);
   BigDecimal totrb= new BigDecimal(vehiculosT);
   BigDecimal porcentaje= totb.divide(totrb,8, RoundingMode.HALF_EVEN).multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
   Por18=porcentaje.toPlainString();
   
   TotalCeldasAmarillas=TotalCeldasAmarillas.add(totb);
   
    
    }

    
    }
    
        
//        st4.close();
//        rs4.close();
        
        
         TotalDatosNoSum=TotalDatosNoSum.add(new BigDecimal(tot));  
        
        }
        

        
        catch(Exception e){
        
            System.out.println(e);
            
    Text=jTextArea1.getText();
    Text=Text+e+"\n";
    jTextArea1.setText(Text);
    
    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
            
            
        }          
            
            

         
            
 
           
           }                 
                     
        BigDecimal BigReclamos=new BigDecimal(reclamosT); 
        BigDecimal BigReclamantes=new BigDecimal(reclamantesT);
        BigDecimal totalDatos= (BigReclamos.multiply(TCreclamos)).add(BigReclamantes.multiply(TCreclamantes));
        
//        BigDecimal totalDatosSum= totalDatos.subtract(TotalDatosNoSum);
        
        
          
        String totalD=totalDatos.setScale(1,RoundingMode.HALF_EVEN).toString();
//      String totalDS=totalDatosSum.setScale(1,RoundingMode.HALF_EVEN).toString();

        String totalDS=TotalCeldasAmarillas.setScale(1,RoundingMode.HALF_EVEN).toString();
            



         String totalPD="0";
         String totalPDS="0";
         
         if(totalDatos.compareTo(BigDecimal.ZERO)!=0){totalPD="100";
         
//         BigDecimal totalPorceDS=totalDatosSum.divide(totalDatos,4,RoundingMode.HALF_EVEN)
//                 .multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);
         
             BigDecimal totalPorceDS=TotalCeldasAmarillas.divide(totalDatos,4,RoundingMode.HALF_EVEN)
                 .multiply(new BigDecimal("100")).setScale(1,RoundingMode.HALF_EVEN);     
         
         
        totalPDS=totalPorceDS.toString(); 
         }
        
        
                                        Data.addDataSubrep(new DataSubReporte(i,aseg, can1, can2,can3,can4, 
                        can5, can6, can7, can8, can9, can10, can11, can12, 
                        can13, can14, can15, can16, can17, can18, 
                        reclamosT, reclamantesT, vehiculosT, 
                        Por1, Por2, Por3, Por4, Por5, Por6, Por7, Por8, Por9, 
                        Por10, Por11, Por12, Por13, Por14, Por15, Por16, Por17, Por18, 
                        reclamosP, reclamantesP, vehiculosP,"", totalD, totalDS, totalPD, totalPDS)); 
    
    

           
           
           }





//    cn.close();            
                             

    Text=jTextArea1.getText();
    Text=Text+"Cerrando conexion a Base de datos y creando pdf..\n";
    jTextArea1.setText(Text);
    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
    
                
                listaData.add(Data);
                
                
                        HashMap Parametros = new HashMap();
         
//                     Parametros.put("parameter3", "Prueba 3"); 
//                     Parametros.put("REPORT_LOCALE",locale);
//                     Parametros.put("REPORT_RESOURCE_BUNDLE",bundle);
        
            
//             File F = new File("Apu.jasper");
//             
          JasperReport reporte = (JasperReport) JRLoader.loadObject(DataSubReporte.class.getResourceAsStream("newReport2.jasper"));  
             
          JasperReport SubReporte = (JasperReport) JRLoader.loadObject(DataSubReporte.class.getResourceAsStream("newReport1.jasper")); 
           
            
           Parametros.put("subReporte", SubReporte); 
           
           
           Parametros.put("reclamos", "RECLAMOS");
           
           
           for(int i=0;i < jList2.getModel().getSize();i++){
           
           Parametros.put("parameter"+(i+1), ""+jList2.getModel().getElementAt(i).toString());
           
           
           }
           
           
                      Parametros.put("reclamantes", "RECLAMANTES");
           
           
           for(int i=0;i < jList3.getModel().getSize();i++){
           
           Parametros.put("parameter"+(i+6), ""+jList3.getModel().getElementAt(i).toString());
           
           
           }
           
           
                                 Parametros.put("vehiculos", "VEHICULOS");
           
           
           for(int i=0;i < jList7.getModel().getSize();i++){
           
           Parametros.put("parameter"+(i+15), ""+jList7.getModel().getElementAt(i).toString());
           
           
           }
           
           Parametros.put("total","TOTAL");
           Parametros.put("totalD","Total de Datos");
           
           
           String tipo="Datos Faltantes";
           Parametros.put("totalDS","Total "+tipo);
           
             if(jRadioButton2.isSelected()){
  
  tipo="Datos Suministrados";
  Parametros.put("totalDS","Datos Suministrados");
  }
           





           
           Parametros.put("tipo",tipo);
           Parametros.put("titulo","CALIDAD DE LA INFORMACIÓN POR ASEGURADORAS");
           
SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
String fecha = dateFormat.format(jDateChooserNewStmFech.getDate());
String fecha1 = dateFormat.format(jDateChooserNewStmFech1.getDate());
           
Parametros.put("fecha","(Desde el "+fecha+" Hasta el "+fecha1+")");   
           
//           Parametros.put("parameter2", "* Número de Placa Policia");
//           Parametros.put("parameter3", "* Número de Resolución");
//           Parametros.put("parameter4", "* Número de Denuncia");
//           Parametros.put("parameter5", "* Número de Oficio");
           
           
//           Parametros.put("reclamantes", "RECLAMANTES");
//           Parametros.put("parameter1", "* Primer Nombre de Asegurados");
//           Parametros.put("parameter2", "* Primer Nombre de Terceros");
//           Parametros.put("parameter3", "* Primer Nombre de Conductores");
//           Parametros.put("parameter4", "* Primer Apellido de Asgurados");
//           Parametros.put("parameter5", "* Primer Apellido");
           
           
           
           
                       
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,  Parametros, new JRBeanCollectionDataSource(listaData));  
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");//<-----Quita Error el Error con la imagen al guardar en formato .XLS 
            
//              JasperViewer Visor =new JasperViewer(jasperPrint,false);
////            if(Demo.IsDemo){Visor=new JasperViewer2(jasperPrint);}
//            
////             Visor.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Fondos/IconoEstimE2.png")));
//             Visor.setTitle("INFORME CIBERNETICA");
//             Visor.setVisible(true);
           
           
           JRExporter exporter = new JRPdfExporter();
//           File archivo = new File("InformeCalidadDatos.pdf"); 
           
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, ArchivoPDF); 
          
        exporter.exportReport();
        
        
    Text=jTextArea1.getText();
    Text=Text+"Abriendo archivo .pdf.. Ok";
    jTextArea1.setText(Text);
    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
        
   
        
            try 
    {Desktop.getDesktop().open(ArchivoPDF);} 
    catch (IOException ex)
    { Logger.getLogger(CiberneticaForm.class.getName()).log(Level.SEVERE, null, ex); } 
        
//                EnviarEmail(ArchivoPDF);
            
//           jProgressBarMixto1.setIndeterminate(true);
//           jProgressBarMixto1.setStringPainted(false);
//           jLabelProgress1.setText("Generando Reporte:");
//           jPanelProgresMixto1.setVisible(false);
//           jProgressBarMixto1.setVisible(false);
//           jLabelProgress1.setVisible(false);
           
                
            } catch (Exception e) {
                
                System.out.println(e);
                
            }
                 
        
        
    
    
    }
    
           jProgressBarMixto1.setIndeterminate(true);
           jProgressBarMixto1.setStringPainted(false);
           jLabelProgress1.setText("");
           jPanelProgresMixto1.setVisible(false);
           jProgressBarMixto1.setVisible(false);
           jLabelProgress1.setVisible(false);
           jPanel2.setVisible(false);
    
    
    
    
                
   
            
            
    
        
        }
         
         }
         
         
         
         

         
            
             
             
  private void EnviarEmail(File file){
  
  
          
       Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp."+jTextFieldSerCorreo.getText()); // << gmail.com
         propiedad.setProperty("mail.smtp.starttls.enable", "true");
          propiedad.setProperty("mail smtp port", ""+jTextFieldPuertoCorreo.getText());// 587
           propiedad.setProperty("mail.smtp.auth", "true");
            propiedad.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
     

 // configuración para otro servidor:          
        
//Consultar con el área encargada del servidor de correos para que te provean de los datos de conexión.
//Desde Outlook, entra a configuración de la cuenta, y ahí verás los datos de conexión. << para cuando es autlook
 
 
//Properties props = new Properties();
//props.setProperty("mail.smtp.host", "smtp.miempresa123.com"); // Depende del servidor 
//props.setProperty("mail.smtp.starttls.enable", "true"); // Depende del servidor
//props.setProperty("mail.smtp.port", "587"); // Puede ser otro puerto
//props.setProperty("mail.smtp.user", "jperez@miempresa123.com");
//props.setProperty("mail.smtp.auth", "true"); // Depende del servidor
           
           
           
           
           
// Configuracion para office365
//spring.mail.host=smtp.office365.com
//spring.mail.port=587
//spring.mail.username=no-reply@.com
//spring.mail.password=
//spring.mail.properties.mail.smtp.auth=true
//spring.mail.properties.mail.smtp.starttls.enable=true
           
           
           
        
        Session sesion = Session.getDefaultInstance(propiedad);
        
//        sesion.setDebug(true); <<--- muestra estatus en consola
        
        
     
    
        String correoEnvia= jTextFieldUsuarioCorreo.getText(); //<<----- Correo desde donde vamos a enviar la informacion 
        String contrasena= String.valueOf(jPasswordFiel1.getPassword());
        String destinatario = "";
        String Asunto=jTextFieldAsunto.getText();
        String mensaje = jTextAreaMensaje.getText();
        
        
        
        ////////////////////////////////////////////////
        
        BodyPart text = new MimeBodyPart();
        BodyPart adjunto = new MimeBodyPart();
        
    try {
        text.setText(mensaje);
        
        
//        String archivo =  System.getProperty("user.dir")+"\\InformeCalidad.pdf";
//        System.out.println(archivo);
        
        adjunto.setDataHandler(new DataHandler(new FileDataSource(file)));
        adjunto.setFileName(file.getName());
//        adjunto.setFileName("InformeCalidad.pdf");
        
        
        MimeMultipart multiparte= new MimeMultipart();
        
        multiparte.addBodyPart(text);
         multiparte.addBodyPart(adjunto);
        
         
         MimeMessage message = new MimeMessage(sesion);

// Se rellena el From
message.setFrom(new InternetAddress(correoEnvia));

// Se rellenan los destinatarios

//InternetAddress[] addressesCc = null;

ArrayList Destinatarios = new ArrayList();


String to = jTextAreaPara.getText();

String[] arrTO = to.split(", ");    

for ( String ssTO : arrTO) {
    
    Destinatarios.add(ssTO);
    System.out.println(ssTO);
}



ArrayList DestinatariosCC = new ArrayList();
String CC = jTextFieldCC.getText().trim();

String[] arrCC = CC.split(", ");    

for ( String ssCC : arrCC) {
  
    if(!ssCC.equals("")){
    DestinatariosCC.add(ssCC);
    System.out.println(ssCC);
    
    }
    

  
}


ArrayList DestinatariosCO = new ArrayList();
String CO = jTextFieldCO.getText();

String[] arrCO = CO.split(", ");    


for ( String ssCO : arrCO) {
    
    if(!ssCO.equals("")){
    DestinatariosCO.add(ssCO);
    System.out.println(ssCO);
    
    }

}




//Destinatarios.add("estimsoft@gmail.com"); // <<--- Para enviar a varios 



//addressesCc = new InternetAddress[Destinatarios.getItemCount()];

 for (int i=0; i < Destinatarios.size(); i++) {
 
//Message.RecipientType.TO Destinatario principal del mensaje
//Message.RecipientType.CC Destinatario al que se envía copia del mensaje
//Message.RecipientType.BCC Destinatario al que se envía copia, pero sin que   

message.addRecipient(Message.RecipientType.TO, new InternetAddress(Destinatarios.get(i).toString()));     
  
     

                    }


      

  for (int i=0; i < DestinatariosCC.size(); i++) {
 
//Message.RecipientType.TO Destinatario principal del mensaje
//Message.RecipientType.CC Destinatario al que se envía copia del mensaje
//Message.RecipientType.BCC Destinatario al que se envía copia, pero sin que   

message.addRecipient(Message.RecipientType.CC, new InternetAddress(DestinatariosCC.get(i).toString()));     
  
     

                    }
 
 
   for (int i=0; i < DestinatariosCO.size(); i++) {
 
//Message.RecipientType.TO Destinatario principal del mensaje
//Message.RecipientType.CC Destinatario al que se envía copia del mensaje
//Message.RecipientType.BCC Destinatario al que se envía copia, pero sin que   

message.addRecipient(Message.RecipientType.BCC, new InternetAddress(DestinatariosCO.get(i).toString()));     
  
     

                    }
  
  
 


// Se rellena el subject
message.setSubject(Asunto);

// Se mete el texto y la foto adjunta.
message.setContent(multiparte);
         
         
        Transport transporte = sesion.getTransport("smtp");
        transporte.connect(correoEnvia,contrasena);
        transporte.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transporte.close();     
         
        
        System.out.println("mensaje enviado");
        
        
    } catch (MessagingException ex) {
        Logger.getLogger(CiberneticaForm.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
        
      
  
  
  
  
  
  }  
  
  private void saveConfi(){
  
  Settings.Set("valueName01",jTextFieldServi.getText());
  Settings.Set("valueName02",jTextFieldPuerto.getText());
  Settings.Set("valueName03",jTextFieldBaseDatos.getText());
  Settings.Set("valueName04",jTextFieldUsuario.getText());
  Settings.Set("valueName05",String.valueOf(jPasswordFiel2.getPassword()));
  
  
  Settings.Set("valueName06",jTextFieldSerCorreo.getText());
  Settings.Set("valueName07",jTextFieldPuertoCorreo.getText());
  Settings.Set("valueName08",jTextFieldUsuarioCorreo.getText());
  Settings.Set("valueName09",jTextFieldUsuario.getText());
  Settings.Set("valueName10",String.valueOf(jPasswordFiel1.getPassword()));
  Settings.Set("valueName11",jTextAreaPara.getText());
  Settings.Set("valueName12",jTextFieldCC.getText());
  Settings.Set("valueName13",jTextFieldCO.getText());
  Settings.Set("valueName14",jTextFieldAsunto.getText());
  Settings.Set("valueName15",jTextAreaMensaje.getText());
  Settings.Set("valueName16",jTextFieldDirectorio.getText());
  
  
   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  
  
      Settings.Set("valueName17", sdf.format(jDateChooserNewStmFech.getDate()));
      Settings.Set("valueName18", sdf.format(jDateChooserNewStmFech1.getDate()));
      Settings.Set("valueName19",jRadioButton1.isSelected());
      Settings.Set("valueName20", jRadioButton2.isSelected());
      Settings.Set("valueName21", jComboBoxDia.getSelectedItem().toString());
      Settings.Set("valueName22", jComboBoxHora.getSelectedItem().toString());
      Settings.Set("valueName23", jComboBox3.getSelectedItem().toString());
      Settings.Set("valueName24", jCheckBox2.isSelected());
      
      Settings.Set("valueName25", jTextFieldServi1.getText());
      Settings.Set("valueName26", jTextFieldPuerto1.getText());
      Settings.Set("valueName27", jTextFieldBaseDatos1.getText());
      
      Settings.Set("valueName28", jRadioButton4.isSelected());
      Settings.Set("valueName29", jRadioButton3.isSelected());
  
  
  
  
  }
 
  
  private void loadConfi(){
  
      jTextFieldServi.setText(Settings.Get("valueName01", "localhost"));
      jTextFieldPuerto.setText(Settings.Get("valueName02", "3306"));
      jTextFieldBaseDatos.setText(Settings.Get("valueName03", "dataciber"));
      jTextFieldUsuario.setText(Settings.Get("valueName04", "prueba"));
      jPasswordFiel2.setText(Settings.Get("valueName05", ""));
      
      
      jTextFieldSerCorreo.setText(Settings.Get("valueName06", "localhost"));
      jTextFieldPuertoCorreo.setText(Settings.Get("valueName07", "3306"));
      jTextFieldUsuarioCorreo.setText(Settings.Get("valueName08", "dataciber"));
      jTextFieldUsuario.setText(Settings.Get("valueName09", "prueba"));
      jPasswordFiel1.setText(Settings.Get("valueName10", ""));
      jTextAreaPara.setText(Settings.Get("valueName11", "prueba"));
      jTextFieldCC.setText(Settings.Get("valueName12", "prueba"));
      jTextFieldCO.setText(Settings.Get("valueName13", "prueba"));
      jTextFieldAsunto.setText(Settings.Get("valueName14", "prueba"));
      jTextAreaMensaje.setText(Settings.Get("valueName15", "prueba"));
      jTextFieldDirectorio.setText(Settings.Get("valueName16", "prueba"));
      
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

String strFecha = Settings.Get("valueName17", "01/01/2015");
String strFecha1 = Settings.Get("valueName18","31/12/2016");
Date fecha = null;
Date fecha1 = null;
try {

fecha = sdf.parse(strFecha);
fecha1 = sdf.parse(strFecha1);

} catch (ParseException ex) {

ex.printStackTrace();

}      
          
          jDateChooserNewStmFech.setDate(fecha);
          jDateChooserNewStmFech1.setDate(fecha1);
      
      jRadioButton1.setSelected(Settings.Get("valueName19", true));
      jRadioButton2.setSelected(Settings.Get("valueName20", true));
      jComboBoxDia.setSelectedItem(Settings.Get("valueName21", "prueba"));
      jComboBoxHora.setSelectedItem(Settings.Get("valueName22", "prueba"));
      jComboBox3.setSelectedItem(Settings.Get("valueName23", "prueba"));
      jCheckBox2.setSelected(Settings.Get("valueName24", true));
      
      
      jTextFieldServi1.setText(Settings.Get("valueName25", "prueba"));
      jTextFieldServi1.setText(Path.of("").toAbsolutePath().toString()+ "\\src\\resources\\dataBase.db");
      jTextFieldPuerto1.setText(Settings.Get("valueName26", "prueba"));
      jTextFieldBaseDatos1.setText(Settings.Get("valueName27", "prueba"));
      
      jRadioButton4.setSelected(Settings.Get("valueName28", true));
      jRadioButton3.setSelected(Settings.Get("valueName29", false));


     
  
  
  
  }
  

}
