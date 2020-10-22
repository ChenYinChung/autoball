--Create Table
--
-- Name: access_token; Type: TABLE; Schema: public; Owner: lottery
--

CREATE TABLE public.draw (
    game_id character varying(20) NOT NULL,
    game_num character varying(20) NOT NULL,
    balls jsonb,
    PRIMARY KEY	(game_num)
);
