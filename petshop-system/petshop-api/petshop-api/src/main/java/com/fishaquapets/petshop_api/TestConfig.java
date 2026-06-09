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
import java.util.HashSet;

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
        // CATEGORIAS
        Category c1 = new Category(null, "Alimentos", CategoryType.PRODUCT);
        Category c2 = new Category(null, "Acessórios", CategoryType.PRODUCT);
        Category c3 = new Category(null, "Higiene", CategoryType.PRODUCT);
        Category c4 = new Category(null, "Peixes", CategoryType.PRODUCT);
        Category c5 = new Category(null, "Aquarismo", CategoryType.PRODUCT);

        // Novas Categorias Adicionadas
        Category c6 = new Category(null, "Farmácia", CategoryType.PRODUCT);
        Category c7 = new Category(null, "Brinquedos", CategoryType.PRODUCT);
        Category c8 = new Category(null, "Roupas", CategoryType.PRODUCT);
        Category c9 = new Category(null, "Aves", CategoryType.PRODUCT);

        categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));

        // FORNECEDORES
        Supplier f1 = new Supplier(null, "PetDistribuidora S.A.", "contato@petdist.com");
        Supplier f2 = new Supplier(null, "MegaPet Atacado", "vendas@megapet.com");
        Supplier f3 = new Supplier(null, "Aquários Brasil Ltda.", "contato@aquabrasil.com");
        Supplier f4 = new Supplier(null, "OceanTech", "suporte@oceantech.com");
        Supplier f5 = new Supplier(null, "PharmaPet Vet", "vendas@pharmapet.com");
        Supplier f6 = new Supplier(null, "PiuPiu Distribuidora", "aves@piupiu.com");
        Supplier f7 = new Supplier(null, "Boutique Animal", "moda@boutiqueanimal.com");
        Supplier f8 = new Supplier(null, "FunPet Toys", "comercial@funpettoys.com");

        supplierRepository.saveAll(Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8));

        // PRODUTOS
        Product p1 = new Product(null, "Ração Premium 15kg", new BigDecimal("250.00"), "Ração de alta qualidade para cães adultos",
                50, 20, Instant.parse("2026-01-10T08:30:00Z"), new BigDecimal("10.00"), new BigDecimal("120.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c1)), new HashSet<>(Arrays.asList(f1, f2)));

        Product p2 = new Product(null, "Shampoo Pet suave", new BigDecimal("45.00"), "Shampoo hipoalergênico",
                100, 30, Instant.parse("2026-02-15T10:00:00Z"), BigDecimal.ZERO, new BigDecimal("20.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c3)), new HashSet<>(Arrays.asList(f2)));

        Product p3 = new Product(null, "Coleira de Couro", new BigDecimal("80.00"), "Coleira resistente tamanho G",
                30, 10, Instant.parse("2026-03-05T14:20:00Z"), new BigDecimal("5.00"), new BigDecimal("35.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c2)), new HashSet<>(Arrays.asList(f1)));

        Product p4 = new Product(null, "Aquário 20L Básico", new BigDecimal("150.00"), "Aquário de vidro simples com tampa",
                15, 5, Instant.parse("2026-03-20T09:45:00Z"), new BigDecimal("5.00"), new BigDecimal("70.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c5, c2)), new HashSet<>(Arrays.asList(f3)));

        Product p5 = new Product(null, "Aquário 100L Profissional", new BigDecimal("850.00"), "Vidro extra clear com acabamento em silicone preto",
                5, 2, Instant.parse("2026-04-02T11:30:00Z"), new BigDecimal("10.00"), new BigDecimal("400.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c5)), new HashSet<>(Arrays.asList(f3, f4)));

        Product p6 = new Product(null, "Filtro Externo Hang-on 300L/H", new BigDecimal("120.00"), "Filtragem química, física e biológica",
                25, 10, Instant.parse("2026-04-10T16:00:00Z"), BigDecimal.ZERO, new BigDecimal("60.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c5, c2)), new HashSet<>(Arrays.asList(f4)));

        Product p7 = new Product(null, "Termostato 50W", new BigDecimal("85.00"), "Aquecedor automático com regulagem",
                40, 15, Instant.parse("2026-05-01T13:15:00Z"), new BigDecimal("15.00"), new BigDecimal("40.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c5, c2)), new HashSet<>(Arrays.asList(f4)));

        Product p8 = new Product(null, "Luminária LED RGB 40cm", new BigDecimal("210.00"), "Iluminação potente para plantas naturais",
                12, 5, Instant.parse("2026-05-12T10:30:00Z"), new BigDecimal("8.00"), new BigDecimal("100.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c5, c2)), new HashSet<>(Arrays.asList(f4)));

        Product p9 = new Product(null, "Kit Sachê Gatos Premium 12un", new BigDecimal("35.00"), "Ração úmida sabor salmão",
                80, 20, Instant.parse("2026-05-20T08:00:00Z"), new BigDecimal("5.00"), new BigDecimal("18.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c1)), new HashSet<>(Arrays.asList(f2)));

        // Modificado: Agora pertence à categoria Brinquedos (c7) e Fornecedor FunPet (f8)
        Product p10 = new Product(null, "Arranhador Torre 1.5m", new BigDecimal("280.00"), "Arranhador de pelúcia com 3 andares e rede",
                10, 3, Instant.parse("2026-05-25T14:00:00Z"), new BigDecimal("15.00"), new BigDecimal("120.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c7, c2)), new HashSet<>(Arrays.asList(f8, f1)));

        Product p11 = new Product(null, "Kit Teste de pH", new BigDecimal("45.00"), "Mede o pH da água doces e salgadas (100 testes)",
                45, 15, Instant.parse("2026-06-01T09:30:00Z"), BigDecimal.ZERO, new BigDecimal("15.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c5, c4)), new HashSet<>(Arrays.asList(f4)));

        Product p12 = new Product(null, "Areia Sanitária Biodegradável 4kg", new BigDecimal("30.00"), "Forma torrões firmes e elimina odores",
                120, 40, Instant.parse("2026-06-05T11:15:00Z"), new BigDecimal("10.00"), new BigDecimal("12.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c3)), new HashSet<>(Arrays.asList(f1, f2)));

        Product p13 = new Product(null, "Bomba Submersa 1000L/H", new BigDecimal("180.00"), "Bomba silenciosa, ideal para filtros e fontes",
                20, 5, Instant.parse("2026-06-08T16:45:00Z"), new BigDecimal("12.00"), new BigDecimal("85.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c5, c2)), new HashSet<>(Arrays.asList(f3, f4)));

        // Novos Produtos Explorando as Novas Categorias e Fornecedores
        Product p14 = new Product(null, "Antipulgas e Carrapatos 10-20kg", new BigDecimal("95.00"), "Comprimido mastigável ação rápida",
                60, 20, Instant.parse("2026-06-08T09:00:00Z"), BigDecimal.ZERO, new BigDecimal("45.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c6)), new HashSet<>(Arrays.asList(f5)));

        Product p15 = new Product(null, "Bolinha Maciça Cães", new BigDecimal("25.00"), "Borracha super resistente para cães destruidores",
                150, 50, Instant.parse("2026-06-08T10:15:00Z"), new BigDecimal("5.00"), new BigDecimal("8.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c7)), new HashSet<>(Arrays.asList(f8)));

        Product p16 = new Product(null, "Gaiola Calopsita Luxo", new BigDecimal("320.00"), "Acompanha poleiros, comedouros e balanço",
                8, 3, Instant.parse("2026-06-09T08:30:00Z"), new BigDecimal("10.00"), new BigDecimal("150.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c9, c2)), new HashSet<>(Arrays.asList(f6)));

        Product p17 = new Product(null, "Moletom Pet Inverno Tam. M", new BigDecimal("65.00"), "Roupinha de algodão macio para dias frios",
                40, 15, Instant.parse("2026-06-09T11:45:00Z"), BigDecimal.ZERO, new BigDecimal("25.00"), BigDecimal.ZERO,
                new HashSet<>(Arrays.asList(c8)), new HashSet<>(Arrays.asList(f7)));

        // Salvando TODOS os 17 produtos no banco de dados
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17));

        // Associações
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

        // VENDAS
        // -------------------------------------------------------------------------
        // Venda 1: Pagamento integral via PIX (Sem descontos)
        // -------------------------------------------------------------------------
        Sale v1 = new Sale(null, Instant.parse("2026-01-05T09:15:00Z"), PaymentStatus.PAGA, PaymentMethod.PIX,
                new BigDecimal("225.00"), Arrays.asList("Entrega realizada na portaria"), SaleType.DELIVERY);
        v1.addItem(p1, 1, 0);
        saleRepository.save(v1);
        orderItemRepository.saveAll(v1.getItens());

        // -------------------------------------------------------------------------
        // Venda 2: Pagou apenas uma parte. Aquário (p5) com 10% de desconto!
        // -------------------------------------------------------------------------
        Sale v2 = new Sale(null, Instant.parse("2026-01-10T14:30:00Z"), PaymentStatus.PAGAMENTO_PARCIAL, PaymentMethod.CARTAO_CREDITO,
                new BigDecimal("500.00"), Arrays.asList("Cliente solicitará instalação posterior"), SaleType.RESERVATION);
        v2.addItem(p5, 1, 10); // <-- 10% de desconto APENAS neste item
        v2.addItem(p6, 1, 0);
        v2.addItem(p7, 1, 0);
        saleRepository.save(v2);
        orderItemRepository.saveAll(v2.getItens());

        // -------------------------------------------------------------------------
        // Venda 3: Venda pendente
        // -------------------------------------------------------------------------
        Sale v3 = new Sale(null, Instant.parse("2026-02-15T11:00:00Z"), PaymentStatus.PENDENTE, PaymentMethod.DINHEIRO,
                BigDecimal.ZERO, Arrays.asList("Aguardando retirada em loja"), SaleType.RESERVATION);
        v3.addItem(p4, 1, 0);
        v3.addItem(p2, 2, 0);
        saleRepository.save(v3);
        orderItemRepository.saveAll(v3.getItens());

        // -------------------------------------------------------------------------
        // Venda 4: Múltiplos itens
        // -------------------------------------------------------------------------
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

        // -------------------------------------------------------------------------
        // VENDA 9: Entrega com Frete Aplicado
        // -------------------------------------------------------------------------
        Sale v9 = new Sale(null, Instant.parse("2026-06-01T10:00:00Z"), PaymentStatus.PAGA, PaymentMethod.PIX,
                new BigDecimal("150.00"), Arrays.asList("Deixar na portaria com o zelador"), SaleType.DELIVERY);
        v9.setFreight(new BigDecimal("15.00")); // <-- Adicionado frete para testar o seu DTO no front-end!
        v9.addItem(p1, 1, 0); // Ração
        v9.addItem(p2, 2, 0); // Shampoo
        saleRepository.save(v9);
        orderItemRepository.saveAll(v9.getItens());

        // -------------------------------------------------------------------------
        // VENDA 10: Compra grande de balcão (Cliente VIP com desconto duplo)
        // -------------------------------------------------------------------------
        Sale v10 = new Sale(null, Instant.parse("2026-06-02T14:20:00Z"), PaymentStatus.PAGA, PaymentMethod.CARTAO_CREDITO,
                new BigDecimal("300.00"), Arrays.asList("Cliente VIP, aplicou desconto em itens e na compra total"), SaleType.COUNTER_SALE);
        v10.setDiscountPercentage(5); // 5% de desconto na venda toda
        v10.addItem(p3, 2, 10); // 10% de desconto APENAS nestas coleiras
        v10.addItem(p8, 1, 15); // 15% de desconto nesta luminária
        saleRepository.save(v10);
        orderItemRepository.saveAll(v10.getItens());

        // -------------------------------------------------------------------------
        // VENDA 11: Reserva pendente (Cliente ligou pedindo para guardar)
        // -------------------------------------------------------------------------
        Sale v11 = new Sale(null, Instant.parse("2026-06-03T09:30:00Z"), PaymentStatus.PENDENTE, PaymentMethod.DINHEIRO,
                BigDecimal.ZERO, Arrays.asList("Cliente passa para buscar amanhã à tarde"), SaleType.RESERVATION);
        v11.addItem(p1, 2, 0);
        v11.addItem(p3, 1, 0);
        saleRepository.save(v11);
        orderItemRepository.saveAll(v11.getItens());

        // -------------------------------------------------------------------------
        // VENDA 12: Pagamento Parcial no Balcão (Faltou dinheiro)
        // -------------------------------------------------------------------------
        Sale v12 = new Sale(null, Instant.parse("2026-06-04T16:45:00Z"), PaymentStatus.PAGAMENTO_PARCIAL, PaymentMethod.DINHEIRO,
                new BigDecimal("100.00"), Arrays.asList("Faltou 20 reais, cliente é de confiança e traz amanhã"), SaleType.COUNTER_SALE);
        v12.addItem(p4, 1, 0); // Aquário
        saleRepository.save(v12);
        orderItemRepository.saveAll(v12.getItens());

        // -------------------------------------------------------------------------
        // VENDA 13: Delivery estornado antes de sair para entrega
        // -------------------------------------------------------------------------
        Sale v13 = new Sale(null, Instant.parse("2026-06-05T11:15:00Z"), PaymentStatus.ESTORNADA, PaymentMethod.PIX,
                BigDecimal.ZERO, Arrays.asList("Cliente cancelou pelo WhatsApp antes do motoboy sair"), SaleType.DELIVERY);
        v13.setFreight(new BigDecimal("10.00")); // Tinha frete lançado, mas a venda foi estornada
        v13.addItem(p1, 1, 0);
        saleRepository.save(v13);
        orderItemRepository.saveAll(v13.getItens());

        // -------------------------------------------------------------------------
        // VENDA 14: Aquarismo avançado (Promoção geral de 20%)
        // -------------------------------------------------------------------------
        Sale v14 = new Sale(null, Instant.parse("2026-06-05T18:00:00Z"), PaymentStatus.PAGA, PaymentMethod.CARTAO_DEBITO,
                new BigDecimal("600.00"), Arrays.asList("Promoção semana do aquarismo"), SaleType.COUNTER_SALE);
        v14.setDiscountPercentage(20); // 20% no total
        v14.addItem(p5, 2, 0);
        v14.addItem(p6, 2, 0);
        v14.addItem(p7, 2, 0);
        saleRepository.save(v14);
        orderItemRepository.saveAll(v14.getItens());

        System.out.println("Seeding realizado com sucesso!");
    }

}
