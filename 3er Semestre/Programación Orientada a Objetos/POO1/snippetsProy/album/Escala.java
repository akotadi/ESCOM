import javax.swing.*;
import java.awt.*;
class Escala extends JPanel {
    ImageIcon src_icon = new ImageIcon ("conejo.jpg");
    ImageIcon dst_icon = ajustarImagen(src_icon);
    JLabel src_display = new JLabel (src_icon);
    JLabel dst_display = new JLabel (dst_icon);
    JScrollPane src_pane = new JScrollPane (src_display);
    JScrollPane dst_pane = new JScrollPane (dst_display);
    JSplitPane split_pane =
      new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,
                      true, src_pane, dst_pane);
private ImageIcon ajustarImagen(ImageIcon tmpIconAux)
{
return new ImageIcon(tmpIconAux.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
} 
public Escala(){
	add(split_pane);
}
public static void main(String[] args) {
        JFrame frame = new JFrame("Escala");
        frame.add(new Escala());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
