/**
 * 
 */
package net.will.javatest.thkij4ed.ch14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Will
 * @version 2011-10-16
 */
public class SimpleDynamicProxy {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("grape");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RealObject real = new RealObject();
		consumer(real);
		
		System.out.println("========================");
		Interface proxy = (Interface) Proxy.newProxyInstance(
// test which ClassLoader is ok:
// java.lang.IllegalArgumentException: interface net.will.javatest.thkij4ed.ch14.Interface is not visible from class loader
//				Integer.class.getClassLoader(),         // TEST point 2, error occurs here.
//				SimpleDynamicProxy.class.getClassLoader(),   // it's ok here.
				Interface.class.getClassLoader(),
				new Class[] { Interface.class },
				new DynamicProxyHandler(real));
		consumer(proxy);
	}

}

class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;
	
	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("proxy: " + proxy.getClass() +
				", method: " + method + ", args: " + args);
		if (args != null) {
			for (Object arg: args) {
				System.out.println(" " + arg);
			}
		}
		
		// Wrong code. Be careful when calling methods on the proxy inside invoke(),
		// because calls through the interface are redirected to this invocation
		// handler through the proxy. So it is an infinite recursive here.
//		return method.invoke(proxy, args);                   // TEST point 1
		return method.invoke(proxied, args);
	}
	
}

interface Interface {
	abstract void doSomething();
	abstract void somethingElse(String arg);
}

class RealObject implements Interface {

	@Override
	public void doSomething() {
		System.out.println("doSomething");
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("somethingElse " + arg);
	}
	
}
