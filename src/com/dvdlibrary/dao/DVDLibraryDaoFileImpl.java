package com.dvdlibrary.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.dvdlibrary.dto.DVD;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
	public static final String DVD_COLLECTION = "dvdcollection.txt";
	public static final String DELIMITER = "::";
	private Map<String, DVD> dvds = new HashMap<>();
	
	@Override //1. Allow the user to add a DVD to the collection
	public DVD addDVD(String dvdID, DVD dvd) throws DVDLibraryDaoException {
	    loadDVDCollection();
	    DVD newDVD = dvds.put(dvdID, dvd);
	    writeDVDCollection();
	    return newDVD;
	}
	
	@Override //2. Allow the user to remove a DVD from the collection
	public DVD removeDVD(String dvdID) throws DVDLibraryDaoException {
		loadDVDCollection();
	    DVD removedDVD = dvds.remove(dvdID);
	    writeDVDCollection();
	    return removedDVD;
	}
	
	@Override //3. Allow the user to edit the information for an existing DVD in the collection
	public DVD editDVD(String dvdID, DVD dvd) throws DVDLibraryDaoException {
		loadDVDCollection();
	    DVD edittedDVD = dvds.replace(dvdID, dvd);
	    writeDVDCollection();
	    return edittedDVD;
	}

	@Override //4. Allow the user to list the DVDs in the collection
	public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
		loadDVDCollection();
	    return new ArrayList<DVD>(dvds.values());
	}

	@Override //5. Allow the user to display the information for a particular DVD
	public DVD getDVD(String dvdID) throws DVDLibraryDaoException {
		loadDVDCollection();
	    return dvds.get(dvdID);
	}
	
	@Override //6. Allow the user to search for a DVD by title
	public DVD getDVDByTitle(String title) throws DVDLibraryDaoException{
		loadDVDCollection();
		List<DVD> dvdList = new ArrayList<DVD>(dvds.values());
		for(DVD dvd : dvdList) {
			if(dvd.getTitle().equals(title)) {
				return dvd;
			}
		}
	    return null;
	}
	
	private DVD unmarshallDVD(String dvdAsText) {
	    String[] dvdTokens = dvdAsText.split(DELIMITER);
	    String dvdID = dvdTokens[0];
	    DVD dvdFromFile = new DVD(dvdID);
	    dvdFromFile.setTitle(dvdTokens[1]);
	    dvdFromFile.setReleaseDate(dvdTokens[2]);
	    dvdFromFile.setMpaaRating(dvdTokens[3]);
	    dvdFromFile.setDirectorName(dvdTokens[4]);
	    dvdFromFile.setStudio(dvdTokens[5]);
	    dvdFromFile.setUserNote(dvdTokens[6]);
	    return dvdFromFile;
	}
	
	private void loadDVDCollection() throws DVDLibraryDaoException {
	    Scanner scanner;

	    try {
	        scanner = new Scanner(new BufferedReader(new FileReader(DVD_COLLECTION)));
	    } catch (FileNotFoundException e) {
	        throw new DVDLibraryDaoException("-_- Could not load DVD collection data into memory.", e);
	    }
	    String currentLine;
	    DVD currentDVD;
	    while (scanner.hasNextLine()) {
	        currentLine = scanner.nextLine();
	        currentDVD = unmarshallDVD(currentLine);
	        dvds.put(currentDVD.getDvdID(), currentDVD);
	    }
	    scanner.close();
	}
	
	private String marshallDVD(DVD aDVD){
	    String dvdAsText = aDVD.getDvdID() + DELIMITER;
	    dvdAsText += aDVD.getTitle() + DELIMITER;
	    dvdAsText += aDVD.getReleaseDate() + DELIMITER;
	    dvdAsText += aDVD.getMpaaRating() + DELIMITER;
	    dvdAsText += aDVD.getDirectorName() + DELIMITER;
	    dvdAsText += aDVD.getStudio() + DELIMITER;
	    dvdAsText += aDVD.getUserNote();
	    return dvdAsText;
	}
	
	private void writeDVDCollection() throws DVDLibraryDaoException {
	    PrintWriter out;
	    try {
	        out = new PrintWriter(new FileWriter(DVD_COLLECTION));
	    } catch (IOException e) {
	        throw new DVDLibraryDaoException("Could not save DVD data.", e);
	    }
	    String dvdAsText;
	    List<DVD> dvdList = this.getAllDVDs();
	    for (DVD currentDVD : dvdList) {
	        dvdAsText = marshallDVD(currentDVD);
	        out.println(dvdAsText);
	        out.flush();
	    }
	    out.close();
	}
}
