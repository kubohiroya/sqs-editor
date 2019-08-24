package net.sqs2.editor.sqs.swing;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sqs2.net.ClassURLStreamHandlerFactory;
import net.sqs2.translator.facade.SQS2PDF;

public class SourceEditor {

	public static final String TITLE = "SQS SourceEditor2.1";

	public static void main(String[] args) throws Exception {
		
		System.setProperty("file.encoding", "UTF-8");
		URL.setURLStreamHandlerFactory(ClassURLStreamHandlerFactory.getSingleton());

		String longOpt = "--" + SQS2PDF.COMMAND_LINE_OPTION_OF_NO_GUI.getLongOpt(); 
		String opt = "-" + SQS2PDF.COMMAND_LINE_OPTION_OF_NO_GUI.getOpt(); 
		
		if (args.length == 0) {
			new SQSSourceEditorMediator();
		} else {
			List<File> fileList = new ArrayList<File>();
			List<URI> uriList = new ArrayList<URI>();
			for(String arg: args){
				if(longOpt.equals(arg) || opt.equals(arg)){
					SQS2PDF.main(args);
					return;
				}else{
					try {
						URL url = new URL(arg);
						uriList.add(url.toURI());
					} catch (MalformedURLException ignore) {
						fileList.add(new File(arg));
					}
				}
			}
			
			new SQSSourceEditorMediator(uriList, fileList);
		}
	}
}
