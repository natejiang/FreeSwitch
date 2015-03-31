package fs.api;

import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class FSRpc
{
	String server;
	String user;
	String pass;
	public FSRpc(String server, String user, String pass) 
	{
		this.server = server;
		this.user = user;
		this.pass = pass;
	}	
	public String api(String api,String command)
	{
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		XmlRpcClient client = new XmlRpcClient();	
		String result = null;
		try 
		{
		   config.setServerURL(new URL(server));
		   config.setBasicUserName(user);
		   config.setBasicPassword(pass);

		   client.setConfig(config);
		   Object resultobj = client.execute("freeswitch.api", new Object[]{api, command});
		   result = resultobj.toString();
		} 
		catch (Exception ex) 
		{
		   ex.printStackTrace();
		}
		return result;
		
	}

}
