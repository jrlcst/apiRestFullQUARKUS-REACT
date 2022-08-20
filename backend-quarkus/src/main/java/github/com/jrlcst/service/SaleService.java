package github.com.jrlcst.service;

import github.com.jrlcst.entities.Sale;
import github.com.jrlcst.repository.SaleRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@ApplicationScoped
public class SaleService {

    @Inject
    SaleRepository repository;

    /**
     * faz a conversão de String para Date,
     * e valida caso a data minima ou maxima vier null,
     * traz data minima de 1 ano atras, e data maxima do dia atual
     * @param minDate
     * @param maxDate
     * @return
     */
    public List<Sale> findSales(String minDate, String maxDate) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = minDate.equals("")? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("")? today : LocalDate.parse(maxDate);

        return repository.findSalesParams(min, max);
    }


}
