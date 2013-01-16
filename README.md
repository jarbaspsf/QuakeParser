QuakeParser
===========

Escolhas do desenvolvedor:
=======

Sobre a contagem de kills ~> A contagem de kills funciona da seguinte maneira: 

caso o jogador se suicide = +0 Kill
caso o jogador mate alguem = +1 Kill
caso um evento mate o jogador = -1 kill

Não poderá haver kills negativas, ou seja um jogador morto por evento 5 vezes e logo em seguida ganhou uma kill terá no total uma kill.
 

Notas de desenvolvimento:
======

1) entendido o problema

2) construido e implementei a solução:
	- Implementado as classes Parser e App nos pacotes quake.log.parser
	e quake.log.scripts

3) criado testes para a classe Passer
	- Criação da classe ParserTeste em quake.log.unit

4) Com os testes feitos dei inicio a refatoração da classe Parser de forma a deixar o codigo mais legivel
e mais facil de ser refatorado no futuro.

5) Implementado o "PLUS" pedido no geist

6) Adicionando testes para o "PLUS" (contagem de meios de mortes)

<<<<<<< HEAD
Notas: depois do desenvolvimento, notei que poderia ter utilizado TDD, uma vez que eu já tinha a entendido e resolvido o problema.


=======
>>>>>>> 27d4d1a61188d772686ff458bbe55958b72c9b22
