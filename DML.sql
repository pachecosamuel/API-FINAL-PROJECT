COPY public.categoria (id_categoria, nome, descricao) FROM stdin;
1	Smartphone	um celular que combina recursos de computadores pessoais, com funcionalidades avançadas.
2	Headphone	Tipo de fone de ouvido mais completo, que oferece alto-falantes e caixa acústica maiores.
3	Videogames	Jogos eletrônicos de interatividade e controle onde o jogador decide as próprias ações no game..
4	Roupas de Academia	Roupas especificas para pratica de esportes.
5	Perfume	Mistura de óleos, álcool e água, cuja função é proporcionar uma agradável e duradoura fragrância de aroma.
6	Tênis masculino	Tênis especialmente selecionados



COPY public.produto (id_produto, nome, descricao, qtd_estoque, data_cadastro, valor_unitario, imagem, id_categoria) FROM stdin;
2	JBL Tune 510BT	oferecem o potente som JBL Pure Bass sem fios.	50	2022-06-06	189.99	\N	2
4	Camisa Nike Dri-Fit	A tecnologia Dri-FIT ajuda a manter seu corpo seco e confortável.	50	2022-06-06	99.9	\N	4
5	Carolina Herrera Good Girl	A Fragrância: Por um lado, ela é Boa, por outro, é Má.	50	2022-06-06	499.99	\N	5
7	Jaqueta Jordan Jumpman	revestimento leve em woven com forro de tela na estrutura e tecido macio nas mangas	80	2022-06-07	535.5	\N	4
9	Galaxy S22 Ultra	Fina e ousada, uma estrutura polida envolve o formato para uma simetria elegante	120	2022-06-07	8549.5	\N	1
1	Iphone 13 Pro 256gb	O maior upgrade do sistema de câmera Pro até hoje. Tela Super Retina xdr com ProMotion.	48	2022-06-06	8500.5	\N	1
10	Notebook Gamer Alienware	Eleve seu gameplay a novos patamares com o Advanced Alienware Cryo-tech™	19	2022-06-07	37498.5	\N	2
3	Sony PlayStation 5	Redefinindo as regras do que o console PS5 pode fazer.	48	2022-06-06	4500	\N	3
8	The One Dolce&Gabbana	tradução de um estilo, a personificação da mulher moderna, ousada, única.	95	2022-06-07	493.62	\N	5
6	Nike Air Max 90 SE	Coloque raio de sol nos pés com o Nike Air Max 90 SE.	58	2022-06-07	889.9	\N	6



COPY public.endereco (id_endereco, cep, rua, bairro, cidade, numero, complemento, uf) FROM stdin;
1	25680387	Fernandes Vieira	Retiro	Petrópolis	1271	n/a	RJ
2	23821596	Avenida Atlântica	Copacabana	Rio de Janeiro	852	Ap 805	RJ
3	40026230	Rua das Laranjeiras	Pelourinho	Salvador	1952	n/a	BA
4	88015070	Centro Florianópolis	Centro	Florianópolis	552	n/a	SC
5	20230010	Centro do Rio	Centro Rio de Janeiro	Rio de Janeiro	802	AP 503	RJ
35	20521-160	Rua Desembargador Izidro	Tijuca	Rio de Janeiro	805		RJ
36	23016-452	Rua Marechal Lott	Campo Grande	Rio de Janeiro	1278		RJ
37	21230-800	Rua General Costa Júnior	Irajá	Rio de Janeiro	105		RJ
38	85862-275	Rua Nicolas Ibraim NasserDistrito Inudstrial	Três Bandeiras	Foz do Iguaçu	2785	até 99998 - lado par	PR
40	85868-060	Rua MangueiraJardim das Laranjeiras	KLP	Foz do Iguaçu	12		PR
41	08970-970	Rua XV de Novembro	Centro	Salesópolis	123	786	SP
42	01440-900	Rua Atlântica	Jardim América	São Paulo	1442	81	SP
43	01440-900	Rua Atlântica	Jardim América	São Paulo	4142	81	SP
44	76900-227	Rua Arseno Rodrigues	Urupá	Ji-Paraná	467	de 54/55 a 218/219	RO
45	76808-034	Rua Sapucaia	Cohab	Porto Velho	25		RO



COPY public.cliente (id_cliente, email, nome_completo, cpf, telefone, data_nascimento, id_endereco) FROM stdin;
1	pacheco.samuel@hotmail.com	Samuel Pacheco	678.514.330-61	2422485027	0020-03-18	1
2	simonerodrigues@gmail.com	Simone Rodrigues	12076809074	2422568696	0011-10-31	1
5	obamabarack@boll.com	Barack Obama	478.989.240-96	2122558877	0022-10-21	45
6	taldolincoln@gmail.com	Abraham Lincoln	586.767.510-64	2222443322	0017-06-01	\N
7	elvinho@gmail.com	Elvis Presley	473.426.690-59	3222441188	0027-09-02	\N
8	justen@gmail.com	Justin Bieber	657.548.090-00	4322556633	0031-03-14	\N
9	omelhoronaldo@gmail.com	Cristiano Ronaldo	263.910.630-95	2222448977	0011-05-18	\N
10	basquete@gmail.com	Kobe Bryant	443.221.250-00		0021-03-28	\N
11	anittax@gmail.com	Anitta Larissa	001.942.600-38	2222336054	0024-06-03	\N


COPY public.pedido (id_pedido, data_pedido, data_entrega, data_envio, status, id_cliente) FROM stdin;
1	2022-06-07	\N	\N	f	1
2	2022-06-07	\N	\N	f	5
3	2022-06-07	\N	\N	f	7
4	2022-06-07	\N	\N	f	9
5	2022-06-07	\N	\N	f	11



COPY public.item_pedido (id_item_pedido, quantidade, preco_venda, percentual_desconto, valor_bruto, valor_liquido, id_pedido, id_produto) FROM stdin;
1	2	16150.95	5.00	8500.50	8075.48	1	1
2	1	34873.605	7.00	37498.50	34873.61	2	10
3	2	8640	4.00	4500.00	4320.00	3	3
4	5	2245.971	9.00	493.62	449.19	4	8
5	2	1726.406	3.00	889.90	863.20	5	6