package com.krushna.rmijndi.server;

import java.io.IOException;
import java.net.URL;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * JNDI Server 1.create a registry on port 1234 2.bind JNDI 3.wait for
 * connection 4.clean up and end
 */
@Component
public class Server {
	private static Registry registry;
	private static InitialContext ctx;

	public void initJNDI() {
		try {
			registry = LocateRegistry.createRegistry(1234);
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
			jndiProperties.put(Context.PROVIDER_URL, "rmi://localhost:1234");
			ctx = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void bindJNDI(String name, Object obj) throws NamingException {
		ctx.bind(name, obj);
	}

	public void unbindJNDI(String name) throws NamingException {
		ctx.unbind(name);
	}

	public void unInitJNDI() throws NamingException {
		ctx.close();
	}

	public void startJNDI() throws NamingException, IOException {
		URL url = new ClassPathResource("client.policy", this.getClass().getClassLoader()).getURL();
	/*	System.setProperty("java.security.policy", url.toString());
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}*/
		initJNDI();
		NMessage msg = new NMessage("Just A Message");
		bindJNDI("/neohope/jndi/test01", msg);
		System.in.read();
		unbindJNDI("/neohope/jndi/test01");
		unInitJNDI();
	}
}