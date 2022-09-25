package com.dvdlibrary.controller;

import java.util.List;

import com.dvdlibrary.dao.DVDLibraryDao;
import com.dvdlibrary.dao.DVDLibraryDaoException;
import com.dvdlibrary.dto.DVD;
import com.dvdlibrary.ui.DVDLibraryView;

public class DVDLibraryController {
	private DVDLibraryDao dao;
	private DVDLibraryView view;
	
	public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
		this.dao = dao;
		this.view = view;
	}

	public void run() {
		boolean keepGoing = true;
		int menuSelection = 0;
		try {
			while (keepGoing) {

				menuSelection = getMenuSelection();

				switch (menuSelection) {
				case 1:
					createDVD();
					break;
				case 2:
					removeDVD();
					break;
				case 3:
					editDVD();
					break;
				case 4:
					listDVDs();
					break;
				case 5:
					viewDVD();
					break;
				case 6:
					searchDVDByTitle();
					break;
				case 7:
					keepGoing = false;
					break;
				default:
					unknownCommand();
				}

			}
			exitMessage();
		} catch (DVDLibraryDaoException e) {
			view.displayErrorMessage(e.getMessage());
		}
	}

	private int getMenuSelection() {
		return view.printMenuAndGetSelection();
	}

	//1. Allow the user to add a DVD to the collection
	private void createDVD() throws DVDLibraryDaoException {
		view.displayCreateDVDBanner();
		DVD newDVD = view.getNewDVDInfo();
		dao.addDVD(newDVD.getDvdID(), newDVD);
		view.displayCreateSuccessBanner();
	}
	
    //2. Allow the user to remove a DVD from the collection
	private void removeDVD() throws DVDLibraryDaoException {
		view.displayRemoveDVDBanner();
		String dvdID = view.getDVDIDChoice();
		DVD removedDVD = dao.removeDVD(dvdID);
		view.displayRemoveResult(removedDVD);
	}

    //3. Allow the user to edit the information for an existing DVD in the collection
	private void editDVD() throws DVDLibraryDaoException {
		view.displayEditDVDBanner();
		String dvdID = view.getDVDIDChoice();
		DVD dvd = dao.getDVD(dvdID);
		DVD edittedDVD = view.getEdittedDVDInformation(dvd);
		DVD editted = dao.editDVD(dvdID, edittedDVD);
		view.displayEditResult(dvd);
	}
	
    //4. Allow the user to list the DVDs in the collection
	private void listDVDs() throws DVDLibraryDaoException {
		view.displayDisplayAllBanner();
		List<DVD> dvdList = dao.getAllDVDs();
		view.displayDVDList(dvdList);
	}

    //5. Allow the user to display the information for a particular DVD
	private void viewDVD() throws DVDLibraryDaoException {
		view.displayDisplayDVDBanner();
		String dvdID = view.getDVDIDChoice();
		DVD dvd = dao.getDVD(dvdID);
		view.displayDVD(dvd);
	}	

    //6. Allow the user to search for a DVD by title
	private void searchDVDByTitle() throws DVDLibraryDaoException{
		view.displayFindDVDByTitleBanner();
		String title = view.getDVDTitleChoice();
		DVD dvd = dao.getDVDByTitle(title);
		view.displayDVD(dvd);
	}

	private void unknownCommand() {
		view.displayUnknownCommandBanner();
	}

	private void exitMessage() {
		view.displayExitBanner();
	}
	
	
}
