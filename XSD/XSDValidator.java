package biz.hahamo.wordpress;

import static java.lang.System.out;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xml.sax.SAXException;

/**
 * Simple XSD Validator which takes arguments from the console at startup (XSDs and XMLs)
 * and validates the XMLs against the XSDs
 *
 * @author Gabor Laszlo Hajba
 */
public class XSDValidator
{
    private static Source[] xmlSources = new Source[]{};
    private static Source[] xsdSources = new Source[]{};

    private static void printUsage()
    {
        out.println("Usage of the application: ");
        out.println("java XSDValidator <list of XML files> <list of XSD files>");
        out.println("XML files should end with .xml XSD files should end with .xsd");
        System.exit(-1);
    }

    private static void getArgumentFiles(List<String> args)
    {
        List<Source> xmls = new ArrayList<Source>(args.size()/2);
        List<Source> xsds = new ArrayList<Source>(args.size()/2);

        for(String arg : args)
        {
            if(arg.endsWith(".xml"))
            {
                xmls.add(new StreamSource(new File(arg)));
            }
            else if(arg.endsWith(".xsd"))
            {
                xsds.add(new StreamSource(new File(arg)));
            }
            else
            {
                out.println(arg + " is not an XML or XSD file.");
            }
        }

        xmlSources = xmls.toArray(xmlSources);
        xsdSources = xsds.toArray(xsdSources);
        if(xmlSources.length < 1 || xsdSources.length < 1)
        {
            out.println("Please provide at least one .xml file and one .xsd file");
            System.exit(-2);
        }
    }

    public static void main(String... args)
    {
        List<String> argsList = Arrays.asList(args);

        if(argsList.size() < 2)
        {
            out.println("Too few parameters provided");
            printUsage();
        }

        getArgumentFiles(argsList);

        try {
            //Source xmlFile = new StreamSource(xmlSources);
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdSources);
            Validator validator = schema.newValidator();

            for(Source xmlFile : xmlSources)
            {
                try{
                    validator.validate(xmlFile);
                    System.out.println(xmlFile.getSystemId() + " is valid");
                }
                catch (SAXException e)
                {
                    System.out.println(xmlFile.getSystemId() + " is invalid");
                    System.out.println("\tReason: " + e.getLocalizedMessage());
                }
                catch(IOException e)
                {
                    System.out.println(xmlFile.getSystemId() + " is invalid");
                    System.out.println("\tReason: " + e.getLocalizedMessage());
                }
            }
        }
        catch (SAXException e)
        {
            System.out.println("Schema creation failed:: " + e.getLocalizedMessage());
        }
    }
}