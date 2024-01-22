--
-- PostgreSQL database dump
--

-- Dumped from database version 15.5 (Ubuntu 15.5-0ubuntu0.23.04.1)
-- Dumped by pg_dump version 15.5 (Ubuntu 15.5-0ubuntu0.23.04.1)
CREATE DATABASE test;
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
-- Name: homeworks; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.homeworks (
    id integer NOT NULL,
    name character varying(50),
    tasks_ids integer[] NOT NULL,
    ban_list integer[],
    solute_time time without time zone NOT NULL,
    pupil integer NOT NULL,
    tutor integer NOT NULL
);


ALTER TABLE public.homeworks OWNER TO admin;

--
-- Name: homeworks_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.homeworks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.homeworks_id_seq OWNER TO admin;

--
-- Name: homeworks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.homeworks_id_seq OWNED BY public.homeworks.id;


--
-- Name: payments; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.payments (
    id integer NOT NULL,
    pupil integer NOT NULL,
    tutor integer NOT NULL,
    lesson_time time without time zone NOT NULL,
    hw_id integer NOT NULL,
    lesson_date date NOT NULL,
    status character varying(30)[] NOT NULL
);


ALTER TABLE public.payments OWNER TO admin;

--
-- Name: payments_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.payments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payments_id_seq OWNER TO admin;

--
-- Name: payments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.payments_id_seq OWNED BY public.payments.id;


--
-- Name: pupils; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.pupils (
    id integer NOT NULL,
    name character varying(90) NOT NULL,
    subjects character varying(20)[],
    price integer,
    tutor_id integer[],
    supervisor_id integer,
    hw_list integer[],
    balance numeric
);


ALTER TABLE public.pupils OWNER TO admin;

--
-- Name: pupils_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.pupils_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pupils_id_seq OWNER TO admin;

--
-- Name: pupils_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.pupils_id_seq OWNED BY public.pupils.id;


--
-- Name: supervisors; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.supervisors (
    id integer NOT NULL,
    name character varying(90) NOT NULL,
    pupils integer[],
    telegram_tag character varying(40) NOT NULL,
    payment_data character varying(100) NOT NULL
);


ALTER TABLE public.supervisors OWNER TO admin;

--
-- Name: supervisors_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.supervisors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.supervisors_id_seq OWNER TO admin;

--
-- Name: supervisors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.supervisors_id_seq OWNED BY public.supervisors.id;


--
-- Name: task_tables; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.task_tables (
    id integer NOT NULL,
    name character varying(30),
    task_table text[] NOT NULL
);


ALTER TABLE public.task_tables OWNER TO admin;

--
-- Name: task_tables_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.task_tables_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_tables_id_seq OWNER TO admin;

--
-- Name: task_tables_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.task_tables_id_seq OWNED BY public.task_tables.id;


--
-- Name: tasks; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.tasks (
    id integer NOT NULL,
    name character varying(90) NOT NULL,
    checking integer,
    answer_type character varying(30) NOT NULL,
    files text,
    complexity character varying(30) NOT NULL,
    task_text text,
    task_images bytea[],
    task_tables_ids integer[],
    answer text[],
    ban_commands text[],
    task_analysis text
);


ALTER TABLE public.tasks OWNER TO admin;

--
-- Name: tasks_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.tasks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tasks_id_seq OWNER TO admin;

--
-- Name: tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.tasks_id_seq OWNED BY public.tasks.id;


--
-- Name: tutors; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.tutors (
    id integer NOT NULL,
    name character varying(90) NOT NULL,
    subjects character varying(20)[],
    pupils integer[],
    payment_data character varying(100) NOT NULL
);


ALTER TABLE public.tutors OWNER TO admin;

--
-- Name: tutors_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.tutors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tutors_id_seq OWNER TO admin;

--
-- Name: tutors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.tutors_id_seq OWNED BY public.tutors.id;


--
-- Name: homeworks id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.homeworks ALTER COLUMN id SET DEFAULT nextval('public.homeworks_id_seq'::regclass);


--
-- Name: payments id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.payments ALTER COLUMN id SET DEFAULT nextval('public.payments_id_seq'::regclass);


--
-- Name: pupils id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.pupils ALTER COLUMN id SET DEFAULT nextval('public.pupils_id_seq'::regclass);


--
-- Name: supervisors id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.supervisors ALTER COLUMN id SET DEFAULT nextval('public.supervisors_id_seq'::regclass);


--
-- Name: task_tables id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.task_tables ALTER COLUMN id SET DEFAULT nextval('public.task_tables_id_seq'::regclass);


--
-- Name: tasks id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.tasks ALTER COLUMN id SET DEFAULT nextval('public.tasks_id_seq'::regclass);


--
-- Name: tutors id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.tutors ALTER COLUMN id SET DEFAULT nextval('public.tutors_id_seq'::regclass);


--
-- Data for Name: homeworks; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.homeworks (id, name, tasks_ids, ban_list, solute_time, pupil, tutor) FROM stdin;
\.


--
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.payments (id, pupil, tutor, lesson_time, hw_id, lesson_date, status) FROM stdin;
\.


--
-- Data for Name: pupils; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.pupils (id, name, subjects, price, tutor_id, supervisor_id, hw_list, balance) FROM stdin;
\.


--
-- Data for Name: supervisors; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.supervisors (id, name, pupils, telegram_tag, payment_data) FROM stdin;
\.


--
-- Data for Name: task_tables; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.task_tables (id, name, task_table) FROM stdin;
\.


--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.tasks (id, name, checking, answer_type, files, complexity, task_text, task_images, task_tables_ids, answer, ban_commands, task_analysis) FROM stdin;
\.


--
-- Data for Name: tutors; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.tutors (id, name, subjects, pupils, payment_data) FROM stdin;
\.


--
-- Name: homeworks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.homeworks_id_seq', 1, false);


--
-- Name: payments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.payments_id_seq', 1, false);


--
-- Name: pupils_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.pupils_id_seq', 1, false);


--
-- Name: supervisors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.supervisors_id_seq', 1, false);


--
-- Name: task_tables_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.task_tables_id_seq', 1, false);


--
-- Name: tasks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.tasks_id_seq', 1, false);


--
-- Name: tutors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.tutors_id_seq', 1, false);


--
-- Name: homeworks homeworks_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.homeworks
    ADD CONSTRAINT homeworks_pkey PRIMARY KEY (id);


--
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);


--
-- Name: pupils pupils_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.pupils
    ADD CONSTRAINT pupils_pkey PRIMARY KEY (id);


--
-- Name: supervisors supervisors_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.supervisors
    ADD CONSTRAINT supervisors_pkey PRIMARY KEY (id);


--
-- Name: task_tables task_tables_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.task_tables
    ADD CONSTRAINT task_tables_pkey PRIMARY KEY (id);


--
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- Name: tutors tutors_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.tutors
    ADD CONSTRAINT tutors_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--
