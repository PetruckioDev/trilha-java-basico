package account.terminal.entity;

import java.math.BigDecimal;

public record Conta(
        int numero,
        String agencia,
        String cliente,
        BigDecimal saldo
) {
}
