package bookSmart;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
	static Scanner entrada = new Scanner(System.in);
	
	Scanner usuarios = new Scanner("catalogoUsuarios.txt");
	
	while(usuarios.hasnext()) {
		Usuario.setNome(usuarios.nextLine());
		Usuario.setEmail(usuarios.nextLine());
		Usuario.setEndereco(usuarios.nextLine());
		Usuario.setSenha(usuarios.nextLine());
		// Usuario.usuarios.add(); fiquei na dúvida aqui se tem que adicionar
		// à lista ou se isso já monta a lista
		
		// Fiquei com dúvida também no Usuario.setEndereco(usuarios.nextLine()),
		// porque endereço se desdobra em vários campos
		// e no arquivo tá um embaixo do outro
		// logradouro
		// numero
		// etc
	}

	static ArrayList<Biblioteca> bibliotecas = new ArrayList<Biblioteca>();// arquivo com todas as bibs

	public static void menu() {
		System.out.println("Bem vindo ao BookSmart! Selecione uma opcao:");
		System.out.println("1) Cadastrar usuario");
		System.out.println("2) Cadastrar biblioteca");
		System.out.println("3) Entrar como usuario");// acho que aqui direciona pro ambiente de user comum, temos que
														// diferenciar do entregador
		System.out.println("4) Entrar como biblioteca");
		int opcaoMenu = entrada.nextInt();
		switch (opcaoMenu) {
		case 1:
			cadastrarUsuario();
		case 2:
			cadastrarUsuario();
		case 3: // quero criar um ambiente de biblioteca assim como tem o dos usuarios
		}
	}

	public static void cadastrarUsuario() {
		System.out.println("Digite o nome: ");
		String nome = entrada.nextLine();
		System.out.println("Digite seu endereco:");
		System.out.println("Logadouro: ");
		String logadouro = entrada.nextLine();
		entrada.nextLine();
		System.out.println("Numero: ");
		int numero = entrada.nextInt();
		System.out.println("Complemento: ");
		String complemento = entrada.nextLine();
		entrada.nextLine();
		System.out.println("Bairro: ");
		String bairro = entrada.nextLine();
		System.out.println("Cidade:");
		String cidade = entrada.nextLine();
		System.out.println("Email");
		String email = entrada.nextLine();
		System.out.println("Escolha uma senha");
		String senha = entrada.nextLine();
		System.out.println("1) CADASTRAR COMO USUARIO, 2) CADASTRAR COMO BIBLIOTECA ");
		int cadastroOpcao = entrada.nextInt();
		switch (cadastroOpcao) {
		case 1:
			Usuario.usuarios
					.add(new Usuario(nome, new Endereco(logadouro, numero, complemento, bairro, cidade), email, senha));
			System.out.println(" Usuario cadastrado com sucesso! Fazer login: ");
			entrar();
		case 2:
			ArrayList<Livro> cadastroCatalogo = new ArrayList<Livro>();
			System.out.println("Adicione os titulos ao seu catalogo. Para parar, digite 0: ");
			while (true) {
				System.out.println("Adicione titulo:");
				String titulo = entrada.nextLine();
				System.out.println("Adicione autor:");
				String autor = entrada.nextLine();
				if (titulo.equals("0")) {
					break;
				} else {
					cadastroCatalogo.add(new Livro(titulo, autor));
				}
			}
			Biblioteca.bibliotecasSistema.add(new Biblioteca(nome,
					new Endereco(logadouro, numero, complemento, bairro, cidade), email, senha, cadastroCatalogo));
		}

	}

	public static void entrar() {
		System.out.println("Digite seu email:");
		String email = entrada.nextLine();
		System.out.println("Digite sua senha");
		String senha = entrada.nextLine();
		for (Usuario user : Usuario.usuarios) {
			for (Entregador entregador : Entregador.voluntariosEntrega) {
				for (Biblioteca biblioteca : Biblioteca.bibliotecasSistema) {
					if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
						ambienteUser(user);
					} else if (entregador.getEmail().equals(email) && entregador.getSenha().equals(senha)) {
						ambienteUser(entregador);
					} else if (biblioteca.getEmail().equals(email) && biblioteca.getSenha().equals(senha)) {
						ambienteUser(biblioteca);
					} else {
						System.out.println("Login ou senha invalidos! Tente novamente: ");
						entrar();
					}
				}
			}
		}
	}

	public static void ambienteUser(Usuario usuario) {

		System.out.println("Ola! O que deseja fazer?");
		System.out.println("1) Doar livro");
		System.out.println("2) Tornar-se um entregador");
		int opcaoComum = entrada.nextInt();
		switch (opcaoComum) {
		case 1:
		case 2:
			usuario.setEhEntregador(true);
			System.out.println("Obrigado por se voluntariar!");
		}

	}

	public static void ambienteUser(Entregador entregador) {
		System.out.println("Ola! O que deseja fazer?");
		System.out.println("1) Doar livro");
		System.out.println("2) Realizar entrega");
		int opcaoEntregador = entrada.nextInt();
		switch (opcaoEntregador) {
		case 1:
		case 2:
			System.out.println("Aqui estao suas entregas pendentes:");
			System.out.println(entregador.entregasPendentes.toString());
		}
	}
	
	public static void ambienteUser(Biblioteca biblioteca) {
		System.out.println("Ola! O que deseja fazer?");
		System.out.println("1) Pedir livro");
		System.out.println("2) Confirmar o recebimento de um livro");
		int opcaoBiblioteca = entrada.nextInt();
		switch(opcaoBiblioteca) {
		case 1: System.out.println("Digite o titulo do livro: ");
		String titulo = entrada.nextLine();
		System.out.println("Digite o autor do livro: ");
		String autor = entrada.nextLine();
			new Pedido(biblioteca, new Livro(titulo, autor)); 
		case 2: biblioteca.selecionarLivrosPedidos();
		System.out.println("Livro recebido com sucesso!");
		}
	}

	public static void main(String[] args) {
		menu();
	}

}
