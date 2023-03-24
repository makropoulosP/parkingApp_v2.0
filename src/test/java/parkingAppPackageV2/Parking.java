package parkingAppPackageV2;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Parking implements ActionListener, MouseListener, FocusListener, ChangeListener{
	
	JLabel label =  new JLabel();
	JLabel registerLabel =  new JLabel();
	JLabel removeLabel = new JLabel();
	JSlider slider = new JSlider(1,24,1);
	JLabel labelSliderParkedHours = new JLabel();
	
	ButtonGroup G1 = new ButtonGroup();
	JRadioButton rdBtnCar = new JRadioButton();
	JRadioButton rdBtnMoto = new JRadioButton();
	
	ButtonGroup G2 = new ButtonGroup();
	JRadioButton rdBtnSlot = new JRadioButton();
	JRadioButton rdBtnPlates = new JRadioButton();
	
	JTextField fieldRegisterPlates = new JTextField();
	JTextField fieldRegisterSlot = new JTextField();
	JTextField fieldRemoveVehicle = new JTextField();

	JFrame frame = new JFrame(); 	 	
	
	JPanel homePanel = new JPanel();
	JPanel graphicPanel = new JPanel();	

	JButton btnRegisterVehicle = new JButton();	
	JButton btnRemoveVehicle = new JButton();
	JButton btnViewInfoByPlate = new JButton();
	
	Border border = BorderFactory.createLineBorder(Color.darkGray,1);

	String carType = "0";
	String removeBy = "0";
	ArrayList <Vehicles> ParkingList = new ArrayList<>();

	Parking(){
	
		try{
		    FileInputStream readData = new FileInputStream("parkingListData.ser");
		    ObjectInputStream readStream = new ObjectInputStream(readData);

		    @SuppressWarnings("unchecked")
			ArrayList<Vehicles> Vehicles = (ArrayList<Vehicles>) readStream.readObject();
		    readStream.close();
		    ParkingList.addAll(Vehicles);
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
		createFrame();
		createHomePanel();
		createGraphicPanel();
		}
	
	void createFrame(){
		
		frame.setTitle("Parking Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(950,600);	
		frame.getContentPane().setBackground(new Color(93, 34, 114));		
		ImageIcon img = new ImageIcon("/parkingApp_v2.0/src/test/java/parkingAppPackageV2/logo.jpg");
		frame.setIconImage(img.getImage());		
		frame.setLayout(null);	
				
		label.setText("Parking Application");
		label.setForeground(Color.white);		
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);		
        label.setBounds(100,10,500,200);
 
        frame.add(label);
        frame.setVisible(true);
        
	}
	void createHomePanel() {
		
		homePanel.setBackground(new Color(53, 34, 114));
		homePanel.setSize(200,600);
		homePanel.setLocation(0,0);   
	    homePanel.setLayout(null);
	     
	    registerLabel.setText("Register a vehicle");
	    registerLabel.setForeground(Color.white);		
	    registerLabel.setVerticalAlignment(JLabel.TOP);
	    registerLabel.setHorizontalAlignment(JLabel.CENTER);		
	    registerLabel.setBounds(0,20,200,30);
	    homePanel.add(registerLabel);
	    
	    fieldRegisterPlates.setText("Enter plates");
	    fieldRegisterPlates.setBounds(10,60,180,25);
	    fieldRegisterPlates.addFocusListener(this);
	    homePanel.add(fieldRegisterPlates);
	    
	    fieldRegisterSlot.setText("Enter slot");
	    fieldRegisterSlot.setBounds(10,100,180,25);
	    fieldRegisterSlot.addFocusListener(this);
	    homePanel.add(fieldRegisterSlot);
	    
	    rdBtnCar.setText("Car");
	    rdBtnCar.setBounds(40,150,60,25);
	    rdBtnCar.addActionListener(this);
	    homePanel.add(rdBtnCar);
	    
	    rdBtnMoto.setText("Moto");
	    rdBtnMoto.setBounds(100,150,60,25);
	    rdBtnMoto.addActionListener(this);
	    homePanel.add(rdBtnMoto);
	    
	    G1.add(rdBtnCar);
	    G1.add(rdBtnMoto);
	    
	    btnRegisterVehicle.setText("Register");
	    btnRegisterVehicle.setBounds(50,200,100,30);	   
	    btnRegisterVehicle.addActionListener(this);
	    homePanel.add(btnRegisterVehicle);
	  
	    removeLabel.setText("Remove a vehicle");
	    removeLabel.setForeground(Color.white);		
	    removeLabel.setVerticalAlignment(JLabel.TOP);
	    removeLabel.setHorizontalAlignment(JLabel.CENTER);		
	    removeLabel.setBounds(0,250,200,30);
	    homePanel.add(removeLabel);
	    
	    fieldRemoveVehicle.setText("Enter plates or slot to remove");
	    fieldRemoveVehicle.setBounds(10,280,180,25);
	    fieldRemoveVehicle.addFocusListener(this);
	    homePanel.add(fieldRemoveVehicle);
	  
	    rdBtnSlot.setText("Remove by slot");
	    rdBtnSlot.setBounds(40,330,130,25);
	    rdBtnSlot.addActionListener(this);
	    homePanel.add(rdBtnSlot);
	    
	    rdBtnPlates.setText("Remove by plates");
	    rdBtnPlates.setBounds(40,360,130,25);
	    rdBtnPlates.addActionListener(this);
	    homePanel.add(rdBtnPlates);
	    
	    G2.add(rdBtnPlates);
	    G2.add(rdBtnSlot);
	    
	    labelSliderParkedHours.setText("Parked time: "+slider.getValue()+" hours");
	    labelSliderParkedHours.setBounds(50,400,150,30);
	    labelSliderParkedHours.setForeground(Color.white);
	    homePanel.add(labelSliderParkedHours);
	    
	    slider.setBounds(25,430,150,50);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(4);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		homePanel.add(slider);
	
	    btnRemoveVehicle.setText("Remove");
	    btnRemoveVehicle.setBounds(50,500,100,30);	   
	    btnRemoveVehicle.addActionListener(this);
	    homePanel.add(btnRemoveVehicle);  
	  
	    frame.getContentPane().add(homePanel);
	    		
	}
	void createGraphicPanel() {
		
		graphicPanel.setVisible(true);
		graphicPanel.setBackground(new Color(66, 75, 79));
		graphicPanel.setSize(750,550);
		graphicPanel.setLocation(200,50);
		graphicPanel.setLayout(null);
		
		///na valw scroll
		
		graphicalUpdate();

		frame.getContentPane().add(graphicPanel);
		
		frame.repaint();
	}

	void addVehicle(String plate, int slot, String carType) {				
		//check an oi pinakides uparxoun hdh, na mhn ginei 2h kataxwrisi tou idiou amaksiou
		if(carType.equals("0")) {
			JOptionPane.showMessageDialog(null, "Select a car or a moto to add", "Error", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		if(checkAvailablePlate(plate)) { //afth den doulevei kala
			JOptionPane.showMessageDialog(null, "Vehicle already parked", "Error", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		else if(!(carType.equals("0"))){
			if(slot<=0 || slot >50) {
				JOptionPane.showMessageDialog(null, "Enter a number between 1-50","Invalid Input", JOptionPane.PLAIN_MESSAGE);
			}
			if(checkAvailableSlot(slot) && slot>0 && slot <=50) {
				int chargeRate = 2; //by default charge rate is 2 euros per hour
				Vehicles v1 = new Vehicles (plate,slot,carType,chargeRate);
				if(carType.equals("car")) {
					chargeRate = 5; //if vehicle is a car charge rate is 5 euros per hour
				}
				v1.setChargeRate(chargeRate);
				ParkingList.add(v1);
				try{
				    FileOutputStream writeData = new FileOutputStream("parkingListData.ser");
				    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
				    writeStream.writeObject(ParkingList);
				    writeStream.flush();
				    writeStream.close();

				}catch (IOException e) {
				    e.printStackTrace();
				}
				graphicalUpdate();
			}
		}
		else {  
			JOptionPane.showMessageDialog(null, "Found another vehicle at that spot", carType, JOptionPane.PLAIN_MESSAGE);
		}
	}
	void removeVehicle(String plateOrSlot, String removeBy ) {					
		graphicalUpdate();
		if(ParkingList.size()==0) {
			JOptionPane.showMessageDialog(null, "Parking lot is empty", "Error", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		if(removeBy.equals("0")) {
			JOptionPane.showMessageDialog(null, "Select remove by slot or remove by plates", "Error", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		else if(!(removeBy.equals("0"))){
			//edw 8elei prosoxi gt analoga posa amaksia exw mesa mexri na to vei petaei joptionpane
			for (int i = 0; i < ParkingList.size();i++) 
		      {
				if (removeBy.equals("plates") && ParkingList.get(i).getPlate().equals(plateOrSlot)) {
					JOptionPane.showMessageDialog(null," You have to pay " + ParkingList.get(i).getChargeRate()*slider.getValue() + " euros", "Error", JOptionPane.PLAIN_MESSAGE); 
					ParkingList.remove(i);
				
					try{
					    FileOutputStream writeData = new FileOutputStream("parkingListData.ser");
					    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
					    writeStream.writeObject(ParkingList);
					    writeStream.flush();
					    writeStream.close();

					}catch (IOException e) {
					    e.printStackTrace();
					}
				}
				else if(removeBy.equals("slot") && Integer.parseInt(plateOrSlot) == ParkingList.get(i).slot) {
					JOptionPane.showMessageDialog(null," You have to pay " + ParkingList.get(i).getChargeRate()*slider.getValue() + " euros", "Error", JOptionPane.PLAIN_MESSAGE); 
					ParkingList.remove(i);
	
					try{
					    FileOutputStream writeData = new FileOutputStream("parkingListData.ser");
					    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
					    writeStream.writeObject(ParkingList);
					    writeStream.flush();
					    writeStream.close();

					}catch (IOException e) {
					    e.printStackTrace();
					}
				}
		      }
			graphicalUpdate();
		}
		else {
			JOptionPane.showMessageDialog(null,  "Vehicle was not found", "Error", JOptionPane.PLAIN_MESSAGE);
		}
	}
				
	boolean checkAvailableSlot(int slot) {
		for (Vehicles n1 : ParkingList) {
	        if (n1.getSlot() == slot) {
	        	 return false; // Parking slot is occupied
	        }
	    }
		return true;		
	}
	boolean checkAvailablePlate(String plates) {
		if (ParkingList.isEmpty()) {
			 return false;
		}
		for (Vehicles m1 : ParkingList) {
	        if (m1.getPlate() == plates) {
	        	 return true; // Parking plates are occupied
	        }
	    }
		return false;
	}
	void graphicalUpdate() {
		graphicPanel.removeAll();
		graphicPanel.repaint();
		
		for(int i=0; i<ParkingList.size();i++) {
			
			JLabel labelVehicles = new JLabel(); //exei to background kai tis pinakides
			JLabel labelIcon = new JLabel(); //exei to  icon
			
			int offset = 15; 
			int offsety = 5; 
			int weight = 70; //fix
			int height = 100; 
			int howManyInRow = 10; 
			int row = (ParkingList.get(i).getSlot()-1)%howManyInRow;
			int collumn =(ParkingList.get(i).getSlot()-1)/howManyInRow;
			
			labelVehicles.setText(ParkingList.get(i).getPlate());
			labelVehicles.setBounds(weight*row+offset,height*collumn+offsety,weight,height); 			if(ParkingList.get(i).getCarType().equals("moto")) {
			labelVehicles.setBackground(new Color(65, 82, 91));
			
			ImageIcon imgThisImg = new ImageIcon("/parkingApp_v2.0/src/test/java/parkingAppPackageV2/moto.png");
			labelIcon.setIcon(imgThisImg);
			}
			if(ParkingList.get(i).getCarType().equals("car")) {
				labelVehicles.setBackground(new Color(86, 108, 121));
				ImageIcon imgThisImg = new ImageIcon("/parkingApp_v2.0/src/test/java/parkingAppPackageV2/car.png");
				labelIcon.setIcon(imgThisImg);
			}
			labelIcon.setBounds(15,25,50,50); //fix pou kanei spawn to icon
			labelIcon.setHorizontalAlignment(JLabel.CENTER);
			labelIcon.setVerticalAlignment(JLabel.TOP);
			labelVehicles.add(labelIcon); 
			
			labelVehicles.setHorizontalAlignment(JLabel.CENTER);
			labelVehicles.setVerticalAlignment(JLabel.BOTTOM);
			labelVehicles.setForeground(Color.white);
			//labelVehicles.setOpaque(true);
			graphicPanel.add(labelVehicles);
			
		}
		for (int i=1 ;i <= 50 ; i++) {
			int offset =15;
			JLabel slotLabel = new JLabel();
			slotLabel.setText(Integer.toString(i));
			slotLabel.setBounds(((i-1)%10)*70+ offset,((i-1)/10)*100+ offset-10,70,100);
			slotLabel.setForeground(Color.white);		
			slotLabel.setVerticalAlignment(JLabel.TOP);
			slotLabel.setHorizontalAlignment(JLabel.CENTER);
			slotLabel.setBackground(new Color(34, 56, 114));
			slotLabel.setOpaque(true); 
			slotLabel.setBorder(border);
			graphicPanel.add(slotLabel);		
		}
		graphicPanel.repaint(); 
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRegisterVehicle) {
			addVehicle(fieldRegisterPlates.getText(),Integer.parseInt(fieldRegisterSlot.getText()),carType); //pws na mhn ta vazw edw
		}
		if(e.getSource()==btnRemoveVehicle) {
			removeVehicle(fieldRemoveVehicle.getText(),removeBy);
		}
		if(e.getSource()==btnViewInfoByPlate) {
			//viewInfoByPlate(field9.getText());
		}
		if (e.getSource()==rdBtnCar) {
			carType = "car";
		}
		if (e.getSource()==rdBtnMoto) {
			carType = "moto";
		}
		if (e.getSource()==rdBtnSlot) {			
			removeBy = "slot";
		}			
		if (e.getSource()==rdBtnPlates) {
			removeBy = "plates";
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		if(e.getSource()== slider) {
			labelSliderParkedHours.setText("Parked time: "+slider.getValue()+ " hours");
		}
		
	}
	
	 public void focusGained(FocusEvent e) {
		 if (e.getSource()==fieldRegisterPlates) {
			 if(fieldRegisterPlates.getText().equals("Enter plates")) {
				 fieldRegisterPlates.setText(""); 
			 }
         }  
		 if (e.getSource()==fieldRegisterSlot) {
			 if(fieldRegisterSlot.getText().equals("Enter slot")) {
				 fieldRegisterSlot.setText(""); 
			 }
         }
		 if (e.getSource()==fieldRemoveVehicle) {
			 if(fieldRemoveVehicle.getText().equals("Enter plates or slot to remove")) {
				 fieldRemoveVehicle.setText(""); 
			 }
         }
		
	   }

	@Override
	public void focusLost(FocusEvent e) {
		 if (e.getSource()==fieldRegisterPlates) {
				 if(fieldRegisterPlates.getText().equals("")) {
					 fieldRegisterPlates.setText("Enter plates");
				 }
         }  
		 if (e.getSource()==fieldRegisterSlot) {
			 if(fieldRegisterSlot.getText().equals("")) {
				 fieldRegisterSlot.setText("Enter slot");
			 }
         }
		 if (e.getSource()==fieldRemoveVehicle) {
			 if(fieldRemoveVehicle.getText().equals("")) {
				 fieldRemoveVehicle.setText("Enter plates or slot to remove");
			 }
         }
	}
}