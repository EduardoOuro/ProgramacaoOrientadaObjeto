/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabancariaestruturada;

import java.util.Scanner;

/**
 *
 * @author luis.eoconrado
 */
public class ContaBancariaEstruturada {

    /**
     * @param args the command line arguments
     */
    static final int TAMANHO = 200;
    static int next = 0;
    static String[] nomes = new String[TAMANHO];
    static String[] cpfs = new String[TAMANHO];
    static int[] numeros = new int[TAMANHO];
    static double[] saldos = new double[TAMANHO];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean sair = false;
        while (!sair) {
            switch (menuOpcoes()) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    saque();
                    break;
                case 3:
                    deposito();
                    break;
                case 4:
                    transferencia();
                    break;
                case 9:
                    sair = true;
                    break;
                default:
                    System.out.println("opção invalida.");
            }
        }
    }

    static int menuOpcoes() {
        System.out.println("Que opção deseja?");
        System.out.println("1 - Cadastrar conta");
        System.out.println("2 - Saque");
        System.out.println("3 - Deposito");
        System.out.println("4 - Transferencia");
        System.out.println("9 - Sair do programa");

        Scanner s = new Scanner(System.in);
        int opcao = s.nextInt();
        s.nextLine();
        return opcao;
    }

    static void cadastrar() {
        Scanner s = new Scanner(System.in);
        System.out.println("Qual é seu nome?");
        String nome = s.nextLine();
        System.out.println("Qual é seu CPF?");
        String cpf = s.nextLine();

        //Guardar os dados
        nomes[next] = nome;
        cpfs[next] = cpf;
        numeros[next] = next;
        saldos[next] = 0.0;

        System.out.println("Olá! " + nome + ", sua nova conta é a: " + next);
        next++;
    }

    static void saque() {
        Scanner s = new Scanner(System.in);
        System.out.println("Qual é o numero da conta?");
        int conta = s.nextInt();
        if (checaConta(conta)) {
            System.out.println("Quanto quer sacar?");
            double valor = s.nextDouble();
            if (retira(conta, valor)) {
                System.out.println("Saque efetuado no valor de " + valor + " reais");
                System.out.println("Seu novo saldo é: " + saldos[conta]);
            }
        }
        s.nextLine();
    }

    static void deposito() {
        Scanner s = new Scanner(System.in);
        System.out.println("Qual é o numero da sua conta?");
        int conta = s.nextInt();
        if (checaConta(conta)) {
            System.out.println("Quanto gostaria de depositar?");
            double valor = s.nextDouble();
            if (deposita(conta, valor)) {
                System.out.println("Seu novo saldo é: " + saldos[conta]);
            }
        }
        s.nextLine();
    }

    static void transferencia() {
        Scanner s = new Scanner(System.in);
        System.out.println("Qual é o numero da sua conta?");
        int conta = s.nextInt();
        if (checaConta(conta)) {
            System.out.println("Qual é o numero da conta de destino?");
            int conta2 = s.nextInt();
            if (checaConta(conta2)) {
                System.out.println("Quanto gostaria de transferir?");
                double valor = s.nextDouble();
                if (retira(conta, valor) && deposita(conta2, valor)) {
                    System.out.println("Transferencia bem sucedida.\n "
                            + "Seu novo saldo é: " + saldos[conta]);
                }
            }
        }
        s.nextLine();
    }

    static boolean checaConta(int conta) {
        if (conta < next) {
            return true;
        } else {
            System.out.println("Numero de conta invalida: " + conta);
            return false;
        }
    }

    static boolean retira(int conta, double valor) {
        if (valor > saldos[conta]) {
            System.out.println("Saldo insuficiente");
            return false;
        } else {
            saldos[conta] -= valor;
            return true;
        }
    }

    static boolean deposita(int conta, double valor) {
        saldos[conta] += valor;
        return true;
    }

}
