package be.davidcorp.view.game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glRectf;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class DisplayWindow extends JFrame{

	   public Canvas canvas = new Canvas();                           
	   private JPanel westPanel=new JPanel();                         
	   private List animalArr;                                       
	   private List animalOptionsBoxArr;                                                              
	   public JLabel optionsLabel=new JLabel();                       
	   public JToggleButton animalIOToggle = new JToggleButton();     
	   public JTextArea animalIOText = new JTextArea();               

	   ArrayList renderableEntities= new ArrayList();                      

	   //states modified by logic class that handle all swing listners 
	   private boolean resizePending=false;  
	   private boolean exitPending=false;   

	   DisplayWindow(List animalArr)
	   {
	     super(); 
	     this.animalArr=animalArr; //created from db in logic class
	   }
	   public void run()
	   {
	     startDisplay();   
	   }


	   public void startDisplay() 
	   {  
	      //Init GUI

//	      setupFrame();             //setup main Jframe
//	      setupMainPanels();        //setup main panels within JFrames borderLayout)
//	      setupContentPanels();     //setup the content panels within the main panel

	      canvas.setSize(Display.getWidth(), Display.getHeight());     
	      canvas.setFocusable(true);                                
	      canvas.setIgnoreRepaint(true);                         

	      this.add(canvas,BorderLayout.CENTER);                       
	      this.setVisible(true);                                     

	      //Display Setup
	      try
	      {
	         Display.setResizable(true);  
	         Display.setParent(canvas);                            
	         Display.sync(60);            
	         Display.create();            
	      }
	      catch(LWJGLException ex)
	      {
	    	  ex.printStackTrace();
//	        Error.fatalError("Failed to Initialise Park Display",ex);  
	      } 

	      //OpenGL INIT                    
	      glClearColor(0.0f,0.0f,0.0f,0.0f); //black backround                          
	      glEnable(GL11.GL_TEXTURE_2D);     
	      glEnable(GL11.GL_BLEND);                                    
	      glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);

	      glMatrixMode(GL_PROJECTION);                                 
	      glLoadIdentity();                                            
	      glOrtho(0,Display.getWidth(),0,Display.getHeight(),1,-1);      
	      glMatrixMode(GL_MODELVIEW);   

	      //Render Loop    
	      while(true)                     
	      {
	    	  glClear(GL_COLOR_BUFFER_BIT);
	           render();

	           checkResizeDisplay();
	           Display.update();
	      }
//	      cleanUp();
	   }

	  public void render()
	  { 
	     //test Render 
		  glColor3f(1,1,1);             //white render color
		  glRectf(100,100,400,400);     
		  glBegin(GL_POINTS);           //point at 5 above mouse location
		  glVertex2d(Mouse.getX(),Mouse.getY()+5 );
		  glEnd();
	  } 

	  public void checkResizeDisplay() //is this even neccisary?
	 {   
	     if(resizePending==true)
	     {
	         glMatrixMode(GL_PROJECTION);                                 
	         glLoadIdentity();                                            
	         glOrtho(0,Display.getWidth(),0,Display.getHeight(),1,-1);      
	         glMatrixMode(GL_MODELVIEW);                                   
	     }

	 }
	  
	  public static void main(String[] args){
		  new DisplayWindow(new List()).run();
	  }

	} 
