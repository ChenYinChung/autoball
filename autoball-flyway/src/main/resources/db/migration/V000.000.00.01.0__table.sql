--Create Table
--
-- Name: access_token; Type: TABLE; Schema: public; Owner: lottery
--

CREATE TABLE public.draw (
    game_id character varying(20) NOT NULL,
    game_num character varying(20) NOT NULL,
    balls jsonb,
    create_date  timestamp NOT NULL DEFAULT NOW(),
    update_date  timestamp,
    retry boolean default false,
    retry_count int default 0,
    status int default 0,
    PRIMARY KEY	(game_num)
);
