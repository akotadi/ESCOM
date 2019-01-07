import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class PalSliders extends JPanel {
        CambioInt actor;
	JSlider sliders[];
	int sel=0;

	public PalSliders(int n, LayoutManager lm, CambioInt ci){
                sliders=new JSlider[n];
		setLayout(lm);
                actor=ci;
		for(int i=0; i< sliders.length; i++){
			sliders[i]=new JSlider( SwingConstants.HORIZONTAL, 0, 180, 10 );
			sliders[i].setMajorTickSpacing( 10 );
       			sliders[i].setPaintTicks( true );
			add(sliders[i]);
			sliders[i].addChangeListener(new ManejaSliders());
		}
	}
	int getSeleccion(){
		return sel;
	}
	class ManejaSliders implements ChangeListener{
                public void stateChanged( ChangeEvent e ){
                        float sval;
                	JSlider js=(JSlider)e.getSource();
                        sval=(float)(js.getValue()*(Math.PI/180f));
                        for(int i=0; i< sliders.length; i++){
				if(sliders[i]==js){
					sel=i;
				}
			} 
                        actor.cambio(sel, sval);    
                }
	}
}
/*
			if(js=)
	                   body.gira((float)(slider.getValue()*convrad));
			if(js=)
	                   body.gira((float)(slider.getValue()*convrad));
*/
