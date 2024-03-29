--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Debian 16.1-1.pgdg120+1)
-- Dumped by pg_dump version 16.1 (Debian 16.1-1.pgdg120+1)

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
-- Name: admins; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.admins (
    id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    patronymic character varying(255),
    role integer,
    surname character varying(255),
    username character varying(255)
);


ALTER TABLE public.admins OWNER TO tutor;

--
-- Name: answers_array; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.answers_array (
    entity_id bigint NOT NULL,
    answers character varying(255)
);


ALTER TABLE public.answers_array OWNER TO tutor;

--
-- Name: checking_map; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.checking_map (
    homework_entity_id bigint NOT NULL,
    tasks_checking_types integer,
    tasks_checking_types_key bigint NOT NULL
);


ALTER TABLE public.checking_map OWNER TO tutor;

--
-- Name: checks; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.checks (
    id bigint NOT NULL,
    candidate_id bigint,
    date timestamp without time zone,
    candidate_role integer
);


ALTER TABLE public.checks OWNER TO tutor;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: tutor
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.hibernate_sequence OWNER TO tutor;

--
-- Name: homeworks; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.homeworks (
    id bigint NOT NULL,
    dead_line date,
    name character varying(255),
    solute_time bigint,
    target_time bigint,
    tutor bigint
);


ALTER TABLE public.homeworks OWNER TO tutor;

--
-- Name: homeworks_pupils; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.homeworks_pupils (
    homework_entity_id bigint NOT NULL,
    pupils_id bigint NOT NULL
);


ALTER TABLE public.homeworks_pupils OWNER TO tutor;

--
-- Name: payments; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.payments (
    id bigint NOT NULL,
    lesson_date date,
    lesson_time time without time zone,
    status character varying(255),
    pupil bigint
);


ALTER TABLE public.payments OWNER TO tutor;

--
-- Name: pupils; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.pupils (
    id bigint NOT NULL,
    balance double precision,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    patronymic character varying(255),
    price integer,
    role integer,
    surname character varying(255),
    username character varying(255),
    verification boolean,
    support bigint
);


ALTER TABLE public.pupils OWNER TO tutor;

--
-- Name: pupils_homework_list; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.pupils_homework_list (
    pupil_entity_id bigint NOT NULL,
    homework_list_id bigint NOT NULL
);


ALTER TABLE public.pupils_homework_list OWNER TO tutor;

--
-- Name: subjects; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.subjects (
    id bigint NOT NULL,
    count_level integer,
    name character varying(255)
);


ALTER TABLE public.subjects OWNER TO tutor;

--
-- Name: subjects_pupils; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.subjects_pupils (
    subjects_id bigint NOT NULL,
    pupils_id bigint NOT NULL
);


ALTER TABLE public.subjects_pupils OWNER TO tutor;

--
-- Name: subjects_tutors; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.subjects_tutors (
    subjects_id bigint NOT NULL,
    tutors_id bigint NOT NULL
);


ALTER TABLE public.subjects_tutors OWNER TO tutor;

--
-- Name: supports; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.supports (
    id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    patronymic character varying(255),
    payment_data character varying(255),
    role integer,
    surname character varying(255),
    telegram_tag character varying(255),
    username character varying(255)
);


ALTER TABLE public.supports OWNER TO tutor;

--
-- Name: task_files; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.task_files (
    entity_id bigint NOT NULL,
    files character varying(255)
);


ALTER TABLE public.task_files OWNER TO tutor;

--
-- Name: task_tables; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.task_tables (
    id bigint NOT NULL,
    name character varying(255),
    tack_table character varying(255)
);


ALTER TABLE public.task_tables OWNER TO tutor;

--
-- Name: tasks; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.tasks (
    id bigint NOT NULL,
    answer_type character varying(255),
    checking integer,
    level1 character varying(255),
    level2 character varying(255),
    name character varying(255),
    tables character varying(255),
    task_text character varying(255),
    subject bigint,
    support bigint
);


ALTER TABLE public.tasks OWNER TO tutor;

--
-- Name: tasks_homeworks; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.tasks_homeworks (
    tasks_id bigint NOT NULL,
    homeworks_id bigint NOT NULL
);


ALTER TABLE public.tasks_homeworks OWNER TO tutor;

--
-- Name: tutors; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.tutors (
    id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    patronymic character varying(255),
    payment_data character varying(255),
    role integer,
    surname character varying(255),
    username character varying(255),
    verification boolean
);


ALTER TABLE public.tutors OWNER TO tutor;

--
-- Name: tutors_pupils; Type: TABLE; Schema: public; Owner: tutor
--

CREATE TABLE public.tutors_pupils (
    tutors_id bigint NOT NULL,
    pupils_id bigint NOT NULL
);


