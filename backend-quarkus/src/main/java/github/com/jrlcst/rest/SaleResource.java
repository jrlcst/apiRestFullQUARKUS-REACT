package github.com.jrlcst.rest;

import github.com.jrlcst.entities.Sale;
import github.com.jrlcst.repository.SaleRepository;
import github.com.jrlcst.rest.dto.CreateSaleRequest;
import github.com.jrlcst.service.SaleService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Path("/sales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SaleResource {
    @Inject
    private SaleService service;
    @Inject
    private SaleRepository repository;

    @GET
    /**
     * traz vendas filtradas por data minima e máxima
     */
    public Response findSales(@QueryParam("minDate") String minDate,
                              @QueryParam("maxDate") String maxDate) {
        List<Sale> query = service.findSales(minDate, maxDate);
        return Response.ok(query).build();
    }

    @POST
    @Transactional
    /**
     * metodo de insert dos dados de uma venda via request http
     */
    public Response createSale(CreateSaleRequest saleRequest) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        Sale sale = new Sale();

        sale.setSellerName(saleRequest.getSellerName());
        sale.setId(saleRequest.getId());
        sale.setVisited(saleRequest.getVisited());
        sale.setAmount(saleRequest.getAmount());
        sale.setDeals(saleRequest.getDeals());
        sale.setDate(today);

        repository.persist(sale);

        return Response.status(Response.Status.CREATED.getStatusCode())
                .entity(sale).build();

    }

    @DELETE()
    @Path("{id}")
    @Transactional
    /**
     * deleta venda por id selecionado
     */
    public Response deleteSale(@PathParam("id") Long id) {
        Sale sale = repository.findById(id);

        if (sale != null) {
            repository.delete(sale);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT()
    @Path("{id}")
    @Transactional
    /**
     * busca a venda pelo id e,
     * atualiza o dado de venda de acordo com o atributo alterado
     */
    public Response updateSale(@PathParam("id") Long id, CreateSaleRequest saleRequest) {
        Sale sale = repository.findById(id);
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        if (sale != null) {
            sale.setSellerName(saleRequest.getSellerName());
            sale.setVisited(saleRequest.getVisited());
            sale.setAmount(saleRequest.getAmount());
            sale.setDeals(saleRequest.getDeals());
            sale.setDate(today);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
