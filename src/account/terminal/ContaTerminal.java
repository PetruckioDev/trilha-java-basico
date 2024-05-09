package account.terminal;

import account.terminal.entity.Conta;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaTerminal {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(ContaTerminal.class.getName());

    public static void main(String[] args) {
        run();
        SCANNER.close();
    }

    public static void run() {

        try {

            logger.info("Por favor, digite o número da Conta:");
            int numero = Integer.parseInt(SCANNER.nextLine());

            logger.info("Por favor, digite o número da Agência:");
            String agencia = SCANNER.nextLine();

            logger.info("Por favor, digite o nome do cliente:");
            String nomeCliente = SCANNER.nextLine().toUpperCase();

            logger.info("Por favor, digite o saldo da numero:");
            BigDecimal saldo = new BigDecimal(SCANNER.nextLine());

            if (!isValidInput(numero, agencia, nomeCliente, saldo)) {
                logger.warning("Por favor, forneça entradas válidas!");
                return;
            }

            Conta cliente = new Conta(numero, agencia, nomeCliente, saldo);
            String bemVindoNovoCliente = """
                    Olá %s, obrigado por criar uma conta em nosso banco.
                    Sua agência é %s, conta %d e seu saldo é %.2f já está disponível para saque.
                    """.formatted(cliente.cliente(), cliente.agencia(), cliente.numero(), cliente.saldo());

            logger.info(bemVindoNovoCliente);

        } catch (NumberFormatException | InputMismatchException e) {
            logger.log(Level.WARNING, "Por favor, forneça entradas válidas!", e);
        }

    }

    private static boolean isValidInput(int numero, String agencia, String nomeCliente, BigDecimal saldo) {
        return numero > 0 && !agencia.isEmpty() && !nomeCliente.isEmpty() && saldo.compareTo(BigDecimal.ZERO) >= 0;
    }
}