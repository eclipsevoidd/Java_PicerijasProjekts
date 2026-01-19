package picerija;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PicerijasDarbinieks {
	
	static JFrame logs;
	static Random rand = new Random();
	
	static Queue<Pasutijums> pasutijumi = new LinkedList<Pasutijums>();
	
	static int x1 = 0;
    static int x2;
    static int atrums = 2;
    static int ID = 0;
    static int izmers;
    
    static boolean piegade;
    
    static java.util.List<JCheckBox> piedevas = new ArrayList<>();
    
    static String picasVeids;
    static String garoza;
    static String dzeriens;
    static String picasPiedevas;
    static String uzkoda;
    static String vards;
    static String talrNr;
    static String adrese;
    
    static double cena;
    
    static JButton registretPoga;
    static JButton apskatitPoga;
    static JButton vesturePoga;
    static JButton exitPoga;
    
    static JPanel saturaPanel;
    
    static String[] picasPath = {"", "atteli/salami.png", "atteli/skinka.png", "atteli/vistas.png", "atteli/hamMushroom.png", "atteli/inarasIpasa.png"};
    static ImageIcon[] picasBildes; // bildes tiek ielādētas startupā
    
    static String[] dzerieniPath = {"", "atteli/pepsi.png", "atteli/coke.png", "atteli/fanta.png", "atteli/sprite.png"};
    static ImageIcon[] dzerieniBildes;
    
    static String[] uzkodasPath = {"", "atteli/fries.png", "atteli/onion.png", "atteli/nuggets.png"};
    static ImageIcon[] uzkodasBildes;
    
    static String[] izmeri = {"20cm ⌀", "30cm ⌀", "45cm ⌀"};
    static String[] piedevasVardi = {"Siers", "Tomāti", "Sēnes", "Gurķi", "Paprika"};
   
    static JScrollPane ritinamaZona;
    
    // sariktē bildes pēc izmēriem sākumā
    public static void loadImages() {
        picasBildes = new ImageIcon[picasPath.length];
        for (int i = 0; i < picasPath.length; i++) {
            ImageIcon icon = new ImageIcon(picasPath[i]);
            Image img = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            picasBildes[i] = new ImageIcon(img);
        }
        
        dzerieniBildes = new ImageIcon[dzerieniPath.length];
        for (int i = 0; i < dzerieniPath.length; i++) {
        	ImageIcon icon = new ImageIcon(dzerieniPath[i]);
        	Image img = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        	dzerieniBildes[i] = new ImageIcon(img);
        }
        
        uzkodasBildes = new ImageIcon[uzkodasPath.length];
        for (int i = 0; i < uzkodasPath.length; i++) {
        	ImageIcon icon = new ImageIcon(uzkodasPath[i]);
        	Image img = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        	uzkodasBildes[i] = new ImageIcon(img);
        }
    }
    
    public static void atjaunotPanelaIzmeru(JPanel panelis) {
        int maxBottom = 0;
        
        for (Component komp : panelis.getComponents()) {
            int elementaApaksa = komp.getY() + komp.getHeight();
            
            if (elementaApaksa > maxBottom) {
                maxBottom = elementaApaksa;
            }
        }
        panelis.setPreferredSize(new Dimension(panelis.getWidth(), maxBottom + 20));
        // atjauno scroll bar
        panelis.revalidate();
        panelis.repaint();
    }
    
    static void setElementi(String nosaukums) {
    	
    	// piekļust jpanel lodziņam, kurš saturēs elementus tajā
        saturaPanel = (JPanel) ritinamaZona.getViewport().getView();
        
        saturaPanel.removeAll();
        saturaPanel.revalidate();
        saturaPanel.repaint();
        
    	switch(nosaukums) {
    	case "registret":
    	    JLabel picasTitle = new JLabel("Izvēlies picu:");
    	    picasTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    picasTitle.setBounds(20, 20, 300, 50);
    	    saturaPanel.add(picasTitle);
    	    
    	    String[] picaVardi = {"Bez picas", "Salami – 5.99€", "Šķiņķa – 5.99€", "Vistas – 6.49€", "Ham & Mushroom – 6.99€", "Ināras Īpašā – 9.99€"};
    	    String[] dzerieniVardi = {"Bez dzēriena", "Pepsi 500ml – 1.49€", "Coca-Cola 500ml – 1.49€", "Fanta 500ml – 1.49€", "Sprite 500ml – 1.49€"};
    	    String[] uzkodasVardi = {"Bez uzkodām", "Frī kartupeļi – 1.99€", "Sīpolu gredzeni – 2.59€", "Vistas nageti 8gab. – 3.19€"};

    	    JComboBox<String> picasCombo = new JComboBox<>(picasPath);
    	    picasCombo.setBounds(20, 60, 300, 50);

    	    picasCombo.setRenderer(new DefaultListCellRenderer() {
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) { // šī koda daļa ir iegūta no interneta!
    	            
    	            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    	            
    	            String filePath = (String) value;
    	            int itemIndex = -1;
    	            for(int i=0; i<picasPath.length; i++) {
    	                if(picasPath[i].equals(filePath)) {
    	                    itemIndex = i;
    	                    break;
    	                }
    	            }
    	            
    	            if (itemIndex != -1) {
    	                label.setText(picaVardi[itemIndex]);
    	                label.setIcon(picasBildes[itemIndex]);
    	            }
    	            
    	            return label;
    	        }
    	    });
    	    saturaPanel.add(picasCombo);
    	    
    	    JLabel izmeriTitle = new JLabel("Izvēlies izmēru:");
    	    izmeriTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    izmeriTitle.setBounds(20, 120, 300, 30);
    	    saturaPanel.add(izmeriTitle);
    	    
    	    JComboBox<String> picasIzmeri = new JComboBox<>(izmeri);
    	    picasIzmeri.setBounds(20, 150, 300, 30);
    	    picasIzmeri.setEnabled(false);
    	    saturaPanel.add(picasIzmeri);
    	    
    	    JLabel garozaTitle = new JLabel("Izvēlies garozas veidu:");
    	    garozaTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    garozaTitle.setBounds(20, 200, 300, 30);
    	    saturaPanel.add(garozaTitle);
    	    
    	    ButtonGroup garoza = new ButtonGroup();
    	    
    	    JRadioButton garozaParasta = new JRadioButton("Parasta");
    	    garozaParasta.setEnabled(false);
    	    garozaParasta.setBounds(20, 220, 70, 50);
    	    
    	    JRadioButton garozaPilngraudu = new JRadioButton("Pilngraudu – 0.50€");
    	    garozaPilngraudu.setEnabled(false);
    	    garozaPilngraudu.setBounds(100, 220, 125, 50);
    	    
    	    garoza.add(garozaParasta);
    	    garoza.add(garozaPilngraudu);
    	    
    	    saturaPanel.add(garozaParasta);
    	    saturaPanel.add(garozaPilngraudu);
    	    
    	    JLabel piedevasTitle = new JLabel("Izvēlies picas piedevas:");
    	    piedevasTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    piedevasTitle.setBounds(20, 265, 300, 30);
    	    saturaPanel.add(piedevasTitle);
    	    
    	    JCheckBox siersPiedeva = new JCheckBox("Siers – 1€");
    	    siersPiedeva.setEnabled(false);
    	    siersPiedeva.setBounds(20, 285, 80, 50);
    	    
    	    JCheckBox tomatiPiedeva = new JCheckBox("Tomāti – 1€");
    	    tomatiPiedeva.setEnabled(false);
    	    tomatiPiedeva.setBounds(120, 285, 110, 50);
    	    
    	    JCheckBox senesPiedeva = new JCheckBox("Sēnes – 1€");
    	    senesPiedeva.setEnabled(false);
    	    senesPiedeva.setBounds(240, 285, 90, 50);
    	    
    	    JCheckBox gurkiPiedeva = new JCheckBox("Gurķi – 1€");
    	    gurkiPiedeva.setEnabled(false);
    	    gurkiPiedeva.setBounds(340, 285, 100, 50);
    	    
    	    JCheckBox paprikaPiedeva = new JCheckBox("Paprika – 1€");
    	    paprikaPiedeva.setEnabled(false);
    	    paprikaPiedeva.setBounds(460, 285, 120, 50);
    	    
    	    piedevas.add(siersPiedeva);
    	    piedevas.add(tomatiPiedeva);
    	    piedevas.add(senesPiedeva);
    	    piedevas.add(gurkiPiedeva);
    	    piedevas.add(paprikaPiedeva);
    	    
    	    saturaPanel.add(siersPiedeva);
    	    saturaPanel.add(tomatiPiedeva);
    	    saturaPanel.add(senesPiedeva);
    	    saturaPanel.add(gurkiPiedeva);
    	    saturaPanel.add(paprikaPiedeva);
    	    
    	    picasCombo.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            if (picasCombo.getSelectedIndex() > 0) {
    	                picasIzmeri.setEnabled(true);
    	                garozaParasta.setEnabled(true);
    	                garozaPilngraudu.setEnabled(true);
    	                
    	                siersPiedeva.setEnabled(true);
    	                tomatiPiedeva.setEnabled(true);
    	                senesPiedeva.setEnabled(true);
    	                gurkiPiedeva.setEnabled(true);
    	                paprikaPiedeva.setEnabled(true);
    	            } else {
    	                picasIzmeri.setEnabled(false);
    	                garozaParasta.setEnabled(false);
    	                garozaPilngraudu.setEnabled(false);
    	                
    	                siersPiedeva.setEnabled(false);
    	                tomatiPiedeva.setEnabled(false);
    	                senesPiedeva.setEnabled(false);
    	                gurkiPiedeva.setEnabled(false);
    	                paprikaPiedeva.setEnabled(false);
    	            }
    	        }
    	    });
    	    
    	    JLabel dzerieniTitle = new JLabel("Izvēlies dzērienu:");
    	    dzerieniTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    dzerieniTitle.setBounds(20, 330, 300, 30);
    	    saturaPanel.add(dzerieniTitle);
    	    
    	    JComboBox<String> dzerieniCombo = new JComboBox<>(dzerieniPath);
    	    dzerieniCombo.setBounds(20, 360, 300, 50);

    	    dzerieniCombo.setRenderer(new DefaultListCellRenderer() {
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) { // šī koda daļa ir iegūta no interneta!
    	            
    	            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    	            
    	            String filePath = (String) value;
    	            int itemIndex = -1;
    	            for(int i = 0; i < dzerieniPath.length; i++) {
    	                if(dzerieniPath[i].equals(filePath)) {
    	                    itemIndex = i;
    	                    break;
    	                }
    	            }
    	            
    	            if (itemIndex != -1) {
    	                label.setText(dzerieniVardi[itemIndex]);
    	                label.setIcon(dzerieniBildes[itemIndex]);
    	            }
    	            
    	            return label;
    	        }
    	    });
    	    saturaPanel.add(dzerieniCombo);
    	    
    	    JLabel uzkodasTitle = new JLabel("Izvēlies uzkodas:");
    	    uzkodasTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    uzkodasTitle.setBounds(20, 425, 300, 30);
    	    saturaPanel.add(uzkodasTitle);
    	    
    	    JComboBox<String> uzkodasCombo = new JComboBox<>(uzkodasPath);
    	    uzkodasCombo.setBounds(20, 455, 300, 50);

    	    uzkodasCombo.setRenderer(new DefaultListCellRenderer() {
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) { // šī koda daļa ir iegūta no interneta!
    	            
    	            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    	            
    	            String filePath = (String) value;
    	            int itemIndex = -1;
    	            for(int i = 0; i < uzkodasPath.length; i++) {
    	                if(uzkodasPath[i].equals(filePath)) {
    	                    itemIndex = i;
    	                    break;
    	                }
    	            }
    	            
    	            if (itemIndex != -1) {
    	                label.setText(uzkodasVardi[itemIndex]);
    	                label.setIcon(uzkodasBildes[itemIndex]);
    	            }
    	            
    	            return label;
    	        }
    	    });
    	    saturaPanel.add(uzkodasCombo);
    	    
    	    JLabel vardaTitle = new JLabel("Ievadi savu vārdu:");
    	    vardaTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    vardaTitle.setBounds(20, 510, 300, 30);
    	    saturaPanel.add(vardaTitle);
    	    
    	    JTextField vardaLodzins = new JTextField();
    	    vardaLodzins.setBounds(20, 545, 200, 30);
    	    saturaPanel.add(vardaLodzins);
    	    
    	    JCheckBox piegadeOption = new JCheckBox("Piegādāt uz mājām? (+ 5.99€)");
    	    piegadeOption.setBounds(20, 575, 135, 50);
    	    saturaPanel.add(piegadeOption);
    	    
    	    JLabel talrunisTitle = new JLabel("Ievadi savu tālruņa numuru:");
    	    talrunisTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    talrunisTitle.setBounds(20, 615, 300, 30);
    	    saturaPanel.add(talrunisTitle);
    	    
    	    JTextField talrunisLodzins = new JTextField();
    	    talrunisLodzins.setBounds(20, 650, 200, 30);
    	    talrunisLodzins.setEnabled(false);
    	    saturaPanel.add(talrunisLodzins);
    	    
    	    JLabel adreseTitle = new JLabel("Ievadi savu adresi:");
    	    adreseTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
    	    adreseTitle.setBounds(20, 685, 300, 30);
    	    saturaPanel.add(adreseTitle);
    	    
    	    JTextField adreseLodzins = new JTextField();
    	    adreseLodzins.setBounds(20, 720, 200, 30);
    	    adreseLodzins.setEnabled(false);
    	    saturaPanel.add(adreseLodzins);
    	    
    	    JButton noformetPasutijumu = new JButton("Noformēt pasūtījumu");
    	    noformetPasutijumu.setBounds(400, 800, 175, 40);
    	    saturaPanel.add(noformetPasutijumu);
    	    
    	    piegadeOption.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            if (piegadeOption.isSelected()) {
    	            	talrunisLodzins.setEnabled(true);
    	            	adreseLodzins.setEnabled(true);
    	            } else {
    	            	talrunisLodzins.setEnabled(false);
    	            	talrunisLodzins.setText("");
    	            	adreseLodzins.setEnabled(false);
    	            	adreseLodzins.setText("");
    	            }
    	        }
    	    });
    	    
    	    noformetPasutijumu.addActionListener(new ActionListener() { // "Noformēt Pasūtījumu" uzspiešana
    	    	@Override
    	        public void actionPerformed(ActionEvent e) {
    	    		ID++;
    	    		picasVeids = picasCombo.getSelectedItem().toString();
    	    		
    	    		switch(picasCombo.getSelectedIndex()) {
    	    		case 1:
    	    			cena += 5.99;
    	    			break;
    	    		case 2:
    	    			cena += 5.99;
    	    			break;
    	    		case 3:
    	    			cena += 6.49;
    	    			break;
    	    		case 4:
    	    			cena += 6.99;
    	    			break;
    	    		case 5:
    	    			cena += 9.99;
    	    			break;
    	    		}
    	    		
    	    		switch(picasIzmeri.getSelectedIndex()) {
    	    		case 0:
    	    			izmers = 20;
    	    			break;
    	    		case 1:
    	    			izmers = 30;
    	    			cena += 2;
    	    			break;
    	    		case 2:
    	    			izmers = 45;
    	    			cena += 5;
    	    			break;
					default:
						break;
    	    		}
    	    		
    	    		if (picasCombo.getSelectedIndex() != 0) {
    	    			if (garozaPilngraudu.isSelected()) {
    	    				PicerijasDarbinieks.garoza = "Pilngraudu";
    	    				cena += 0.50;
    	    			} else {
    	    				PicerijasDarbinieks.garoza = "Parastā";
    	    			}
    	    		}
    	    		
    	    		for (int i = 0; i < piedevas.size(); i++) {
    	    			if (piedevas.get(i).isSelected()) {
    	    				cena += 1.00;
    	    				picasPiedevas += piedevasVardi[i] + ", ";
    	    			}
    	    		}
    	    		
    	    		dzeriens = dzerieniCombo.getSelectedItem().toString();
    	    		if (dzerieniCombo.getSelectedIndex() != 0) {
    	    			cena += 1.49;
    	    		}
    	    		
    	    		uzkoda = uzkodasCombo.getSelectedItem().toString();
    	    		
    	    		switch(uzkodasCombo.getSelectedIndex()) {
    	    		case 1:
    	    			cena += 1.99;
    	    		case 2:
    	    			cena += 2.59;
    	    		case 3:
    	    			cena += 3.19;
    	    		}
    	    		
    	    		vards = vardaLodzins.getText();
    	    		
    	    		piegade = piegadeOption.isSelected();
    	    		if (piegadeOption.isSelected()) {
    	    			cena += 5.99;
    	    		}
    	    		
    	    		talrNr = talrunisLodzins.getText();
    	    		
    	    		adrese = adreseLodzins.getText();
    	    		
    	    		pasutijumi.add(new Pasutijums(ID, picasVeids, izmers, PicerijasDarbinieks.garoza, picasPiedevas, dzeriens, uzkoda, vards, piegade, talrNr, adrese, cena));
    	    		noformetPasutijumu.setEnabled(false);
    	    		apskatitPoga.setEnabled(false);
    	    		registretPoga.setEnabled(true);
    	    		setElementi("pasutijumi");
    	        }
    	    	
    	    });
    	    
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
		
		loadImages();
		
		logs = new JFrame("Picērija");
		logs.setSize(1024, 768);
		logs.setLocationRelativeTo(null);
		logs.setDefaultCloseOperation(3);
		logs.setResizable(false);
		logs.setIconImage(new ImageIcon("atteli/icon.png").getImage().getScaledInstance(-1, -1, Image.SCALE_SMOOTH));
		
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
				apskatitPoga.setEnabled(true);
				vesturePoga.setEnabled(true);
			}
        });
        
        apskatitPoga = new JButton("Pasūtījumi");
        apskatitPoga.setName("apskatit");
		apskatitPoga.setFocusPainted(false);
        apskatitPoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        apskatitPoga.setBounds(70, 255, 250, 50);
        
        apskatitPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setElementi("apskatit");
				registretPoga.setEnabled(true);
				apskatitPoga.setEnabled(false);
				vesturePoga.setEnabled(true);
			}
        });
        
        vesturePoga = new JButton("Pasūtījumu vēsture");
        vesturePoga.setName("vesture");
		vesturePoga.setFocusPainted(false);
        vesturePoga.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
        vesturePoga.setBounds(70, 310, 250, 50);
        
        vesturePoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setElementi("vesture");
				registretPoga.setEnabled(true);
				apskatitPoga.setEnabled(true);
				vesturePoga.setEnabled(false);
			}
        });
        
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