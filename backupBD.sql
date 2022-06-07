--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-06-07 12:33:44

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 24833)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id_categoria integer NOT NULL,
    nome character varying(30) NOT NULL,
    descricao character varying(150)
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 24832)
-- Name: categoria_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_id_categoria_seq OWNER TO postgres;

--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 215
-- Name: categoria_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_id_categoria_seq OWNED BY public.categoria.id_categoria;


--
-- TOC entry 212 (class 1259 OID 24809)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    email character varying(100) NOT NULL,
    nome_completo character varying(100) NOT NULL,
    cpf character varying(14) NOT NULL,
    telefone character varying(11),
    data_nascimento date,
    id_endereco integer
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 24808)
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 211
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- TOC entry 210 (class 1259 OID 24802)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
    id_endereco integer NOT NULL,
    cep character varying(9) NOT NULL,
    rua character varying(100) NOT NULL,
    bairro character varying(50) NOT NULL,
    cidade character varying(30),
    numero integer NOT NULL,
    complemento character varying(20),
    uf character varying(2)
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24801)
-- Name: endereco_id_endereco_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.endereco_id_endereco_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_id_endereco_seq OWNER TO postgres;

--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 209
-- Name: endereco_id_endereco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.endereco_id_endereco_seq OWNED BY public.endereco.id_endereco;


--
-- TOC entry 220 (class 1259 OID 24869)
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_pedido (
    id_item_pedido integer NOT NULL,
    quantidade integer NOT NULL,
    preco_venda double precision NOT NULL,
    percentual_desconto numeric(3,2),
    valor_bruto numeric(10,2),
    valor_liquido numeric(10,2),
    id_pedido integer,
    id_produto integer
);


ALTER TABLE public.item_pedido OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24868)
-- Name: item_pedido_id_item_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_pedido_id_item_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_pedido_id_item_pedido_seq OWNER TO postgres;

--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 219
-- Name: item_pedido_id_item_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_pedido_id_item_pedido_seq OWNED BY public.item_pedido.id_item_pedido;


--
-- TOC entry 214 (class 1259 OID 24821)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido (
    id_pedido integer NOT NULL,
    data_pedido date NOT NULL,
    data_entrega date,
    data_envio date,
    status boolean,
    id_cliente integer
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 24820)
-- Name: pedido_id_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedido_id_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pedido_id_pedido_seq OWNER TO postgres;

--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 213
-- Name: pedido_id_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedido_id_pedido_seq OWNED BY public.pedido.id_pedido;


--
-- TOC entry 218 (class 1259 OID 24840)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
    id_produto integer NOT NULL,
    nome character varying(30) NOT NULL,
    descricao character varying(100),
    qtd_estoque integer NOT NULL,
    data_cadastro date,
    valor_unitario double precision NOT NULL,
    imagem character varying(100),
    id_categoria integer
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24839)
-- Name: produto_id_produto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produto_id_produto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_id_produto_seq OWNER TO postgres;

--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 217
-- Name: produto_id_produto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produto_id_produto_seq OWNED BY public.produto.id_produto;


--
-- TOC entry 3192 (class 2604 OID 24836)
-- Name: categoria id_categoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id_categoria SET DEFAULT nextval('public.categoria_id_categoria_seq'::regclass);


--
-- TOC entry 3190 (class 2604 OID 24812)
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- TOC entry 3189 (class 2604 OID 24805)
-- Name: endereco id_endereco; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco ALTER COLUMN id_endereco SET DEFAULT nextval('public.endereco_id_endereco_seq'::regclass);


--
-- TOC entry 3194 (class 2604 OID 24872)
-- Name: item_pedido id_item_pedido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido ALTER COLUMN id_item_pedido SET DEFAULT nextval('public.item_pedido_id_item_pedido_seq'::regclass);


--
-- TOC entry 3191 (class 2604 OID 24824)
-- Name: pedido id_pedido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido ALTER COLUMN id_pedido SET DEFAULT nextval('public.pedido_id_pedido_seq'::regclass);


--
-- TOC entry 3193 (class 2604 OID 24843)
-- Name: produto id_produto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto ALTER COLUMN id_produto SET DEFAULT nextval('public.produto_id_produto_seq'::regclass);


