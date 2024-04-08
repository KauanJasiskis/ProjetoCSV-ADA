Projeto CSV - ADA BootCamp
Neste projeto, decidimos manipular arquivos CSV e realizar algumas operações sobre eles.

Operações:
Ler o arquivo CSV e salvar no banco de dados.
Utilizar funções de stream para realizar as seguintes verificações a partir dos dados do banco:
Quantidade de categorias no estoque.
Quantidade por categoria no estoque.
Verificar se algum produto tem baixa quantidade no estoque.
Calcular o valor médio de todos os produtos no estoque.
Incluir um método para gravar todos os dados do banco em um arquivo CSV separado.
Como utilizar o programa:
Clone o repositório do git.
No terminal da sua IDE, utilize o comando docker-compose up.
Configure seu banco de dados MySQL ou utilize o DBGate com base na imagem especificada no arquivo docker-compose.
Se desejar realizar alguma inserção no arquivo original, basta modificar o arquivo produtos.csv, e o programa inserirá os dados no banco.
O arquivo para resgatar o que está no banco e salvá-lo nele é produtosDoBanco.csv.
Certifique-se de que o arquivo tenha uma linha adicional abaixo da última linha com dados informados para evitar bugs.
