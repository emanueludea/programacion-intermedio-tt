--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0 (Debian 17.0-1.pgdg120+1)
-- Dumped by pg_dump version 17.0 (Debian 17.0-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: alumno; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alumno (
    nombre character varying(50),
    apellido character varying(50),
    cedula character varying(10),
    semestre smallint,
    edad smallint
);


ALTER TABLE public.alumno OWNER TO postgres;

--
-- Name: alumnos_profesor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alumnos_profesor (
    cedula_profesor character varying(10),
    cedula_alumno character varying(10)
);


ALTER TABLE public.alumnos_profesor OWNER TO postgres;

--
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.curso (
    codigo smallint NOT NULL,
    nombre character varying(50),
    duracion smallint
);


ALTER TABLE public.curso OWNER TO postgres;

--
-- Name: curso_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.curso_codigo_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.curso_codigo_seq OWNER TO postgres;

--
-- Name: curso_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.curso_codigo_seq OWNED BY public.curso.codigo;


--
-- Name: materia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.materia (
    codigo smallint NOT NULL,
    nombre character varying(50),
    creditos smallint
);


ALTER TABLE public.materia OWNER TO postgres;

CREATE SEQUENCE public.materia_codigo_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.materia_codigo_seq OWNER TO postgres;

--
-- Name: materia_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.materia_codigo_seq OWNED BY public.materia.codigo;


--
-- Name: materia_profesor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.materia_profesor (
    cedula_profesor character varying(10),
    codigo_materia smallint
);


ALTER TABLE public.materia_profesor OWNER TO postgres;

--
-- Name: matricula; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matricula (
    codigo_materia smallint,
    cedula_alumno character varying(10)
);


ALTER TABLE public.matricula OWNER TO postgres;

--
-- Name: profesor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profesor (
    cedula character varying(10) NOT NULL,
    nombre character varying(50),
    apellido character varying(50),
    oficina smallint,
    telefono integer
);


ALTER TABLE public.profesor OWNER TO postgres;

--
-- Name: curso codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso ALTER COLUMN codigo SET DEFAULT nextval('public.curso_codigo_seq'::regclass);


--
-- Name: materia codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.materia ALTER COLUMN codigo SET DEFAULT nextval('public.materia_codigo_seq'::regclass);


--
-- Data for Name: alumno; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alumno (nombre, apellido, cedula, semestre, edad) FROM stdin;
\.


--
-- Data for Name: alumnos_profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alumnos_profesor (cedula_profesor, cedula_alumno) FROM stdin;
\.


--
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.curso (codigo, nombre, duracion) FROM stdin;
\.


--
-- Data for Name: materia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.materia (codigo, nombre, creditos) FROM stdin;
\.


--
-- Data for Name: materia_profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.materia_profesor (cedula_profesor, codigo_materia) FROM stdin;
\.


--
-- Data for Name: matricula; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.matricula (codigo_materia, cedula_alumno) FROM stdin;
\.


--
-- Data for Name: profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profesor (cedula, nombre, apellido, oficina, telefono) FROM stdin;
\.


--
-- Name: curso_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.curso_codigo_seq', 1, false);


--
-- Name: materia_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.materia_codigo_seq', 1, false);


--
-- Name: profesor profesor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_pkey PRIMARY KEY (cedula);


--
-- PostgreSQL database dump complete
--

