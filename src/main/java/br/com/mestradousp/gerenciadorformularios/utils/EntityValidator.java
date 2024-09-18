package br.com.mestradousp.gerenciadorformularios.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Supplier;

public class EntityValidator<T, ID> {
    private final JpaRepository<T, ID> repository;
    private final Supplier<RuntimeException> notFoundException;
    private final Supplier<RuntimeException> conflictException;

    public EntityValidator(
            JpaRepository<T, ID> repository,
            Supplier<RuntimeException> notFoundException,
            Supplier<RuntimeException> conflictException
    ) {
        this.repository = repository;
        this.notFoundException = notFoundException;
        this.conflictException = conflictException;
    }

    public T validateIfEntityExists(ID id) {
       return repository.findById(id).orElseThrow(notFoundException);
    }

    public void validateIfEntityAlreadyCreated(ID id) {
        repository.findById(id).ifPresent(a -> { throw conflictException.get(); });
    }
}
