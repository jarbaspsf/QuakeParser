QuakeParser
===========
Update
======

Ant de Execução criado, digite somente "ant" ou "ant exec" para executar.

Formatado no padrão JSON

Escolhas do desenvolvedor:
=======

Sobre a contagem de kills ~> A contagem de kills funciona da seguinte maneira: 

caso o jogador se suicide = +0 Kill

caso o jogador mate alguem = +1 Kill

caso um evento mate o jogador = -1 kill

Não poderá haver kills negativas, ou seja um jogador morto por evento 5 vezes e logo em seguida ganhou uma kill terá no total uma kill.
 

Notas de desenvolvimento:
======

##1) Entendido o problema

Após selecionar a linha que continha o log de kill (if contains killed)
inicialmente pensei em "cortar" (split) a string por espaços vazios, mas percebi que os jogadores podem ter nomes compostos
então decedi fazer o split pelo caracter ':' isso me dava um array com 3 posições, onde a 3 possição estava:
o nome do jogador que matou alguem
killed
o nome do jogador morto
by
o nome do meio de morte (MOD)

com o seguinte layout:

exemplo killed celular by MOD_ROCKET_SPLASHER

com isso só era preciso fazer sucessivos spits, o primeiro pela palavra killed assim eu poderia ter
o nome do jogador que matou alguem [0]
o nome do jogador que foi morto [1]
by [1]
o nome do meio de morte [1]

a fazer o ultimo split pela palavra by eu teria tudo que seria necessário


##2) Construída e implementada a solução:

Implementado as classes Parser e App nos pacotes quake.log.parser e quake.log.scripts

O contrutor da classe Parser recebe o caminho para o arquivo, e trata a exceção caso esse aquivo esteja fora do padrão
ou não seja encontrado.

Escolhi um HashMap para guardar o "killRate" dos jogadores
da forma: nome (chave), qtdKill (valor)

Inicialmente ao instanciar um objeto parser ele irá identificar os jogadores e 
setar suas kills para 0

O metodo atualizarKills irá percorrer novamente o arquivo e irá iniciar a contagem de kills de cada jogador
seguindo a regra acima. (decisões do desenvolvedor)

##3) Criação testes para a classe Passer

Criação da classe ParserTeste em quake.log.unit

##4) Refatoração

Com os testes feitos dei inicio a refatoração da classe Parser de forma a deixar o codigo mais legivel
e mais facil de ser refatorado no futuro.

##5) Implementado o "PLUS"

Implementando e adicionando Testes para o "PLUS" (Contagem de kills por "meios de mortes")

Notas: 
======

Depois do desenvolvimento, notei que poderia ter utilizado TDD, uma vez que eu já tinha a entendido e resolvido o problema.

O jUnity foi o framework utulizados para testes, acredio que o eclipse (ou netbeans) já estejam integrados com o mesmo

Como desenvolvedor minhas prioridades foram (em ordem de importância):

1 - Fazer Funcionar
2 - Criar Testes que me dessem segurança
3 - Refatorar o codigo para deixa-lo mais fácil de futuramente ser refatorado.
4 - Performance

O Código está comentado.

Foi divertido.
