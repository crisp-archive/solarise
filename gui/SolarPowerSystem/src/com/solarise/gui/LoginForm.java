package com.solarise.gui;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LoginForm extends Shell {
	private Text txtUsername;
	private Text txtPassword;
	Button btnLogin;
	Button btnSignup;
	private boolean isLogin=false;
	/**
	 * Create the shell.
	 * @param display
	 */
	public LoginForm(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setBounds(59, 45, 61, 17);
		lblUsername.setText("Email");
		
		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setBounds(59, 81, 61, 17);
		lblPassword.setText("Password");
		
		txtUsername = new Text(this, SWT.BORDER);
		txtUsername.setBounds(126, 39, 153, 23);
		txtUsername.setText(loadEmail());
		
		txtPassword = new Text(this, SWT.BORDER|SWT.PASSWORD);
		txtPassword.setBounds(126, 75, 153, 23);
		
		btnSignup = new Button(this, SWT.NONE);
		btnSignup.setBounds(50, 120, 111, 27);
		btnSignup.setText("Sign Up");
		btnSignup.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					URI uri=new URI("http://solarise-qut.appspot.com/signup.jsp");
					Desktop.getDesktop().browse(uri);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnLogin = new Button(this, SWT.NONE);
		btnLogin.setBounds(170, 120, 111, 27);
		btnLogin.setText("Log In");
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String email = txtUsername.getText();
				String password = txtPassword.getText();
				try{
					DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
				    DocumentBuilder builder=factory.newDocumentBuilder();
				    Document doc=builder.parse("http://solarise-qut.appspot.com/login?email="+email+"&password="+password+"&type=xml");
				    Element root=doc.getDocumentElement();
				    NodeList list=root.getChildNodes();
				    Long status=Long.parseLong(list.item(0).getTextContent());
				    if(status==200){
				    	isLogin=true;
				    	saveEmail(email);
				    	e.display.dispose();
					}
					else{
						isLogin=false;
						final Shell mbSh=new Shell();
						MessageBox mb=new MessageBox(mbSh,SWT.ICON_WARNING);
						mb.setText("Login Error!");
						mb.setMessage("Illegal email or password.");
						mb.open();
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		txtPassword.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.keyCode==13){
					String email = txtUsername.getText();
					String password = txtPassword.getText();
					try{
						DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
					    DocumentBuilder builder=factory.newDocumentBuilder();
					    Document doc=builder.parse("http://solarise-qut.appspot.com/login?email="+email+"&password="+password+"&type=xml");
					    Element root=doc.getDocumentElement();
					    NodeList list=root.getChildNodes();
					    Long status=Long.parseLong(list.item(0).getTextContent());
					    if(status==200){
					    	isLogin=true;
					    	saveEmail(email);
					    	e.display.dispose();
						}
						else{
							isLogin=false;
							final Shell mbSh=new Shell();
							MessageBox mb=new MessageBox(mbSh,SWT.ICON_WARNING);
							mb.setText("Login Error!");
							mb.setMessage("Illegal email or password.");
							mb.open();
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		});
		
		txtUsername.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.keyCode==13){
					String email = txtUsername.getText();
					String password = txtPassword.getText();
					try{
						DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
					    DocumentBuilder builder=factory.newDocumentBuilder();
					    Document doc=builder.parse("http://solarise-qut.appspot.com/login?email="+email+"&password="+password+"&type=xml");
					    Element root=doc.getDocumentElement();
					    NodeList list=root.getChildNodes();
					    Long status=Long.parseLong(list.item(0).getTextContent());
					    if(status==200){
					    	isLogin=true;
					    	saveEmail(email);
					    	e.display.dispose();
						}
						else{
							isLogin=false;
							final Shell mbSh=new Shell();
							MessageBox mb=new MessageBox(mbSh,SWT.ICON_WARNING);
							mb.setText("Login Error!");
							mb.setMessage("Illegal email or password.");
							mb.open();
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		});
		
		createContents();
	}
	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Solarise - Login");
		setSize(360, 210);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public boolean isLogin(){
		return isLogin;
	}
	
	public String getEmail(){
		return txtUsername.getText();
	}
	
	private String loadEmail(){
		File f = new File("email.ssp");
		if (f.exists()) {
		    try {
		    	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		    	return br.readLine();
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	return "";
		    }
		}
		else{
		    return "";
		}
	}
	
	private void saveEmail(String email) throws IOException{
		File f=new File("email.ssp");
		if(!f.exists())
			f.createNewFile();
		BufferedWriter output = new BufferedWriter(new FileWriter(f));
		output.write(email);
		output.close();
	}
}
