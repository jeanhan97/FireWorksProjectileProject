/* Name: Ji Eun Han       
 * Assignment Number: Project 03
 * Section: Mon, Wed 12:30 
 * Lab TA: Jack, Sofia, Xena
 * I collaborated with Mackenzie Lee. 
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;




public class FireworksApp extends JFrame implements ActionListener, ChangeListener{

	protected JLabel promptuserangle, promptuserspeed, promptusercolor, prompttimedelay, promptuserboom;
	protected JTextField userangle, userspeed, usercolor, timedelay;
	protected JButton launchbutton; 
	protected JSlider angleslider, speedslider, delayslider;
	protected double xboom, yboom; //x,y coordinate of fireworks explosion
	protected int width, height;
	protected JRadioButton red, green, blue, cyan, black, magenta, pink, darkgrey, lightgrey; 
	protected JRadioButton rippleboom, squareboom, sunlightboom, flowerboom, bubbleboom;
	protected ButtonGroup buttongroup;
	protected JSlider startslider;
	protected JLabel launchwhere;
	
	protected Timer timer;
	protected JLabel countdown;
	
	protected Color fireworkcolor;
	protected int speed, counter, time;
	protected double gravity, angle; 

	
	public FireworksApp(){
		setTitle("FireworksApp");
		setSize(500,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout (new FlowLayout());
		
		
		//Launch Angle user prompt display
		promptuserangle = new JLabel("Choose your launch angle: ");
		add(promptuserangle);
		angleslider = new JSlider(JSlider.HORIZONTAL, 0,90,0);
		angleslider.addChangeListener(this);
		add(angleslider);
		userangle = new JTextField (3);
		add(userangle);
		
		//Speed user prompt display 
		promptuserspeed = new JLabel("Enter your launch speed: ");
		add(promptuserspeed);
		speedslider = new JSlider(JSlider.HORIZONTAL, 0,90,0);
		speedslider.addChangeListener(this);
		add(speedslider);
		userspeed = new JTextField (3);
		add(userspeed);
		
		//How long it takes until explosion after launch prompt display 
		prompttimedelay = new JLabel("Enter the time delay: ");
		add(prompttimedelay);
		delayslider = new JSlider(JSlider.HORIZONTAL, 0,10,0);
		delayslider.addChangeListener(this);
		add(delayslider);
		timedelay = new JTextField (3);
		add(timedelay);
		
		
		//Fireworks Color,  user prompt display
		promptusercolor = new JLabel("Pick your desired color for fireworks: ");
		add(promptusercolor);
		
		red = new JRadioButton("Red");
		red.addActionListener(this);
		add(red);
		
		green = new JRadioButton("Green");
		green.addActionListener(this);
		add(green);
		
		blue = new JRadioButton("Blue");
		blue.addActionListener(this);
		add(blue);
		
		cyan = new JRadioButton("Cyan");
		cyan.addActionListener(this);
		add(cyan);
		
		black = new JRadioButton("Black");
		black.addActionListener(this);
		add(black);
		
		magenta = new JRadioButton("Magenta");
		magenta.addActionListener(this);
		add(magenta);
		
		pink = new JRadioButton("Pink");
		pink.addActionListener(this);
		add(pink);
		
		darkgrey = new JRadioButton("Dark grey");
		darkgrey.addActionListener(this);
		add(darkgrey);
		
		lightgrey = new JRadioButton("Light Grey");
		lightgrey.addActionListener(this);
		add(lightgrey);
		
		//Only one color can be picked
		buttongroup = new ButtonGroup();
		buttongroup.add(red);
		buttongroup.add(green);
		buttongroup.add(blue);
		buttongroup.add(cyan);
		buttongroup.add(black);
		buttongroup.add(magenta);
		buttongroup.add(pink);
		buttongroup.add(darkgrey);
		buttongroup.add(lightgrey);
		
		
		//Fireworks type of explosion, display user prompt 
		promptuserboom = new JLabel("Choose your desired type of firework explosion: ");
		add(promptuserboom);
	
		rippleboom = new JRadioButton("Ripple Explosion");
		add(rippleboom);
		
		squareboom = new JRadioButton("Square Explosion");
		add(squareboom);
		
		sunlightboom = new JRadioButton("Sunlight Explosion");
		add(sunlightboom);
		
		flowerboom = new JRadioButton("Spring Flower Explosion");
		add(flowerboom);
		
		bubbleboom = new JRadioButton("Bubble Explosion");
		add(bubbleboom);
		
		//Only one explosion type can be picked
		buttongroup = new ButtonGroup();
		buttongroup.add(rippleboom);
		buttongroup.add(squareboom);
		buttongroup.add(sunlightboom);
		buttongroup.add(flowerboom);
		buttongroup.add(bubbleboom);
		
		//Extra credit problem: set launch position
		launchwhere = new JLabel ("Choose your launch position");
		add(launchwhere);
			
		startslider = new JSlider(1,3,2);
		startslider.setPaintLabels(true);
		Hashtable<Integer,JLabel > table = new Hashtable<Integer,JLabel>();
		table.put(1, new JLabel("Left"));
		table.put(2, new JLabel("Center"));
		table.put(3, new JLabel("Right"));
		
		int extent2 = 0;
		startslider.setExtent(extent2);
		startslider.setLabelTable(table);
		
		add(startslider);
		
		
		//Launch button 
		launchbutton = new JButton("LAUNCH");
		launchbutton.addActionListener(this);
		add(launchbutton); 
		
		countdown = new JLabel();
		add(countdown);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed");
		
		Object source = e.getSource();
		
		if (source == red){
			fireworkcolor = Color.RED;
		} else if (source == green){
			fireworkcolor = Color.GREEN;
		} else if (source == cyan){
			fireworkcolor = Color.CYAN;
		} else if (source == blue){
			fireworkcolor = Color.BLUE;
		} else if (source == pink){
			fireworkcolor = Color.PINK;
		} else if (source == black){
			fireworkcolor = Color.BLACK;
		} else if (source == magenta){
			fireworkcolor = Color.MAGENTA;
		} else if (source == darkgrey){
			fireworkcolor = Color.DARK_GRAY;
		} else if (source == lightgrey){
			fireworkcolor = Color.LIGHT_GRAY;
		}
		
		
		Object source2 = e.getSource();
		
		if (source2 == launchbutton){
			
			System.out.println("Firework launched!");
			
			counter = delayslider.getValue();
			ActionListener taskperformer = new ActionListener(){
				public void actionPerformed (ActionEvent e){
					
					if (--counter > 0){
						countdown.setText("Your firework will explode in " + String.valueOf(counter));
					} else {
						countdown.setText("Boom");
						timer.stop();
						projectileapp projectileapp = new projectileapp();
						projectileapp.setVisible(true);
					}
					
				}
			};
			timer = new Timer(1000,taskperformer);
			timer.start();
			
		} 
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		//launch angle parameter 
		System.out.println("statechanged");
		Object slidersource = e.getSource();
		
		if (slidersource == angleslider){
			angleslider.setMinorTickSpacing(2);
			angleslider.setMajorTickSpacing(10);
			angleslider.setPaintTicks(true);
			angleslider.setPaintLabels(true);
			angleslider.setLabelTable(angleslider.createStandardLabels(10)); //increments 10
			
			userangle.setText(Integer.toString(angleslider.getValue()));
			
		} else if (slidersource == speedslider){
			//launch speed parameter 
			speedslider.setMinorTickSpacing(2);
			speedslider.setMajorTickSpacing(10);
			speedslider.setPaintTicks(true);
			speedslider.setPaintLabels(true);
			speedslider.setLabelTable(speedslider.createStandardLabels(10)); //increments 10
			
			userspeed.setText(Integer.toString(speedslider.getValue()));

			
		} else if (slidersource == delayslider){
			//time delay parameter
			delayslider.setMinorTickSpacing(1);
			delayslider.setMajorTickSpacing(5);
			delayslider.setPaintTicks(true);
			delayslider.setPaintLabels(true);
			delayslider.setLabelTable(delayslider.createStandardLabels(10)); //increments 10
			
			timedelay.setText(Integer.toString(delayslider.getValue()));

		}
		
	}
	
	
	public class Fireworks extends JPanel{
		
		public void paintComponent(Graphics g){
			width = getWidth();
			height = getHeight();
			
			System.out.println("Canvas.paintComponent");
			
			
			gravity = 9.8;
			speed = speedslider.getValue();
			angle = Math.toRadians(angleslider.getValue());
			time = delayslider.getValue();
			
			//draw projectile depending on where the x,y of explosion is 
			
			int [] xpoints = new int[1001];
			int [] ypoints = new int[1001];
			
			g.setColor(fireworkcolor);
			
			for (int i= 0; i <= 1000; i++){
				if (startslider.getValue() == 1){
					xpoints[i]= (int)Math.round((((speed*Math.cos(angle)*time*i))/1000)+ width/2- width/5);
					ypoints[i]= height-((int)Math.round((((speed*Math.sin(angle)*time*i))/1000 - (((0.5*gravity*time*i*time*i))/(1000*1000)))));
				} else if (startslider.getValue() == 2){
					xpoints[i]= (int)Math.round((((speed*Math.cos(angle)*time*i))/1000)+width/2);
					ypoints[i]= height-((int)Math.round((((speed*Math.sin(angle)*time*i))/1000 - (((0.5*gravity*time*i*time*i))/(1000*1000)))));
				} else if (startslider.getValue() == 3){
					xpoints[i]= (int)Math.round((((speed*Math.cos(angle)*time*i))/1000)+ width/2+ width/5);
					ypoints[i]= height-((int)Math.round((((speed*Math.sin(angle)*time*i))/1000 - (((0.5*gravity*time*i*time*i))/(1000*1000)))));
				}
			}
			
			g.drawPolyline(xpoints, ypoints, 1001);
			
			int xcoord = xpoints[1000];
			int ycoord = ypoints[1000];
			
			//draw out the different explosion types 
			
			if (rippleboom.isSelected()==true){
				for (int i=1; i<=8; i++){
					g.drawOval(xcoord-(width/(i*10)), ycoord-(height/(i*10)), width/(i*5), height/(i*5));
					g.drawOval(xcoord-(width/(i*10)-70), ycoord-(height/(i*10)-40), width/(i*5), height/(i*5));
					g.drawOval(xcoord-(width/(i*10)+40), ycoord-(height/(i*10)+30), width/(i*5), height/(i*5));
					g.drawOval(xcoord-(width/(i*10)+90), ycoord-(height/(i*10)-60), width/(i*5), height/(i*5));

				}

				
			} else if (squareboom.isSelected()==true){
				for (int i=0; i<=5; i++){
					g.drawRect(xcoord-(i*10), ycoord-(i*10), i*20, i*20);
					g.drawRect(xcoord-width/6-(i*10), ycoord-height/6-(i*10), 20+i*20, 20+i*20);
					g.drawRect(xcoord+width/8-(i*10), ycoord-height/8-(i*10), 20+i*20, 20+i*20);

				}
				

			} else if (sunlightboom.isSelected()==true){
				g.drawOval(xcoord-5, ycoord-5, 10, 10);
				for (int i=1; i<=5;i++){
					g.drawLine(xcoord, ycoord, xcoord-(50-10*i), ycoord-(i*10));
					g.drawLine(xcoord, ycoord, xcoord-(50-10*i), ycoord+(i*10));
					g.drawLine(xcoord, ycoord, xcoord+(50-10*i), ycoord-(i*10));
					g.drawLine(xcoord, ycoord, xcoord+(50-10*i), ycoord+(i*10));
				}
				
				g.drawOval(xcoord-55, ycoord-55, 10, 10);
				for (int i=1; i<=5;i++){
					g.drawLine(xcoord-50, ycoord-50, xcoord-50-(50-10*i), ycoord-50-(i*10));
					g.drawLine(xcoord-50, ycoord-50, xcoord-50+(50-10*i), ycoord-50-(i*10));
					g.drawLine(xcoord-50, ycoord-50, xcoord-50-(50-10*i), ycoord-50+(i*10));
					g.drawLine(xcoord-50, ycoord-50, xcoord-50+(50-10*i), ycoord-50+(i*10));
				}
				
				g.drawOval(xcoord+15, ycoord-65, 10, 10);
				for (int i=1; i<=5;i++){
					g.drawLine(xcoord+20, ycoord-60, xcoord+20-(50-10*i), ycoord-60-(i*10));
					g.drawLine(xcoord+20, ycoord-60, xcoord+20+(50-10*i), ycoord-60-(i*10));
					g.drawLine(xcoord+20, ycoord-60, xcoord+20-(50-10*i), ycoord-60+(i*10));
					g.drawLine(xcoord+20, ycoord-60, xcoord+20+(50-10*i), ycoord-60+(i*10));
				}
				
				g.drawOval(xcoord+55, ycoord+25, 10, 10);
				for (int i=1; i<=5;i++){
					g.drawLine(xcoord+60, ycoord+30, xcoord+60-(50-10*i), ycoord+30-(i*10));
					g.drawLine(xcoord+60, ycoord+30, xcoord+60+(50-10*i), ycoord+30-(i*10));
					g.drawLine(xcoord+60, ycoord+30, xcoord+60-(50-10*i), ycoord+30+(i*10));
					g.drawLine(xcoord+60, ycoord+30, xcoord+60+(50-10*i), ycoord+30+(i*10));
				}
				
				g.drawOval(xcoord+85, ycoord-75, 10, 10);
				for (int i=1; i<=5;i++){
					g.drawLine(xcoord+90, ycoord-70, xcoord+90-(50-10*i), ycoord-70-(i*10));
					g.drawLine(xcoord+90, ycoord-70, xcoord+90+(50-10*i), ycoord-70-(i*10));
					g.drawLine(xcoord+90, ycoord-70, xcoord+90-(50-10*i), ycoord-70+(i*10));
					g.drawLine(xcoord+90, ycoord-70, xcoord+90+(50-10*i), ycoord-70+(i*10));
				}
			
			} else if (flowerboom.isSelected()==true){
				g.fillOval(xcoord-50, ycoord-40, 40, 40);
				g.fillOval(xcoord-50, ycoord-20, 40, 40);
				g.fillOval(xcoord-30, ycoord-10, 40, 40);
				g.fillOval(xcoord-30, ycoord-60, 40, 40);
				
				g.fillOval(xcoord+10, ycoord-40, 40, 40);
				g.fillOval(xcoord+10, ycoord-20, 40, 40);
				g.fillOval(xcoord-10, ycoord-10, 40, 40);
				g.fillOval(xcoord-10, ycoord-60, 40, 40);
				
				g.setColor(Color.YELLOW);
				g.drawOval(xcoord-90, ycoord-90, 90, 90);
				g.drawOval(xcoord-90, ycoord-45, 90, 90);
				g.drawOval(xcoord-45, ycoord, 90, 90);
				g.drawOval(xcoord-45, ycoord-135, 90, 90);
				g.drawOval(xcoord, ycoord-90, 90, 90);
				g.drawOval(xcoord, ycoord-45, 90, 90);


				g.setColor(Color.WHITE);
				
				g.drawOval(xcoord-60, ycoord-60, 60, 60);
				g.drawOval(xcoord-60, ycoord-30, 60, 60);
				g.drawOval(xcoord-30, ycoord, 60, 60);
				g.drawOval(xcoord-30, ycoord-90, 60, 60);
				g.drawOval(xcoord, ycoord-60, 60, 60);
				g.drawOval(xcoord, ycoord-30, 60, 60);
				
				g.drawOval(xcoord-120, ycoord-120, 120, 120);
				g.drawOval(xcoord-120, ycoord-60, 120, 120);
				g.drawOval(xcoord-60, ycoord, 120, 120);
				g.drawOval(xcoord-60, ycoord-180, 120, 120);
				g.drawOval(xcoord, ycoord-120, 120, 120);
				g.drawOval(xcoord, ycoord-60, 120, 120);
				
				g.fillOval(xcoord-20, ycoord-40, 40, 40);
			
			} else if (bubbleboom.isSelected()==true){
				for (int i=1; i<=8; i++){
					g.drawOval(xcoord-(width/(i*5)), ycoord-(height/(i*5)), (width/(i*5)), (height/(i*5)));
					g.drawOval(xcoord, ycoord-(height/(i*5)), (width/(i*5)), (height/(i*5)));
					g.drawOval(xcoord-(width/(i*5)), ycoord-(height/(i*10)), (width/(i*5)), (height/(i*5)));
					g.drawOval(xcoord, ycoord-(height/(i*10)), (width/(i*5)), (height/(i*5)));
				}
			}
		}
	}
	
	public class projectileapp extends JFrame{
		public projectileapp(){
			setTitle("ProjectileLoop");
			setSize(600,600);
			
			Fireworks canvas = new Fireworks();
			add(canvas);
		}
	}
	
	public static void main (String [] args){
		new FireworksApp().setVisible(true);
	}

}
	
	
