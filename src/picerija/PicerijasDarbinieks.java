package picerija;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
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
    
    static JButton registretPoga;
    static JButton apskatitPoga;
    static JButton exitPoga;
    
    public static JScrollPane izveidoRitinamoPaneli() {
        JPanel saturaPanelis = new JPanel();
        saturaPanelis.setLayout(null);
        saturaPanelis.setBackground(new Color(240, 240, 240));
        
        saturaPanelis.setPreferredSize(new java.awt.Dimension(600, 1000));
        
        JScrollPane scrollPane = new JScrollPane(saturaPanelis);
        scrollPane.setBounds(330, 190, 620, 450);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); // tīšanas intervāls
        
        return scrollPane;
    }
    
	public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {	
		
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		
		logs = new JFrame("Picērija");
		logs.setSize(1024, 768);
		logs.setLocationRelativeTo(null);
		logs.setDefaultCloseOperation(3);
		logs.setResizable(false);
		
		ImageIcon bg = new ImageIcon("atteli/bg.png");
		int bildeWidth = bg.getIconWidth();
		x2 = bildeWidth; // nākamās bildes x vērtība
		
		JPanel bground = new JPanel();
        bground.setLayout(null);
        bground.setBackground(new Color(0, 0, 0));
		
        JLabel teksts = new JLabel("Picērija");
		teksts.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 100));
		teksts.setForeground(Color.BLACK);
		teksts.setBounds(0, 0, 1024, 170);
		teksts.setHorizontalAlignment(SwingConstants.CENTER);
		
		registretPoga = new JButton("Jauns pasūtījums");
		registretPoga.setFocusPainted(false);
        registretPoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        registretPoga.setBounds(70, 200, 250, 50);
        
        apskatitPoga = new JButton("Pasūtījumi");
		apskatitPoga.setFocusPainted(false);
        apskatitPoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        apskatitPoga.setBounds(70, 255, 250, 50);
        
        exitPoga = new JButton("Iziet");
		exitPoga.setFocusPainted(false);
        exitPoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        exitPoga.setBounds(63, 590, 265, 50);
        
        exitPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        });
        
		JLabel bgBilde1 = new JLabel(bg);
        bgBilde1.setBounds(x1, 0, bildeWidth, 768);
        
        JLabel bgBilde2 = new JLabel(bg);
        bgBilde2.setBounds(x2, 0, bildeWidth, 768);
        
        JButton bgBlock = new JButton();
        bgBlock.setBounds(43, 27, 920, 670);
        bgBlock.setEnabled(false);
        
        JButton bgBlock2 = new JButton();
        bgBlock2.setBounds(63, 190, 265, 400);
        bgBlock2.setEnabled(false);
        
        JScrollPane ritinamaZona = izveidoRitinamoPaneli();
        
        // piekļust jpanel lodziņam, kurš saturēs elementus tajā
        JPanel contentPanel = (JPanel) ritinamaZona.getViewport().getView();
        
        for (int i = 1; i <= 10; i++) {
            JLabel orderLabel = new JLabel("Pasūtījums #" + i);
            orderLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
            orderLabel.setBounds(20, (i-1) * 80, 300, 30);
            contentPanel.add(orderLabel);
            
            JLabel detailsLabel = new JLabel("Detaļas par pasūtījumu...");
            detailsLabel.setBounds(20, (i-1) * 80 + 35, 300, 20);
            contentPanel.add(detailsLabel);
        }
        
        bground.add(ritinamaZona);
        
        bground.add(registretPoga);
        bground.add(apskatitPoga);
        bground.add(exitPoga);
        bground.add(bgBlock2);
        bground.add(teksts);
        bground.add(bgBlock);
        bground.add(bgBilde1);
        bground.add(bgBilde2);
        
        
        
        logs.add(bground);
        
        //TAIMERA KODS
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
		
		Action closeAction = new AbstractAction() {
			private static final long serialVersionUID = -244035018929600442L;

			@Override
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		};

		logs.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		    .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "aizvertLogu");

		logs.getRootPane().getActionMap().put("aizvertLogu", closeAction);
		
		
		
	}

}
