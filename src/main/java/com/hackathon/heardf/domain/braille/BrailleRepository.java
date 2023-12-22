package com.hackathon.heardf.domain.braille;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrailleRepository extends JpaRepository<Braille, Long> {
}
