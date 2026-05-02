--
-- PostgreSQL database dump
--

\restrict Z8RPeK95jFIZTeAjh7cs0vZE8NKNCshPdcagZXfngmx9OI4LnsznktlchU7nlSr

-- Dumped from database version 16.13 (Ubuntu 16.13-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.13 (Ubuntu 16.13-0ubuntu0.24.04.1)

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
-- Name: follows; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.follows (
    follower_key integer NOT NULL,
    followed_key integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT follows_check CHECK ((follower_key <> followed_key))
);


ALTER TABLE public.follows OWNER TO myuser;

--
-- Name: post; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.post (
    post_id integer NOT NULL,
    user_id integer NOT NULL,
    parent_post_id integer,
    numofreplies integer DEFAULT 0,
    numoflikes integer DEFAULT 0,
    numofshares integer DEFAULT 0,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.post OWNER TO myuser;

--
-- Name: post_post_id_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.post_post_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.post_post_id_seq OWNER TO myuser;

--
-- Name: post_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: myuser
--

ALTER SEQUENCE public.post_post_id_seq OWNED BY public.post.post_id;


--
-- Name: postcontent; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.postcontent (
    content_id integer NOT NULL,
    post_id integer,
    content_type character varying(20),
    text_content text,
    media_url character varying(500)
);


ALTER TABLE public.postcontent OWNER TO myuser;

--
-- Name: postcontent_content_id_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.postcontent_content_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.postcontent_content_id_seq OWNER TO myuser;

--
-- Name: postcontent_content_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: myuser
--

ALTER SEQUENCE public.postcontent_content_id_seq OWNED BY public.postcontent.content_id;


--
-- Name: postlike; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.postlike (
    post_id integer NOT NULL,
    user_id integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.postlike OWNER TO myuser;

--
-- Name: postshare; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.postshare (
    post_id integer NOT NULL,
    user_id integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.postshare OWNER TO myuser;

--
-- Name: users; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    password character varying(32),
    email character varying(32),
    name character varying(16),
    profile_picture bytea,
    bio character varying(128),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.users OWNER TO myuser;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO myuser;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: myuser
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: post post_id; Type: DEFAULT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.post ALTER COLUMN post_id SET DEFAULT nextval('public.post_post_id_seq'::regclass);


--
-- Name: postcontent content_id; Type: DEFAULT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postcontent ALTER COLUMN content_id SET DEFAULT nextval('public.postcontent_content_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: follows; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.follows (follower_key, followed_key, created_at) FROM stdin;
1	2	2026-05-01 20:30:34.286415
1	3	2026-05-01 20:30:34.286415
1	4	2026-05-01 20:30:34.286415
2	1	2026-05-01 20:30:34.286415
2	4	2026-05-01 20:30:34.286415
2	5	2026-05-01 20:30:34.286415
3	1	2026-05-01 20:30:34.286415
3	5	2026-05-01 20:30:34.286415
4	1	2026-05-01 20:30:34.286415
4	5	2026-05-01 20:30:34.286415
4	7	2026-05-01 20:30:34.286415
5	1	2026-05-01 20:30:34.286415
5	3	2026-05-01 20:30:34.286415
6	1	2026-05-01 20:30:34.286415
6	2	2026-05-01 20:30:34.286415
6	4	2026-05-01 20:30:34.286415
7	3	2026-05-01 20:30:34.286415
7	4	2026-05-01 20:30:34.286415
7	6	2026-05-01 20:30:34.286415
8	2	2026-05-01 20:30:34.286415
8	6	2026-05-01 20:30:34.286415
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.post (post_id, user_id, parent_post_id, numofreplies, numoflikes, numofshares, created_at) FROM stdin;
1	1	\N	3	5	2	2026-04-10 09:15:00
2	1	\N	1	3	0	2026-04-12 14:30:00
3	2	\N	2	4	1	2026-04-10 11:00:00
4	2	\N	0	2	0	2026-04-14 07:45:00
5	3	\N	1	6	3	2026-04-11 03:20:00
6	3	\N	2	2	0	2026-04-15 02:10:00
7	4	\N	1	7	4	2026-04-11 18:00:00
8	4	\N	0	3	1	2026-04-16 10:30:00
9	5	\N	2	4	1	2026-04-12 20:00:00
10	6	\N	1	5	2	2026-04-13 12:00:00
11	7	\N	0	3	0	2026-04-14 16:45:00
12	8	\N	1	2	0	2026-04-15 06:30:00
13	2	1	0	2	0	2026-04-10 09:30:00
14	3	1	0	1	0	2026-04-10 09:45:00
15	5	1	0	3	0	2026-04-10 10:00:00
16	1	3	0	1	0	2026-04-10 12:00:00
17	4	3	0	2	0	2026-04-10 13:30:00
18	6	2	0	0	0	2026-04-12 15:00:00
19	7	5	0	1	0	2026-04-11 08:00:00
20	1	6	0	0	0	2026-04-15 02:30:00
21	8	6	0	1	0	2026-04-15 03:00:00
22	5	7	0	2	0	2026-04-11 19:00:00
23	3	9	0	1	0	2026-04-12 21:00:00
24	6	9	0	0	0	2026-04-12 22:30:00
25	4	10	0	1	0	2026-04-13 13:00:00
26	2	12	0	0	0	2026-04-15 07:00:00
27	1	\N	0	0	0	2026-05-01 20:30:57.942145
\.


--
-- Data for Name: postcontent; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.postcontent (content_id, post_id, content_type, text_content, media_url) FROM stdin;
1	1	text	just deployed my first spring boot app. only cried twice.	\N
2	2	text	hot take: postgresql is the best database and its not even close	\N
3	3	text	summited camelback at sunrise. almost passed out but the views were worth it	\N
4	4	text	woke up at 5am voluntarily. who even am i anymore	\N
5	5	text	its 3am and i just discovered my entire codebase has a typo in every variable name. shipping it anyway.	\N
6	6	text	should i learn rust or go next? wrong answers only	\N
7	7	text	golden hour in sedona. no filter needed when the sky does this	\N
8	8	text	my dog learned to open the fridge. we are in the endgame now.	\N
9	9	text	euler identity is proof that math is just poetry with extra steps	\N
10	10	text	made homemade pasta for the first time. life will never be the same	\N
11	11	text	redesigned my portfolio for the 47th time. this is the one. (it is not the one.)	\N
12	12	text	new pr on deadlift today. the grind never stops	\N
13	13	text	only twice? thats actually impressive for spring boot	\N
14	14	text	welcome to the club. the errors only get weirder from here	\N
15	15	text	what stack? i need to know everything	\N
16	16	text	how early did you start?? i can barely make it to 9am lectures	\N
17	17	text	the desert sunrise hits different. need to go back	\N
18	18	text	postgresql supremacy. this is the way.	\N
19	19	text	this is either genius or unhinged. respect either way	\N
20	20	text	rust. always rust. the borrow checker builds character	\N
21	21	text	go is way easier to pick up honestly. rust is a time commitment	\N
22	22	text	sedona is unreal. how do you even capture that light??	\N
23	23	text	euler identity walked so complex analysis could run	\N
24	24	text	have you read a mathematicians apology? youd love it	\N
25	25	text	ok recipe or it didnt happen	\N
26	26	text	what program are you running? been looking for a new split	\N
27	27	text	meow	\N
\.


--
-- Data for Name: postlike; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.postlike (post_id, user_id, created_at) FROM stdin;
1	2	2026-04-10 09:20:00
1	3	2026-04-10 09:25:00
1	5	2026-04-10 10:05:00
1	6	2026-04-10 11:00:00
1	7	2026-04-10 14:00:00
2	3	2026-04-12 14:45:00
2	5	2026-04-12 15:30:00
2	6	2026-04-12 16:00:00
3	1	2026-04-10 11:30:00
3	4	2026-04-10 12:00:00
3	7	2026-04-10 13:00:00
3	8	2026-04-10 15:00:00
4	1	2026-04-14 08:00:00
4	3	2026-04-14 09:00:00
5	1	2026-04-11 07:00:00
5	2	2026-04-11 08:30:00
5	4	2026-04-11 09:00:00
5	6	2026-04-11 10:00:00
5	7	2026-04-11 08:15:00
5	8	2026-04-11 11:00:00
6	1	2026-04-15 02:25:00
6	2	2026-04-15 06:00:00
7	1	2026-04-11 18:30:00
7	2	2026-04-11 19:00:00
7	3	2026-04-11 19:15:00
7	5	2026-04-11 19:30:00
7	6	2026-04-11 20:00:00
7	7	2026-04-11 20:30:00
7	8	2026-04-11 21:00:00
8	1	2026-04-16 11:00:00
8	3	2026-04-16 12:00:00
8	6	2026-04-16 13:00:00
9	1	2026-04-12 20:30:00
9	3	2026-04-12 21:15:00
9	4	2026-04-12 22:00:00
9	7	2026-04-12 23:00:00
10	1	2026-04-13 12:30:00
10	2	2026-04-13 13:00:00
10	4	2026-04-13 13:30:00
10	5	2026-04-13 14:00:00
10	8	2026-04-13 15:00:00
11	1	2026-04-14 17:00:00
11	3	2026-04-14 18:00:00
11	5	2026-04-14 19:00:00
12	2	2026-04-15 07:00:00
12	6	2026-04-15 08:00:00
\.


--
-- Data for Name: postshare; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.postshare (post_id, user_id, created_at) FROM stdin;
1	5	2026-04-10 10:30:00
1	7	2026-04-10 15:00:00
3	1	2026-04-10 12:30:00
5	2	2026-04-11 09:00:00
5	7	2026-04-11 09:30:00
5	8	2026-04-11 12:00:00
7	1	2026-04-11 19:00:00
7	2	2026-04-11 19:30:00
7	5	2026-04-11 20:00:00
7	8	2026-04-11 21:30:00
8	5	2026-04-16 12:00:00
9	6	2026-04-12 23:00:00
10	7	2026-04-13 14:00:00
10	8	2026-04-13 16:00:00
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.users (user_id, password, email, name, profile_picture, bio, created_at) FROM stdin;
1	pass1234	alice@email.com	alice	\N	caffeine-powered developer	2026-05-01 20:30:34.267994
2	pass1234	bob@email.com	bob	\N	hiking and hot takes	2026-05-01 20:30:34.267994
3	pass1234	charlie@email.com	charlie	\N	night owl. code goblin.	2026-05-01 20:30:34.267994
4	pass1234	diana@email.com	diana	\N	photographer + dog mom	2026-05-01 20:30:34.267994
5	pass1234	eve@email.com	eve	\N	math nerd, music snob	2026-05-01 20:30:34.267994
6	pass1234	frank@email.com	frank	\N	aspiring chef	2026-05-01 20:30:34.267994
7	pass1234	grace@email.com	grace	\N	design + frontend	2026-05-01 20:30:34.267994
8	pass1234	hank@email.com	hank	\N	gym rat	2026-05-01 20:30:34.267994
\.


--
-- Name: post_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: myuser
--

SELECT pg_catalog.setval('public.post_post_id_seq', 27, true);


--
-- Name: postcontent_content_id_seq; Type: SEQUENCE SET; Schema: public; Owner: myuser
--

SELECT pg_catalog.setval('public.postcontent_content_id_seq', 27, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: myuser
--

SELECT pg_catalog.setval('public.users_user_id_seq', 8, true);


--
-- Name: follows follows_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.follows
    ADD CONSTRAINT follows_pkey PRIMARY KEY (follower_key, followed_key);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (post_id, user_id);


--
-- Name: post post_post_id_key; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_post_id_key UNIQUE (post_id);


--
-- Name: postcontent postcontent_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postcontent
    ADD CONSTRAINT postcontent_pkey PRIMARY KEY (content_id);


--
-- Name: postlike postlike_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postlike
    ADD CONSTRAINT postlike_pkey PRIMARY KEY (post_id, user_id);


--
-- Name: postshare postshare_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postshare
    ADD CONSTRAINT postshare_pkey PRIMARY KEY (post_id, user_id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: follows follows_followed_key_fkey; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.follows
    ADD CONSTRAINT follows_followed_key_fkey FOREIGN KEY (followed_key) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- Name: follows follows_follower_key_fkey; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.follows
    ADD CONSTRAINT follows_follower_key_fkey FOREIGN KEY (follower_key) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- Name: post post_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: postcontent postcontent_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postcontent
    ADD CONSTRAINT postcontent_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.post(post_id) ON DELETE CASCADE;


--
-- Name: postlike postlike_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postlike
    ADD CONSTRAINT postlike_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.post(post_id) ON DELETE CASCADE;


--
-- Name: postlike postlike_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postlike
    ADD CONSTRAINT postlike_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- Name: postshare postshare_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postshare
    ADD CONSTRAINT postshare_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.post(post_id) ON DELETE CASCADE;


--
-- Name: postshare postshare_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.postshare
    ADD CONSTRAINT postshare_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

\unrestrict Z8RPeK95jFIZTeAjh7cs0vZE8NKNCshPdcagZXfngmx9OI4LnsznktlchU7nlSr

