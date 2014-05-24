package org.csm.providers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The GsonMessageProvier class implements the MessageBodyWriter and MessageBodyReader
 * methods necessary to act as the interface between the Jersey Framework and the GSON
 * serialization/de-serialization library.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class GsonMessageProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

	// The class has one public Gson object for all class methods
	private Gson gson;

	/**
	 * Class constructor instantiates the Gson member object.
	 *
	 * The constructor simply instantiates a GsonBuilder object, that in turns, 
	 * instantiates a Gson object that will look for the @Expose annotation in all
	 * data model objects so that the data model objects can contain private member
	 * variables that will not be exposed to the RESTful API.
	 */
	public GsonMessageProvider() {
		
	}

	/**
	 * Implementation for MessageBodyReader
	 */
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO: Improve handling
		return true;
	}


	/**
	 * Read in a JSON string and have Gson attempt to return the appropriate Java object
	 */
	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {

		/*
		 * For reading, we don't need to take out the private field, and just read as
		 * it is.
		 */

		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();

		InputStreamReader inputStreamReader = new InputStreamReader(entityStream, "UTF-8");

		try {
			Type jsonType;

			/* Set the appropriate type for gson to return: if the type already
			 * equals the generic type, then we'll just use that as the jsonType,
			 * otherwise, use the genericType object as the jsonType */
			if (type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}

			// return the Java object from gson
			return gson.fromJson(inputStreamReader, jsonType);
		} finally {
			// always close the input stream reader object
			inputStreamReader.close();
		}
	}

	/**
	 * Implemented for MessageBodyWriter
	 */
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO: Improve handling
		return true;
	}

	/**
	 * Implemented for MessageBodyReader
	 */
	@Override
	public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO: Improve handling
		return -1;
	}

	/**
	 * 
	 */
	@Override
	public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {

		/* We are instantiating a Gson object using the GsonBuilder so that we
		 * can configure it so that it excludes fields without the @Expose
		 * annotation during serialization. This will allow us additional
		 * control over what object properties are exposed to the RESTful API
		 */

		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(entityStream, "UTF-8");

		try {

			Type jsonType;

			/* Set the appropriate type for gson to return: if the type already
			 * equals the generic type, then we'll just use that as the jsonType,
			 * otherwise, use the genericType object as the jsonType */
			if (type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}

			gson.toJson(object, jsonType, outputStreamWriter);
		} finally {

			// always close the output stream writer
			outputStreamWriter.close();
		}
	}
}
