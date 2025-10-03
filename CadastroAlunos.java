import java.awt.*; // Importa as classes
import java.util.*; // Importa componentes gráficos
import java.util.List; // Importa utilitários
import javax.swing.*;
import javax.swing.border.EmptyBorder; // importa o EmptyBorder


//= Classe que representa um "Nó" da árvore binária =

class Node {    

 String nome;   // Guarda o nome do aluno
 Node esquerda, direita; // Ligações para o nó da esquerda e da direita (subárvores)


 // Construtor do nó: recebe o nome e cria sem filhos (esquerda e direita nulos)

 Node(String nome) {  

  this.nome = nome;
  this.esquerda = null;
  this.direita = null;

 }

}


// = Classe da Árvore Binária de Busca =

class ArvoreBinaria {

 private Node raiz;  // Nó raiz da árvore (início). Começa como null.

 // Construtor: inicia a árvore vazia

 public ArvoreBinaria() {

  this.raiz = null;

 }

 // Inserir um novo aluno na árvore

 public boolean inserir(String nome) {

  if (buscar(nome)) { // Se já existe esse nome, não insere de novo
   return false;

  }

  raiz = inserirRec(raiz, nome); // Caso contrário, chama a função recursiva
  return true;

 }

 // Função recursiva para inserir um novo nó na árvore

 private Node inserirRec(Node no, String nome) {

  if (no == null) {    // Achou espaço vazio na árvore
   return new Node(nome);  // Cria o novo nó (aluno)

  }

  if (nome.compareTo(no.nome) < 0) { // Se o nome for "menor" (vem antes no alfabeto)
   no.esquerda = inserirRec(no.esquerda, nome);
  } else {       // Se o nome for "maior" (vem depois no alfabeto)
   no.direita = inserirRec(no.direita, nome);

  }

  return no; // Retorna o nó atualizado

 }

 // Buscar todos os alunos que contenham um trecho do nome

  public List<String> buscarPorParte(String trecho) {
  List<String> resultados = new ArrayList<>();

  buscarParteRec(raiz, trecho.toLowerCase(), resultados);
  return resultados;

 }

 private void buscarParteRec(Node no, String trecho, List<String> resultados) {

  if (no != null) {
   buscarParteRec(no.esquerda, trecho, resultados);
  if (no.nome.toLowerCase().contains(trecho)) {
   resultados.add(no.nome);
  }

  buscarParteRec(no.direita, trecho, resultados);
   }

 }

 // Buscar se um aluno já existe na árvore

 public boolean buscar(String nome) {
  return buscarRec(raiz, nome); // Começa a busca a partir da raiz

 }

 // Função recursiva de busca

 private boolean buscarRec(Node no, String nome) {

  if (no == null) return false;  // Nó vazio → não encontrou
  if (nome.equals(no.nome)) return true; // Encontrou o nome
  if (nome.compareTo(no.nome) < 0) { // Se for menor, busca na esquerda
   return buscarRec(no.esquerda, nome);

  } else {       // Se for maior, busca na direita
   return buscarRec(no.direita, nome);

  }

 }

 // Listar todos os alunos em ordem alfabética

 public java.util.List<String> listarEmOrdem() {

  java.util.List<String> nomes = new ArrayList<>(); // Lista para guardar os nomes
  emOrdem(raiz, nomes); // Percorre a árvore em ordem
  return nomes;
 }

 // Percorre a árvore em ordem: esquerda -> raiz -> direita
 private void emOrdem(Node no, java.util.List<String> nomes) {

  if (no != null) {
   emOrdem(no.esquerda, nomes); // Primeiro percorre a esquerda
   nomes.add(no.nome);   // Depois adiciona o nome do nó atual
   emOrdem(no.direita, nomes); // Por fim percorre a direita

  }

 }

}

// = Classe Principal =

public class CadastroAlunos {

 // Cada campus terá sua própria árvore de alunos

 private static Map<String, ArvoreBinaria> campusArvores = new LinkedHashMap<>();

 // Inicializa os campi da universidade (cada um com uma árvore vazia)

 static {
  campusArvores.put("Anália Franco", new ArvoreBinaria());
  campusArvores.put("Guarulhos", new ArvoreBinaria());
  campusArvores.put("Liberdade", new ArvoreBinaria());
  campusArvores.put("Paulista", new ArvoreBinaria());
  campusArvores.put("São Miguel", new ArvoreBinaria());
  campusArvores.put("Santo Amaro", new ArvoreBinaria());
  campusArvores.put("Villa Lobos", new ArvoreBinaria());

 }

 // Método principal (ponto de entrada do programa)
  public static void main(String[] args) {

  // Cria a tela gráfica de forma segura (thread do Swing)

  SwingUtilities.invokeLater(CadastroAlunos::criarTelaPrincipal);

 }

 // Criação da janela principal do sistema

