package picerija;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PicerijasDarbinieks {
	
	static JFrame logs;
	static Random rand = new Random();
	
	static int x1 = 0;
    static int x2;
    static int atrums = 2;

	public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		
		logs = new JFrame("Picērija");
		logs.setSize(1024, 768);
		logs.setLocationRelativeTo(null);
		logs.setDefaultCloseOperation(3);
		logs.setResizable(false );
		
		ImageIcon bg = new ImageIcon("atteli/bg.png");
		int bildeWidth = bg.getIconWidth();
		x2 = bildeWidth; // nākamās bildes x vērtība
		
		JPanel bground = new JPanel();
        bground.setLayout(null); 
        bground.setBackground(new Color(0, 0, 0));
		
		JLabel teksts = new JLabel("Lāča Picērija");
		teksts.setFont(new Font("Franklin Gothic Demi Italic", Font.BOLD, 100));
		teksts.setForeground(Color.BLACK);
		teksts.setBounds(0, 0, 1024, 120); 
		teksts.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel bgBilde1 = new JLabel(bg);
        bgBilde1.setBounds(x1, 0, bildeWidth, 768);
        
        JLabel bgBilde2 = new JLabel(bg);
        bgBilde2.setBounds(x2, 0, bildeWidth, 768);
        
        bground.add(teksts); 
        bground.add(bgBilde1);
        bground.add(bgBilde2);

        logs.add(bground);
        
        /* 
         * 	TAIMERA KODS
         */
        
        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x1 -= atrums;
                x2 -= atrums;

                if (x1 <= -bildeWidth) {
                    x1 = x2 + bildeWidth;
                }
                
                if (x2 <= -bildeWidth) {
                    x2 = x1 + bildeWidth;
                }

                bgBilde1.setLocation(x1, 0);
                bgBilde2.setLocation(x2, 0);
            }
        });
		
		
		//logs.setUndecorated(true);
		logs.setVisible(true);
		timer.start();
		
	}

}
