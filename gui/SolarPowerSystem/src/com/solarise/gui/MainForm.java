package com.solarise.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Combo;

import com.solarise.entity.PanelStringEntity;
import com.solarise.entity.PlanEntity;
import com.solarise.entity.RecordEntity;

public class MainForm extends Shell {
	/**
	 * Launch the application.
	 * @param args
	 */
	static String email;
	Combo panelCombo;
	Combo planCombo;
	Combo recordCombo;
	Text textRoofArea;
	Text textNumber;
	Text textEnergy;
	Text textEGen;
	Text textComment;
	GetAllPanels gap=new GetAllPanels();
	GetAllPlans gal;
	GetAllRecords gar;
	ArrayList<PanelStringEntity> panelList= gap.getAllPanels();
	ArrayList<PlanEntity> planList;
	ArrayList<RecordEntity> recordList;
	MapHandler mh;
	public static void callMain(){
		try {
			Display display = Display.getDefault();
			MainForm shell = new MainForm(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		//callMain();
		
		Display display = Display.getDefault();
		LoginForm shell = new LoginForm(display);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			email=shell.getEmail();
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if(shell.isLogin()){
			callMain();
		}
	}
	
	public void addTrackerTab(TabFolder tabFolder){
		TabItem ti = new TabItem(tabFolder, SWT.NONE);
		ti.setText("Tracker");
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		ti.setControl(composite);
		
		gar=new GetAllRecords(email);
		recordList= gar.getAllRecords();
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Saved Records");
		label_1.setBounds(10, 10, 100, 17);
		
		recordCombo = new Combo(composite, SWT.NONE);
		String [] recordString=new String[recordList.size()];
		for(int i=0;i<recordList.size();i++){
			recordString[i]="#"+i;
		}
		recordCombo.setItems(recordString);
		recordCombo.setBounds(180, 10, 80, 25);
		recordCombo.select(0);
		
		Label labelTagProject = new Label(composite, SWT.NONE);
		labelTagProject.setText("Project");
		labelTagProject.setBounds(10, 40, 170, 17);
		final Label labelShowProject = new Label(composite, SWT.NONE);
		labelShowProject.setBounds(180, 40, 300, 17);
		
		Label labelTagData = new Label(composite, SWT.NONE);
		labelTagData.setText("Data");
		labelTagData.setBounds(10, 70, 170, 17);
		final Label labelShowData = new Label(composite, SWT.NONE);
		labelShowData.setBounds(180, 70, 170, 17);
		
		Label labelTagComment = new Label(composite, SWT.NONE);
		labelTagComment.setText("Comment");
		labelTagComment.setBounds(10, 100, 170, 17);
		final Label labelShowComment = new Label(composite, SWT.NONE);
		labelShowComment.setBounds(180, 100, 300, 17);
		
		Button buttonView = new Button(composite, SWT.NONE);
		buttonView.setText("View");
		buttonView.setBounds(260, 10, 80, 25);
		buttonView.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					int i=recordCombo.getSelectionIndex();
					if(i<0 || i>=recordList.size()){  
						return;
					}
					labelShowProject.setText("#"+recordList.get(i).projectId);
					labelShowData.setText(""+recordList.get(i).data);
					labelShowComment.setText(recordList.get(i).comment);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		Button buttonRefresh = new Button(composite, SWT.NONE);
		buttonRefresh.setText("Refresh");
		buttonRefresh.setBounds(480, 10, 80, 25);
		buttonRefresh.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					recordCombo.removeAll();
					recordList.clear();
					recordList=gar.getAllRecords();
					String [] planString=new String[recordList.size()];
					for(int i=0;i<recordList.size();i++){
						planString[i]="#"+i;
					}
					recordCombo.setItems(planString);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		final Browser browser1=new Browser(composite,SWT.FILL);
		browser1.setBounds(10, 240, 400, 270);
		final Browser browser2=new Browser(composite,SWT.FILL);
		browser2.setBounds(410, 240, 400, 270);
		
		Button buttonShow = new Button(composite, SWT.NONE);
		buttonShow.setText("Show Graphics");
		buttonShow.setBounds(350, 10, 120, 25);
		buttonShow.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					int i=recordCombo.getSelectionIndex();
					int pid=Integer.parseInt(planList.get(Integer.parseInt(recordList.get(i).projectId.toString())).panelId.toString());
					PanelStringEntity pse=panelList.get(pid);
					browser1.setUrl("http://solarise-qut.appspot.com/embeddedREChart.jsp?projectId="+pid);
					browser2.setUrl("http://solarise-qut.appspot.com/embeddedRMChart.jsp?projectId="+pid);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
	}
	
	public void addPanelTab(TabFolder tabFolder){
		TabItem ti = new TabItem(tabFolder, SWT.NONE);
		ti.setText("Panels");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		ti.setControl(composite);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Select Solar Panel");
		label_1.setBounds(10, 10, 100, 17);
		
		panelCombo = new Combo(composite, SWT.NONE);
		String [] panelString=new String[panelList.size()];
		for(int i=0;i<panelList.size();i++){
			panelString[i]=panelList.get(i).model;
		}
		panelCombo.setItems(panelString);
		panelCombo.setBounds(180, 10, 210, 25);
		panelCombo.select(0);
		
		Label labelTagNumber = new Label(composite, SWT.NONE);
		labelTagNumber.setText("Number of panels");
		labelTagNumber.setBounds(10, 40, 170, 17);
		textNumber=new Text(composite,SWT.BORDER);
		textNumber.setBounds(180, 40, 80, 20);
		
		Label labelRoof = new Label(composite, SWT.WRAP);
		labelRoof.setText("Roof Area (square meters)");
		labelRoof.setBounds(280, 40, 170, 17);
		textRoofArea=new Text(composite,SWT.BORDER);
		textRoofArea.setBounds(450, 40, 80, 20);
		
		Button buttonRecommend = new Button(composite, SWT.NONE);
		buttonRecommend.setText("Recommend");
		buttonRecommend.setBounds(550, 40, 80, 25);
		buttonRecommend.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					int i=panelCombo.getSelectionIndex();
					if(i<0 || i>=panelList.size()){  
						return;
					}
					PanelStringEntity pse=panelList.get(i);
				
					Double l=Double.parseDouble(pse.length);
					Double w=Double.parseDouble(pse.width);
					Double roofArea;
					if(textRoofArea.getText()==null || textRoofArea.getText().length()==0){
						roofArea=0.0;
					}
					else{
						roofArea=Double.parseDouble(textRoofArea.getText());
					}
					int capable=(int) Math.floor(roofArea/(l*w)*1000000);
					textNumber.setText(""+capable);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		Label labelEnergy = new Label(composite, SWT.WRAP);
		labelEnergy.setText("Energy Usage (kilowatts*hours)");
		labelEnergy.setBounds(10, 70, 170, 17);
		textEnergy=new Text(composite,SWT.BORDER);
		textEnergy.setBounds(180, 70, 80, 20);
		
		Label labelTagManufacturer = new Label(composite, SWT.NONE);
		labelTagManufacturer.setText("Manufacturer");
		labelTagManufacturer.setBounds(10, 100, 170, 17);
		final Label labelShowManufacturer = new Label(composite, SWT.NONE);
		labelShowManufacturer.setBounds(180, 100, 170, 17);
		
		Label labelTagPrice = new Label(composite, SWT.NONE);
		labelTagPrice.setText("Price");
		labelTagPrice.setBounds(360, 100, 170, 17);
		final Label labelShowPrice = new Label(composite, SWT.NONE);
		labelShowPrice.setBounds(540, 100, 170, 17);
		
		Label labelTagPowerOutput = new Label(composite, SWT.NONE);
		labelTagPowerOutput.setText("Power Output");
		labelTagPowerOutput.setBounds(10, 130, 170, 17);
		final Label labelShowPowerOutput = new Label(composite, SWT.NONE);
		labelShowPowerOutput.setBounds(180, 130, 170, 17);
		
		Label labelTagFee = new Label(composite, SWT.NONE);
		labelTagFee.setText("Building Fee");
		labelTagFee.setBounds(360, 130, 170, 17);
		final Label labelShowFee = new Label(composite, SWT.NONE);
		labelShowFee.setBounds(540, 130, 170, 17);
		
		Label labelTagLength = new Label(composite, SWT.NONE);
		labelTagLength.setText("Length");
		labelTagLength.setBounds(10, 160, 170, 17);
		final Label labelShowLength = new Label(composite, SWT.NONE);
		labelShowLength.setBounds(180, 160, 170, 17);
		
		Label labelTagWidth = new Label(composite, SWT.NONE);
		labelTagWidth.setText("Width");
		labelTagWidth.setBounds(360, 160, 170, 17);
		final Label labelShowWidth = new Label(composite, SWT.NONE);
		labelShowWidth.setBounds(540, 160, 170, 17);
		
		Label labelTagHeight = new Label(composite, SWT.NONE);
		labelTagHeight.setText("Height");
		labelTagHeight.setBounds(10, 190, 170, 17);
		final Label labelShowHeight = new Label(composite, SWT.NONE);
		labelShowHeight.setBounds(180, 190, 170, 17);
		
		Label labelTagWeight = new Label(composite, SWT.NONE);
		labelTagWeight.setText("Weight");
		labelTagWeight.setBounds(360, 190, 170, 17);
		final Label labelShowWeight = new Label(composite, SWT.NONE);
		labelShowWeight.setBounds(540, 190, 170, 17);
		
		final Browser browser1=new Browser(composite,SWT.FILL);
		browser1.setBounds(10, 240, 400, 270);
		final Browser browser2=new Browser(composite,SWT.FILL);
		browser2.setBounds(410, 240, 400, 270);
		
		Button buttonView = new Button(composite, SWT.NONE);
		buttonView.setText("Show Details");
		buttonView.setBounds(400, 10, 80, 25);
		buttonView.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					int i=panelCombo.getSelectionIndex();
					if(i<0 || i>=panelList.size()){  
						return;
					}
					PanelStringEntity pse=panelList.get(i);
					
					labelShowManufacturer.setText(pse.manufacturer);
					labelShowLength.setText(pse.length+" mm");
					labelShowWidth.setText(pse.width+" mm");
					labelShowHeight.setText(pse.height+" mm");
					labelShowWeight.setText(pse.weight+" kg");
					labelShowPowerOutput.setText(pse.powerOutput+" W");
					labelShowFee.setText("50 $");
					labelShowPrice.setText(pse.price+" $");
				
					browser1.setUrl("http://solarise-qut.appspot.com/embeddedEChart.jsp?output="+pse.powerOutput+"&loss=0.02");
					browser2.setUrl("http://solarise-qut.appspot.com/embeddedMChart.jsp?output="+pse.powerOutput+"&loss=0.02");
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
	}
	
	public void addEstimationTab(TabFolder tabFolder){
		TabItem ti=new TabItem(tabFolder, SWT.NONE);
		ti.setText("Estimation");
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		ti.setControl(composite);
		
		Label labelTag1 = new Label(composite, SWT.NONE);
		labelTag1.setText("Plan Information");
		labelTag1.setBounds(10, 10, 170, 17);
		
		Label labelTagPname = new Label(composite, SWT.NONE);
		labelTagPname.setText("Panel Model");
		labelTagPname.setBounds(10, 40, 170, 17);
		final Label labelShowPname = new Label(composite, SWT.NONE);
		labelShowPname.setBounds(180, 40, 170, 17);
		
		Label labelTagNumber = new Label(composite, SWT.NONE);
		labelTagNumber.setText("Number");
		labelTagNumber.setBounds(360, 40, 170, 17);
		final Label labelShowNumber = new Label(composite, SWT.NONE);
		labelShowNumber.setBounds(530, 40, 170, 17);
		
		Label labelTagAddress = new Label(composite, SWT.NONE);
		labelTagAddress.setText("Address");
		labelTagAddress.setBounds(10, 70, 170, 17);
		final Label labelShowAddress = new Label(composite, SWT.NONE);
		labelShowAddress.setBounds(180, 70, 170, 17);
		
		Label labelTagCoordinates = new Label(composite, SWT.NONE);
		labelTagCoordinates.setText("Coordinates");
		labelTagCoordinates.setBounds(360, 70, 170, 17);
		final Label labelShowCoordinates = new Label(composite, SWT.NONE);
		labelShowCoordinates.setBounds(530, 70, 170, 17);
		
		Label labelTag2 = new Label(composite, SWT.NONE);
		labelTag2.setText("Estimation");
		labelTag2.setBounds(10, 100, 170, 17);
		
		Label labelTagPowerOutput = new Label(composite, SWT.NONE);
		labelTagPowerOutput.setText("Power Output");
		labelTagPowerOutput.setBounds(10, 130, 170, 17);
		final Label labelShowPowerOutput = new Label(composite, SWT.NONE);
		labelShowPowerOutput.setBounds(180, 130, 170, 17);
		
		Label labelTagSunshine = new Label(composite, SWT.NONE);
		labelTagSunshine.setText("Duration of Sunshine");
		labelTagSunshine.setBounds(360, 130, 170, 17);
		final Label labelShowSunshine = new Label(composite, SWT.NONE);
		labelShowSunshine.setBounds(530, 130, 170, 17);
		
		Label labelTagE1 = new Label(composite, SWT.NONE);
		labelTagE1.setText("Daily Electricity Generated");
		labelTagE1.setBounds(10, 160, 170, 17);
		final Label labelShowE1 = new Label(composite, SWT.NONE);
		labelShowE1.setBounds(180, 160, 170, 17);
		
		Label labelTagE2 = new Label(composite, SWT.NONE);
		labelTagE2.setText("Annual Electricity Generated");
		labelTagE2.setBounds(360, 160, 170, 17);
		final Label labelShowE2 = new Label(composite, SWT.NONE);
		labelShowE2.setBounds(530, 160, 170, 17);
		
		Label labelTagE3 = new Label(composite, SWT.NONE);
		labelTagE3.setText("Daily Equivalent Electricity Fee");
		labelTagE3.setBounds(10, 190, 170, 17);
		final Label labelShowE3 = new Label(composite, SWT.NONE);
		labelShowE3.setBounds(180, 190, 170, 17);
		
		Label labelTag3 = new Label(composite, SWT.NONE);
		labelTag3.setText("Surplus Power");
		labelTag3.setBounds(10, 220, 170, 17);
		
		Label labelTagE4 = new Label(composite, SWT.NONE);
		labelTagE4.setText("Daily Electricity Usage");
		labelTagE4.setBounds(10, 250, 170, 17);
		final Label labelShowE4 = new Label(composite, SWT.NONE);
		labelShowE4.setBounds(180, 250, 170, 17);
		
		Label labelTagE5 = new Label(composite, SWT.NONE);
		labelTagE5.setText("Daily Surplus Electricity");
		labelTagE5.setBounds(360, 250, 170, 17);
		final Label labelShowE5 = new Label(composite, SWT.NONE);
		labelShowE5.setBounds(530, 250, 170, 17);
		
		Label labelTagE6 = new Label(composite, SWT.NONE);
		labelTagE6.setText("Annual Surplus Electricity");
		labelTagE6.setBounds(10, 280, 170, 17);
		final Label labelShowE6 = new Label(composite, SWT.NONE);
		labelShowE6.setBounds(180, 280, 170, 17);
		
		Button buttonCalc = new Button(composite, SWT.NONE);
		buttonCalc.setText("Calculate");
		buttonCalc.setBounds(360, 310, 80, 25);
		buttonCalc.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
				//error process
				//if(textNumber.getText().length()==0 || textEnergy.getText().length()==0){
					//return;
				//}
				//
					int i=panelCombo.getSelectionIndex();
					if(i<0 || i>=panelList.size()){  
						return;
					}
					PanelStringEntity pse=panelList.get(i);
					labelShowPname.setText(pse.model);
					labelShowNumber.setText(textNumber.getText());
					labelShowAddress.setText(mh.getAddress());
					labelShowCoordinates.setText(String.format("%.2f",mh.getLatitude())+", "+String.format("%.2f",mh.getLongitude()));
					labelShowPowerOutput.setText(pse.powerOutput+" W");
					labelShowSunshine.setText("10 Hours");
					int number=Integer.parseInt(textNumber.getText());
					double po=Double.parseDouble(pse.powerOutput);
					double dailyOutput= po*10*number*3600/3.6/1000000;
					double energy=Double.parseDouble(textEnergy.getText());
					labelShowE1.setText(String.format("%.2f", dailyOutput)+" kw*h | "+String.format("%.2f", dailyOutput*0.08)+" $");
					labelShowE2.setText(String.format("%.2f", dailyOutput*365)+" kw*h | "+String.format("%.2f", dailyOutput*0.08*365)+" $");
					labelShowE3.setText(String.format("%.2f", dailyOutput*0.12)+" $");
					labelShowE4.setText(textEnergy.getText()+" kw*h");
					labelShowE5.setText(String.format("%.2f", dailyOutput-energy)+" kw*h | "+String.format("%.2f", (dailyOutput-energy)*0.12)+" $");
					labelShowE6.setText(String.format("%.2f", 365*(dailyOutput-energy))+" kw*h | "+String.format("%.2f", 365*(dailyOutput-energy)*0.12)+" $");
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		Button buttonSave = new Button(composite, SWT.NONE);
		buttonSave.setText("Save");
		buttonSave.setBounds(450, 310, 80, 25);
		buttonSave.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					PlanEntity pe=new PlanEntity();
					pe.address=mh.getAddress();
					pe.energyUse=Double.parseDouble(textEnergy.getText());
					pe.latitude=mh.getLatitude();
					pe.longitude=mh.getLongitude();
					pe.number=Long.parseLong(textNumber.getText());
					pe.panelId=(long) planCombo.getSelectionIndex();
					pe.roofArea=Double.parseDouble(textRoofArea.getText());
					SavePlan sp=new SavePlan(email, pe);
					final Shell mbSh=new Shell();
					MessageBox mb=new MessageBox(mbSh,SWT.ICON_INFORMATION);
					mb.setMessage("This project is saved! Please refresh in Projects tab.");
					mb.setText("Solarise");
					mb.open();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
	}
	
	public void addProjectTab(TabFolder tabFolder){
		gal=new GetAllPlans(email);
		planList= gal.getAllPlans();
		
		TabItem tiProject=new TabItem(tabFolder, SWT.NONE);
		tiProject.setText("Projects");
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tiProject.setControl(composite);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Saved Projects");
		label_1.setBounds(10, 10, 100, 17);
		
		planCombo = new Combo(composite, SWT.NONE);
		String [] planString=new String[planList.size()];
		for(int i=0;i<planList.size();i++){
			planString[i]="#"+i;
		}
		planCombo.setItems(planString);
		planCombo.setBounds(180, 10, 80, 25);
		planCombo.select(0);
		
		Label labelTagPanel = new Label(composite, SWT.NONE);
		labelTagPanel.setText("Panel Model");
		labelTagPanel.setBounds(10, 40, 170, 17);
		final Label labelShowPanel = new Label(composite, SWT.NONE);
		labelShowPanel.setBounds(180, 40, 300, 17);
		
		Label labelTagNumber = new Label(composite, SWT.NONE);
		labelTagNumber.setText("Number");
		labelTagNumber.setBounds(10, 70, 170, 17);
		final Label labelShowNumber = new Label(composite, SWT.NONE);
		labelShowNumber.setBounds(180, 70, 170, 17);
		
		Label labelTagAddress = new Label(composite, SWT.NONE);
		labelTagAddress.setText("Address");
		labelTagAddress.setBounds(10, 100, 170, 17);
		final Label labelShowAddress = new Label(composite, SWT.NONE);
		labelShowAddress.setBounds(180, 100, 300, 17);
		
		Label labelTagCoordinates = new Label(composite, SWT.NONE);
		labelTagCoordinates.setText("Coordinates");
		labelTagCoordinates.setBounds(10, 130, 170, 17);
		final Label labelShowCoordinates = new Label(composite, SWT.NONE);
		labelShowCoordinates.setBounds(180, 130, 170, 17);
		
		Label labelTagRoof = new Label(composite, SWT.NONE);
		labelTagRoof.setText("Roof Area");
		labelTagRoof.setBounds(10, 160, 170, 17);
		final Label labelShowRoof = new Label(composite, SWT.NONE);
		labelShowRoof.setBounds(180, 160, 170, 17);
		
		Label labelTagUse = new Label(composite, SWT.NONE);
		labelTagUse.setText("Energy Usage");
		labelTagUse.setBounds(10, 190, 170, 17);
		final Label labelShowUse = new Label(composite, SWT.NONE);
		labelShowUse.setBounds(180, 190, 170, 17);
		
		Button buttonView = new Button(composite, SWT.NONE);
		buttonView.setText("View");
		buttonView.setBounds(260, 10, 80, 25);
		buttonView.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					int i=planCombo.getSelectionIndex();
					if(i<0 || i>=planList.size()){  
						return;
					}
					PlanEntity pe=planList.get(i);
					int pid=Integer.parseInt(String.format("%d",pe.panelId));
					labelShowPanel.setText(panelList.get(pid).model);
					labelShowNumber.setText(String.format("%d",pe.number));
					labelShowAddress.setText(pe.address);
					labelShowCoordinates.setText(String.format("%.2f",pe.latitude)+", "+String.format("%.2f",pe.longitude));
					labelShowRoof.setText(String.format("%.2f",pe.roofArea));
					labelShowUse.setText(String.format("%.2f", pe.energyUse));
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		Button buttonRefresh = new Button(composite, SWT.NONE);
		buttonRefresh.setText("Refresh");
		buttonRefresh.setBounds(350, 10, 80, 25);
		buttonRefresh.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					planCombo.removeAll();
					planList.clear();
					planList=gal.getAllPlans();
					String [] planString=new String[planList.size()];
					for(int i=0;i<planList.size();i++){
						planString[i]="#"+i;
					}
					planCombo.setItems(planString);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		Label labelTagEGen = new Label(composite, SWT.NONE);
		labelTagEGen.setText("Electricity Generated (kw*h)");
		labelTagEGen.setBounds(10, 240, 170, 17);
		textEGen=new Text(composite,SWT.BORDER);
		textEGen.setBounds(180, 240, 80, 20);
		
		Label labelTagComment = new Label(composite, SWT.NONE);
		labelTagComment.setText("Comment");
		labelTagComment.setBounds(10, 270, 170, 17);
		textComment=new Text(composite,SWT.BORDER);
		textComment.setBounds(180, 270, 80, 20);
		
		Button buttonAdd = new Button(composite, SWT.NONE);
		buttonAdd.setText("Add Tracker");
		buttonAdd.setBounds(260, 270, 80, 25);
		buttonAdd.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				try{
					RecordEntity re=new RecordEntity();
					re.comment=textComment.getText();
					re.data=Double.parseDouble(textEGen.getText());
					int i=recordCombo.getSelectionIndex();
					re.projectId=recordList.get(i).projectId;
					AddTracker at=new AddTracker(email, re);
					final Shell mbSh=new Shell();
					MessageBox mb=new MessageBox(mbSh,SWT.ICON_INFORMATION);
					mb.setMessage("This tracker is saved! Please refresh in Tracker page.");
					mb.setText("Solarise");
					mb.open();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
	}
	
	public void addMapTab(TabFolder tabFolder){
		TabItem tiProject=new TabItem(tabFolder, SWT.NONE);
		tiProject.setText("Geolocation");
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tiProject.setControl(composite);
		Browser browser=new Browser(composite,SWT.FILL);
		mh=new MapHandler(browser, "getResult");
		browser.setBounds(0, 0, 830, 550);
		browser.setUrl("http://solarise-qut.appspot.com/embeddedMap.jsp");
	}
	/**
	 * Create the shell.
	 * @param display
	 */
	public MainForm(Display display) {
		super(display, SWT.SHELL_TRIM & (~SWT.RESIZE));
		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setBounds(10, 10, 830, 550);
		// add all tabs
		addPanelTab(tabFolder);
		addMapTab(tabFolder);
		addEstimationTab(tabFolder);
		addProjectTab(tabFolder);
		addTrackerTab(tabFolder);
		// tab dislaimer
		TabItem tbtmDisclaimer = new TabItem(tabFolder, SWT.NONE);
		tbtmDisclaimer.setText("Disclaimer");
				
		Composite compositeDisclaimer = new Composite(tabFolder, SWT.NONE);
		compositeDisclaimer.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmDisclaimer.setControl(compositeDisclaimer);
		
		Label labelDisclaimer = new Label(compositeDisclaimer, SWT.WRAP);
		labelDisclaimer.setText(getDisclaimer());
		labelDisclaimer.setBounds(20, 20, 580, 400);
		
		// tab about
		TabItem tbtmAbout = new TabItem(tabFolder, SWT.NONE);
		tbtmAbout.setText("About");
		
		Composite compositeAbout = new Composite(tabFolder, SWT.NONE);
		compositeAbout.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmAbout.setControl(compositeAbout);
		
		Label labelAbout = new Label(compositeAbout, SWT.NONE);
		labelAbout.setText("Solarise Team\n\nChunYu Chen, n7615094\nJia-Long You, n8413983\nWanlong Zhang, n7697317\nJue Wang, n7400896\nTzit Xiang Lim, n7387512");
		labelAbout.setBounds(20, 20, 200, 200);
		
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Solarise Solar Power System - "+email);
		setSize(855, 600);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public String getDisclaimer(){
		String s = null;
		StringBuffer sb = new StringBuffer();
		File f = new File("disclaimer.txt");
		if (f.exists()) {
		    try {
		    	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		    	while ((s = br.readLine()) != null) {
		    		sb.append(s+"\n\n");
		     	}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    return sb.toString();
		}
		else{
		    return "Dislaimer file crashes.";
		}
	}
}