 private static void criarTelaPrincipal() {

  JFrame janela = new JFrame("Árvore Binária - UNICSUL"); // Cria a janela principal
  janela.setSize(400, 300); // Define tamanho da janela
  janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao clicar no "X"
  janela.setLayout(new BorderLayout()); // Layout principal (Norte, Centro, Sul...)

  // Título no topo da janela
  JLabel titulo = new JLabel("Sistema de Cadastro de Alunos", SwingConstants.CENTER);
  titulo.setFont(new Font("Arial", Font.BOLD, 16));
  janela.add(titulo, BorderLayout.NORTH);

  // Painel para organizar os botões em grade

  JPanel botoesPanel = new JPanel();
  botoesPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 linhas, 1 coluna, espaçamento 10px
  botoesPanel.setBorder(new EmptyBorder(20, 40, 20, 40)); // Margens: top=20, left=40, bottom=20, right=40

  // Botão "Cadastrar Novo Aluno"

  JButton btnCadastrar = new JButton("Cadastrar Novo Aluno");
  btnCadastrar.addActionListener(e -> cadastrarAluno()); // Ao clicar, chama a função cadastrarAluno()

  // Botão "Localizar Aluno"

  JButton btnLocalizar = new JButton("Localizar Aluno");
  btnLocalizar.addActionListener(e -> localizarAluno()); // Ao clicar, chama localizarAluno()

  // Botão "Listar Alunos"

  JButton btnListar = new JButton("Listar Alunos do Campus");
  btnListar.addActionListener(e -> listarAlunos()); // Ao clicar, chama listarAlunos()

  // Adiciona os botões no painel

  botoesPanel.add(btnCadastrar);
  botoesPanel.add(btnLocalizar);
  botoesPanel.add(btnListar);

  // Coloca os botões no centro da tela

  janela.add(botoesPanel, BorderLayout.CENTER);

  // Mostra a janela na tela
  janela.setVisible(true);

 }

 // --- Janela para cadastrar aluno ---

  private static void cadastrarAluno() {

  JTextField nomeField = new JTextField(); // Campo de texto para digitar o nome
  JComboBox<String> campusBox = new JComboBox<>(campusArvores.keySet().toArray(new String[0])); // Lista de campi
  // Painel para organizar os campos

  JPanel panel = new JPanel(new GridLayout(0, 1));
  panel.add(new JLabel("Nome do aluno:"));
  panel.add(nomeField);
  panel.add(new JLabel("Selecione o campus:"));
  panel.add(campusBox);

  // Caixa de diálogo com opções OK / Cancelar
  int result = JOptionPane.showConfirmDialog(null, panel, "Cadastrar Novo Aluno",
    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
  if (result == JOptionPane.OK_OPTION) { // Se o usuário clicou em "OK"
   String nome = nomeField.getText().trim(); // Pega o texto digitado
   String campus = (String) campusBox.getSelectedItem(); // Pega o campus selecionado

   // Verifica se os campos foram preenchidos

   if (nome.isEmpty() || campus == null) {
    JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro", JOptionPane.WARNING_MESSAGE);

    return;
   }

   // Verifica se já existe esse aluno em algum campus

   ArvoreBinaria arvoreDoCampus = campusArvores.get(campus);

     if (arvoreDoCampus.buscar(nome)) {
     JOptionPane.showMessageDialog(null, "O aluno '" + nome + "' já está cadastrado nesse campus.",
      "Erro", JOptionPane.ERROR_MESSAGE);

    return;

   }

   // Insere o aluno no campus escolhido

   if (campusArvores.get(campus).inserir(nome)) {
    JOptionPane.showMessageDialog(null, "Aluno '" + nome + "' cadastrado no campus " + campus + ".");

   } else {
    JOptionPane.showMessageDialog(null, "Falha ao cadastrar aluno.", "Erro", JOptionPane.ERROR_MESSAGE);

   }

  }

 }

 // = Função para localizar um aluno pelo nome ou parte dele =

 private static void localizarAluno() {
  String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
  if (nome == null || nome.trim().isEmpty()) return; // Se não digitou nada, cancela

  boolean encontrado = false;

 // Procura em cada campus

  for (Map.Entry<String, ArvoreBinaria> entry : campusArvores.entrySet()) {
  List<String> resultados = entry.getValue().buscarPorParte(nome);

   if (!resultados.isEmpty()) {
   JOptionPane.showMessageDialog(null,
    "Campus: " + entry.getKey() + "\n" + String.join("\n", resultados));

   encontrado = true;

  }

 }
 // Se não achou em nenhum campus

  if (!encontrado) {
   JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado com '" + nome + "'.");
 }

}

 // --- Função para listar os alunos de um campus ---

 private static void listarAlunos() {

  // Caixa de seleção para escolher o campus

  JComboBox<String> campusBox = new JComboBox<>(campusArvores.keySet().toArray(new String[0]));
  int result = JOptionPane.showConfirmDialog(null, campusBox,

    "Selecione o campus", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

  if (result == JOptionPane.OK_OPTION) { // Se clicou em "OK"
   String campus = (String) campusBox.getSelectedItem();

   if (campus != null) {

    // Pega os nomes do campus escolhido

    java.util.List<String> nomes = campusArvores.get(campus).listarEmOrdem();

    if (nomes.isEmpty()) {
     JOptionPane.showMessageDialog(null, "(Nenhum aluno cadastrado)");

    } else {
     JOptionPane.showMessageDialog(null, String.join("\n", nomes),
       "Alunos do campus " + campus, JOptionPane.INFORMATION_MESSAGE);
    }

   }

  }

 }

}

/*
Projeto 1 – Árvores Binárias
Integrantes:

- Nicole de Oliveira Matos
- Kelvin Medina da Silva Dias
- Rodrigo Leal Ferreira Castro
*/