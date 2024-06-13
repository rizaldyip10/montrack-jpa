package com.purwadhika.montrackv2.repositories;

import com.purwadhika.montrackv2.entities.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PocketRepository extends JpaRepository<Pocket, Long> {
}
