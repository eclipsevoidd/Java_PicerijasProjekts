package picerija;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    static JButton vesturePoga;
    static JButton exitPoga;
    
    static JPanel saturaPanel;
    
    static JScrollPane ritinamaZona;
    
    public static void atjaunotPanelaIzmeru(JPanel panelis) {
        int maxBottom = 0;
        
        for (Component comp : panelis.getComponents()) {
            int elementaApaksa = comp.getY() + comp.getHeight();
            
            if (elementaApaksa > maxBottom) {
                maxBottom = elementaApaksa;
            }
        }
        panelis.setPreferredSize(new Dimension(panelis.getWidth(), maxBottom + 20));
        
        // Tell the scroll pane to update its bars
        panelis.revalidate();
        panelis.repaint();
    }
    
    static void setElementi(String nosaukums) {
    	// piekļust jpanel lodziņam, kurš saturēs elementus tajā
        saturaPanel = (JPanel) ritinamaZona.getViewport().getView();
        
    	switch(nosaukums) {
    	case "registret":
    		for (int i = 0; i < 20; i++) {
    			JLabel pasutijumsTitle = new JLabel("pizza!");
                pasutijumsTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
                pasutijumsTitle.setBounds(20, (10 + 35) * i, 300, 30);
                saturaPanel.add(pasutijumsTitle);
                
                ImageIcon picaImg = new ImageIcon("atteli/pica.png");
                JLabel pica = new JLabel(picaImg);
                pica.setBounds(0, 42 * i, 32, 32);
                saturaPanel.add(pica);
                
    		}
    		
    		break;
    	case "apskatit":
    		
    		break;
    	default:
    		
    		break;
    	}
    	atjaunotPanelaIzmeru(saturaPanel);
    }
    
    public static JScrollPane izveidoRitinamoPaneli() {
        JPanel saturaPanelis = new JPanel();
        saturaPanelis.setLayout(null);
        saturaPanelis.setBackground(new Color(230, 230, 230));
        
        saturaPanelis.setPreferredSize(new Dimension(600, 0));
        
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
		registretPoga.setName("registret");
		registretPoga.setFocusPainted(false);
        registretPoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        registretPoga.setBounds(70, 200, 250, 50);
        
        registretPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setElementi("registret");
				registretPoga.setEnabled(false);
			}
        });
        
        apskatitPoga = new JButton("Pasūtījumi");
        apskatitPoga.setName("apskatit");
		apskatitPoga.setFocusPainted(false);
        apskatitPoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        apskatitPoga.setBounds(70, 255, 250, 50);
        
        vesturePoga = new JButton("Pasūtījumu vēsture");
        vesturePoga.setName("vesture");
		vesturePoga.setFocusPainted(false);
        vesturePoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        vesturePoga.setBounds(70, 310, 250, 50);
        
        exitPoga = new JButton("Iziet");
        exitPoga.setName("iziet");
		exitPoga.setFocusPainted(false);
        exitPoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        exitPoga.setBounds(63, 589, 265, 50);
        exitPoga.setBackground(new Color(255, 170, 180));
        
        exitPoga.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                exitPoga.setBackground(new Color(255, 125, 145));
            }

            public void mouseExited(MouseEvent evt) {
                exitPoga.setBackground(new Color(255, 170, 180));
            }
        });
        
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
        
        ritinamaZona = izveidoRitinamoPaneli();
        
        bground.add(ritinamaZona);
        
        bground.add(registretPoga);
        bground.add(apskatitPoga);
        bground.add(vesturePoga);
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
		
		Action aizvertArEsc = new AbstractAction() {
			private static final long serialVersionUID = -244035018929600442L;

			@Override
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		};

		logs.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		    .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "aizvertLogu");

		logs.getRootPane().getActionMap().put("aizvertLogu", aizvertArEsc);
		
	}

}
