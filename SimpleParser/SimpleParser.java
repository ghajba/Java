import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class SimpleParser
{
	public static void main(String... args)
	{
		if(args.length != 1) return;
		
		String fileName = args[0];
		List<String> lines = new ArrayList<String>();
		BufferedWriter bufferedWriter = null;
		BufferedReader bufferedReader = null;
		try
		{
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			bufferedWriter = new BufferedWriter(new FileWriter(new File("output.txt")));
			
			String line;
			
			while((line = bufferedReader.readLine()) != null)
			{
				if(line.startsWith("04"))
				{
					// get the values, calculate and replace everything
					// and write everything out
					String[] splitted = line.split("\\+");
					lines.add(0,lines.remove(0).replaceFirst("0000000000000",splitted[6]));
					BigDecimal gross = new BigDecimal(splitted[6]);
					gross = gross.divide(new BigDecimal(100),2,RoundingMode.HALF_UP);
					BigDecimal tax = new BigDecimal(splitted[6]);
					tax = tax.multiply(new BigDecimal(19));
					tax = tax.divide(new BigDecimal(10000),2,RoundingMode.HALF_UP);
					gross = gross.add(tax);
					splitted[6] = "0000000000000";
					
					splitted[7] = splitted[7].replace("0000000000000",String.format("%1$013d",new Integer(tax.toString().replace(".",""))));
					splitted[2] = String.format("%1$013d",new Integer(gross.toString().replace(".","")));;
					StringBuffer builder = new StringBuffer();
					for(int i = 0; i < 7; i++)
					{
						builder.append(splitted[i]);
						builder.append("+");
					}
					builder = builder.append(splitted[7]);
					
					for(String str : lines)
					{
						bufferedWriter.write(str);
						bufferedWriter.newLine();
					}
					bufferedWriter.write(builder.toString());
					bufferedWriter.newLine();
					lines.clear();
					bufferedWriter.flush();
					continue;
				}
				lines.add(line);
			}
		}
		catch (FileNotFoundException e) {
		  e.printStackTrace();
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally
		{
			try
			{
				if(bufferedWriter != null)
				{
					bufferedWriter.flush();
					bufferedWriter.close();
				}
				if(bufferedReader != null)
				{
					bufferedReader.close();
				}
			}
			catch(Exception e)
			{
				// ...
			}
		}
	}
}