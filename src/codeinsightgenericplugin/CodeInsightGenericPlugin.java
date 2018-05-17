package codeinsightgenericplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.flexnet.codeinsight.plugins.agent.ExecutionContext;
import com.flexnet.codeinsight.plugins.agent.ScanExecutor;

/**
 * This is a sample plugin using codeinsight-agent.
 */
public class CodeInsightGenericPlugin {

	/**
	 *  Reads the properties from generic.plugin.props file and scans the paths provided in args
	 *  @param args paths to be scanned, if none provided plugin will show a message and return
	 */
	public static void main(String[] args) {
		
		if (args == null || args.length == 0) {
			System.out.println("Please pass at least one valid path to scan");
			return;
		}

		try {
		    MercurialShell merc = new MercurialShell();	
                  
                    merc.shell(args[0], args[1]);
                    System.out.println(merc.output);
                    
                  
                    
                    
                    Properties p = getProps();

			File logDir = new File("./logs");
			logDir.mkdirs();

			File logger = new File(logDir, "agent.log");
			logger.createNewFile();
			/* Set flx.agent.root system property if you want customize root directory 
			/* other than user.home */ 
			System.setProperty("flx.agent.root", System.getProperty("user.dir"));
			System.out.println("Logging messages under .logs/agent.log");
			
			PrintStream fp = new PrintStream(logger);
			ExecutionContext ec = ExecutionContext.getInstance(p, fp);
                        
                        
			
			ScanExecutor executor = new ScanExecutor(ec);
                        
                        
                        List<String> paths = Arrays.asList(args[0]);
			
			fp.println("testConnection :::"+executor.testConnection());
			//fp.println("scan results :::"+executor.scanCodebase(paths));
                        
                        
                        fp.println("scan results :::"+executor.scanCodebase(paths));
                        
                        
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Properties getProps() throws Exception {
		Properties p = new Properties();
		File pFile = new File("generic.plugin.props");
		boolean isNew = pFile.createNewFile();
		if (isNew) {
			throw new IllegalArgumentException("Found empty properties file \"generic.plugin.props\"");
		}
		
		p.load(new FileInputStream(pFile));
		
		//required
		if (StringUtils.isBlank(p.getProperty("codeinsight.server"))) {
			throw new IllegalArgumentException("Found invalid \"codeinsight.server\" value");
		}
		//required
		if (StringUtils.isBlank(p.getProperty("codeinsight.auth.token"))) {
			throw new IllegalArgumentException("Found invalid \"codeinsight.auth.token\" value");
		}
		//required
		if (StringUtils.isBlank(p.getProperty("codeinsight.project.name"))) {
			throw new IllegalArgumentException("Found invalid \"codeinsight.project.name\" value");
		}
		//required
		if (StringUtils.isBlank(p.getProperty("plugin.root.path"))) {
			throw new IllegalArgumentException("Found invalid \"plugin.root.path\" value");
		}
		return p;
	}
}
