package com.assertj.playground.domain

data class Student(
        val name: String,
        val surname: String,
        val degree: Degree,
        val tutors: List<Tutor>,
        val extraActivities: List<String?>
)
