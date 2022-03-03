package com.examly.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "themes")
public class Theme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer themeId;

	@NotNull
	@Column(unique = true)
	private String themeName;
	@NotNull
	private String themeImageURL;
	@NotNull
	private String themeDescription;
	private String themePhotographer;
	private String themeVideographer;
	private String themeReturnGift;
	@NotNull
	private Long themeCost;

	public Theme() {
		super();
	}

	public Theme(String themeName, String themeImageURL, String themeDescription, String themePhotographer,
			String themeVideographer, String themeReturnGift, Long themeCost) {
		super();
		this.themeName = themeName;
		this.themeImageURL = themeImageURL;
		this.themeDescription = themeDescription;
		this.themePhotographer = themePhotographer;
		this.themeVideographer = themeVideographer;
		this.themeReturnGift = themeReturnGift;
		this.themeCost = themeCost;
	}

	public Theme(String themeName, String themeImageURL, String themeDescription, Long themeCost) {
		super();
		this.themeName = themeName;
		this.themeImageURL = themeImageURL;
		this.themeDescription = themeDescription;
		this.themeCost = themeCost;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getThemeImageURL() {
		return themeImageURL;
	}

	public void setThemeImageURL(String themeImageURL) {
		this.themeImageURL = themeImageURL;
	}

	public String getThemeDescription() {
		return themeDescription;
	}

	public void setThemeDescription(String themeDescription) {
		this.themeDescription = themeDescription;
	}

	public String getThemePhotographer() {
		return themePhotographer;
	}

	public void setThemePhotographer(String themePhotographer) {
		this.themePhotographer = themePhotographer;
	}

	public String getThemeVideographer() {
		return themeVideographer;
	}

	public void setThemeVideographer(String themeVideographer) {
		this.themeVideographer = themeVideographer;
	}

	public String getThemeReturnGift() {
		return themeReturnGift;
	}

	public void setThemeReturnGift(String themeReturnGift) {
		this.themeReturnGift = themeReturnGift;
	}

	public Long getThemeCost() {
		return themeCost;
	}

	public void setThemeCost(Long themeCost) {
		this.themeCost = themeCost;
	}

}
