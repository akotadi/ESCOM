--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)
-- Dumped by pg_dump version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: holita_contenido; Type: TABLE; Schema: public; Owner: vive
--

CREATE TABLE public.holita_contenido (
    id integer NOT NULL,
    nombre character varying(50),
    numero character varying(50),
    horas character varying(50)
);


ALTER TABLE public.holita_contenido OWNER TO vive;

--
-- Name: holita_contenido_id_seq; Type: SEQUENCE; Schema: public; Owner: vive
--

CREATE SEQUENCE public.holita_contenido_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.holita_contenido_id_seq OWNER TO vive;

--
-- Name: holita_contenido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: vive
--

ALTER SEQUENCE public.holita_contenido_id_seq OWNED BY public.holita_contenido.id;


--
-- Name: holita_mensajes; Type: TABLE; Schema: public; Owner: vive
--

CREATE TABLE public.holita_mensajes (
    id integer NOT NULL,
    "MS_000" character varying(50),
    "MS_001" character varying(50),
    "MS_002" character varying(50),
    "MS_003" character varying(50),
    "MS_004" character varying(50),
    "MS_005" character varying(50),
    "MS_006" character varying(50)
);


ALTER TABLE public.holita_mensajes OWNER TO vive;

--
-- Name: holita_mensajes_id_seq; Type: SEQUENCE; Schema: public; Owner: vive
--

CREATE SEQUENCE public.holita_mensajes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.holita_mensajes_id_seq OWNER TO vive;

--
-- Name: holita_mensajes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: vive
--

ALTER SEQUENCE public.holita_mensajes_id_seq OWNED BY public.holita_mensajes.id;


--
-- Name: holita_tiempodeasignacion; Type: TABLE; Schema: public; Owner: vive
--

CREATE TABLE public.holita_tiempodeasignacion (
    id integer NOT NULL,
    hora_teoria_semana character varying(50),
    hora_practica_semana character varying(50),
    hora_teoria_nivel character varying(50),
    horas_total_nivel character varying(50)
);


ALTER TABLE public.holita_tiempodeasignacion OWNER TO vive;

--
-- Name: holita_tiempodeasignacion_id_seq; Type: SEQUENCE; Schema: public; Owner: vive
--

CREATE SEQUENCE public.holita_tiempodeasignacion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.holita_tiempodeasignacion_id_seq OWNER TO vive;

--
-- Name: holita_tiempodeasignacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: vive
--

ALTER SEQUENCE public.holita_tiempodeasignacion_id_seq OWNED BY public.holita_tiempodeasignacion.id;


--
-- Name: holita_unidaddeaprendizaje; Type: TABLE; Schema: public; Owner: vive
--

CREATE TABLE public.holita_unidaddeaprendizaje (
    id integer NOT NULL,
    nombre character varying(50),
    proposito character varying(50),
    objetivo character varying(50),
    nivel integer,
    "CTEPIC" double precision,
    modalidad character varying(50),
    vigencia character varying(50),
    area_de_informacion character varying(50),
    "CATCC" double precision,
    orientacion_educativa character varying(50),
    aprobado_por character varying(50),
    tipo character varying(50),
    autorizado_por character varying(50),
    revisado_por character varying(50),
    id_contenido_id integer,
    id_tiempo_id integer,
    id_ut_id integer
);


ALTER TABLE public.holita_unidaddeaprendizaje OWNER TO vive;

--
-- Name: holita_unidaddeaprendizaje_id_seq; Type: SEQUENCE; Schema: public; Owner: vive
--

CREATE SEQUENCE public.holita_unidaddeaprendizaje_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.holita_unidaddeaprendizaje_id_seq OWNER TO vive;

--
-- Name: holita_unidaddeaprendizaje_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: vive
--

ALTER SEQUENCE public.holita_unidaddeaprendizaje_id_seq OWNED BY public.holita_unidaddeaprendizaje.id;


--
-- Name: holita_unidadtematica; Type: TABLE; Schema: public; Owner: vive
--

CREATE TABLE public.holita_unidadtematica (
    id integer NOT NULL,
    nombre character varying(50),
    numero character varying(50),
    competencia character varying(50)
);


ALTER TABLE public.holita_unidadtematica OWNER TO vive;

--
-- Name: holita_unidadtematica_id_seq; Type: SEQUENCE; Schema: public; Owner: vive
--

CREATE SEQUENCE public.holita_unidadtematica_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.holita_unidadtematica_id_seq OWNER TO vive;

--
-- Name: holita_unidadtematica_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: vive
--

ALTER SEQUENCE public.holita_unidadtematica_id_seq OWNED BY public.holita_unidadtematica.id;


--
-- Name: holita_contenido id; Type: DEFAULT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_contenido ALTER COLUMN id SET DEFAULT nextval('public.holita_contenido_id_seq'::regclass);


--
-- Name: holita_mensajes id; Type: DEFAULT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_mensajes ALTER COLUMN id SET DEFAULT nextval('public.holita_mensajes_id_seq'::regclass);


--
-- Name: holita_tiempodeasignacion id; Type: DEFAULT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_tiempodeasignacion ALTER COLUMN id SET DEFAULT nextval('public.holita_tiempodeasignacion_id_seq'::regclass);


--
-- Name: holita_unidaddeaprendizaje id; Type: DEFAULT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_unidaddeaprendizaje ALTER COLUMN id SET DEFAULT nextval('public.holita_unidaddeaprendizaje_id_seq'::regclass);


--
-- Name: holita_unidadtematica id; Type: DEFAULT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_unidadtematica ALTER COLUMN id SET DEFAULT nextval('public.holita_unidadtematica_id_seq'::regclass);