ALTER TABLE public.tutors_pupils OWNER TO tutor;

--
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.admins (id, email, name, password, patronymic, role, surname, username) FROM stdin;
\.


--
-- Data for Name: answers_array; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.answers_array (entity_id, answers) FROM stdin;
\.


--
-- Data for Name: checking_map; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.checking_map (homework_entity_id, tasks_checking_types, tasks_checking_types_key) FROM stdin;
\.


--
-- Data for Name: checks; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.checks (id, candidate_id, date, candidate_role) FROM stdin;
\.


--
-- Data for Name: homeworks; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.homeworks (id, dead_line, name, solute_time, target_time, tutor) FROM stdin;
\.


--
-- Data for Name: homeworks_pupils; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.homeworks_pupils (homework_entity_id, pupils_id) FROM stdin;
\.


--
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.payments (id, lesson_date, lesson_time, status, pupil) FROM stdin;
\.


--
-- Data for Name: pupils; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.pupils (id, balance, email, name, password, patronymic, price, role, surname, username, verification, support) FROM stdin;
\.


--
-- Data for Name: pupils_homework_list; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.pupils_homework_list (pupil_entity_id, homework_list_id) FROM stdin;
\.


--
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.subjects (id, count_level, name) FROM stdin;
\.


--
-- Data for Name: subjects_pupils; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.subjects_pupils (subjects_id, pupils_id) FROM stdin;
\.


--
-- Data for Name: subjects_tutors; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.subjects_tutors (subjects_id, tutors_id) FROM stdin;
\.


--
-- Data for Name: supports; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.supports (id, email, name, password, patronymic, payment_data, role, surname, telegram_tag, username) FROM stdin;
\.


--
-- Data for Name: task_files; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.task_files (entity_id, files) FROM stdin;
\.


--
-- Data for Name: task_tables; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.task_tables (id, name, tack_table) FROM stdin;
\.


--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.tasks (id, answer_type, checking, level1, level2, name, tables, task_text, subject, support) FROM stdin;
\.


--
-- Data for Name: tasks_homeworks; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.tasks_homeworks (tasks_id, homeworks_id) FROM stdin;
\.


--
-- Data for Name: tutors; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.tutors (id, email, name, password, patronymic, payment_data, role, surname, username, verification) FROM stdin;
\.


--
-- Data for Name: tutors_pupils; Type: TABLE DATA; Schema: public; Owner: tutor
--

COPY public.tutors_pupils (tutors_id, pupils_id) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: tutor
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: admins admins_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (id);


--
-- Name: checking_map checking_map_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.checking_map
    ADD CONSTRAINT checking_map_pkey PRIMARY KEY (homework_entity_id, tasks_checking_types_key);


--
-- Name: checks checks_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.checks
    ADD CONSTRAINT checks_pkey PRIMARY KEY (id);


--
-- Name: homeworks homeworks_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.homeworks
    ADD CONSTRAINT homeworks_pkey PRIMARY KEY (id);


--
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);


--
-- Name: pupils pupils_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.pupils
    ADD CONSTRAINT pupils_pkey PRIMARY KEY (id);


--
-- Name: subjects subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);


--
-- Name: supports supports_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.supports
    ADD CONSTRAINT supports_pkey PRIMARY KEY (id);


--
-- Name: task_tables task_tables_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.task_tables
    ADD CONSTRAINT task_tables_pkey PRIMARY KEY (id);


--
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- Name: tutors tutors_pkey; Type: CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.tutors
    ADD CONSTRAINT tutors_pkey PRIMARY KEY (id);


--
-- Name: tasks fk3fecy7xvqv5hq1a6sssq06ecy; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT fk3fecy7xvqv5hq1a6sssq06ecy FOREIGN KEY (subject) REFERENCES public.subjects(id);


--
-- Name: tasks_homeworks fk3le3l2aj020t63wl7avldp0vm; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.tasks_homeworks
    ADD CONSTRAINT fk3le3l2aj020t63wl7avldp0vm FOREIGN KEY (homeworks_id) REFERENCES public.homeworks(id);


--
-- Name: tutors_pupils fk7au8bs25bck4o8myy4wm1yv77; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.tutors_pupils
    ADD CONSTRAINT fk7au8bs25bck4o8myy4wm1yv77 FOREIGN KEY (tutors_id) REFERENCES public.tutors(id);


--
-- Name: payments fk7y9o12w5ygb7r4tee03bprjwm; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT fk7y9o12w5ygb7r4tee03bprjwm FOREIGN KEY (pupil) REFERENCES public.pupils(id);


