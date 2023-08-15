package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {

	private final MyLogger myLogger;
	//private final ObjectProvider<MyLogger> myLoggerObjectProvider;
	
	public void logic(String id) {
		//MyLogger myLogger = myLoggerObjectProvider.getObject();
		myLogger.log("service id = " + id);
	}
}
