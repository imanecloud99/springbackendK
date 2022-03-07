package inpt.sud.springbackendK

import inpt.sud.springbackendK.dao.CategoryRepository
import inpt.sud.springbackendK.dao.ProductRepository
import inpt.sud.springbackendK.data.Category
import inpt.sud.springbackendK.data.Product
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import java.util.function.Consumer
import kotlin.random.Random


@SpringBootApplication
class SpringbackendKApplication: CommandLineRunner{
	@Autowired
	var productRepository : ProductRepository? =null
	@Autowired
	var categoryRepository: CategoryRepository? = null

	@Autowired
	var repositoryRestConfiguration: RepositoryRestConfiguration? = null



	@Throws(Exception::class)
	override fun run(vararg args: String?) {

		repositoryRestConfiguration!!.exposeIdsFor(
			Product::class.java, Category::class.java
		)

		categoryRepository!!.save<Category>(Category(null, "Computers", null, null))
		categoryRepository!!.save<Category>(Category(null, "Printers", null, null))
		categoryRepository!!.save<Category>(Category(null, "Smartphones", null, null))
		val rnd = Random
		categoryRepository!!.findAll().forEach(Consumer { category: Category? ->
			for (i in 0..9) {
				val p = Product()
				p.setName(RandomString.make(10))
				p.setCurrentPrice((100 + rnd.nextInt(10000)).toDouble())
				p.setAvailable(rnd.nextBoolean())
				p.setPromotion(rnd.nextBoolean())
				p.setSelected(rnd.nextBoolean())
				p.setCategory(category!!)
				p.setPhotoName("unknown.png")
				productRepository!!.save(p)
			}
		})
	}
}

fun main(args: Array<String>) {
	runApplication<SpringbackendKApplication>(*args)
}
