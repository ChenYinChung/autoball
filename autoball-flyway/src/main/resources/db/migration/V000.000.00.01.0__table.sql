--Create Table
--
-- Name: access_token; Type: TABLE; Schema: public; Owner: lottery
--

CREATE TABLE public.draw (
    game_num character varying(20) NOT NULL,
    game_info jsonb,
    draw_status int NOT NULL,
    PRIMARY KEY	(game_num)
);
