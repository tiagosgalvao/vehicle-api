CREATE EXTENSION IF NOT EXISTS "citext";

CREATE TYPE STATUS AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TYPE COLOUR AS ENUM ('BLACK', 'BLUE', 'GREEN', 'YELLOW', 'RED', 'WHITE');
CREATE TYPE FUEL AS ENUM ('DIESEL', 'ETHANOL', 'GASOLINE');

CREATE TABLE MANUFACTURER
(
	ID          SERIAL    NOT NULL UNIQUE,
	NAME        CITEXT    NOT NULL,
	CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW(),
	UPDATE_DATE TIMESTAMP NOT NULL DEFAULT NOW(),
	STATUS      STATUS    NOT NULL DEFAULT 'ACTIVE'
);
CREATE UNIQUE INDEX MANUFACTURER_UNIQUE_KEY ON MANUFACTURER (NAME);

CREATE TABLE MODEL
(
	ID              SERIAL    NOT NULL UNIQUE,
	MANUFACTURER_ID INT       NOT NULL,
	NAME            CITEXT    NULL,
	CREATE_DATE     TIMESTAMP NOT NULL DEFAULT NOW(),
	UPDATE_DATE     TIMESTAMP NOT NULL DEFAULT NOW(),
	STATUS          STATUS    NOT NULL DEFAULT 'ACTIVE',
	CONSTRAINT FK_MODEL_X_MANUFACTURER FOREIGN KEY (MANUFACTURER_ID) REFERENCES MANUFACTURER (ID)
);
CREATE UNIQUE INDEX MODEL_UNIQUE_KEY ON MODEL (NAME);

CREATE TABLE VEHICLE
(
	ID                 SERIAL         NOT NULL UNIQUE,
	MODEL_ID           INT            NOT NULL,
	ABS                BOOLEAN        NOT NULL DEFAULT FALSE,
	ARMORED            BOOLEAN        NOT NULL DEFAULT FALSE,
	AIRBAG             BOOLEAN        NOT NULL DEFAULT FALSE,
	ALARM              BOOLEAN        NOT NULL DEFAULT FALSE,
	AIR_CONDITIONING   BOOLEAN        NOT NULL DEFAULT FALSE,
	AUTOMATIC          BOOLEAN        NOT NULL DEFAULT FALSE,
	BLUETOOTH          BOOLEAN        NOT NULL DEFAULT FALSE,
	CD_PLAYER          BOOLEAN        NOT NULL DEFAULT FALSE,
	COLOR              COLOUR         NOT NULL DEFAULT 'BLACK',
	ELETRIC_STEERING   BOOLEAN        NOT NULL DEFAULT FALSE,
	FUEL               FUEL           NOT NULL DEFAULT 'GASOLINE',
	HYDRAULIC_STEERING BOOLEAN        NOT NULL DEFAULT FALSE,
	GPS                BOOLEAN        NOT NULL DEFAULT FALSE,
	KM                 NUMERIC(13, 2) NOT NULL DEFAULT 0,
	LEATHER_SEAT       BOOLEAN        NOT NULL DEFAULT FALSE,
	MP3PLAYER          BOOLEAN        NOT NULL DEFAULT FALSE,
	PLAQUE             VARCHAR(10)    NOT NULL,
	QT_DOORS           INT            NOT NULL DEFAULT 2,
	RADIO              BOOLEAN        NOT NULL DEFAULT FALSE,
	YEAR               INT            NOT NULL,
	CREATE_DATE        TIMESTAMP      NOT NULL DEFAULT NOW(),
	UPDATE_DATE        TIMESTAMP      NOT NULL DEFAULT NOW(),
	STATUS             STATUS         NOT NULL DEFAULT 'ACTIVE',
	CONSTRAINT FK_VEHICLE_X_MODEL FOREIGN KEY (MODEL_ID) REFERENCES MODEL (ID)
);

INSERT INTO manufacturer ("name") VALUES('Manufacturer 1');
INSERT INTO model ("name", manufacturer_id) VALUES('Model 1', (select id from manufacturer where name='Manufacturer 1'));