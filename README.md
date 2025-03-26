🪧Apresentação TCC🔗:

https://www.canva.com/design/DAGiBtz-75E/_nI3561SuuF3fO1Fboi_TA/edit?utm_content=DAGiBtz-75E&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

**Projeto: AproveiteBem** 
<H2>PARA USAR:</H2>
<br>
Para rodaro back é necessário ter: 
MYSQL Workbeach instalado(Foi usado a Versão 8.04)
link para download:https://dev.mysql.com/downloads/workbench/

<h2>CONFIGURAÇÃO</h2>
Configuração do banco, a api foi construiada com banco em localhost, logo a consifguração de user e password vai depender do banco.
No projeto | Vá no arquivo application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/aproveitebem
spring.datasource.username=root
spring.datasource.password=sol123

altere estes 3 componentes conforme as especificações do seu banco. Por padrão o mysql roda na porta 3306, o nome do banco pode manter.
mude as propriedades
username - *****
password - ***** 

<h3>Criando Banco</h3>
agora, no WorkBeach, crie um database. No script do database utlize o código:
Create database aproveitebem;
use aproveitebem;

<h3>Agora é só rodar a aplicação</h3>

**Tipo: Aplicação Web** 

O que é: 

A ideia de desenvolver uma aplicação web para solucionar problemas reais e cotidianos surgiu durante a aula de Análise e Projeto de Sistemas e foi amadurecendo ao longo das diversas disciplinas do Curso Técnico. O AproveiteBem nasceu com o propósito de simplificar tarefas do dia a dia, como a organização da geladeira, oferecendo um sistema de lembretes de validade de alimentos e uma lista de compras integrada. Tudo isso em um único lugar, prático e acessível! 

**Anatomia do projeto:**  

**Front-End:** TypeScript e React 
	
 	> Biblioteca amplamente utilizada, com grande comunidade e suporte.
	> Componentização facilita a reutilização e manutenção do código.
 	> Adiciona tipagem estática ao JavaScript, reduzindo erros em tempo de execução.
	> Melhora a organização do código e a experiência do desenvolvedor

**Back-End:** Java Spring boot 

	> Framework que simplifica a criação de APIs RESTful.
	> Possui integração nativa com bancos de dados e segurança embutida.
	> Facilita a injeção de dependências e configuração automática.

**Banco de Dados:** MySQL 

	> Banco de dados relacional amplamente utilizado, confiável e de fácil gerenciamento.
	> Suporta transações ACID, garantindo consistência e integridade dos dados.
	> Ótima integração com Java e Spring Boot
 

 **Carrinho de Compras:** O usuário pode:  

- **Adicionar** um produto ao carrinho informando o ID do carrinho e o ID do produto.  
- **Remover** um produto do carrinho informando o ID do carrinho e o ID do produto.  
- **Excluir** um carrinho por ID.  

---  

**Categoria:** O usuário pode:  

- **Adicionar** uma categoria informando o nome, produtos relacionados e forma de armazenamento.  
- **Listar** todas as categorias ou buscar por nome.  
- **Buscar** uma categoria por ID.  
- **Deletar** todas as categorias ou deletar por ID.  
- **Editar** uma categoria existente.  

---  

**Item da Lista:** O usuário pode:  

- **Adicionar** um item à lista informando o ID do carrinho, nome do item e quantidade.  
- **Remover** um item da lista por ID.  

---  

**Produto:** O usuário pode:  

- **Adicionar** um produto informando o nome, data de fabricação e validade, categoria e a quantidade.  
- **Listar** todos os produtos ou buscar por nome.  
- **Buscar** um produto por ID.  
- **Deletar** todos os produtos ou deletar por ID.  
- **Editar** um produto existente.  

---  

**Usuários:** O usuário pode:  

- **Cadastrar** um novo usuário informando nome completo, e-mail.
- **Buscar** um usuário pelo e-mail.  
- **Editar** as informações de um usuário (nome completo e e-mail).  
- **Deletar** um usuário pelo e-mail.  
