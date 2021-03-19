CREATE TABLE public.blocks (
	number bigint NOT NULL,
	time varchar(36) NULL,
	CONSTRAINT number_pkey PRIMARY KEY (number)
)