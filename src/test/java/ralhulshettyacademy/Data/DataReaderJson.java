package ralhulshettyacademy.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReaderJson {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//Java one method FileUtils pkg
		//readJson to string
		
	String jsonContent=	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//ralhulshettyacademy//Data//PuurchaseOrder.Json"),
			StandardCharsets.UTF_8);
	
	//convert this string to hashmap
	//jackson databind library from maven repository
	
	ObjectMapper mapper =new ObjectMapper();
	List<HashMap<String,String>> data=  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
	});
	
	return data;
	
	
	
	}

}
