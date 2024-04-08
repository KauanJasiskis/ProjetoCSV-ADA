Projeto CSV - ADA BootCamp
Neste projeto foi decidido que iriamos manipular arquivos csv e realizar algumas operacoes sobre eles.
As operacoes seriam:
- Ler o arquivo CSV e salvar ele no banco
 A partir dos dados do banco utilizar funcoes stream para verificar:
- a quantidade de categorias no estoque;
- quantidade por categoria no estoque ;
- verificar se algum produto tinha baixa quantidade no estoque;
- calcular o valor medio de todos os produtos no estque;
  Tambem inclui um metodo para gravar tudo que esta no banco em um arquivo CSV separado tambem.
Como utilizar o programa:
- Clone o repositorio todo do git;
-  dentro do terminal da sua IDE utilize o comando "docker-compose up";
-  -configure o seu banco de dados mysql ou dbgate baseado na image que esta no arquivo docker-compose;
-  Se quiser realizar alguma insercao no arquivo original basta modificar o arquivo que o programa vai inserir no banco "produtos.csv";
-  O arquivo novo que vai resgatar o que esta no banco e salvar nele é o "produtosDoBanco.csv";
-  O programa precisa que o arquivo esteja com uma linha adicional em baixo da ultima linha com dados informados para evitar bug!!
  

