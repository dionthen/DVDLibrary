package com.dvdlibrary;

import com.dvdlibrary.controller.DVDLibraryController;
import com.dvdlibrary.dao.DVDLibraryDao;
import com.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.dvdlibrary.ui.DVDLibraryView;
import com.dvdlibrary.ui.UserIO;
import com.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

	public static void main(String[] args) {
		 UserIO myIo = new UserIOConsoleImpl();
         DVDLibraryView myView = new DVDLibraryView(myIo);
         DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
         DVDLibraryController controller = new DVDLibraryController(myDao, myView);
         controller.run();  
	}
}
