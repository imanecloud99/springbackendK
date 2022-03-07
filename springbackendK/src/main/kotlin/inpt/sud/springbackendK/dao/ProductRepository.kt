package inpt.sud.springbackendK.dao

import inpt.sud.springbackendK.data.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping

@CrossOrigin("*")
@RepositoryRestResource
interface ProductRepository: JpaRepository<Product?, Long?> {
    @RestResource(path = "/selectedProducts")
    public fun findBySelectedIsTrue(): List<Product>
    @RestResource(path = "/productsById/{id}")
    public fun findByCategoryId(category_id: Long): List<Product>
}