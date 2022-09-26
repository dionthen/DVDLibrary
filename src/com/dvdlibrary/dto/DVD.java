package com.dvdlibrary.dto;

import java.time.LocalDate;
import java.util.Objects;

public class DVD {
	private String title;
	private LocalDate releaseDate;
	private String mpaaRating;
	private String directorName;
	private String studio;
	private String userRating;

	public DVD(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMpaaRating() {
		return mpaaRating;
	}

	public void setMpaaRating(String mpaaRating) {
		this.mpaaRating = mpaaRating;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getUserRating() {
		return userRating;
	}

	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + Objects.hashCode(this.title);
		hash = 37 * hash + Objects.hashCode(this.releaseDate);
		hash = 37 * hash + Objects.hashCode(this.mpaaRating);
		hash = 37 * hash + Objects.hashCode(this.directorName);
		hash = 37 * hash + Objects.hashCode(this.studio);
		hash = 37 * hash + Objects.hashCode(this.userRating);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DVD other = (DVD) obj;
		if (!Objects.equals(this.title, other.title)) {
			return false;
		}
		if (!Objects.equals(this.releaseDate, other.releaseDate)) {
			return false;
		}
		if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
			return false;
		}
		if (!Objects.equals(this.directorName, other.directorName)) {
			return false;
		}
		if (!Objects.equals(this.studio, other.studio)) {
			return false;
		}
		if (!Objects.equals(this.userRating, other.userRating)) {
			return false;
		}
		return true;
	}
}
