package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
	
	private final LogDemoService logoDemoService;
	private final MyLogger myLogger;
	//private final ObjectProvider<MyLogger> myLoggerProvider;
	
	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) throws InterruptedException {
		String requestURL = request.getRequestURL().toString();
		//MyLogger myLogger = myLoggerProvider.getObject();
		System.out.println("myLogger=" + myLogger.getClass());
		myLogger.setRequestURL(requestURL);
		
		myLogger.log("controller test");
		Thread.sleep(1000);
		logoDemoService.logic("testId");
		return "OK";
	}
}
