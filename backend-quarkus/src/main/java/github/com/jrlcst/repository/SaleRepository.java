package github.com.jrlcst.repository;

import github.com.jrlcst.entities.Sale;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class SaleRepository implements PanacheRepository<Sale> {

    /**
     * executa a query no banco para trazer a venda,
     * passando a data minima e maxima como parametros
     * @param min
     * @param max
     * @return
     */
    public List<Sale> findSalesParams(LocalDate min, LocalDate max){
        var params = Parameters.with(
                "min", min).and("max", max).map();

        PanacheQuery<Sale> query = find(
                "SELECT obj FROM Sale obj " +
                        "WHERE obj.date BETWEEN :min AND :max " +
                        "ORDER BY obj.amount DESC", params);
        return query.list();
    }
}
