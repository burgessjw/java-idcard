package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(),
              String.format(template, name));
  }

  @RequestMapping("/idcard")
  public String idcard(@RequestParam(value="region", defaultValue="110101") String region) throws ParseException {
  	String address = "110101";

  	Random r = new Random();
  	int year = r.nextInt(100) + 1900;

    Date start = new Date(year-1900,0,1);
    Date end = new Date(year-1900,11,31);
    int days = daysBetween(start, end);

	Calendar c = Calendar.getInstance(); 
	c.setTime(start); 
	c.add(Calendar.DATE, r.nextInt(days));
  	SimpleDateFormat sdf = new SimpleDateFormat("yMMdd");  
	String birthDate = sdf.format(c.getTime());

	String order = String.format("%-3s", String.valueOf(r.nextInt(999))).replace(' ', '0');

	String idCardNoWithoutChecksum = address + birthDate + order;

	int weight[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
	String marks[] = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

	int sum = 0;
	for (int j = 0; j<17; j++) {

		int i = integers[j];

		System.out.println(i);

	}

    return idCardNoWithoutChecksum;
  }

  public static int daysBetween(Date start, Date end) throws ParseException
  {
	Calendar cal = Calendar.getInstance();
    cal.setTime(start);
    long date1 = cal.getTimeInMillis();
    cal.setTime(end);
    long date2 = cal.getTimeInMillis();      
    long days=(date2-date1)/(1000*3600*24);  
    return Integer.parseInt(String.valueOf(days));
  }
}
