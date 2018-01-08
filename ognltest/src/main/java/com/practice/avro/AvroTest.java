package com.practice.avro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

public class AvroTest {

	@Test
	public void test() throws IOException {

		User user = new User();
		user.setName("ssa");
		user.setFavoriteNumber(123);
		user.setFavoriteColor("blue");
		DatumWriter<User> writer = new SpecificDatumWriter<>(User.class);
		DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(writer);
		dataFileWriter.create(user.getSchema(), new File("users.avro"));
		dataFileWriter.append(user);
		dataFileWriter.close();

		DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
		DataFileReader<User> dataFileReader = new DataFileReader<User>(new File("users.avro"), userDatumReader);
		User user1 = null;
		while (dataFileReader.hasNext()) {
			// Reuse user object by passing it to next(). This saves us from
			// allocating and garbage collecting many objects for files with
			// many items.
			user1 = dataFileReader.next(user1);
			System.out.println(user1);
		}
		
		dataFileReader.close();

	}

}
