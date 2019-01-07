import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;

public class Applet1 extends JApplet
{
 PrintStream os;
 DataInputStream is ; 
   
 Vector cdOrigen=new Vector();
 Vector cdDestino=new Vector();
 
   String Destino = "";
   String Origen="";
   
 Socket conexion ;
  
  
   String AOrigen[];
   String ADestino[];
   int estado;	 //creo una variable estado entera
   JLabel jLabel1 = new JLabel();
   JLabel jLabel2 = new JLabel();
   JComboBox jComboBox1 = new JComboBox();
   JComboBox jComboBox2 = new JComboBox();
   JButton jButton1 = new JButton();
 //  JTable  jTable2 = new JTable();
   JLabel  jLabel3 = new JLabel();
   JLabel  jLabel4 = new JLabel();
   JButton jButton2 = new JButton();
   JLabel  jLabel5 = new JLabel();
   JScrollPane jScrollPane1 = new JScrollPane();
   JScrollPane jScrollPane2 = new JScrollPane();
  
   DefaultTableModel modelo = new DefaultTableModel();
   JTable  jTable1 = new JTable(modelo);
  
   JTextArea jTextArea1 = new JTextArea();
   JScrollPane jScrollPane3 = new JScrollPane(jTextArea1);
   String cadena[]=new String[300];

  public void init()
  {
    try
    {
      conexion=new Socket("localhost", 7777);
      is = new DataInputStream(conexion.getInputStream());
      os = new PrintStream(conexion.getOutputStream());

        System.out.println("conectado");
        String unOrigen = is.readLine();
        while( unOrigen.equals("#") == false )
        {
          cdOrigen.add(unOrigen);
          System.out.println("## "+unOrigen);
          unOrigen = is.readLine();
        }

        String unDestino = is.readLine();
        while(unDestino.equals("*") == false)
        {
          cdDestino.add(unDestino);
          System.out.println("** "+unDestino);
          unDestino = is.readLine();
        }

    }catch(IOException  e){System.out.println("ERROR");}

     jComboBox1.addItem("[Selecciona Origen]");
     jComboBox2.addItem("[Selecciona Destino]");
     
     for(int i=0;i<cdOrigen.size();i++ )
       jComboBox1.addItem(cdOrigen.get(i) );
       
     for(int i=0;i<cdDestino.size();i++ )
       jComboBox2.addItem(cdDestino.get(i) );

       this.getContentPane().setLayout(null);
       jLabel1.setText("Origen");
       jLabel1.setBounds(new Rectangle(60, 75, 45, 25));
       jLabel2.setText("Destino");
       jLabel2.setBounds(new Rectangle(60, 110, 45, 25));
       jComboBox1.setBounds(new Rectangle(135, 75, 130, 20));
       jComboBox2.setBounds(new Rectangle(135, 110, 130, 20));
       jButton1.setText("Comprar");
       jButton1.setBounds(new Rectangle(385, 320, 100, 30));
     //  jLabel3.setText("Avion");
       jLabel3.setBounds(new Rectangle(55, 175, 55, 55));
       jLabel4.setText("RESULTADO");
     //  jLabel4.setBounds(new Rectangle(55, 250, 55, 30));
       jLabel4.setBounds(new Rectangle(250, 160, 200, 30));
       jButton2.setText("Buscar");
       jButton2.setBounds(new Rectangle(300, 80, 120, 45));
       jLabel5.setText("AGENCIA DE VIAJES CIC");
       jLabel5.setBounds(new Rectangle(100, 15, 360, 40));
       jLabel5.setFont(new Font("Tahoma", 1, 25));
      // jScrollPane1.setBounds(new Rectangle(130, 175, 345, 55));
       jScrollPane2.setBounds(new Rectangle(130, 190, 345, 100));
       jScrollPane3.setBounds(new Rectangle(50,315,325,55) );
       jTextArea1.setText("");
       jTextArea1.setEditable(false);
       jTextArea1.setBounds(new Rectangle(50, 315, 325, 55));
       jScrollPane3.setPreferredSize(new Dimension(500,500));
       jScrollPane3.setBounds(50 ,315 ,325 ,55);

       this.getContentPane().add(jTextArea1, null);
       jScrollPane2.getViewport().add(jTable1, null);
       this.getContentPane().add(jScrollPane2, null);
//       jScrollPane1.getViewport().add(jTable2, null);
       this.getContentPane().add(jScrollPane1, null);
       this.getContentPane().add(jLabel5, null);
       this.getContentPane().add(jButton2, null);
       this.getContentPane().add(jLabel4, null);
       this.getContentPane().add(jLabel3, null);
       this.getContentPane().add(jButton1, null);
       this.getContentPane().add(jComboBox2, null);
       this.getContentPane().add(jComboBox1, null);
       this.getContentPane().add(jLabel2, null);
       this.getContentPane().add(jLabel1, null);
       this.getContentPane().add(jScrollPane3);
       
       modelo.addColumn("Linea");
       modelo.addColumn("Costo");
       modelo.addColumn("Disponible");
       modelo.addColumn("Servicio");
                
       // BOTON BUSCAR
       jButton2.addActionListener(new ActionListener()
       {
            public void actionPerformed(ActionEvent e)
            {
                try        // NOS CONECTAMOS AL SERVIDOR
                {
                    Origen = jComboBox1.getSelectedItem().toString();
                    os.println( Origen );
                    os.flush();
                    
                    Destino = jComboBox2.getSelectedItem().toString();
                    os.println( Destino );
                    os.flush();
                    
                String tipo = is.readLine();         
                    while( tipo.equals("$") == false )
                    {
                    String datos[] = new String[4];
        
                        datos[3] = tipo;
                        datos[0] = is.readLine();
                        datos[1] = is.readLine();
                    String cupo = is.readLine();
                    String vendido = is.readLine();
                    int disponible =  ( Integer.parseInt(cupo) - Integer.parseInt(vendido) ); 
                        datos[2] =  disponible > 0 ? (""+disponible) : "AGOTADO"  ;
                        modelo.addRow(datos);             
                        tipo = is.readLine();
                    }    
                }
                catch(Exception ex)
                {
                    
                }
            }
         });
         // BOTON COMPRAR
         jButton1.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
                try        // NOS CONECTAMOS AL SERVIDOR
                {
                int seleccion = jTable1.getSelectedRow();
                    if( seleccion > -1 )
                    {
                        jTextArea1.append("COMPRA DE " 
                        + 
  (modelo.getValueAt(seleccion ,3).equals("a") ? "Avión" : "Camión") 
                        + " DE " + Origen + "\n A " + Destino + " POR " +    
   modelo.getValueAt(seleccion ,0) );
                        os.println( modelo.getValueAt(seleccion ,3) );
                        os.flush();
                        
                        os.println( modelo.getValueAt(seleccion ,0) );
                        os.flush();
                        
                    }
                 
                }
                catch(Exception ex)
                {
                }
            }
         });    
               /*
                int seleccion ;

                seleccion = jTable1.getSelectedRow();
                if( seleccion != -1 )
                {
                String seleccionado = new String();
                seleccionado = (String)modelo.getValueAt(seleccion ,0)
                + (String)modelo.getValueAt(seleccion ,1)
                + (String)modelo.getValueAt(seleccion ,2)
                + (String)modelo.getValueAt(seleccion ,3);
                jTextArea1.append(seleccionado);
                jTextArea1.setEnabled(true);
                }//fin del if
                System.out.println(seleccionado);
                }
                catch(IOException e2)
                { }
            }
        });
  */     
       
  }
  
  
}