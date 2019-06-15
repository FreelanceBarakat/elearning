package com.ldap.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.util.ResourceUtils;

import com.ldap.repository.models.Person;

public class LdifUpdater {
	final String fileName = "schema.ldif";
	String entry = "\ndn: uid= {uid},ou=people,dc=ldap,dc=com\n" + 
			"objectclass: top\n" + 
			"objectclass: person\n" + 
			"objectclass: organizationalPerson\n" + 
			"objectclass: inetOrgPerson\n" + 
			"cn: {cn}\n" + 
			"uid: {uid}\n" + 
			"userPassword: {userPassword}\n";
	
	public void save(Person p) throws IOException, URISyntaxException {
		entry = entry.replace("{uid}", p.getEmail());
		entry = entry.replace("{cn}", p.getName());
		entry = entry.replace("{userPassword}", p.getUserPassword());
		appendToFile();
	}
	
	public void appendToFile() throws IOException, URISyntaxException {
		
			 
		File file = new File(fileName);
		boolean x = file.exists();
		FileWriter fileWriter = new FileWriter (file.getAbsoluteFile(), true);
		BufferedWriter out = new BufferedWriter(fileWriter);
		out.write(entry);
		
		out.close();
		fileWriter.close();
		
	}
}
