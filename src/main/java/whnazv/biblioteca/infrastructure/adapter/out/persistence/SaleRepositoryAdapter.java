package whnazv.biblioteca.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.out.SaleRepositoryPort;
import whnazv.biblioteca.domain.model.Sale;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.entity.SaleEntity;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.repository.SaleRepository;
import whnazv.biblioteca.infrastructure.mapper.entity.SaleMapperEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SaleRepositoryAdapter implements SaleRepositoryPort {

    private final SaleRepository repository;
    private final SaleMapperEntity mapper;

    @Override
    public Sale save(Sale sale) {
        SaleEntity entity = mapper.toEntity(sale);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public List<Sale> findAll() {
        return repository.findAll().stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public List<Sale> findByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Sale> searchSales(String query) {
        return repository.searchSales(query).stream()
                .map(mapper::toModel)
                .toList();
    }
}
