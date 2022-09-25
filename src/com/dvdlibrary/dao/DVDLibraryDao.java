package com.dvdlibrary.dao;

import java.util.List;

import com.dvdlibrary.dto.DVD;

public interface DVDLibraryDao {

	//1. Allow the user to add a DVD to the collection
    DVD addDVD(String dvdID, DVD dvd) throws DVDLibraryDaoException;
    
    //2. Allow the user to remove a DVD from the collection
    DVD removeDVD(String dvdID) throws DVDLibraryDaoException;

    //3. Allow the user to edit the information for an existing DVD in the collection
    DVD editDVD(String dvdID, DVD dvd) throws DVDLibraryDaoException;

    //4. Allow the user to list the DVDs in the collection
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;
    
    //5. Allow the user to display the information for a particular DVD
    DVD getDVD(String dvdID) throws DVDLibraryDaoException;
    
    //6. Allow the user to search for a DVD by title
    DVD getDVDByTitle(String title) throws DVDLibraryDaoException;
    
    

}
