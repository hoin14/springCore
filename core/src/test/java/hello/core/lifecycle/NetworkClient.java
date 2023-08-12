package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean{
	
	private String url;

	public NetworkClient() {
		System.out.println("생성자 호출=" + url);
		connect();
		call("START");
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void connect() {
		System.out.println("connet=" + url);
	}

	public void call(String message) {
		System.out.println("call:" + url + "message=" + message);
	}
	
	public void disconnect() {
		System.out.println("close:" + url);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		connect();
		call("START!");
	}
	
	@Override
	public void destroy() throws Exception {
		disconnect();
	}
}
