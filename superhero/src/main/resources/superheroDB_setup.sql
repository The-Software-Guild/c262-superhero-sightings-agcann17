DROP DATABASE IF EXISTS superheroDB;
CREATE DATABASE superheroDB;

USE superheroDB;

CREATE TABLE location(
	id INT PRIMARY KEY auto_increment,
    address VARCHAR(200),
    description VARCHAR(200),
    latitude DECIMAL(6,3) NOT NULL,
    longitude DECIMAL(6,3) NOT NULL
);

CREATE TABLE superpower(
	id INT PRIMARY KEY auto_increment,
    superpower VARCHAR(30) NOT NULL
);

CREATE TABLE org(
	id INT PRIMARY KEY auto_increment,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(200),
    location INT NOT NULL,
    constraint foreign key (location) references location(id)
);

CREATE TABLE villainHero(
	id INT PRIMARY KEY auto_increment,
    label VARCHAR(30) NOT NULL
);

INSERT INTO villainHero(label) VALUES ("superhero");
INSERT INTO villainHero(label) VALUES ("supervillain");

CREATE TABLE person(
	id INT PRIMARY KEY auto_increment,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(200),
    superpower INT NOT NULL,
    villainHero INT NOT NULL,
    constraint foreign key (superpower) references superpower(id),
    constraint foreign key (villainHero) references villainHero(id)
);

CREATE TABLE sighting(
	person INT NOT NULL,
    location INT NOT NULL,
    date DATETIME NOT NULL,
    constraint pk_sighting primary key (person, location)
);

CREATE TABLE members(
    person INT NOT NULL,
    org INT NOT NULL,
    constraint pk_memeber primary key (person, org),
    constraint foreign key (person) references person(id),
    constraint foreign key (org) references org(id)
);