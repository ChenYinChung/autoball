CREATE EXTENSION IF NOT EXISTS ltree;

--Create Table

--
-- Name: access_token; Type: TABLE; Schema: public; Owner: lottery
--

CREATE TABLE public.draw (
    id bigserial NOT NULL ,
    game_num character varying(20) NOT NULL,
    draw_result json NOT NULL,
    draw_status int NOT NULL,
    PRIMARY KEY	(id)
);
