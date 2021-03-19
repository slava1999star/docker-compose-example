package com.docker.jpa;

import com.docker.dto.BlockDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<BlockDto, String> {

}
