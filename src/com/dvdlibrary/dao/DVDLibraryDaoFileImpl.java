package com.dvdlibrary.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.dvdlibrary.dto.DVD;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
	public static final String DVD_COLLECTION = "dvdcollection.txt";
	public static final String DELIMITER = "::";
	private Map<String, DVD> dvds = new HashMap<>();

	@Override // 1. Allow the user to add a DVD to the collection
	public DVD addDVD(String dvdID, DVD dvd) throws DVDLibraryDaoException {
		loadDVDCollection();
		DVD newDVD = dvds.put(dvdID, dvd);
		writeDVDCollection();
		return newDVD;
	}

	@Override // 2. Allow the user to remove a DVD from the collection
	public DVD removeDVD(String dvdID) throws DVDLibraryDaoException {
		loadDVDCollection();
		DVD removedDVD = dvds.remove(dvdID);
		writeDVDCollection();
		return removedDVD;
	}

	// 3. Allow the user to edit the information for an existing DVD in the
	// collection
	@Override
	public DVD changeReleaseDate(String title, LocalDate releaseDate) throws DVDLibraryDaoException {
		loadDVDCollection();
		DVD dvdToEdit = dvds.get(title);
		dvdToEdit.setReleaseDate(releaseDate);
		writeDVDCollection();
		return dvdToEdit;
	}

	@Override
	public DVD changeMpaaRating(String title, String mpaaRating) throws DVDLibraryDaoException {
		loadDVDCollection();
		DVD dvdToEdit = dvds.get(title);
		dvdToEdit.setMpaaRating(mpaaRating);
		writeDVDCollection();
		return dvdToEdit;
	}

	@Override
	public DVD changeDirectorName(String title, String directorName) throws DVDLibraryDaoException {
		loadDVDCollection();
		DVD dvdToEdit = dvds.get(title);
		dvdToEdit.setDirectorName(directorName);
		writeDVDCollection();
		return dvdToEdit;
	}

	@Override
	public DVD changeStudioName(String title, String studioName) throws DVDLibraryDaoException {
		loadDVDCollection();
		DVD dvdToEdit = dvds.get(title);
		dvdToEdit.setStudio(studioName);
		writeDVDCollection();
		return dvdToEdit;
	}

	@Override
	public DVD changeUserRating(String title, String userRating) throws DVDLibraryDaoException {
		loadDVDCollection();
		DVD dvdToEdit = dvds.get(title);
		dvdToEdit.setUserRating(userRating);
		writeDVDCollection();
		return dvdToEdit;
	}

	@Override // 4. Allow the user to list the DVDs in the collection
	public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
		loadDVDCollection();
		return new ArrayList<DVD>(dvds.values());
	}

	// 5. Allow the user to display the information for a particular DVD
	@Override
	public Map<String, DVD> getDvdsLastYears(int years) throws DVDLibraryDaoException {
		LocalDate now = LocalDate.now();
		LocalDate sinceThisDate = now.minusYears(years);
		loadDVDCollection();
		Map<String, DVD> dvdsLastYears = dvds.entrySet().stream()
				.filter((dvd) -> dvd.getValue().getReleaseDate().isAfter(sinceThisDate))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return dvdsLastYears;
	}

	@Override
	public Map<String, DVD> getDvdsByMpaaRating(String mpaaRating) throws DVDLibraryDaoException {
		loadDVDCollection();
		Map<String, DVD> dvdsMpaRating = dvds.entrySet().stream()
				.filter((dvd) -> dvd.getValue().getMpaaRating().equalsIgnoreCase(mpaaRating))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return dvdsMpaRating;
	}

	@Override
	public Map<String, DVD> getDvdsByDirector(String directorName) throws DVDLibraryDaoException {
		loadDVDCollection();
		Map<String, DVD> dvdsByDirector = dvds.entrySet().stream()
				.filter((dvd) -> dvd.getValue().getDirectorName().equalsIgnoreCase(directorName))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return dvdsByDirector;
	}

	@Override
	public Map<String, DVD> getDvdsByStudio(String studioName) throws DVDLibraryDaoException {
		loadDVDCollection();
		Map<String, DVD> dvdsByStudio = dvds.entrySet().stream()
				.filter((dvd) -> dvd.getValue().getStudio().equalsIgnoreCase(studioName))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return dvdsByStudio;
	}

	@Override // 6. Allow the user to search for a DVD by title
	public DVD getDVD(String title) throws DVDLibraryDaoException {
		loadDVDCollection();
		return dvds.get(title);
	}

	//Marshalling
	private String marshallDVD(DVD aDVD) {
		String dvdAsText = aDVD.getTitle() + DELIMITER;
		dvdAsText += aDVD.getReleaseDate() + DELIMITER;
		dvdAsText += aDVD.getMpaaRating() + DELIMITER;
		dvdAsText += aDVD.getDirectorName() + DELIMITER;
		dvdAsText += aDVD.getStudio() + DELIMITER;
		dvdAsText += aDVD.getUserRating();
		return dvdAsText;
	}
	
	//Unmarshalling
	private DVD unmarshallDVD(String dvdAsText) {
		String [] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        String releaseDate = dvdTokens[1];
        String mpaaRating = dvdTokens[2];
        String directorName = dvdTokens[3];
        String studio = dvdTokens[4];
        String userRating = dvdTokens[5];
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setReleaseDate(LocalDate.parse(releaseDate));
        dvdFromFile.setMpaaRating(mpaaRating);
        dvdFromFile.setDirectorName(directorName);
        dvdFromFile.setUserRating(userRating);
        dvdFromFile.setStudio(studio);
        return dvdFromFile;
	}

	//Loading
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
			dvds.put(currentDVD.getTitle(), currentDVD);
		}
		scanner.close();
	}

	//Writing
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
