package com.mycompany.a4;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;


public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private final int tickrate =20;
	private UITimer timer;
	
	private CheckBox check;
	private ComSound comS;
	private ComAccelerate comA;
	private ComAbout comAb;
	private ComHelp comH;
	private ComQuit comQ;
	private ComTurnLeft comL;
	private ComBreakSpeed comB;
	private ComTurnRight comR;
	private ComPause comP;
	private ComPosition comPo;
	
	private Button buttL;
	private Button buttA;
	private Button buttB;
	private Button buttR;
	private Button buttP;
	private Button buttPo;
	
	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld();  //create observable gameworld
		mv = new MapView();
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);		
		this.add(BorderLayout.NORTH,sv);
		this.add(BorderLayout.CENTER,mv);		
		topSide();
		leftSide();
		rightSide();
		bottomSide();
		gw.init();  
		this.show();
		gw.createSound();
		timer=new UITimer(this);
		timer.schedule(tickrate, true, this);
		GameWorld.setGameWidth(mv.getWidth());
		GameWorld.setGameHeight(mv.getHeight());		
		mv.setMVOrgin(new Point(mv.getX(),mv.getY()));
		MapView.setMapViewWidth(mv.getWidth());
		MapView.setMapViewHeight(mv.getHeight());
	}
	
	
	private Button topButt(Button obj) { //ensures placement of button
		obj.getAllStyles().setBgTransparency(255);;
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setFgColor(ColorUtil.WHITE);
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		obj.getAllStyles().setMarginTop(100);
		obj.getAllStyles().setPadding(TOP,5);
		obj.getAllStyles().setPadding(BOTTOM,5);
		return obj;
		
	}
	
	private Button commButt(Button obj) { //common button
	
		obj.getAllStyles().setBgTransparency(255);;
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setFgColor(ColorUtil.WHITE);
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		obj.getAllStyles().setPadding(TOP,5);
		obj.getAllStyles().setPadding(BOTTOM,5);
		return obj;
	}
	
	private void topSide() {
		
		Toolbar tool= new Toolbar();
		this.setToolbar(tool);
		tool.setTitle("OnTarget Game");
		comA= new ComAccelerate(gw);
		tool.addCommandToSideMenu(comA);
		comAb= new ComAbout();
		tool.addCommandToSideMenu(comAb);
		comH= new ComHelp();
		tool.addCommandToRightBar(comH);
		
		check=new CheckBox();
		check.getAllStyles().setBgTransparency(255);
		check.getAllStyles().setBgColor(ColorUtil.YELLOW);
		//check.setFocusable(false);
		
		comS= new ComSound(gw);
		check.setCommand(comS);
		tool.addComponentToSideMenu(check);
		
		comQ= new ComQuit();
		tool.addCommandToSideMenu(comQ);
			
	}
	
	private void leftSide() { //2 buttons
		
		Container left=new Container(new BoxLayout(BoxLayout.Y_AXIS));
		left.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
	    comA=new ComAccelerate(gw);
		buttA=new Button(comA);
		buttA=topButt(buttA);
		left.add(buttA);
		addKeyListener('a',comA);
		comL=new ComTurnLeft(gw);
		buttL=new Button(comL);
		buttL=commButt(buttL);
		left.add(buttL);
		addKeyListener('l',comL);
		this.add(BorderLayout.WEST,left);
		
	}
	
	private void rightSide() { //2 buttons
		
		Container right=new Container (new BoxLayout(BoxLayout.Y_AXIS));
		right.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		comB=new ComBreakSpeed(gw);
		buttB=new Button(comB);
		buttB=topButt(buttB);
		right.add(buttB);
		addKeyListener('b',comB);
		comR=new ComTurnRight(gw);
		buttR=new Button(comR);
		buttR=commButt(buttR);
		right.add(buttR);
		addKeyListener('r',comR);	
		this.add(BorderLayout.EAST,right);
						
	}
	
	private void bottomSide() { //4 buttons
		
		Container bottom= new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottom.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		buttP=new Button("Pause");
		buttP=commButt(buttP);
		buttP.getAllStyles().setMarginLeft(900);
		comP=new ComPause(gw,this,buttP);
		buttP.setCommand(comP);
		bottom.add(buttP);
		
		comPo=new ComPosition(gw);
		buttPo=new Button(comPo);
		buttPo=commButt(buttPo);
		bottom.add(buttPo);
		this.add(BorderLayout.SOUTH,bottom);
				
	}


	@Override
	public void run() {
		gw.tick();
		
	}


	public void pressPause() {
	
		if(!gw.getPause()){
			
			timer.cancel();
			removeKeyListener('a',comA);
			removeKeyListener('l',comL);
			removeKeyListener('b',comB);
			removeKeyListener('r',comR);
			comA.setEnabled(false);
			comL.setEnabled(false);
			comB.setEnabled(false);
			comR.setEnabled(false);
			comPo.setEnabled(true);
			buttA.setEnabled(false);
			buttL.setEnabled(false);
			buttB.setEnabled(false);
			buttR.setEnabled(false);
			buttPo.setEnabled(true);
			gw.turnOffSound();
			gw.setPause(!gw.getPause());
		}
		else {
			timer.schedule(tickrate,true,this);
			addKeyListener('a',comA);
			addKeyListener('l',comL);
			addKeyListener('b',comB);
			addKeyListener('r',comR);
			comA.setEnabled(true);
			comL.setEnabled(true);
			comB.setEnabled(true);
			comR.setEnabled(true);
			comPo.setEnabled(false);
			buttA.setEnabled(true);
			buttL.setEnabled(true);
			buttB.setEnabled(true);
			buttR.setEnabled(true);
			buttPo.setEnabled(false);
			gw.turnOnSound();
			gw.setPause(!gw.getPause());
		}
		
		
	}

		
}
