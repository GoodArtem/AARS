-- Drop table

-- DROP TABLE public.employee;

CREATE TABLE IF NOT EXISTS public.employee (
    id uuid NOT NULL,
	employee_name varchar NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	CONSTRAINT employee_pk PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.format;

CREATE TABLE IF NOT EXISTS public.format (
    id uuid NOT NULL,
	format_name varchar NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	CONSTRAINT format_pk PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.theme;

CREATE TABLE IF NOT EXISTS public.theme (
    id uuid NOT NULL,
	theme_name varchar NULL,
	archive_date timestamp NULL,
	cipher varchar NULL,
	has_changes varchar NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	CONSTRAINT theme_pk PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.directory;

CREATE TABLE IF NOT EXISTS public.directory (
    id uuid NOT NULL,
	directory_name varchar NULL,
	directory_type varchar NULL,
	theme_id uuid NULL,
	parent_id uuid NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	old_theme_id int4 NULL,
	old_parent_id int4 NULL,
	CONSTRAINT directory_pk PRIMARY KEY (id),
	CONSTRAINT directory_fk FOREIGN KEY (parent_id) REFERENCES directory(id) ON DELETE CASCADE,
	CONSTRAINT directory_fk_1 FOREIGN KEY (theme_id) REFERENCES theme(id) ON DELETE CASCADE
);

-- Drop table

-- DROP TABLE public.inventory_card;

CREATE TABLE IF NOT EXISTS public.inventory_card (
    id uuid NOT NULL,
	inventory_number int4 NULL,
	inventory_number_suf varchar NULL,
	card_name varchar NULL,
	designation varchar NULL,
	cipher varchar NULL,
	sheet_count varchar NULL,
	tl uuid NULL,
	vtd uuid NULL,
	mk uuid NULL,
	mkt uuid NULL,
	voptk uuid NULL,
	okufsb uuid NULL,
	okufreg uuid NULL,
	card_type int4 NULL,
	card_date timestamp NULL,
	state int4 NULL,
	annulled_date timestamp NULL,
	theme_id uuid NULL,
	directory_id uuid NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	old_theme_id int4 NULL,
	old_directory_id int4 NULL,
	old_tl int4 NULL,
    old_vtd int4 NULL,
    old_mk int4 NULL,
    old_mkt int4 NULL,
    old_voptk int4 NULL,
    old_okufsb int4 NULL,
    old_okufreg int4 NULL,
	old_format_ids varchar NULL,
	old_format_name varchar NULL,
	CONSTRAINT inventory_card_pk PRIMARY KEY (id),
	CONSTRAINT inventory_card_fk FOREIGN KEY (theme_id) REFERENCES theme(id) ON DELETE CASCADE,
	CONSTRAINT inventory_card_fk_1 FOREIGN KEY (directory_id) REFERENCES directory(id) ON DELETE CASCADE
);

-- Drop table

-- DROP TABLE public.applicability;

CREATE TABLE IF NOT EXISTS public.applicability (
    id uuid NOT NULL,
	applicability_date timestamp NULL,
	designation varchar NULL,
	cipher varchar NULL,
	inventory_card_id uuid NULL,
	app_inventory_card_id uuid NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	old_inventory_card_id int4 NULL,
	old_app_inventory_card_id int4 NULL,
	CONSTRAINT applicability_pk PRIMARY KEY (id),
	CONSTRAINT applicability_fk FOREIGN KEY (inventory_card_id) REFERENCES inventory_card(id) ON DELETE CASCADE,
	CONSTRAINT applicability_fk_1 FOREIGN KEY (app_inventory_card_id) REFERENCES inventory_card(id) ON DELETE SET NULL
);

-- Drop table

-- DROP TABLE public.card_to_format;

CREATE TABLE IF NOT EXISTS public.card_to_format (
	inventory_card_id uuid NULL,
	format_id uuid NULL,
	CONSTRAINT card_to_format_un UNIQUE (inventory_card_id, format_id),
	CONSTRAINT card_to_format_fk FOREIGN KEY (inventory_card_id) REFERENCES inventory_card(id) ON DELETE CASCADE,
	CONSTRAINT card_to_format_fk_1 FOREIGN KEY (format_id) REFERENCES format(id) ON DELETE CASCADE
);

-- Drop table

-- DROP TABLE public.copies_info;

CREATE TABLE IF NOT EXISTS public.copies_info (
    id uuid NOT NULL,
	copy_date timestamp NULL,
	received_copy varchar NULL,
	annulled_copy varchar NULL,
	designation varchar NULL,
	inventory_card_id uuid NULL,
	employee_id uuid NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	old_inventory_card_id int4 NULL,
	old_employee_id int4 NULL,
	CONSTRAINT copies_info_pk PRIMARY KEY (id),
	CONSTRAINT copies_info_fk FOREIGN KEY (inventory_card_id) REFERENCES inventory_card(id) ON DELETE CASCADE,
	CONSTRAINT copies_info_fk_1 FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE SET NULL
);

-- Drop table

-- DROP TABLE public.once_only_issue;

CREATE TABLE IF NOT EXISTS public.once_only_issue (
    id uuid NOT NULL,
	issue_date timestamp NULL,
	ex_number varchar NULL,
	designation varchar NULL,
	to_whom varchar NULL,
	inventory_card_id uuid NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	old_inventory_card_id int4 NULL,
	CONSTRAINT once_only_issue_pk PRIMARY KEY (id),
	CONSTRAINT once_only_issue_fk FOREIGN KEY (inventory_card_id) REFERENCES inventory_card(id) ON DELETE CASCADE
);

-- Drop table

-- DROP TABLE public.stocktaking;

CREATE TABLE IF NOT EXISTS public.stocktaking (
    id uuid NOT NULL,
	changing int4 NULL,
	doc_number varchar NULL,
	date_changing timestamp NULL,
	changed_sheets varchar NULL,
	inventory_card_id uuid NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	old_inventory_card_id int4 NULL,
	CONSTRAINT stocktaking_pk PRIMARY KEY (id),
	CONSTRAINT stocktaking_fk FOREIGN KEY (inventory_card_id) REFERENCES inventory_card(id) ON DELETE CASCADE
);

-- Drop table

-- DROP TABLE public.subscriber;

CREATE TABLE IF NOT EXISTS public.subscriber (
    id uuid NOT NULL,
	subscriber_name varchar NULL,
	subscribe_date timestamp NULL,
	ex_number varchar NULL,
	designation varchar NULL,
	annulled varchar NULL,
	theme_id uuid NULL,
	revision int4 NOT NULL DEFAULT 1,
	old_id int4 NOT NULL,
	old_theme_id int4 NULL,
	CONSTRAINT subscriber_pk PRIMARY KEY (id),
	CONSTRAINT subscriber_fk FOREIGN KEY (theme_id) REFERENCES theme(id) ON DELETE CASCADE
);