--
-- Data for Name: holita_contenido; Type: TABLE DATA; Schema: public; Owner: vive
--

COPY public.holita_contenido (id, nombre, numero, horas) FROM stdin;
\.


--
-- Data for Name: holita_mensajes; Type: TABLE DATA; Schema: public; Owner: vive
--

COPY public.holita_mensajes (id, "MS_000", "MS_001", "MS_002", "MS_003", "MS_004", "MS_005", "MS_006") FROM stdin;
\.


--
-- Data for Name: holita_tiempodeasignacion; Type: TABLE DATA; Schema: public; Owner: vive
--

COPY public.holita_tiempodeasignacion (id, hora_teoria_semana, hora_practica_semana, hora_teoria_nivel, horas_total_nivel) FROM stdin;
\.


--
-- Data for Name: holita_unidaddeaprendizaje; Type: TABLE DATA; Schema: public; Owner: vive
--

COPY public.holita_unidaddeaprendizaje (id, nombre, proposito, objetivo, nivel, "CTEPIC", modalidad, vigencia, area_de_informacion, "CATCC", orientacion_educativa, aprobado_por, tipo, autorizado_por, revisado_por, id_contenido_id, id_tiempo_id, id_ut_id) FROM stdin;
\.


--
-- Data for Name: holita_unidadtematica; Type: TABLE DATA; Schema: public; Owner: vive
--

COPY public.holita_unidadtematica (id, nombre, numero, competencia) FROM stdin;
\.


--
-- Name: holita_contenido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vive
--

SELECT pg_catalog.setval('public.holita_contenido_id_seq', 1, false);


--
-- Name: holita_mensajes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vive
--

SELECT pg_catalog.setval('public.holita_mensajes_id_seq', 1, false);


--
-- Name: holita_tiempodeasignacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vive
--

SELECT pg_catalog.setval('public.holita_tiempodeasignacion_id_seq', 1, false);


--
-- Name: holita_unidaddeaprendizaje_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vive
--

SELECT pg_catalog.setval('public.holita_unidaddeaprendizaje_id_seq', 1, false);


--
-- Name: holita_unidadtematica_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vive
--

SELECT pg_catalog.setval('public.holita_unidadtematica_id_seq', 1, false);


--
-- Name: holita_contenido holita_contenido_pkey; Type: CONSTRAINT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_contenido
    ADD CONSTRAINT holita_contenido_pkey PRIMARY KEY (id);


--
-- Name: holita_mensajes holita_mensajes_pkey; Type: CONSTRAINT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_mensajes
    ADD CONSTRAINT holita_mensajes_pkey PRIMARY KEY (id);


--
-- Name: holita_tiempodeasignacion holita_tiempodeasignacion_pkey; Type: CONSTRAINT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_tiempodeasignacion
    ADD CONSTRAINT holita_tiempodeasignacion_pkey PRIMARY KEY (id);


--
-- Name: holita_unidaddeaprendizaje holita_unidaddeaprendizaje_pkey; Type: CONSTRAINT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_unidaddeaprendizaje
    ADD CONSTRAINT holita_unidaddeaprendizaje_pkey PRIMARY KEY (id);


--
-- Name: holita_unidadtematica holita_unidadtematica_pkey; Type: CONSTRAINT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_unidadtematica
    ADD CONSTRAINT holita_unidadtematica_pkey PRIMARY KEY (id);


--
-- Name: holita_unidaddeaprendizaje_id_contenido_id_aba9d57e; Type: INDEX; Schema: public; Owner: vive
--

CREATE INDEX holita_unidaddeaprendizaje_id_contenido_id_aba9d57e ON public.holita_unidaddeaprendizaje USING btree (id_contenido_id);


--
-- Name: holita_unidaddeaprendizaje_id_tiempo_id_d1debd44; Type: INDEX; Schema: public; Owner: vive
--

CREATE INDEX holita_unidaddeaprendizaje_id_tiempo_id_d1debd44 ON public.holita_unidaddeaprendizaje USING btree (id_tiempo_id);


--
-- Name: holita_unidaddeaprendizaje_id_ut_id_5ebb77ea; Type: INDEX; Schema: public; Owner: vive
--

CREATE INDEX holita_unidaddeaprendizaje_id_ut_id_5ebb77ea ON public.holita_unidaddeaprendizaje USING btree (id_ut_id);


--
-- Name: holita_unidaddeaprendizaje holita_unidaddeapren_id_contenido_id_aba9d57e_fk_holita_co; Type: FK CONSTRAINT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_unidaddeaprendizaje
    ADD CONSTRAINT holita_unidaddeapren_id_contenido_id_aba9d57e_fk_holita_co FOREIGN KEY (id_contenido_id) REFERENCES public.holita_contenido(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: holita_unidaddeaprendizaje holita_unidaddeapren_id_tiempo_id_d1debd44_fk_holita_ti; Type: FK CONSTRAINT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_unidaddeaprendizaje
    ADD CONSTRAINT holita_unidaddeapren_id_tiempo_id_d1debd44_fk_holita_ti FOREIGN KEY (id_tiempo_id) REFERENCES public.holita_tiempodeasignacion(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: holita_unidaddeaprendizaje holita_unidaddeapren_id_ut_id_5ebb77ea_fk_holita_un; Type: FK CONSTRAINT; Schema: public; Owner: vive
--

ALTER TABLE ONLY public.holita_unidaddeaprendizaje
    ADD CONSTRAINT holita_unidaddeapren_id_ut_id_5ebb77ea_fk_holita_un FOREIGN KEY (id_ut_id) REFERENCES public.holita_unidadtematica(id) DEFERRABLE INITIALLY DEFERRED;


--
-- PostgreSQL database dump complete
--

