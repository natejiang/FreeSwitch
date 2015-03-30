package fs.api;

import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

@WebServlet(name="originate",urlPatterns={"/originate"})
public class Originate extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		String lega = request.getParameter("lega");
		String legb = request.getParameter("legb");
		String server = request.getParameter("server");
		RequestDispatcher rd;
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		XmlRpcClient client = new XmlRpcClient();
		try 
		{
		   config.setServerURL(new URL("http://192.168.122.64:8080/RPC2"));
		   config.setBasicUserName("freeswitch");
		   config.setBasicPassword("works");

		   client.setConfig(config);
		   String api = "sofia/internal/"+ lega +" &park()";
		   Object result = client.execute("freeswitch.api", new Object[]{"originate", api});
		   request.setAttribute("result",result.toString());
		} 
		catch (Exception ex) 
		{
		   ex.printStackTrace();
		} 
		rd = request.getRequestDispatcher("/result.jsp");		
		rd.forward(request, response);
		 
		  
		 
		
		
	}
	
	
	
	
}
