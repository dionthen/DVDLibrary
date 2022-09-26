package com.dvdlibrary.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
					findDVDs();
					break;
				case 6:
					getDVD();
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

	// 1. Allow the user to add a DVD to the collection
	private void createDVD() throws DVDLibraryDaoException {
		view.displayCreateDVDBanner();
		DVD newDVD = view.getNewDVDInfo();
		dao.addDVD(newDVD.getTitle(), newDVD);
		view.displayCreateSuccessBanner();
	}

	//2. Allow the user to remove a DVD from the collection
	private void removeDVD() throws DVDLibraryDaoException {
		view.displayRemoveDVDBanner();
		String title = view.getDVDTitleChoice();
		DVD removedDvd = dao.removeDVD(title);
		view.displayRemoveResult(removedDvd);
	}
	
	//3. Allow the user to edit the information for an existing DVD in the collection
	private void editDVD() throws DVDLibraryDaoException {
		view.displayEditDVDBanner();
		String title = view.getDVDTitleChoice();
		DVD dvdToEdit = dao.getDVD(title);
		if (dvdToEdit == null) {
			view.displayNullDVD();
		} else {
			view.displayDVD(dvdToEdit);
			int editMenuSelection = 0;
			boolean keepGoing = true;
			while (keepGoing) {
				editMenuSelection = view.printEditMenuAndGetSelection();

				switch (editMenuSelection) {
				case 1:
					editReleaseDate(title);
					break;
				case 2:
					editMpaaRating(title);
					break;
				case 3:
					editDirectorName(title);
					break;
				case 4:
					editStudioName(title);
					break;
				case 5:
					editUserRating(title);
					break;
				case 6:
					keepGoing = false;
					break;
				default:
					unknownCommand();
				}
				if (keepGoing == false) {
					break;
				}
			}
		}
	}

	private void editReleaseDate(String title) throws DVDLibraryDaoException {
		LocalDate newReleaseDate = view.getReleaseDate();
		DVD editedDVD = dao.changeReleaseDate(title, newReleaseDate);
		view.displayEditResult();
	}

	private void editMpaaRating(String title) throws DVDLibraryDaoException {
		String newMpaaRating = view.getMpaaRating();
		DVD editedDVD = dao.changeMpaaRating(title, newMpaaRating);
		view.displayEditResult();
	}

	private void editDirectorName(String title) throws DVDLibraryDaoException {
		String newDirectorName = view.getDirectorName();
		DVD editedDVD = dao.changeDirectorName(title, newDirectorName);
		view.displayEditResult();
	}

	private void editStudioName(String title) throws DVDLibraryDaoException {
		String newStudioName = view.getStudioName();
		DVD editedDVD = dao.changeStudioName(title, newStudioName);
		view.displayEditResult();
	}

	private void editUserRating(String title) throws DVDLibraryDaoException {
		String newUserRating = view.getUserRating();
		DVD editedDVD = dao.changeUserRating(title, newUserRating);
		view.displayEditResult();
	}
	
	//4. Allow the user to list the DVDs in the collection
	private void listDVDs() throws DVDLibraryDaoException {
		view.displayDVDListBanner();
		List<DVD> dvdList = dao.getAllDVDs();
		view.displayDVDList(dvdList);
	}

	//5. Allow the user to display the information for a particular DVD
	private void findDVDs() throws DVDLibraryDaoException {
		view.displayFindDVDsBanner();
		int findDVDsSelection = 0;
		boolean keepGoing = true;
		while (keepGoing) {
			findDVDsSelection = view.printFindMenuAndGetSelection();
			switch (findDVDsSelection) {
			case 1:
				findMoviesLastNYears();
				break;
			case 2:
				findMoviesByMpaaRating();
				break;
			case 3:
				findMoviesByDirector();
				break;
			case 4:
				findMoviesByStudio();
				break;
			case 5:
				keepGoing = false;
				break;
			default:
				unknownCommand();
			}
		}
	}

	private void findMoviesLastNYears() throws DVDLibraryDaoException {
		int n = view.getNYears();
		Map<String, DVD> filteredDVDs = dao.getDvdsLastYears(n);
		view.displayDVDs(filteredDVDs);
	}

	private void findMoviesByMpaaRating() throws DVDLibraryDaoException {
		String mpaaRating = view.getMpaaRating();
		Map<String, DVD> filteredDVDs = dao.getDvdsByMpaaRating(mpaaRating);
		view.displayDVDs(filteredDVDs);
	}

	private void findMoviesByDirector() throws DVDLibraryDaoException {
		String director = view.getDirectorName();
		Map<String, DVD> filteredDVDs = dao.getDvdsByDirector(director);
		view.displayDVDs(filteredDVDs);
	}

	private void findMoviesByStudio() throws DVDLibraryDaoException {
		String studio = view.getStudioName();
		Map<String, DVD> filteredDVDs = dao.getDvdsByStudio(studio);
		view.displayDVDs(filteredDVDs);
	}
	
	//6. Allow the user to search for a DVD by title
	private void getDVD() throws DVDLibraryDaoException {
		view.displayDisplayDVDBanner();
		String dvdTitle = view.getDVDTitleChoice();
		DVD dvd = dao.getDVD(dvdTitle);
		view.displayDVD(dvd);
	}

	private void exitMessage() {
		view.displayExitBanner();
	}

	private void unknownCommand() {
		view.displayUnknownCommandBanner();
	}
}
