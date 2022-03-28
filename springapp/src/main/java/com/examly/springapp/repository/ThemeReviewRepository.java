package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.ThemeReview;

public interface ThemeReviewRepository extends JpaRepository<ThemeReview, Integer>{

}
