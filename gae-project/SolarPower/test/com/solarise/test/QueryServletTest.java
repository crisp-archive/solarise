package com.solarise.test;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class QueryServletTest {
	InputStream is;
	DataInputStream dis;
	@Before
	public void setUp() throws Exception {
		is = null;
	}

	@Test
	public void NotSupportedType() throws IOException{
		URL url = new URL("http://solarise-qut.appspot.com/query?function=get_all_panels&type=abc");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "<?xml version=\"1.0\" ?><solarise><error>Such Type Is Not Supported.</error></solarise>");
	}

	@Test
	public void XMLNotSupportedFunction() throws IOException{
		URL url = new URL("http://solarise-qut.appspot.com/query?function=get_all_pan&type=xml");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "<?xml version=\"1.0\" ?><solarise><error>Unsupported function.</error></solarise>");
	}
	
	@Test
	public void JSONNotSupportedFunction() throws IOException{
		URL url = new URL("http://solarise-qut.appspot.com/query?function=get_all_pan&type=json");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "{\t\"error\": \"Unsupported function.\"}");
	}
	
	@Test
	public void XMLNullFunction() throws IOException{
		URL url = new URL("http://solarise-qut.appspot.com/query?type=xml");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "<?xml version=\"1.0\" ?><solarise><error>No Function Specified.</error></solarise>");
	}
	
	@Test
	public void JSONNullFunction() throws IOException{
		URL url = new URL("http://solarise-qut.appspot.com/query?type=json");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "{\t\"error\": \"No Function Specified.\"}");
	}
	/*
	 * The following two unit tests are only available when database has only the first panel
	 */
	@Test
	public void XMLGetAllPanels() throws IOException{
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_panels&type=xml");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "<?xml version=\"1.0\" ?><solarise><panel id=\"0\"><height>35</height><length>1580</length><weight>35.0</weight><width>808</width><manufacturer>Suntech</manufacturer><model>STP170S-24Ad</model><efficiency>14.1</efficiency><powerOutput>170</powerOutput><price>391.0</price><technology>Monocrystalline Silicon</technology><inverterLife>10</inverterLife><efficiencyLoss>0.02</efficiencyLoss></panel></solarise>");
	}
	
	@Test
	public void JSONGetAllPanels() throws IOException{
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_panels&type=json");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "var panels=\"{\\\"function\\\":\\\"Get All Panels\\\",\\\"number\\\":\\\"1\\\",\\\"panel\\\":[{\\\"id\\\": \\\"0\\\",\\\"height\\\": \\\"35\\\",\\\"length\\\": \\\"1580\\\",\\\"manufacturer\\\": \\\"Suntech\\\",\\\"model\\\": \\\"STP170S-24Ad\\\",\\\"efficiency\\\": \\\"14.1\\\",\\\"powerOutput\\\": \\\"170\\\",\\\"price\\\": \\\"391.0\\\",\\\"technology\\\": \\\"Monocrystalline Silicon\\\",\\\"weight\\\": \\\"35.0\\\",\\\"width\\\": \\\"808\\\",\\\"inverterLife\\\": \\\"10\\\",\\\"efficiencyLoss\\\": \\\"0.02\\\"}]}\";");
	}
	
	@Test
	public void XMLGetAllPanelsNoPanel() throws IOException{
		/*
		 * Attention Please!
		 * This unit test case is only available when no panel info is in datastore.
		 * So, normally, it would fail!
		 */
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_panels&type=xml");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "<?xml version=\"1.0\" ?><solarise><error>No entry.</error></solarise>");
	}
	
	@Test
	public void JSONGetAllPanelsNoPanel() throws IOException{
		/*
		 * Attention Please!
		 * This unit test case is only available when no panel info is in datastore.
		 * So, normally, it would fail!
		 */
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_panels&type=json");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "{\\\"function\\\":\\\"Get All Panels\\\",\\\"number\\\":\\\"0\\\",}");
	}
	
	@Test
	public void XMLGetAllPlans() throws IOException{
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_plans&type=xml&email=crispgm@gmail.com");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "<?xml version=\"1.0\" ?><solarise><plan id=\"0\"><address>90 Grays Rd, Gaythorne</address><energyUse>32.0</energyUse><latitude>-27.420979</latitude><longitude>152.97944299999995</longitude><number>33</number><panelId>0</panelId><roofArea>43.0</roofArea></plan></solarise>");
	}
	
	@Test
	public void JSONGetAllPlans() throws IOException{
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_plans&type=json&email=crispgm@gmail.com");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "var plans=\"{\\\"function\\\":\\\"Get All Plans\\\",\\\"number\\\":\\\"1\\\",\\\"plan\\\":[{\\\"id\\\": \\\"0\\\",\\\"address\\\": \\\"90 Grays Rd, Gaythorne\\\",\\\"energyUse\\\": \\\"32.0\\\",\\\"latitude\\\": \\\"-27.420979\\\",\\\"longitude\\\": \\\"152.97944299999995\\\",\\\"number\\\": \\\"33\\\",\\\"panelId\\\": \\\"0\\\",\\\"roofArea\\\": \\\"43.0\\\"}]}\";");
	}
	
	@Test
	public void XMLGetAllPlansNoPanel() throws IOException{
		/*
		 * Attention Please!
		 * This unit test case is only available when no plan info is in datastore.
		 * So, normally, it would fail!
		 */
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_plans&type=xml");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "<?xml version=\"1.0\" ?><solarise><error>No entry.</error></solarise>");
	}
	
	@Test
	public void JSONGetAllPlansNoPanel() throws IOException{
		/*
		 * Attention Please!
		 * This unit test case is only available when no plan info is in datastore.
		 * So, normally, it would fail!
		 */
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_plans&type=json");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "{\\\"function\\\":\\\"Get All Plans\\\",\\\"number\\\":\\\"0\\\",}");
	}
	
	@Test
	public void XMLGetAllRecords() throws IOException{
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_records&type=xml&email=crispgm@gmail.com");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "<?xml version=\"1.0\" ?><solarise><record id=\"0\"><projectId>0</projectId><data>2.3</data><comment>first record</comment></record></solarise>");
	}
	
	@Test
	public void JSONGetAllRecords() throws IOException{
		URL url = new URL("http://127.0.0.1:8888/query?function=get_all_records&type=json&email=crispgm@gmail.com");
	    is = url.openStream();  // throws an IOException
	    dis = new DataInputStream(new BufferedInputStream(is));
	    String content="";
	    String line=null;
	    while ((line = dis.readLine()) != null) {
	        content+=line;
	    }
		assertEquals(content, "var records=\"{\\\"function\\\":\\\"Get All Records\\\",\\\"number\\\":\\\"1\\\",\\\"record\\\":[{\\\"id\\\": \\\"0\\\",\\\"projectId\\\": \\\"0\\\",\\\"data\\\": \\\"2.3\\\",\\\"comment\\\": \\\"first record\\\"}]}\";");
	}
}
