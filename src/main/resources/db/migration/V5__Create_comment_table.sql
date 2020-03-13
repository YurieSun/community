create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	type int not null,
	commentator int not null,
	content varchar(1024),
	like_count int default 0,
	gmt_create bigint,
	gmt_modified bigint,
	constraint comment_pk
		primary key (id)
);