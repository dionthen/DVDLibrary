package com.dvdlibrary.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.dvdlibrary.dto.DVD;

public interface DVDLibraryDao {

	// 1. Allow the user to add a DVD to the collection
	DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;

	// 2. Allow the user to remove a DVD from the collection
	DVD removeDVD(String title) throws DVDLibraryDaoException;

	// 3. Allow the user to edit the information for an existing DVD in the collection
	DVD changeReleaseDate(String title, LocalDate releaseDate) throws DVDLibraryDaoException;

	DVD changeMpaaRating(String title, String mpaaRating) throws DVDLibraryDaoException;

	DVD changeDirectorName(String title, String directorName) throws DVDLibraryDaoException;

	DVD changeStudioName(String title, String studioName) throws DVDLibraryDaoException;

	DVD changeUserRating(String title, String userRating) throws DVDLibraryDaoException;

	// 4. Allow the user to list the DVDs in the collection
	List<DVD> getAllDVDs() throws DVDLibraryDaoException;

	// 5. Allow the user to display the information for a particular DVD
	Map<String, DVD> getDvdsLastYears(int years) throws DVDLibraryDaoException;

	Map<String, DVD> getDvdsByMpaaRating(String mpaaRating) throws DVDLibraryDaoException;

	Map<String, DVD> getDvdsByDirector(String directorName) throws DVDLibraryDaoException;

	Map<String, DVD> getDvdsByStudio(String studioName) throws DVDLibraryDaoException;

	// 6. Allow the user to search for a DVD by title
	DVD getDVD(String title) throws DVDLibraryDaoException;

}
