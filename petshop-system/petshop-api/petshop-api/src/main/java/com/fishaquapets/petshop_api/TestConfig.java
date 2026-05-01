package com.fishaquapets.petshop_api;

import com.fishaquapets.petshop_api.model.entity.Category;
import com.fishaquapets.petshop_api.model.entity.Supplier;
import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Categorias
        Category c1 = new Category(null, "Alimentos");
        Category c2 = new Category(null, "Acessórios");
        Category c3 = new Category(null, "Higiene");
        Category c4 = new Category(null, "Peixes");
        Category c5 = new Category(null, "Aquarismo");
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        // Fornecedores
        Supplier f1 = new Supplier(null, "PetDistribuidora S.A.", "contato@petdist.com");
        Supplier f2 = new Supplier(null, "MegaPet Atacado", "vendas@megapet.com");
        Supplier f3 = new Supplier(null, "Aquários Brasil Ltda.", "contato@aquabrasil.com");
        Supplier f4 = new Supplier(null, "OceanTech", "suporte@oceantech.com");
        supplierRepository.saveAll(Arrays.asList(f1, f2, f3, f4));

        // Produtos
        Product p1 = new Product(null, "Ração Premium 15kg", new BigDecimal("250.00"),
                "Ração de alta qualidade para cães adultos", new BigDecimal("10.00"));

        Product p2 = new Product(null, "Shampoo Pet suave", new BigDecimal("45.00"),
                "Shampoo hipoalergênico", BigDecimal.ZERO);

        Product p3 = new Product(null, "Coleira de Couro", new BigDecimal("80.00"),
                "Coleira resistente tamanho G", new BigDecimal("5.00"));
        Product p4 = new Product(null, "Aquário 20L Básico", new BigDecimal("150.00"),
                "Aquário de vidro simples com tampa", new BigDecimal("5.00"));

        Product p5 = new Product(null, "Aquário 100L Profissional", new BigDecimal("850.00"),
                "Vidro extra clear com acabamento em silicone preto", new BigDecimal("10.00"));

        Product p6 = new Product(null, "Filtro Externo Hang-on 300L/H", new BigDecimal("120.00"),
                "Filtragem química, física e biológica", BigDecimal.ZERO);

        Product p7 = new Product(null, "Termostato 50W", new BigDecimal("85.00"),
                "Aquecedor automático com regulagem", new BigDecimal("15.00"));

        Product p8 = new Product(null, "Luminária LED RGB 40cm", new BigDecimal("210.00"),
                "Iluminação potente para plantas naturais", new BigDecimal("8.00"));

        // Associando
        p1.addCategoria(c1);
        p1.addFornecedor(f1);

        p2.addCategoria(c3);
        p2.addFornecedor(f1);
        p2.addFornecedor(f2); // Um produto com dois fornecedores

        p3.addCategoria(c2);
        p3.addFornecedor(f2);
        p4.addCategoria(c5);
        p4.addFornecedor(f3);

        p5.addCategoria(c5);
        p5.addFornecedor(f3);

        p6.addCategoria(c5);
        p6.addFornecedor(f4);

        p7.addCategoria(c5);
        p7.addFornecedor(f4);

        p8.addCategoria(c5);
        p8.addCategoria(c2); // Aquarismo e Acessórios
        p8.addFornecedor(f4);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

        // Vendas
        // Venda 1: Pagamento integral via PIX
        Sale v1 = new Sale(null, PaymentStatus.PAGA, PaymentMethod.PIX, new BigDecimal("225.00"), Arrays.asList("Entrega realizada na portaria"));
        v1.addItem(p1, 1);
        saleRepository.save(v1);
        orderItemRepository.saveAll(v1.getItens());

        // Venda 2: Pagou apenas uma parte do aquário e acessórios no cartão de crédito
        Sale v2 = new Sale(null, PaymentStatus.PAGAMENTO_PARCIAL, PaymentMethod.CARTAO_CREDITO, new BigDecimal("500.00"), Arrays.asList("Cliente solicitará instalação posterior"));
        v2.addItem(p5, 1);
        v2.addItem(p6, 1);
        v2.addItem(p7, 1);
        saleRepository.save(v2);
        orderItemRepository.saveAll(v2.getItens());

        // Venda 3: Venda pendente em dinheiro para retirada futura
        Sale v3 = new Sale(null, PaymentStatus.PENDENTE, PaymentMethod.DINHEIRO, BigDecimal.ZERO, Arrays.asList("Aguardando retirada em loja"));
        v3.addItem(p4, 1);
        v3.addItem(p2, 2);
        saleRepository.save(v3);
        orderItemRepository.saveAll(v3.getItens());

        // Venda 4: Venda paga no débito com múltiplos itens pequenos
        Sale v4 = new Sale(null, PaymentStatus.PAGA, PaymentMethod.CARTAO_DEBITO, new BigDecimal("121.00"), Arrays.asList("Cliente utilizou sacola própria"));
        v4.addItem(p3, 1);
        v4.addItem(p2, 1);
        saleRepository.save(v4);
        orderItemRepository.saveAll(v4.getItens());

        // VENDAS COM CASOS ESPECIAIS
        // -------------------------------------------------------------------------
        // VENDA 5: Venda de Kit de Aquarismo Completo (Grande Quantidade)
        // -------------------------------------------------------------------------
        Sale v5 = new Sale(null, PaymentStatus.PAGA, PaymentMethod.CARTAO_DEBITO,
                new BigDecimal("415.00"),
                Arrays.asList("Venda de kit iniciante", "Cliente solicitou teste de água"));
        v5.addItem(p4, 1); // Aquário
        v5.addItem(p6, 1); // Filtro
        v5.addItem(p7, 1); // Termostato
        v5.addItem(p8, 1); // Luminária
        saleRepository.save(v5);
        orderItemRepository.saveAll(v5.getItens());

        // -------------------------------------------------------------------------
        // VENDA 6: Venda ESTORNADA (Devolução de produto)
        // -------------------------------------------------------------------------
        Sale v6 = new Sale(null, PaymentStatus.ESTORNADA, PaymentMethod.CARTAO_CREDITO,
                BigDecimal.ZERO,
                Arrays.asList("Produto devolvido por incompatibilidade", "Estorno processado no cartão"));
        v6.addItem(p3, 2); // 2 Coleiras
        saleRepository.save(v6);
        orderItemRepository.saveAll(v6.getItens());

        // -------------------------------------------------------------------------
        // VENDA 7: Venda de Alto Volume (Estoque de Ração)
        // -------------------------------------------------------------------------
        Sale v7 = new Sale(null, PaymentStatus.PAGA, PaymentMethod.PIX,
                new BigDecimal("1125.00"),
                Arrays.asList("Venda para ONG de proteção animal", "Desconto adicional aplicado via cupom"));
        v7.addItem(p1, 5); // 5 sacos de ração premium
        saleRepository.save(v7);
        orderItemRepository.saveAll(v7.getItens());

        // -------------------------------------------------------------------------
        // VENDA 8: Pagamento Parcial com múltiplos itens de higiene
        // -------------------------------------------------------------------------
        Sale v8 = new Sale(null, PaymentStatus.PAGAMENTO_PARCIAL, PaymentMethod.DINHEIRO,
                new BigDecimal("50.00"),
                Arrays.asList("Cliente pagou parte em dinheiro", "Restante será pago na retirada"));
        v8.addItem(p2, 4); // 4 Shampoos
        v8.addItem(p3, 1); // 1 Coleira
        saleRepository.save(v8);
        orderItemRepository.saveAll(v8.getItens());

        System.out.println("Seeding realizado com sucesso!");
    }

}
