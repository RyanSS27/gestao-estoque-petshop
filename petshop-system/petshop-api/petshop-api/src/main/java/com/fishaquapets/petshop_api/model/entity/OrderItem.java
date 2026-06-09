package com.fishaquapets.petshop_api.model.entity;


import com.fishaquapets.petshop_api.model.pk.OrderItemPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "itens_pedido")
public class OrderItem {
    @Setter(AccessLevel.NONE)
    @EmbeddedId // id integrado
    private OrderItemPK id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "porcentagem_desconto_em_venda")
    private Integer discountPercentage;

    public OrderItem() {}

    public OrderItem(Sale sale, Product product, Integer quantidade, Integer discountPercentage) {
        this.id = new OrderItemPK(sale, product);
        this.quantidade = quantidade;
        this.valorUnitario = product.getSalePrice();
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getTotalItemValue() {
        if (valorUnitario == null || quantidade == null)
            return BigDecimal.ZERO;

        BigDecimal subTotalBruto = valorUnitario.multiply(BigDecimal.valueOf(quantidade));

        // Aplica o desconto, caso exista
        if (this.discountPercentage != null && this.discountPercentage > 0) {

            // Transforma o Integer em BigDecimal e divide por 100 (Ex: 15 vira 0.1500)
            BigDecimal fatorDesconto = BigDecimal.valueOf(this.discountPercentage)
                    .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

            // Multiplica o subtotal bruto pelo fator para descobrir os "reais" descontados
            BigDecimal valorDesconto = subTotalBruto.multiply(fatorDesconto);

            // Retorna o subtotal bruto menos o desconto em dinheiro
            return subTotalBruto.subtract(valorDesconto).setScale(2, RoundingMode.HALF_UP);
        }

        // Retorna apenas o bruto se não houver desconto
        return subTotalBruto.setScale(2, RoundingMode.HALF_UP);
    }

    public Sale getVenda() {
        return id.getSale();
    }

    public Product getProduto() {
        return id.getProduct();
    }

    public Long getSaleId() {
        return this.id.getSaleId();
    }

    public Long getProductId() {
        return this.id.getProductId();
    }

    public String getProductName() { return id.getProduct().getName(); }

    /*
        Por enquanto, não utilizados (passíveis de remoção):

        public void adicionar(int quantity) {
            this.quantity += quantity;
        }

        public void retirara(int quantity) {
            this.quantity += quantity;
        }
    */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}