package com.bmastudio;

import java.util.List;
import java.util.Set;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import com.bmastudio.API.Services.data.SimilarWord;
import com.bmastudio.BMASimilar.Services.CompareNIST;
import com.bmastudio.BMASimilar.Services.SubItemGenerateThread;
import com.bmastudio.dotbrandtools.dataview.ItemView;

@Component
public class HelloMe implements CommandMarker {
 
	@CliAvailabilityIndicator({"compare word"})
	public boolean isCallMeAvailable() {
		return true;
	}
		
	@CliCommand(value = "compare word", help = "Print a HELLO me")
	public String callMe(
		@CliOption(key = { "message" }, mandatory = false, help = "List of word") final String message,
		@CliOption(key = { "location" }, mandatory = false, help = "Location of file", specifiedDefaultValue="Default location") final String location) 
	{	
		
		ItemView item = new ItemView();
		item.setName("vuhoai");
		item.setDescription("Toi la nam");
		
		SubItemGenerateThread thread = new SubItemGenerateThread();
		
		thread.setItem(item);
		
		thread.setTypos(true);
	
		thread.setSimilar70(true);
	
		thread.setSimilar85(true);
		
		thread.start();
		System.out.println("RUN GENERATE " + item.getName());
		
		return "HHEHEH";
	}
	
}
