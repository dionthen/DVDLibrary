package com.dvdlibrary.ui;

import java.util.List;

import com.dvdlibrary.dto.DVD;

public class DVDLibraryView {
	private UserIO io;

	public DVDLibraryView(UserIO io) {
		this.io = io;
	}

	// Start of Menu code
	public int printMenuAndGetSelection() {
		io.print("Main Menu");
		io.print("1. Add New DVD to the Collection");
		io.print("2. Remove a DVD from the Collection");
		io.print("3. Edit Information for a DVD in the Collection");
		io.print("4. List DVDs in the Collection");
		io.print("5. Display the information for a DVD in the Collection");
		io.print("6. Search for a DVD by Title");
		io.print("7. Exit");
		return io.readInt("Please select from the above choices.", 1, 7);
	}
	// End of Menu code

	// Start of 1. Allow the user to add a DVD to the collection
	public DVD getNewDVDInfo() {
		String dvdID = io.readString("Please enter DVD ID");
		String title = io.readString("Please enter Title");
		String releaseDate = io.readString("Please enter Release Date");
		String mpaaRating = io.readString("Please enter MPAA Rating");
		String directorName = io.readString("Please enter Director's Name");
		String studio = io.readString("Please enter Studio");
		String userNote = io.readString("Please enter User's Rating or Note");
		DVD currentDVD = new DVD(dvdID);
		currentDVD.setTitle(title);
		currentDVD.setReleaseDate(releaseDate);
		currentDVD.setMpaaRating(mpaaRating);
		currentDVD.setDirectorName(directorName);
		currentDVD.setStudio(studio);
		currentDVD.setUserNote(userNote);
		return currentDVD;
	}

	public void displayCreateDVDBanner() {
		io.print("=== Create DVD ===");
	}

	public void displayCreateSuccessBanner() {
		io.print("DVD successfully created.");
		io.readString("Please hit enter to continue");
	}
	// End of 1. Allow the user to add a DVD to the collection
	
	// Start of 2. Allow the user to remove a DVD from the collection
	public void displayRemoveDVDBanner() {
		io.print("=== Remove DVD ===");
	}

	public void displayRemoveResult(DVD dvd) {
		if (dvd != null) {
			io.print("DVD successfully removed.");
		} else {
			io.print("No such DVD.");
		}
		io.readString("Please hit enter to continue.");
	}
	// End of 2. Allow the user to remove a DVD from the collection
	
	// Start of 3. Allow the user to edit the information for an existing DVD in the collection
	public DVD getEdittedDVDInformation(DVD dvd) {
		if (dvd != null) {
			String title = io.readString("Please edit Title");
			String releaseDate = io.readString("Please edit Release Date");
			String mpaaRating = io.readString("Please edit MPAA Rating");
			String directorName = io.readString("Please edit Director's Name");
			String studio = io.readString("Please edit Studio");
			String userNote = io.readString("Please edit User's Rating or Note");

			dvd.setTitle(title);
			dvd.setReleaseDate(releaseDate);
			dvd.setMpaaRating(mpaaRating);
			dvd.setDirectorName(directorName);
			dvd.setStudio(studio);
			dvd.setUserNote(userNote);
			return dvd;
		} else {
			return null;
		}
	}

	public void displayEditDVDBanner() {
		io.print("=== Edit DVD ===");
	}

	public void displayEditResult(DVD dvd) {
		if (dvd != null) {
			io.print("DVD successfully editted.");
		} else {
			io.print("No such DVD.");
		}
		io.readString("Please hit enter to continue.");
	}
	// End of 3. Allow the user to edit the information for an existing DVD in the collection

	// Start of 4. Allow the user to list the DVDs in the collection
	public void displayDVDList(List<DVD> dvdList) {
		if (!dvdList.isEmpty()) {
			for (DVD currentDVD : dvdList) {
				String dvdInfo = String.format("#%s : %s %s %s %s %s %s", 
						currentDVD.getDvdID(), 
						currentDVD.getTitle(),
						currentDVD.getReleaseDate(), 
						currentDVD.getMpaaRating(), 
						currentDVD.getDirectorName(),
						currentDVD.getStudio(), 
						currentDVD.getUserNote());
				io.print(dvdInfo);
			}
		} else {
			io.print("DVD Collection is empty.");
			io.print("Please add a new DVD to the Collection.");
		}
		io.readString("Please hit enter to continue.");
	}

	public void displayDisplayAllBanner() {
		io.print("=== Display All DVDs ===");
	}

	public void displayDisplayDVDBanner() {
		io.print("=== Display DVD ===");
	}
	// End of 4. Allow the user to list the DVDs in the collection

	//Start of 5. Allow the user to display the information for a particular DVD
	public void displayDVD(DVD dvd) {
		if (dvd != null) {
			io.print("DVD ID: " + dvd.getDvdID());
			io.print("Title: " + dvd.getTitle());
			io.print("Release Date: " + dvd.getReleaseDate());
			io.print("MPAA Rating: " + dvd.getMpaaRating());
			io.print("Director's Name: " + dvd.getDirectorName());
			io.print("Studio: " + dvd.getStudio());
			io.print("User's Rating/Note: " + dvd.getUserNote());
			io.print("");
		} else {
			io.print("No such DVD.");
		}
		io.readString("Please hit enter to continue.");
	}
	//End of 5. Allow the user to display the information for a particular DVD

    //Start of 6. Allow the user to search for a DVD by title
	public void displayFindDVDByTitleBanner() {
		io.print("=== Find DVD By Title ===");
	}
		
	public String getDVDTitleChoice() {
		return io.readString("Please enter the DVD Title.");
	}
	// Reuse displayDVD(DVD) from 5.

    //End of 6. Allow the user to search for a DVD by title

	
	public void displayExitBanner() {
		io.print("Good Bye!!!");
	}

	public void displayUnknownCommandBanner() {
		io.print("Unknown Command!!!");
	}

	public void displayErrorMessage(String errorMsg) {
		io.print("=== ERROR ===");
		io.print(errorMsg);
	}

	public String getDVDIDChoice() {
		return io.readString("Please enter the DVD ID.");
	}	
}
