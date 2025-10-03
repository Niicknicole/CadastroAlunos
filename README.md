# Cadastro de Alunos
Sistema de cadastro de alunos implementado em Java, utilizando uma **árvore binária** para organização dos dados e uma **interface gráfica (GUI)** para facilitar a interação do usuário.  

## Funcionalidades
- Inserção de novos alunos na árvore binária.
- Busca de alunos pelo nome.
- Remoção de alunos do cadastro.
- Exibição da árvore e dos alunos cadastrados de forma organizada na interface gráfica.

## Tecnologias
- Java (Swing para GUI)
- Estrutura de dados: Árvore Binária

## Descrição da Árvore Binária
O sistema utiliza uma **árvore binária de busca**, onde cada nó representa um aluno.  
- O **nó esquerdo** contém alunos com nomes menores (lexicograficamente) que o nó atual.  
- O **nó direito** contém alunos com nomes maiores.  
- Essa estrutura permite buscas, inserções e remoções eficientes, mantendo os dados organizados de forma hierárquica.
