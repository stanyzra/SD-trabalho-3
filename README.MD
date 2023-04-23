# Alunos
Stany Helberth de Souza Gomes da Silva | RA112683

Felipe Roveroni de Lima | RA112675

# Enunciado
Implemente um SD em uma linguagem de sua escolha, e que faça uso de uma das técnicas de passagem de mensagens estudadas na disciplina. O requisito a ser atendido, é que dadas duas ou mais máquinas do SD, se aquela que estiver atendendo requisições cair, a outra deve assumir o controle e o sistema continuar funcionando.

O trabalho pode ser feito em grupos de até 3 pessoas.

O trabalho deve ser apresentado no dia 24/04, mostrando sua execução. Pode ser no laboratório, ou utilizando as máquinas de vocês, ou até mesmo máquinas virtuais.

# Inicializar os servidores e o load balancer
Para compilar os arquivos e iniciar os servidores e o load balancer, rode o script compile_and_start_all.sh (recomenda-se o uso do Bash):

```bash
$ ./compile_and_start_all.sh
```

Com isso, três terminais serão abertos, um para o load balancer e outros dois para os servidores, todos com suas respectivas portas.

# Utilizar o cliente
Para enviar uma mensagem pelo cliente, utilize o seguinte comando no terminal:

```bash
$ java -classpath destDir/ trabalho_3.Client <server_port>
```

Onde `server_port` é a porta do servidor que você gostaria de enviar a mensagem. Note que, no caso desse sistema, o ideal utilizar o valor 1099 para `server_port`, visto que esse é a porta padrão que está sendo utilizada para o load balancer.