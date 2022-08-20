package com.assignemnt.demo.config;

import com.assignemnt.demo.model.Customer;
import com.assignemnt.demo.model.Product;
import com.assignemnt.demo.repository.CustomerRepository;
import com.assignemnt.demo.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DummyDataCreator implements CommandLineRunner {
    Logger LOG = LoggerFactory.getLogger(DummyDataCreator.class);

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private ObjectMapper mapper;

    public DummyDataCreator(CustomerRepository userRepository, ProductRepository productRepository, ObjectMapper mapper) {
        this.customerRepository = userRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("-----Saving dummy data in database-----");
        saveDummyCustomers();
        saveDummyProducts();
        LOG.info("-----Dummy data saved in database-----");
    }

    private void saveDummyCustomers() {

        List<Customer> customers = Arrays.asList(new Customer("John"),
                new Customer("Qasim"),
                new Customer("Rashid"));
        customerRepository.saveAll(customers);
    }

    private void saveDummyProducts() throws JsonProcessingException {
        String JSON = "[{\"name\":\"iPhone 9\",\"brand\":\"Apple\",\"price\":549,\"color\":\"white\"},{\"name\":\"iPhone X\",\"brand\":\"Apple\",\"price\":899,\"color\":\"Black\"},{\"name\":\"Samsung Universe 9\",\"brand\":\"Samsung\",\"price\":1249,\"color\":\"white\"},{\"name\":\"OPPOF19\",\"brand\":\"OPPO\",\"price\":280,\"color\":\"Black\"},{\"name\":\"Huawei P30\",\"brand\":\"Huawei\",\"price\":499,\"color\":\"white\"},{\"name\":\"MacBook Pro\",\"brand\":\"APPle\",\"price\":1749,\"color\":\"Black\"},{\"name\":\"Samsung Galaxy Book\",\"brand\":\"Samsung\",\"price\":1499,\"color\":\"white\"},{\"name\":\"Microsoft Surface Laptop 4\",\"brand\":\"Microsoft Surface\",\"price\":1499,\"color\":\"Black\"},{\"name\":\"Infinix INBOOK\",\"brand\":\"Infinix\",\"price\":1099,\"color\":\"white\"},{\"name\":\"HP Pavilion 15-DK1056WM\",\"brand\":\"HP Pavilion\",\"price\":1099,\"color\":\"Black\"},{\"name\":\"perfume Oil\",\"brand\":\"Impression of Acqua Di Gio\",\"price\":13,\"color\":\"white\"},{\"name\":\"Brown Perfume\",\"brand\":\"Royal_Mirage\",\"price\":40,\"color\":\"Black\"},{\"name\":\"Fog Scent Xpressio Perfume\",\"brand\":\"Fog Scent Xpressio\",\"price\":13,\"color\":\"white\"},{\"name\":\"Non-Alcoholic Concentrated Perfume Oil\",\"brand\":\"Al Munakh\",\"price\":120,\"color\":\"Black\"},{\"name\":\"Eau De Perfume Spray\",\"brand\":\"Lord - Al-Rehab\",\"price\":30,\"color\":\"white\"},{\"name\":\"Hyaluronic Acid Serum\",\"brand\":\"L'Oreal Paris\",\"price\":19,\"color\":\"Black\"},{\"name\":\"Tree Oil 30ml\",\"brand\":\"Hemani Tea\",\"price\":12,\"color\":\"white\"},{\"name\":\"Oil Free Moisturizer 100ml\",\"brand\":\"Dermive\",\"price\":40,\"color\":\"Black\"},{\"name\":\"Skin Beauty Serum.\",\"brand\":\"ROREC White Rice\",\"price\":46,\"color\":\"white\"},{\"name\":\"Freckle Treatment Cream- 15gm\",\"brand\":\"Fair & Clear\",\"price\":70,\"color\":\"Black\"},{\"name\":\"- Daal Masoor 500 grams\",\"brand\":\"Saaf & Khaas\",\"price\":20,\"color\":\"white\"},{\"name\":\"Elbow Macaroni - 400 gm\",\"brand\":\"Bake Parlor Big\",\"price\":14,\"color\":\"Black\"},{\"name\":\"Orange Essence Food Flavou\",\"brand\":\"Baking Food Items\",\"price\":14,\"color\":\"white\"},{\"name\":\"cereals muesli fruit nuts\",\"brand\":\"fauji\",\"price\":46,\"color\":\"Black\"},{\"name\":\"Gulab Powder 50 Gram\",\"brand\":\"Dry Rose\",\"price\":70,\"color\":\"white\"},{\"name\":\"Plant Hanger For Home\",\"brand\":\"Boho Decor\",\"price\":41,\"color\":\"Black\"},{\"name\":\"Flying Wooden Bird\",\"brand\":\"Flying Wooden\",\"price\":51,\"color\":\"white\"},{\"name\":\"3D Embellishment Art Lamp\",\"brand\":\"LED Lights\",\"price\":20,\"color\":\"Black\"},{\"name\":\"Handcraft Chinese style\",\"brand\":\"luxury palace\",\"price\":60,\"color\":\"white\"},{\"name\":\"Key Holder\",\"brand\":\"Golden\",\"price\":30,\"color\":\"Black\"}]";
        List<Product> products = mapper.readValue(JSON, new TypeReference<List<Product>>(){});
        System.out.println("Product Size###### " + products.size());
        productRepository.saveAll(products);
    }
}
