package Data;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.fileoutput.FileOutputContext;

public class DefaultOutPutDataMy implements FileOutputContext {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_SEPARATOR = " " ;

	  protected String fileName;
	  protected String separator;

	  public DefaultOutPutDataMy(String fileName) {
	    this.fileName = fileName ;
	    this.separator = DEFAULT_SEPARATOR ;
	  }

	  public BufferedWriter getFileWriter() {
	    FileOutputStream outputStream ;
	    try {
	      outputStream = new FileOutputStream(fileName);
	    } catch (FileNotFoundException e) {
	      throw new JMetalException("Exception when calling method getFileWriter()", e) ;
	    }
	    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

	    return new BufferedWriter(outputStreamWriter);
	  }

	  public String getSeparator() {
	    return separator;
	  }

	  public void setSeparator(String separator) {
	    this.separator = separator;
	  }

	  public String getFileName() {
	    return fileName ;
	  }

}