--
-- Name: homeworks fk99sqkp8d5rpfgxteucfd3k3yq; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.homeworks
    ADD CONSTRAINT fk99sqkp8d5rpfgxteucfd3k3yq FOREIGN KEY (tutor) REFERENCES public.tutors(id);


--
-- Name: homeworks_pupils fka0gdm99iylmisa006c35g3d3f; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.homeworks_pupils
    ADD CONSTRAINT fka0gdm99iylmisa006c35g3d3f FOREIGN KEY (pupils_id) REFERENCES public.pupils(id);


--
-- Name: checking_map fkb19b3fut40nw6ifu83hfgrfam; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.checking_map
    ADD CONSTRAINT fkb19b3fut40nw6ifu83hfgrfam FOREIGN KEY (tasks_checking_types_key) REFERENCES public.tasks(id);


--
-- Name: checking_map fkdypbito7qgba4d6p68o27cv65; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.checking_map
    ADD CONSTRAINT fkdypbito7qgba4d6p68o27cv65 FOREIGN KEY (homework_entity_id) REFERENCES public.homeworks(id);


--
-- Name: subjects_tutors fkeqqu9n2no0orr1ts7sul1vp7f; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.subjects_tutors
    ADD CONSTRAINT fkeqqu9n2no0orr1ts7sul1vp7f FOREIGN KEY (tutors_id) REFERENCES public.tutors(id);


--
-- Name: subjects_tutors fkfqudoe28ycus83g936raskhmm; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.subjects_tutors
    ADD CONSTRAINT fkfqudoe28ycus83g936raskhmm FOREIGN KEY (subjects_id) REFERENCES public.subjects(id);


--
-- Name: homeworks_pupils fkfs3ri0iwqs7o6e0dai1ioai75; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.homeworks_pupils
    ADD CONSTRAINT fkfs3ri0iwqs7o6e0dai1ioai75 FOREIGN KEY (homework_entity_id) REFERENCES public.homeworks(id);


--
-- Name: tasks fkfyh73ix9c2fv1lol06taqt3g8; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT fkfyh73ix9c2fv1lol06taqt3g8 FOREIGN KEY (support) REFERENCES public.supports(id);


--
-- Name: answers_array fkgdi5wu4oj1cn7fnxusbj47rr2; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.answers_array
    ADD CONSTRAINT fkgdi5wu4oj1cn7fnxusbj47rr2 FOREIGN KEY (entity_id) REFERENCES public.tasks(id);


--
-- Name: pupils_homework_list fkhw6y3gk0ck8lbphq06bexe6et; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.pupils_homework_list
    ADD CONSTRAINT fkhw6y3gk0ck8lbphq06bexe6et FOREIGN KEY (homework_list_id) REFERENCES public.homeworks(id);


--
-- Name: pupils fki40kcsrgjp9ha060c1x21h72x; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.pupils
    ADD CONSTRAINT fki40kcsrgjp9ha060c1x21h72x FOREIGN KEY (support) REFERENCES public.supports(id);


--
-- Name: subjects_pupils fkim39ucyyjkc0alhtny0vw176t; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.subjects_pupils
    ADD CONSTRAINT fkim39ucyyjkc0alhtny0vw176t FOREIGN KEY (pupils_id) REFERENCES public.pupils(id);


--
-- Name: subjects_pupils fkljwiqcdsfsfvo1rm8y6sybx8w; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.subjects_pupils
    ADD CONSTRAINT fkljwiqcdsfsfvo1rm8y6sybx8w FOREIGN KEY (subjects_id) REFERENCES public.subjects(id);


--
-- Name: task_files fkms6xiwrvlcfy7l1k57lwpidn5; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.task_files
    ADD CONSTRAINT fkms6xiwrvlcfy7l1k57lwpidn5 FOREIGN KEY (entity_id) REFERENCES public.tasks(id);


--
-- Name: pupils_homework_list fkn492hln39ulfgcnov8yjxuqwj; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.pupils_homework_list
    ADD CONSTRAINT fkn492hln39ulfgcnov8yjxuqwj FOREIGN KEY (pupil_entity_id) REFERENCES public.pupils(id);


--
-- Name: tasks_homeworks fkobgfw08nm277ibtvnf5dtprfg; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.tasks_homeworks
    ADD CONSTRAINT fkobgfw08nm277ibtvnf5dtprfg FOREIGN KEY (tasks_id) REFERENCES public.tasks(id);


--
-- Name: tutors_pupils fkpdwfpemsihdljfny6hucpmjup; Type: FK CONSTRAINT; Schema: public; Owner: tutor
--

ALTER TABLE ONLY public.tutors_pupils
    ADD CONSTRAINT fkpdwfpemsihdljfny6hucpmjup FOREIGN KEY (pupils_id) REFERENCES public.pupils(id);


--
-- PostgreSQL database dump complete
--

