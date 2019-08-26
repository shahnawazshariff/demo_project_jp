package com.jpmorgan.repository;

import com.jpmorgan.entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {

}
