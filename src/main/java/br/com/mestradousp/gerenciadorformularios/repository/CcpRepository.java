package br.com.mestradousp.gerenciadorformularios.repository;

import br.com.mestradousp.gerenciadorformularios.model.Ccp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CcpRepository extends JpaRepository<Ccp, Long> {
    Optional<Ccp> findByEmail(String email);
}
