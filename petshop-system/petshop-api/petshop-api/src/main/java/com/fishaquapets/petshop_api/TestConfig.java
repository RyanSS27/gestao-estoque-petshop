package com.fishaquapets.petshop_api;

import com.fishaquapets.petshop_api.model.entity.Category;
import com.fishaquapets.petshop_api.model.entity.Supplier;
import com.fishaquapets.petshop_api.model.entity.Product;
import com.fishaquapets.petshop_api.model.entity.Sale;
import com.fishaquapets.petshop_api.model.enums.CategoryType;
import com.fishaquapets.petshop_api.model.enums.PaymentStatus;
import com.fishaquapets.petshop_api.model.enums.PaymentMethod;
import com.fishaquapets.petshop_api.model.enums.SaleType;
import com.fishaquapets.petshop_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.Instant;
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
        Category c1 = new Category(null, "Alimentos", CategoryType.PRODUCT);
        Category c2 = new Category(null, "Acessórios", CategoryType.PRODUCT);
        Category c3 = new Category(null, "Higiene", CategoryType.PRODUCT);
        Category c4 = new Category(null, "Peixes", CategoryType.PRODUCT);
        Category c5 = new Category(null, "Aquarismo", CategoryType.PRODUCT);
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        // Fornecedores
        Supplier f1 = new Supplier(null, "PetDistribuidora S.A.", "contato@petdist.com");
        Supplier f2 = new Supplier(null, "MegaPet Atacado", "vendas@megapet.com");
        Supplier f3 = new Supplier(null, "Aquários Brasil Ltda.", "contato@aquabrasil.com");
        Supplier f4 = new Supplier(null, "OceanTech", "suporte@oceantech.com");
        supplierRepository.saveAll(Arrays.asList(f1, f2, f3, f4));

        // Produtos
        Product p1 = new Product(null, "Ração Premium 15kg", new BigDecimal("250.00"),
                50, Instant.parse("2026-01-10T08:30:00Z"), "Ração de alta qualidade para cães adultos", new BigDecimal("10.00"));

        Product p2 = new Product(null, "Shampoo Pet suave", new BigDecimal("45.00"),
                100, Instant.parse("2026-02-15T10:00:00Z"), "Shampoo hipoalergênico", BigDecimal.ZERO);

        Product p3 = new Product(null, "Coleira de Couro", new BigDecimal("80.00"),
                30, Instant.parse("2026-03-05T14:20:00Z"), "Coleira resistente tamanho G", new BigDecimal("5.00"));

        Product p4 = new Product(null, "Aquário 20L Básico", new BigDecimal("150.00"),
                15, Instant.parse("2026-03-20T09:45:00Z"), "Aquário de vidro simples com tampa", new BigDecimal("5.00"));

        Product p5 = new Product(null, "Aquário 100L Profissional", new BigDecimal("850.00"),
                5, Instant.parse("2026-04-02T11:30:00Z"), "Vidro extra clear com acabamento em silicone preto", new BigDecimal("10.00"));

        Product p6 = new Product(null, "Filtro Externo Hang-on 300L/H", new BigDecimal("120.00"),
                25, Instant.parse("2026-04-10T16:00:00Z"), "Filtragem química, física e biológica", BigDecimal.ZERO);

        Product p7 = new Product(null, "Termostato 50W", new BigDecimal("85.00"),
                40, Instant.parse("2026-05-01T13:15:00Z"), "Aquecedor automático com regulagem", new BigDecimal("15.00"));

        Product p8 = new Product(null, "Luminária LED RGB 40cm", new BigDecimal("210.00"),
                12, Instant.parse("2026-05-12T10:30:00Z"), "Iluminação potente para plantas naturais", new BigDecimal("8.00"));

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
        // Venda 1: Pagamento integral via PIX (Sem descontos)
        Sale v1 = new Sale(null, Instant.parse("2026-01-05T09:15:00Z"), PaymentStatus.PAGA, PaymentMethod.PIX,
                new BigDecimal("225.00"), Arrays.asList("Entrega realizada na portaria"), SaleType.DELIVERY);
        v1.addItem(p1, 1, 0);
        saleRepository.save(v1);
        orderItemRepository.saveAll(v1.getItens());

        // Venda 2: Pagou apenas uma parte. Aquário (p5) com 10% de desconto!
        Sale v2 = new Sale(null, Instant.parse("2026-01-10T14:30:00Z"), PaymentStatus.PAGAMENTO_PARCIAL, PaymentMethod.CARTAO_CREDITO,
                new BigDecimal("500.00"), Arrays.asList("Cliente solicitará instalação posterior"), SaleType.RESERVATION);
        v2.addItem(p5, 1, 10); // <-- 10% de desconto APENAS neste item
        v2.addItem(p6, 1, 0);
        v2.addItem(p7, 1, 0);
        saleRepository.save(v2);
        orderItemRepository.saveAll(v2.getItens());

        // Venda 3: Venda pendente
        Sale v3 = new Sale(null, Instant.parse("2026-02-15T11:00:00Z"), PaymentStatus.PENDENTE, PaymentMethod.DINHEIRO,
                BigDecimal.ZERO, Arrays.asList("Aguardando retirada em loja"), SaleType.RESERVATION);
        v3.addItem(p4, 1, 0);
        v3.addItem(p2, 2, 0);
        saleRepository.save(v3);
        orderItemRepository.saveAll(v3.getItens());

        // Venda 4: Múltiplos itens
        Sale v4 = new Sale(null, Instant.parse("2026-03-02T17:45:00Z"), PaymentStatus.PAGA, PaymentMethod.CARTAO_DEBITO,
                new BigDecimal("121.00"), Arrays.asList("Cliente utilizou sacola própria"), SaleType.COUNTER_SALE);
        v4.addItem(p3, 1, 0);
        v4.addItem(p2, 1, 0);
        saleRepository.save(v4);
        orderItemRepository.saveAll(v4.getItens());

        // -------------------------------------------------------------------------
        // VENDA 5: Luminária (p8) com 20% de desconto (Queima de estoque)
        // -------------------------------------------------------------------------
        Sale v5 = new Sale(null, Instant.parse("2026-03-20T10:20:00Z"), PaymentStatus.PAGA, PaymentMethod.CARTAO_DEBITO,
                new BigDecimal("415.00"), Arrays.asList("Venda de kit iniciante", "Cliente solicitou teste de água"), SaleType.COUNTER_SALE);
        v5.addItem(p4, 1, 0); // Aquário
        v5.addItem(p6, 1, 0); // Filtro
        v5.addItem(p7, 1, 0); // Termostato
        v5.addItem(p8, 1, 20); // <-- 20% de desconto neste item
        saleRepository.save(v5);
        orderItemRepository.saveAll(v5.getItens());

        // -------------------------------------------------------------------------
        // VENDA 6: Venda ESTORNADA (Devolução de produto)
        // -------------------------------------------------------------------------
        Sale v6 = new Sale(null, Instant.parse("2026-04-05T15:10:00Z"), PaymentStatus.ESTORNADA, PaymentMethod.CARTAO_CREDITO,
                BigDecimal.ZERO, Arrays.asList("Produto devolvido por incompatibilidade", "Estorno processado no cartão"), SaleType.COUNTER_SALE);
        v6.addItem(p3, 2, 0); // 2 Coleiras
        saleRepository.save(v6);
        orderItemRepository.saveAll(v6.getItens());

        // -------------------------------------------------------------------------
        // VENDA 7: Venda com 15% de Desconto no TOTAL (Cupom geral)
        // -------------------------------------------------------------------------
        Sale v7 = new Sale(null, Instant.parse("2026-05-10T13:00:00Z"), PaymentStatus.PAGA, PaymentMethod.PIX,
                new BigDecimal("1125.00"), Arrays.asList("Venda para ONG de proteção animal", "Desconto adicional aplicado via cupom"), SaleType.DELIVERY);

        v7.setDiscountPercentage(15); // <-- Desconto de 15% aplicado na venda inteira!
        v7.addItem(p1, 5, 0);
        saleRepository.save(v7);
        orderItemRepository.saveAll(v7.getItens());

        // -------------------------------------------------------------------------
        // VENDA 8: Shampoos (p2) com 5% de desconto
        // -------------------------------------------------------------------------
        Sale v8 = new Sale(null, Instant.parse("2026-05-12T08:50:00Z"), PaymentStatus.PAGAMENTO_PARCIAL, PaymentMethod.DINHEIRO,
                new BigDecimal("50.00"), Arrays.asList("Cliente pagou parte em dinheiro", "Restante será pago na retirada"), SaleType.RESERVATION);
        v8.addItem(p2, 4, 5); // <-- 5% de desconto nestes shampoos
        v8.addItem(p3, 1, 0); // Coleira normal
        v8.setDiscountPercentage(2);
        saleRepository.save(v8);
        orderItemRepository.saveAll(v8.getItens());

        System.out.println("Seeding realizado com sucesso!");
    }

}
