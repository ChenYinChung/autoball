--Create Table
--
-- Name: access_token; Type: TABLE; Schema: public; Owner: lottery
--

CREATE TABLE public.draw (
    game_id character varying(20) NOT NULL,
    game_num character varying(20) NOT NULL,
    balls jsonb,
    start_date  timestamp NOT NULL DEFAULT NOW(),
    update_date  timestamp,
    PRIMARY KEY	(game_num)
);
