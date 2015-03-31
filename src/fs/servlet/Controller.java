package fs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fs.api.FSRpc;
@WebServlet(name="controller",urlPatterns={"/controller"})
public class Controller extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,java.io.IOException
	{
		String api;
		String result;
		String lega;
		String command = null;
		RequestDispatcher rd;
		api = request.getParameter("api");
		lega = request.getParameter("lega");
		
		if(api.equals("originate"))
		{
			command = "sofia/internal/"+ lega +" &park()";			
		}
		
		FSRpc fs = new FSRpc("http://192.168.122.64:8080/RPC2","freeswitch","works");
		result = fs.api(api,command);
		request.setAttribute("result", result);		
		rd = request.getRequestDispatcher("/result.jsp");		
		rd.forward(request, response);
		
	}

}
