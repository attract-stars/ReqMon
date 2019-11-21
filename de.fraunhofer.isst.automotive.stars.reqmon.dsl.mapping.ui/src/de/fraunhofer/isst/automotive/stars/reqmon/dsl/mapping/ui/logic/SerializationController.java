package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.SaveModel;

/**
 * This class saves or loads a given SaveModel. As save location it uses the filename that must be set.
 * 
 * @author sgraf
 *
 */
public class SerializationController {
	
	private static final SerializationController instance = new SerializationController();
	private String filename;
	
	/**
	 * Returns the instance of the SerializationController class.
	 * @return the instance of the SerializationController class.
	 */
	public static SerializationController getInstance() {
		return instance;
	}

	/**
	 * Returns the filename which is used for the serialization.
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Returns the absolute path of the serialization file.
	 * @return the absolute serialization file path
	 */
	public String getAbsolutSerFilePath() {
		if (filename == null) {
			return "No filename exists!";
		}
		return new File(filename).getAbsolutePath();
	}

	/**
	 * Sets the filename for the serialization. It replaces the filename extension with ".ser". 
	 * @param filename the name for the serialization
	 */
	public void setFilename(String filename) {
		if (filename == null) {
			System.out.println("The filename is null!");
			this.filename = null;
			return;
		}
		
		String[] name = filename.split(Pattern.quote("."));
		if (name.length != 0) {
			this.filename = name[0] + ".ser";
		}
	}
	
	/**
	 * Serializes the content of the given SaveModel.
	 * @param model the model that shall be serialized
	 */
	public void save(SaveModel model) {
		// Serialization
		FileOutputStream fileStream = null;
		ObjectOutputStream out = null;
		
		try {
			if (filename == null) {
				System.out.println("No filename exists! Can not save model!");
				return;
			}
			
			fileStream = new FileOutputStream(new File(filename));
			System.out.println("Save model as " + filename);
			
			//System.out.println("syspath: " + model.getSysPath());
			
			out = new ObjectOutputStream(fileStream);
			
			out.writeObject(model);
			
			out.close();
			fileStream.close();
			
			System.out.println("Model has been serialized");
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the saved data and returns it in form of a SaveModel.
	 * @return the loaded data as SaveModel
	 */
	public SaveModel load() {
		SaveModel saveModel = null;
		FileInputStream fileStream = null;
		ObjectInputStream in = null;
		
		// Deserialization
		try {
			if (filename == null) {
				System.out.println("No saved model exists (filename is null)!");
				return null;
			}
			
			File file = new File(filename);
			fileStream = new FileInputStream(file);
			System.out.println("File path: " + file.getAbsolutePath());
			
			in = new ObjectInputStream(fileStream);
			
			saveModel = (SaveModel)in.readObject();
			
			in.close();
			fileStream.close();
			
		//	System.out.println("Model has been deserialized");
		//	System.out.println("Syspath = " + saveModel.getSysPath());
		//	System.out.println("ReqPath = " + saveModel.getReqPath());
		/*	if (saveModel.getEditorContentList() != null) {
				for(String content : saveModel.getEditorContentList()) {
					System.out.println("Editor content: " + content);
				}
			}
			if (saveModel.getReqList() != null) {
				for(IRequirementElement req : saveModel.getReqList()) {
					System.out.println("ReqName: " + req.getElementName());
					//System.out.println("ReqType: " + req.getElementType());
				}
			}
			System.out.println("Generator: " + saveModel.getGenLabel() + ", index: " 
					+ saveModel.getGeneratorIndex());
			
		*/	
		} catch (FileNotFoundException e) {
			System.out.println("No saved model!");
			//e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return saveModel;
	}
	

	

}
