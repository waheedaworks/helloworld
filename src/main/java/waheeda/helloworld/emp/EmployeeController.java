package waheeda.helloworld.emp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.logging.LoggingSystem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	LoggingSystem log = LoggingSystem.get(EmployeeController.class.getClassLoader());
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final AtomicIntegerArray integerArray = new AtomicIntegerArray(10000);
	String bytes = "testetssdjf;ldskjflsdf;lsd;f sfs;dlkf;'skf;s kdf;lksd;fksd;fks;dfk;'sdlkf';sdkf;slkdf;ks;flkas'd;f k';sldfk ;sdf ';sdf'; skf';lkas'd;flk'asd;lfk';asldkf's;ldkf's;dlkf's;dlkf's;dlkf'sad;lkf';asldkf';sdlkf';sladk'g;lksf;lkgj;ldfjglkjandlkgjhalkjghlkajdglkjfglakjd;glk";

	@RequestMapping("/employee")
	public Employee getEmployee(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Employee(counter.incrementAndGet(), String.format(template, name), bytes.getBytes());
	}
	
	@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
	public void getFile(
	    @PathVariable("file_name") String fileName, 
	    HttpServletResponse response) {
	    try {
	    	InputStream inputStream = EmployeeController.class.getClassLoader().getResourceAsStream("binder.pdf");
	    	InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
	    	BufferedReader reader = new BufferedReader(streamReader);
	    	for (String line; (line = reader.readLine()) != null;) {
	    	   System.out.println(line);
	    	}
	    	
	    	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    	InputStream is = classloader.getResourceAsStream("binder.pdf");
	      
	      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	      response.setContentType("application/pdf");
	      response.flushBuffer();
	    } catch (IOException ex) {
	      //log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
	      throw new RuntimeException("IOError writing file to output stream");
	    }

	}
}
