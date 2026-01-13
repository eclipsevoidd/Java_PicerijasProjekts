package picerija;

import java.awt.Color;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PicerijasDarbinieks {
	
	static JFrame logs;
	static Random rand = new Random();

	public static void main(String[] args) {
		
		logs = new JFrame("Picērija");
		logs.setSize(800, 600);
		logs.setLocationRelativeTo(null);
		logs.setDefaultCloseOperation(3);
		
		JPanel bground = new JPanel();
		bground.setBackground(new Color(rand.nextInt(43)+3, rand.nextInt(43)+3, rand.nextInt(43)+3));
		bground.setLayout(new BoxLayout(bground, 1));
		
		logs.add(bground);
		
		logs.setVisible(true);
		
	}

}
