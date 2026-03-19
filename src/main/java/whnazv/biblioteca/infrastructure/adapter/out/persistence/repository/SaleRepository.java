package whnazv.biblioteca.infrastructure.adapter.out.persistence.repository;

import whnazv.biblioteca.infrastructure.adapter.out.persistence.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findByUserId(Long userId);

    @Query("""
        SELECT DISTINCT s FROM SaleEntity s
        JOIN s.items i
        JOIN BookEntity b ON b.id = i.bookId
        WHERE CAST(s.id AS string) LIKE CONCAT('%', :query, '%')
           OR CAST(s.userId AS string) LIKE CONCAT('%', :query, '%')
           OR LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(b.author) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    List<SaleEntity> searchSales(String query);
}
