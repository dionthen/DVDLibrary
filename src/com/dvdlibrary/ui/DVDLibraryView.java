package com.dvdlibrary.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
	public void displayCreateDVDBanner() {
		io.print("=== Create DVD ===");
	}
	
	public DVD getNewDVDInfo() {
		String title = io.readString("Please enter the DVD title");
		LocalDate releaseDate = io.readDate("Please enter the DVD release date");
		String MpaaRating = io.readString("Please enter the MPAA rating");
		String directorName = io.readString("Please enter the director's name");
		String studio = io.readString("Please enter the DVD studio");
		String userRating = io.readString("Please enter your rating of the DVD");
		// Instantiating a new DVD object using the title to satisfy the constructors
		// requirements
		DVD currentDvd = new DVD(title);
		currentDvd.setReleaseDate(releaseDate);
		currentDvd.setMpaaRating(MpaaRating);
		currentDvd.setDirectorName(directorName);
		currentDvd.setStudio(studio);
		currentDvd.setUserRating(userRating);
		return currentDvd;
	}

	public void displayCreateSuccessBanner() {
		io.readString("DVD successfully created. Please hit enter to continue");
	}

	//2. Allow the user to remove a DVD from the collection
	public void displayRemoveDVDBanner() {
		io.print("=== Remove DVD ===");
	}

	public void displayRemoveResult(DVD dvdRecord) {
		if (dvdRecord != null) {
			io.print("DVD successfully removed.");
		} else {
			io.print("No such DVD.");
		}
		io.readString("Please hit enter to continue.");
	}
	
	//3. Allow the user to edit the information for an existing DVD in the collection
	public void displayEditDVDBanner() {
		io.print("=== Edit DVD ===");

	}

	public int printEditMenuAndGetSelection() {
		io.print("Which field do you want to change?");
		io.print("Edit DVD menu");
		io.print("1. Release date");
		io.print("2. MPAA rating");
		io.print("3. Director's name");
		io.print("4. Studio name");
		io.print("5. User rating");
		io.print("6. Exit edit menu");
		return io.readInt("Please select from the above choices.", 1, 6);
	}
	
	public LocalDate getReleaseDate() {
		return io.readDate("Please enter the new DVD release date.");
	}

	public String getMpaaRating() {
		return io.readString("Please enter the new DVD MPAA rating.");
	}

	public String getDirectorName() {
		return io.readString("Please enter the new director's name.");
	}

	public String getUserRating() {
		return io.readString("Please enter the new user rating.");
	}

	public String getStudioName() {
		return io.readString("Please enter the studio name.");
	}
	
	public void displayEditResult() {
		io.print("DVD Successfully edited.");
	}

	// Start of 4. Allow the user to list the DVDs in the collection
	public void displayDVDList(List<DVD> dvdList) {
		if (!dvdList.isEmpty()) {
			String dvdHeadings = String.format("%25s | %12s | %4s | %17s | %7s | %25s", "Title", "Release Date", "MPAA",
					"Director Name", "Studio", "Rating");
			io.print(dvdHeadings);
			io.print(
					"-----------------------------------------------------------------------------------------------------------------");
			for (DVD currentDVD : dvdList) {
				String dvdInfo = String.format("%25s | %12s | %4s | %17s | %7s | %25s", currentDVD.getTitle(),
						currentDVD.getReleaseDate(), currentDVD.getMpaaRating(), currentDVD.getDirectorName(),
						currentDVD.getStudio(),currentDVD.getUserRating());
				io.print(dvdInfo);
			}
		} else {
			io.print("DVD Collection is empty.");
			io.print("Please add a new DVD to the Collection.");
		}
		io.readString("Please hit enter to continue.");
	}

	public void displayDVDListBanner() {
		io.print("=== Display all DVDs ===");
	}

	public void displayDisplayDVDBanner() {
		io.print("=== Display DVD ===");
	}

	//5. Allow the user to display the information for a particular DVD
	public void displayFindDVDsBanner() {
		io.print("=== Find DVDS ===");
	}

	public int printFindMenuAndGetSelection() {
		io.print("Find DVD menu");
		io.print("1. Find all movies released in the last N years");
		io.print("2. Find all movies by MPAA rating");
		io.print("3. Find all movies by director");
		io.print("4. Find all movies by Studio");
		io.print("5. Exit find DVD menu");
		return io.readInt("Please select from the above choices.", 1, 5);
	}

	public int getNYears() {
		return io.readInt("How many years? (N)");
	}

	public String displayDVDs(Map<String, DVD> filteredDvds) {
		if (filteredDvds.isEmpty()) {
			io.print("No DVDs to display");
		} else {
			String dvdHeadings = String.format("%25s | %12s | %4s | %17s | %7s | %25s", "Title", "Release Date", "MPAA",
					"Director Name", "Studio", "Rating");
			io.print(dvdHeadings);
			io.print(
					"-----------------------------------------------------------------------------------------------------------------");
			filteredDvds.values().stream()
					.forEach((Dvd) -> io.print(
							String.format("%25s | %12s | %4s | %17s | %7s | %25s", Dvd.getTitle(), Dvd.getReleaseDate(),
									Dvd.getMpaaRating(), Dvd.getDirectorName(), Dvd.getStudio(), Dvd.getUserRating())));
		}
		return io.readString("Please hit enter to continue");

	}
	
	//6. Allow the user to search for a DVD by title
	public String getDVDTitleChoice() {
		return io.readString("Please enter the DVD title.");
	}

	public void displayDVD(DVD dvd) {
		if (dvd != null) {
			io.print("=== " + dvd.getTitle() + " Summary ===");
			io.print("Title: " + dvd.getTitle());
			io.print("Release date: " + dvd.getReleaseDate());
			io.print("MPAA rating: " + dvd.getMpaaRating());
			io.print("Director's name: " + dvd.getDirectorName());
			io.print("Studio: " + dvd.getStudio());
			io.print("User rating: " + dvd.getUserRating());
		} else {
			io.print("No such DVD");
		}
		io.readString("Please hit enter to continue.");
	}

	public void displayExitBanner() {
		io.print("Good bye!");
	}

	public void displayUnknownCommandBanner() {
		io.print("Unknown command!");
	}	

	public void displayNullDVD() {
		io.print("No such DVD");
	}

	public void displayErrorMessage(String errorMsg) {
		io.print("=== ERROR ===");
		io.print(errorMsg);
	}
}
