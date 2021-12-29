package com.krushna.rmijndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.krushna.rmijndi.server.NMessage;

public class JNDIClientTest {
	public static void main(String[] args) throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "rmi://localhost:1234");

		InitialContext ctx = new InitialContext(jndiProperties);
		NMessage msg = (NMessage) ctx.lookup("/neohope/jndi/test01");
		System.out.println(msg.message);
		ctx.close();
	}
}
