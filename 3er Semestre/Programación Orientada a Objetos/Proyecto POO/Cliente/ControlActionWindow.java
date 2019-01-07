import java.awt.event.*;
import javax.swing.*;

public class ControlActionWindow implements ActionListener{

    GUIExam interfaz;
    int value = 0;

    public ControlActionWindow(GUIExam objeto){
        interfaz = objeto;
        value = interfaz.exam.ultimaPregunta;
    }
    
    public void actionPerformed(ActionEvent e) {

        //interfaz.lQuestion.setEditable(true);

        if (e.getSource() == interfaz.bInfo) {
            JOptionPane.showMessageDialog(null,"Aplicador de Exámenes, hecho por: \n\n\t- Álvarez Barajas Enrique\n\t"
                +"- Calva Hernández José Manuel\n\t- Rojas Espinoza Omar\n\t- Ruiz López Luis Carlos");
        }
        else if (e.getSource() == interfaz.bSend && interfaz.exam.ultimaPregunta < 10) {
            if (interfaz.rbA.isSelected()) {
                interfaz.exam.vAnswers.addElement('A');
            }
            else if (interfaz.rbB.isSelected()) {
                interfaz.exam.vAnswers.addElement('B');
            }
            else if (interfaz.rbC.isSelected()) {
                interfaz.exam.vAnswers.addElement('C');
            }
            else if (interfaz.rbD.isSelected()) {
                interfaz.exam.vAnswers.addElement('D');
            }
            else{
                interfaz.exam.vAnswers.addElement('E');
            }
            ++interfaz.exam.ultimaPregunta;
            if (interfaz.exam.ultimaPregunta == 10) {
                interfaz.rbA.setEnabled(false);interfaz.rbA.setText("");
                interfaz.rbB.setEnabled(false);interfaz.rbB.setText("");
                interfaz.rbC.setEnabled(false);interfaz.rbC.setText("");
                interfaz.rbD.setEnabled(false);interfaz.rbD.setText("");
                interfaz.lQuestion1.setText("");
                interfaz.lQuestion2.setText("");
                interfaz.bSend.setEnabled(false);
                interfaz.lError.setText("Has terminado el examen, puedes cerrar la ventana.");
                
            }
            else{
                String s, s1, s2;
                s = interfaz.exam.Questions.elementAt(interfaz.exam.ultimaPregunta).getPregunta();
                System.out.println(s.length());
                if (s.length()>70) {
                    s1 = s.substring(0,70);
                    s2 = s.substring(70,s.length());
                    interfaz.lQuestion1.setText(s1);
                    interfaz.lQuestion2.setText(s2);
                    //lQuestion = new JLabel(s1+"\n"+s2);
                }
                else{
                    interfaz.lQuestion1.setText(s);
                    interfaz.lQuestion2.setText("");
                }
                interfaz.gButtons.clearSelection();
                //interfaz.lQuestion.setText(interfaz.exam.Questions.elementAt(interfaz.exam.ultimaPregunta).getPregunta());
                interfaz.lOpcionA.setText(interfaz.exam.Questions.elementAt(interfaz.exam.ultimaPregunta).getOpcionA());
                interfaz.lOpcionB.setText(interfaz.exam.Questions.elementAt(interfaz.exam.ultimaPregunta).getOpcionB());
                interfaz.lOpcionC.setText(interfaz.exam.Questions.elementAt(interfaz.exam.ultimaPregunta).getOpcionC());
                interfaz.lOpcionD.setText(interfaz.exam.Questions.elementAt(interfaz.exam.ultimaPregunta).getOpcionD());
            }
        }
        else if(e.getSource() == interfaz.  bClose){
            int x = JOptionPane.showConfirmDialog(null,"¿Estás seguro?\nTu progreso será guardado.","Ventana de Confirmación",JOptionPane.YES_NO_OPTION);
            if (x==0) {
                for (int i = value; i < interfaz.exam.vAnswers.size() ; ++i ) {
                    System.out.println (interfaz.exam.vAnswers.elementAt(i).toString().charAt(0)+"\n");
                    System.out.println(interfaz.exam.Questions.elementAt(i).getRespuesta()+"\n");
                    if (interfaz.exam.vAnswers.elementAt(i).toString().charAt(0) == interfaz.exam.Questions.elementAt(i).getRespuesta()) {
                        interfaz.exam.calificacion++;
                    }
                }
                JOptionPane.showMessageDialog(null,"Your Score is: "+interfaz.exam.calificacion);
                if (interfaz.newExam) {
                    interfaz.connect.nuevoEU(interfaz.idUser,interfaz.exam);
                }
                else{
                    interfaz.connect.updateEU(interfaz.idUser,interfaz.exam);
                }
                interfaz.connect.updateRespuesta(interfaz.idUser,interfaz.exam);
                System.out.println(interfaz.exam.ultimaPregunta+", "+interfaz.exam.calificacion+", "+interfaz.exam.vAnswers.size()+", "+value);
                GUIChoose choose = new GUIChoose(interfaz.idUser, interfaz.connect);
                interfaz.dispose();
                choose.setVisible(true);
            }
        }

        //interfaz.lQuestion.setEditable(false);
    }
}