--
-- TOC entry 3358 (class 0 OID 24833)
-- Dependencies: 216
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (id_categoria, nome, descricao) FROM stdin;
1	Smartphone	um celular que combina recursos de computadores pessoais, com funcionalidades avançadas.
2	Headphone	Tipo de fone de ouvido mais completo, que oferece alto-falantes e caixa acústica maiores.
3	Videogames	Jogos eletrônicos de interatividade e controle onde o jogador decide as próprias ações no game..
4	Roupas de Academia	Roupas especificas para pratica de esportes.
5	Perfume	Mistura de óleos, álcool e água, cuja função é proporcionar uma agradável e duradoura fragrância de aroma.
6	Tênis masculino	Tênis especialmente selecionados
\.


--
-- TOC entry 3354 (class 0 OID 24809)
-- Dependencies: 212
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

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
\.


--
-- TOC entry 3352 (class 0 OID 24802)
-- Dependencies: 210
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

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
\.


--
-- TOC entry 3362 (class 0 OID 24869)
-- Dependencies: 220
-- Data for Name: item_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_pedido (id_item_pedido, quantidade, preco_venda, percentual_desconto, valor_bruto, valor_liquido, id_pedido, id_produto) FROM stdin;
1	2	16150.95	5.00	8500.50	8075.48	1	1
2	1	34873.605	7.00	37498.50	34873.61	2	10
3	2	8640	4.00	4500.00	4320.00	3	3
4	5	2245.971	9.00	493.62	449.19	4	8
5	2	1726.406	3.00	889.90	863.20	5	6
\.


--
-- TOC entry 3356 (class 0 OID 24821)
-- Dependencies: 214
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pedido (id_pedido, data_pedido, data_entrega, data_envio, status, id_cliente) FROM stdin;
1	2022-06-07	\N	\N	f	1
2	2022-06-07	\N	\N	f	5
3	2022-06-07	\N	\N	f	7
4	2022-06-07	\N	\N	f	9
5	2022-06-07	\N	\N	f	11
\.


--
-- TOC entry 3360 (class 0 OID 24840)
-- Dependencies: 218
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

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
\.


--
-- TOC entry 3374 (class 0 OID 0)
-- Dependencies: 215
-- Name: categoria_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_id_categoria_seq', 6, true);


--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 211
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 11, true);


--
-- TOC entry 3376 (class 0 OID 0)
-- Dependencies: 209
-- Name: endereco_id_endereco_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.endereco_id_endereco_seq', 45, true);


--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 219
-- Name: item_pedido_id_item_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_pedido_id_item_pedido_seq', 5, true);


--
-- TOC entry 3378 (class 0 OID 0)
-- Dependencies: 213
-- Name: pedido_id_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_id_pedido_seq', 5, true);


--
-- TOC entry 3379 (class 0 OID 0)
-- Dependencies: 217
-- Name: produto_id_produto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produto_id_produto_seq', 10, true);


--
-- TOC entry 3202 (class 2606 OID 24838)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 3198 (class 2606 OID 24814)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente);


--
-- TOC entry 3196 (class 2606 OID 24807)
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id_endereco);


--
-- TOC entry 3206 (class 2606 OID 24874)
-- Name: item_pedido item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (id_item_pedido);


--
-- TOC entry 3200 (class 2606 OID 24826)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (id_pedido);


--
-- TOC entry 3204 (class 2606 OID 24845)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id_produto);


--
-- TOC entry 3207 (class 2606 OID 24815)
-- Name: cliente cliente_id_endereco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_id_endereco_fkey FOREIGN KEY (id_endereco) REFERENCES public.endereco(id_endereco);


--
-- TOC entry 3210 (class 2606 OID 24875)
-- Name: item_pedido item_pedido_id_pedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_id_pedido_fkey FOREIGN KEY (id_pedido) REFERENCES public.pedido(id_pedido);


--
-- TOC entry 3211 (class 2606 OID 24880)
-- Name: item_pedido item_pedido_id_produto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_id_produto_fkey FOREIGN KEY (id_produto) REFERENCES public.produto(id_produto);


--
-- TOC entry 3208 (class 2606 OID 24827)
-- Name: pedido pedido_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- TOC entry 3209 (class 2606 OID 24846)
-- Name: produto produto_id_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_id_categoria_fkey FOREIGN KEY (id_categoria) REFERENCES public.categoria(id_categoria);


-- Completed on 2022-06-07 12:33:44

--
-- PostgreSQL database dump complete
--

